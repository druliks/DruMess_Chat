package ru.druliks.drumesschat.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import ru.druliks.drumesschat.domain.account.Register
import ru.druliks.drumesschat.domain.type.None
import javax.inject.Inject

//Класс ViewModel аккаунта для выполнения регистрации и хранения ответа от сервера
class AccountViewModel @Inject constructor(
    val registerUseCase: Register
) : BaseViewModel() {
    var registerData: MutableLiveData<None> = MutableLiveData()

    fun register(email: String, name: String, password: String) {
        registerUseCase(Register.Params(email, name, password)) { it.either(::handleFailure, ::handleRegister) }
    }

    private fun handleRegister(none: None) {
        this.registerData.value = none
    }

    override fun onCleared() {
        super.onCleared()
        registerUseCase.unsubscribe()
    }
}