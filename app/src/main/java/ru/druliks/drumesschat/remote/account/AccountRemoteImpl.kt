package ru.druliks.drumesschat.remote.account

import ru.druliks.drumesschat.data.account.AccountRemote
import ru.druliks.drumesschat.domain.type.Either
import ru.druliks.drumesschat.domain.type.Exception.Failure
import ru.druliks.drumesschat.domain.type.None
import ru.druliks.drumesschat.remote.core.Request
import ru.druliks.drumesschat.remote.service.ApiService
import javax.inject.Inject


//Класс содержащий функции для взаимодействия с аккаунтом в сети
class AccountRemoteImpl @Inject constructor(
    private val request: Request,
    private val service: ApiService
):AccountRemote {
    override fun register(
        email: String,
        name: String,
        password: String,
        token: String,
        userDate: Long
    ): Either<Failure, None> {
        return request.make(service.register(createRegisterMap(email, name, password, token, userDate))) { None() }
    }

    private fun createRegisterMap(
        email: String,
        name: String,
        password: String,
        token: String,
        userDate: Long
    ): Map<String, String> {
        val map = HashMap<String, String>()
        map.put(ApiService.PARAM_EMAIL, email)
        map.put(ApiService.PARAM_NAME, name)
        map.put(ApiService.PARAM_PASSWORD, password)
        map.put(ApiService.PARAM_TOKEN, token)
        map.put(ApiService.PARAM_USER_DATE, userDate.toString())
        return map
    }

}