package com.example.wooyj.util

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import coil.result
import coil.transform.CircleCropTransformation
import com.example.wooyj.R
import com.google.android.material.imageview.ShapeableImageView
import timber.log.Timber

/**
 *
 * 2022/09/02.
 *
 * @author wooyj
 *
 */

@BindingAdapter("imageLoadByUrl")
fun ImageView.setImageLoadByUrl(urlString: String?) {
    if (urlString != null) {
        load(urlString) {
            transformations(CircleCropTransformation())
            listener(
                onError = { _, errorResult ->
                    Timber.tag("imageLoadByUrl").e("image를 로드하는데 실패했습니다.")
                    Timber.tag("imageLoadByUrl").e("${errorResult.throwable.localizedMessage}")
                    load(ContextCompat.getDrawable(context, R.drawable.profile_image))
                }
            )
        }
    }
}
