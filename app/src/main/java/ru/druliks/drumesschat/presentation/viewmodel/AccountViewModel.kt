package ru.druliks.drumesschat.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import ru.druliks.drumesschat.domain.account.*
import ru.druliks.drumesschat.domain.type.None
import javax.inject.Inject

//Класс ViewModel аккаунта для выполнения регистрации и хранения ответа от сервера
class AccountViewModel @Inject constructor(
    val registerUseCase: Register,
    val loginUseCase: Login,
    val getAccountUseCase: GetAccount,
    val logoutUseCase: Logout,
    val editAccountUseCase: EditAccount
) : BaseViewModel() {

    var registerData: MutableLiveData<None> = MutableLiveData()
    var accountData: MutableLiveData<AccountEntity> = MutableLiveData()
    var editAccountData: MutableLiveData<AccountEntity> = MutableLiveData()
    var logoutData: MutableLiveData<None> = MutableLiveData()

    fun register(email: String, name: String, password: String) {
        registerUseCase(Register.Params(email, name, password)) { it.either(::handleFailure, ::handleRegister) }
    }

    fun login(email: String, password: String) {
        loginUseCase(Login.Params(email, password)) {
            it.either(::handleFailure, ::handleAccount)
        }
    }

    fun getAccount() {
        getAccountUseCase(None()) { it.either(::handleFailure, ::handleAccount) }
    }

    fun logout() {
        logoutUseCase(None()) { it.either(::handleFailure, ::handleLogout) }
    }

    fun editAccount(entity: AccountEntity) {
        editAccountUseCase(entity) { it.either(::handleFailure, ::handleEditAccount) }
    }


    private fun handleRegister(none: None) {
        this.registerData.value = none
    }

    private fun handleAccount(account: AccountEntity) {
        this.accountData.value = account
    }

    private fun handleEditAccount(account: AccountEntity) {
        this.editAccountData.value = account
    }

    private fun handleLogout(none: None) {
        this.logoutData.value = none
    }

    override fun onCleared() {
        super.onCleared()
        registerUseCase.unsubscribe()
        loginUseCase.unsubscribe()
        getAccountUseCase.unsubscribe()
        logoutUseCase.unsubscribe()
    }
}