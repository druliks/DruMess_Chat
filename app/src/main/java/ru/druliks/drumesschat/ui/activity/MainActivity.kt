package ru.druliks.drumesschat.ui.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ru.druliks.drumesschat.R
import ru.druliks.drumesschat.cache.AccountCacheImpl
import ru.druliks.drumesschat.cache.SharedPrefsManager
import ru.druliks.drumesschat.data.account.AccountRepositoryImpl
import ru.druliks.drumesschat.domain.account.AccountRepository
import ru.druliks.drumesschat.domain.account.Register
import ru.druliks.drumesschat.remote.account.AccountRemoteImpl
import ru.druliks.drumesschat.remote.core.NetworkHandler
import ru.druliks.drumesschat.remote.core.Request
import ru.druliks.drumesschat.remote.service.ServiceFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPrefs = this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE)

        val accountCache = AccountCacheImpl(SharedPrefsManager(sharedPrefs))
        val accountRemote = AccountRemoteImpl(Request(NetworkHandler(this)), ServiceFactory.makeService(true))

        val accountRepository: AccountRepository = AccountRepositoryImpl(accountRemote, accountCache)

        accountCache.saveToken("12345")

        val register = Register(accountRepository)
        register(Register.Params("abcd@m.com", "abcd", "123")) {
            it.either({
                Toast.makeText(this, it.javaClass.simpleName, Toast.LENGTH_SHORT).show()
            }, {
                Toast.makeText(this, "Аккаунт создан", Toast.LENGTH_SHORT).show()
            })
        }
    }
}
