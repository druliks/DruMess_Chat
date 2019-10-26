package ru.druliks.drumesschat.presentation

import ru.druliks.drumesschat.cache.SharedPrefsManager
import ru.druliks.drumesschat.domain.account.CheckAuth
import ru.druliks.drumesschat.domain.type.None
import javax.inject.Inject
import javax.inject.Singleton

//Вспомогательный класс для проверки авторизации пользователя
@Singleton
class Authenticator
@Inject constructor(
    val checkAuth: CheckAuth
) {
    fun userLoggedIn(body: (Boolean) -> Unit) {
        checkAuth(None()) {
            it.either({ body(false) }, { body(it) })
        }
    }
}