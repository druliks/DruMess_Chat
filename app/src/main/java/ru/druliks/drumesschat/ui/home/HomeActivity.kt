package ru.druliks.drumesschat.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.navigation.*
import ru.druliks.drumesschat.R
import ru.druliks.drumesschat.domain.account.AccountEntity
import ru.druliks.drumesschat.domain.friends.FriendEntity
import ru.druliks.drumesschat.domain.type.Failure
import ru.druliks.drumesschat.domain.type.None
import ru.druliks.drumesschat.presentation.viewmodel.AccountViewModel
import ru.druliks.drumesschat.presentation.viewmodel.FriendsViewModel
import ru.druliks.drumesschat.ui.App
import ru.druliks.drumesschat.ui.core.BaseActivity
import ru.druliks.drumesschat.ui.core.BaseFragment
import ru.druliks.drumesschat.ui.core.GlideHelper
import ru.druliks.drumesschat.ui.core.ext.onFailure
import ru.druliks.drumesschat.ui.core.ext.onSuccess
import ru.druliks.drumesschat.ui.firebase.NotificationHelper
import ru.druliks.drumesschat.ui.friends.FriendRequestsFragment
import ru.druliks.drumesschat.ui.friends.FriendsFragment
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    override var fragment: BaseFragment = ChatsFragment()

    override val contentId = R.layout.activity_navigation

    private lateinit var accountViewModel: AccountViewModel

    @Inject
    lateinit var friendsViewModel: FriendsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        App.appComponent.inject(this)

        accountViewModel = viewModel {
            onSuccess(accountData, ::handleAccount)
            onSuccess(logoutData, ::handleLogout)
            onFailure(failureData, ::handleFailure)
        }

        friendsViewModel = viewModel {
            onSuccess(addFriendData, ::handleAddFriend)
            onSuccess(friendRequestsData, ::handleFriendRequests)
            onFailure(failureData, ::handleFailure)
        }

        supportActionBar?.setHomeAsUpIndicator(R.drawable.menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager.beginTransaction().replace(R.id.requestContainer, FriendRequestsFragment()).commit()

        val type: String? = intent.getStringExtra("type")
        when (type) {
            NotificationHelper.TYPE_ADD_FRIEND -> {
                openDrawer()
                friendsViewModel.getFriendRequests()
                requestContainer.visibility = View.VISIBLE
            }
        }

        btnLogout.setOnClickListener {
            accountViewModel.logout()
        }

        btnChats.setOnClickListener {
            replaceFragment(ChatsFragment())
            closeDrawer()
        }

        btnAddFriend.setOnClickListener {
            if (containerAddFriend.visibility == View.VISIBLE) {
                containerAddFriend.visibility = View.GONE
            } else {
                containerAddFriend.visibility = View.VISIBLE
            }
        }

        btnAdd.setOnClickListener {
            hideSoftKeyboard()
            showProgress()
            friendsViewModel.addFriend(etEmail.text.toString())
        }

        btnFriends.setOnClickListener {
            replaceFragment(FriendsFragment())
            closeDrawer()
        }

        btnRequests.setOnClickListener {
            friendsViewModel.getFriendRequests()

            if (requestContainer.visibility == View.VISIBLE) {
                requestContainer.visibility = View.GONE
            } else {
                requestContainer.visibility = View.VISIBLE
            }
        }

        profileContainer.setOnClickListener {
            navigator.showAccount(this)
            Handler(Looper.getMainLooper()).postDelayed({
                closeDrawer()
            }, 200)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                if (drawerLayout.isDrawerOpen(navigationView)) {
                    drawerLayout.closeDrawer(navigationView)
                } else {
                    drawerLayout.openDrawer(navigationView)
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        accountViewModel.getAccount()
    }

    private fun openDrawer() {
        hideSoftKeyboard()
        drawerLayout.openDrawer(navigationView)
    }

    private fun closeDrawer(animate: Boolean = true) {
        hideSoftKeyboard()
        drawerLayout.closeDrawer(navigationView, animate)
    }

    private fun handleAccount(accountEntity: AccountEntity?) {
        accountEntity?.let {
            GlideHelper.loadImage(this, it.image, ivUserImage)
            tvUserName.text = it.name
            tvUserEmail.text = it.email
            tvUserStatus.text = it.status

            tvUserStatus.visibility = if (it.status.isNotEmpty()) View.VISIBLE else View.GONE
        }
    }

    private fun handleLogout(none: None?) {
        navigator.showLogin(this)
        finish()
    }

    private fun handleAddFriend(none: None?) {
        etEmail.text.clear()
        containerAddFriend.visibility = View.GONE

        hideProgress()
        showMessage("Запрос отправлен.")
    }

    private fun handleFriendRequests(requests: List<FriendEntity>?) {
        if (requests?.isEmpty() == true) {
            requestContainer.visibility = View.GONE
            if (drawerLayout.isDrawerOpen(navigationView)) {
                showMessage("Нет входящих приглашений.")
            }
        }
    }

    override fun handleFailure(failure: Failure?) {
        hideProgress()
        when (failure) {
            Failure.ContactNotFoundError -> navigator.showEmailNotFoundDialog(this, etEmail.text.toString())
            else -> super.handleFailure(failure)
        }
    }


    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(navigationView)) {
            hideSoftKeyboard()
            drawerLayout.closeDrawer(navigationView)
        } else {
            super.onBackPressed()
        }
    }
}