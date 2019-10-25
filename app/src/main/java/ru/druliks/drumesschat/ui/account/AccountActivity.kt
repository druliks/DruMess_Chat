package ru.druliks.drumesschat.ui.account

import android.os.Bundle
import ru.druliks.drumesschat.ui.App
import ru.druliks.drumesschat.ui.core.BaseActivity
import ru.druliks.drumesschat.ui.core.BaseFragment

class AccountActivity : BaseActivity() {
    override var fragment: BaseFragment = AccountFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }
}