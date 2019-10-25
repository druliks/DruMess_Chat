package ru.druliks.drumesschat.domain.media

import android.graphics.Bitmap
import android.net.Uri
import ru.druliks.drumesschat.domain.interactor.UseCase
import javax.inject.Inject

class GetPickedImage @Inject constructor(
    private val mediaRepository: MediaRepository
) : UseCase<Bitmap, Uri?>() {

    override suspend fun run(params: Uri?) = mediaRepository.getPickedImage(params)
}