package ru.druliks.drumesschat.domain.account

import com.google.gson.annotations.SerializedName

//Модельный класс, содержащий данные аккуанта
data class AccountEntity (
    @SerializedName("user_id")
    var id: Long,
    var name: String,
    var email: String,
    @SerializedName("token")
    var token: String,
    var status: String,
    @SerializedName("user_date")
    var userDate: Long,
    var image: String,
    var password: String
)