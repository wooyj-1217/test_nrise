package com.example.wooyj.network

import com.example.wooyj.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    private val CONNECT_TIMEOUT = 20L
    private val WRITE_TIMEOUT = 20L
    private val READ_TIMEOUT = 20L

    @Provides
    fun provideBodyLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor { message ->
            if (message.length < 16 * 1024 * 1024) {
                Timber.tag("NetworkModule : ").d(message)
            }
            //log가 너무 길면 EOF 오류 발생하므로 제한해주어야 한다. 디버깅 옵션에서 로거 버퍼 크기를 16M로 설정하자.
        }.setLevel(HttpLoggingInterceptor.Level.BODY)


    @Provides
    fun provideOkHttpClientForApi(
        bodyLog: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(
            CONNECT_TIMEOUT,
            TimeUnit.SECONDS
        )                                           // 연결 타임아웃 시간 설정
        .writeTimeout(
            WRITE_TIMEOUT,
            TimeUnit.SECONDS
        )                                               // 쓰기 타임아웃 시간 설정
        .readTimeout(
            READ_TIMEOUT,
            TimeUnit.SECONDS
        )                                                 // 읽기 타임아웃 시간 설정
        .addNetworkInterceptor(Interceptor { chain ->                                                // Add Header
            val requestBuilder = chain.request().newBuilder()
                .addHeader("Accept", "application/json")
                .header("Content-Type", "application/json")
            chain.proceed(requestBuilder.build())
        })
        .addInterceptor(bodyLog)                                                            // 로그 찍는 용도
        .build()

    @Provides
    fun provideRetrofitServiceApi(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideNetworkService(retrofit: Retrofit): NetworkService =
        retrofit.create(NetworkService::class.java)

    @Provides
    fun provideNetworkRepository(service: NetworkService): NetworkRepository =
        NetworkRepository(service)


}