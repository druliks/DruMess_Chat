package ru.druliks.drumesschat.domain.type.Exception

//Класс-маркер содержит в себе типы возможных ошибок
/**
 * Base Class for handling errors/failures/exceptions.
 */
sealed class Failure {
    object NetworkConnectionError: Failure()
    object ServerError: Failure()
}