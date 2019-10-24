package ru.druliks.drumesschat.presentation.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.druliks.drumesschat.presentation.viewmodel.AccountViewModel
import ru.druliks.drumesschat.presentation.viewmodel.ViewModelFactory

//Класс-модуль для биндинга ViewModel-классов и их Фабрики.
@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel::class)
    abstract fun bindAccountViewModel(accountViewModel: AccountViewModel): ViewModel
}