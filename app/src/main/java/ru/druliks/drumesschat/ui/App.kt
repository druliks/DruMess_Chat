package ru.druliks.drumesschat.ui

import android.app.Application
import dagger.Component
import ru.druliks.drumesschat.presentation.injection.AppModule
import ru.druliks.drumesschat.presentation.injection.CacheModule
import ru.druliks.drumesschat.presentation.injection.RemoteModule
import ru.druliks.drumesschat.presentation.injection.ViewModelModule
import ru.druliks.drumesschat.ui.core.navigation.RouteActivity

import ru.druliks.drumesschat.ui.firebase.FirebaseService
import ru.druliks.drumesschat.ui.home.ChatsFragment
import ru.druliks.drumesschat.ui.home.HomeActivity
import ru.druliks.drumesschat.ui.login.LoginFragment
import ru.druliks.drumesschat.ui.register.RegisterActivity
import ru.druliks.drumesschat.ui.register.RegisterFragment
import javax.inject.Singleton


//Класс прилоажения для инициализации компонентов приложения
class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this)).build()
    }
}

@Singleton
@Component(modules = [AppModule::class, CacheModule::class, RemoteModule::class, ViewModelModule::class])
interface AppComponent {

    //activities
    fun inject(activity: RegisterActivity)

    fun inject(activity: RouteActivity)
    fun inject(activity: HomeActivity)

    //fragments
    fun inject(fragment: RegisterFragment)
    fun inject(fragment: LoginFragment)
    fun inject(fragment: ChatsFragment)

    //services
    fun inject(service: FirebaseService)
}