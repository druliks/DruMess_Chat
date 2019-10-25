package ru.druliks.drumesschat.remote.account

import ru.druliks.drumesschat.data.account.AccountRemote
import ru.druliks.drumesschat.domain.account.AccountEntity
import ru.druliks.drumesschat.domain.type.Either
import ru.druliks.drumesschat.domain.type.Failure
import ru.druliks.drumesschat.domain.type.None
import ru.druliks.drumesschat.remote.core.Request
import ru.druliks.drumesschat.remote.service.ApiService
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap


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

    override fun login(email: String, password: String, token: String): Either<Failure, AccountEntity> {
        return request.make(service.login(createLoginMap(email, password, token))) { it.user }
    }


    override fun updateToken(userId: Long, token: String, oldToken: String): Either<Failure, None> {
        return request.make(service.updateToken(createUpdateTokenMap(userId, token, oldToken))) { None() }
    }

    override fun editUser(
        userId: Long,
        email: String,
        name: String,
        password: String,
        status: String,
        token: String,
        image: String
    ): Either<Failure, AccountEntity> {
        return request.make(service.editUser(createUserEditMap(userId, email, name,
            password, status, token, image))) { it.user }
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

    private fun createLoginMap(email: String, password: String, token: String): Map<String, String> {
        val map = HashMap<String, String>()
        map.put(ApiService.PARAM_EMAIL, email)
        map.put(ApiService.PARAM_PASSWORD, password)
        map.put(ApiService.PARAM_TOKEN, token)
        return map
    }

    private fun createUpdateTokenMap(userId: Long, token: String, oldToken: String): Map<String, String> {
        val map = HashMap<String, String>()
        map.put(ApiService.PARAM_USER_ID, userId.toString())
        map.put(ApiService.PARAM_TOKEN, token)
        map.put(ApiService.PARAM_OLD_TOKEN, oldToken)
        return map
    }

    private fun createUserEditMap(
        id: Long,
        email: String,
        name: String,
        password: String,
        status: String,
        token: String,
        image: String
    ): Map<String, String> {
        val map = HashMap<String, String>()
        map.put(ApiService.PARAM_USER_ID, id.toString())
        map.put(ApiService.PARAM_EMAIL, email)
        map.put(ApiService.PARAM_NAME, name)
        map.put(ApiService.PARAM_PASSWORD, password)
        map.put(ApiService.PARAM_STATUS, status)
        map.put(ApiService.PARAM_TOKEN, token)
        if (image.startsWith("../")) {
            map.put(ApiService.PARAM_IMAGE_UPLOADED, image)
        } else {
            map.put(ApiService.PARAM_IMAGE_NEW, image)
            map.put(ApiService.PARAM_IMAGE_NEW_NAME, "user_${id}_${Date().time}_photo")
        }
        return map
    }

}