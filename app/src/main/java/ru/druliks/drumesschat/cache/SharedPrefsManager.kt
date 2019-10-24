package ru.druliks.drumesschat.cache

import android.content.SharedPreferences
import ru.druliks.drumesschat.domain.type.Either
import ru.druliks.drumesschat.domain.type.Exception.Failure
import ru.druliks.drumesschat.domain.type.None
import javax.inject.Inject

//Класс для работы с SharedPreferences
class SharedPrefsManager @Inject constructor(private val prefs: SharedPreferences) {
    companion object {
        const val ACCOUNT_TOKEN = "account_token"
    }

    fun saveToken(token: String): Either<Failure, None> {
        prefs.edit().apply {
            putString(ACCOUNT_TOKEN, token)
        }.apply()

        return Either.Right(None())
    }

    fun getToken(): Either<Failure, String> {
        return Either.Right(prefs.getString(ACCOUNT_TOKEN, ""))
    }
}