package ru.druliks.drumesschat.domain.friends

import com.google.gson.annotations.SerializedName

//Модельный класс содержит данные друга
class FriendEntity (
    @SerializedName("user_id")
    val id:Long,
    val name: String,
    val email: String,
    @SerializedName("friends_id")
    val friendsId:Long,
    val status:String,
    val image:String
)