package ru.druliks.drumesschat.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.druliks.drumesschat.domain.type.Failure
import ru.druliks.drumesschat.domain.type.HandleOnce


//Базовый класс поведения всех ViewModel
abstract class BaseViewModel : ViewModel() {

    var failureData: MutableLiveData<HandleOnce<Failure>> = MutableLiveData()

    protected fun handleFailure(failure: Failure) {
        this.failureData.value = HandleOnce(failure)
    }
}