package ru.druliks.drumesschat.domain.media

import android.graphics.Bitmap
import ru.druliks.drumesschat.domain.interactor.UseCase
import javax.inject.Inject

//UseCase для кодирования изображения в строку
class EncodeImageBitmap @Inject constructor(
    private val mediaRepository: MediaRepository
) : UseCase<String, Bitmap?>() {

    override suspend fun run(params: Bitmap?) = mediaRepository.encodeImageBitmap(params)
}