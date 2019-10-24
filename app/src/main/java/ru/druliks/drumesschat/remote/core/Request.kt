package ru.druliks.drumesschat.remote.core

import retrofit2.Call
import retrofit2.Response
import ru.druliks.drumesschat.domain.type.Either
import ru.druliks.drumesschat.domain.type.Exception.Failure
import javax.inject.Inject
import javax.inject.Singleton

//Класс выполнения сетевых запросов и проверки ответа от сервера
@Singleton
class Request @Inject constructor(private val networkHandler: NetworkHandler) {
    fun <T : BaseResponse, R> make(call: Call<T>, transform: (T) -> R): Either<Failure, R> {
        return when (networkHandler.isConnected) {
            true -> execute(call, transform)
            false, null -> Either.Left(Failure.NetworkConnectionError)
        }
    }

    private fun <T : BaseResponse, R> execute(call: Call<T>, transform: (T) -> R): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSucceed()) {
                true -> Either.Right(transform((response.body()!!)))
                false -> Either.Left(Failure.ServerError)
            }
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError)
        }
    }
}

fun <T : BaseResponse> Response<T>.isSucceed(): Boolean {
    return isSuccessful && body() != null && (body() as BaseResponse).success == 1
}