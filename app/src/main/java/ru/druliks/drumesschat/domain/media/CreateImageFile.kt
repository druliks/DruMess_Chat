package ru.druliks.drumesschat.domain.media

import android.net.Uri
import ru.druliks.drumesschat.domain.interactor.UseCase
import ru.druliks.drumesschat.domain.type.None
import javax.inject.Inject

//UseCase для создания файла изображения
class CreateImageFile @Inject constructor(
    private val mediaRepository: MediaRepository
) :UseCase<Uri, None>() {
    override suspend fun run(params: None)=mediaRepository.createImageFile()

}