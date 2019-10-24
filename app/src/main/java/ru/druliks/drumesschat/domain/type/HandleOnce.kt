package ru.druliks.drumesschat.domain.type

//Класс обертка для предотращения повторного получения данных
open class HandleOnce<out T>(private val content:T) {
    private var hasBeenHandled = false

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }
}