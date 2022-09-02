package com.example.wooyj.data

data class MyRanking(
    val location : String,
    val my_profile_image : String,
    val my_rank : Int,
    val segment : String,
    val percent : String,
    val rank_list : List<RankingListItem>
)

data class RankingListItem(
    val rank : Int,
    val profile_image : String,
    val nick_name : String,
    val age : Int,
    val is_me : Boolean,
    var diff_rank : Int = 0,
)