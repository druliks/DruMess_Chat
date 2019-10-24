package ru.druliks.drumesschat.presentation.injection

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass


//Класс-аннотация для обозначения ViewModel-классов при их биндинге.
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)