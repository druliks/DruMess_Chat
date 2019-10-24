package ru.druliks.drumesschat.ui.core.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import ru.druliks.drumesschat.presentation.Authenticator
import ru.druliks.drumesschat.ui.home.HomeActivity
import ru.druliks.drumesschat.ui.login.LoginActivity
import ru.druliks.drumesschat.ui.register.RegisterActivity
import javax.inject.Inject
import javax.inject.Singleton


//Вспомогательный класс для запуска activity
@Singleton
class Navigator
@Inject constructor(private val authenticator: Authenticator) {

    fun showMain(context: Context) {
        when (authenticator.userLoggedIn()) {
            true -> showHome(context, false)
            false -> showLogin(context, false)
        }
    }

    fun showLogin(context: Context, newTask: Boolean = true) = context.startActivity<LoginActivity>(newTask = newTask)

    fun showHome(context: Context, newTask: Boolean = true) = context.startActivity<HomeActivity>(newTask = newTask)

    fun showSignUp(context: Context) = context.startActivity<RegisterActivity>()
}

private inline fun <reified T> Context.startActivity(newTask: Boolean = false, args: Bundle? = null) {
    this.startActivity(Intent(this, T::class.java).apply {
        if (newTask) {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        putExtra("args", args)
    })
}