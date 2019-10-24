package ru.druliks.drumesschat.remote.core

//POJO класс для хранения ответа сервера
open class BaseResponse (
    val success:Int,
    val message:String
)