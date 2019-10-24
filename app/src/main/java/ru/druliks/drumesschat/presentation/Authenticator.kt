package ru.druliks.drumesschat.presentation

import ru.druliks.drumesschat.cache.SharedPrefsManager
import javax.inject.Inject
import javax.inject.Singleton

//Вспомогательный класс для проверки авторизации пользователя
@Singleton
class Authenticator
@Inject constructor(
    val sharedPrefsManager: SharedPrefsManager
){
    fun userLoggedIn() = sharedPrefsManager.containsAnyAccount()
}