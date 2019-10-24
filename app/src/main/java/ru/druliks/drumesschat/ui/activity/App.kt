package ru.druliks.drumesschat.ui.activity

import android.app.Application
import dagger.Component
import ru.druliks.drumesschat.presentation.injection.AppModule
import ru.druliks.drumesschat.presentation.injection.CacheModule
import ru.druliks.drumesschat.presentation.injection.RemoteModule
import ru.druliks.drumesschat.presentation.injection.ViewModelModule
import ru.druliks.drumesschat.ui.fragment.RegisterFragment
import ru.druliks.drumesschat.ui.service.FirebaseService
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

    //fragments
    fun inject(fragment: RegisterFragment)

    //services
    fun inject(service: FirebaseService)

}