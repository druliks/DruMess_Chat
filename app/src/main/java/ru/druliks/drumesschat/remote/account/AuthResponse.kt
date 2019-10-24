package ru.druliks.drumesschat.remote.account

import ru.druliks.drumesschat.domain.account.AccountEntity
import ru.druliks.drumesschat.remote.core.BaseResponse

//POJO-класс для хранения ответа от сервера при авторизации
class AuthResponse(
    success: Int,
    message: String,
    val user: AccountEntity
) : BaseResponse(success, message)