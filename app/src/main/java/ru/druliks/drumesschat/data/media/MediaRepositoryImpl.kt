package ru.druliks.drumesschat.data.media

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import ru.druliks.drumesschat.domain.media.MediaRepository
import ru.druliks.drumesschat.domain.type.Either
import ru.druliks.drumesschat.domain.type.Failure
import java.io.File
import javax.inject.Singleton

//Реальзация интерфейса репозитория для работы с файлами
@Singleton
class MediaRepositoryImpl(val context: Context) : MediaRepository {

    override fun createImageFile(): Either<Failure, Uri> {
        val uri = MediaHelper.createImageFile(context)
        return if (uri == null) {
            Either.Left(Failure.FilePickError)
        } else {
            Either.Right(uri)
        }
    }

    override fun encodeImageBitmap(bitmap: Bitmap?): Either<Failure, String> {
        if (bitmap == null) return Either.Left(Failure.FilePickError)

        val imageString = MediaHelper.encodeToBase64(bitmap, Bitmap.CompressFormat.JPEG, 100)

        return if (imageString.isEmpty()) {
            Either.Left(Failure.FilePickError)
        } else {
            Either.Right(imageString)
        }
    }

    override fun getPickedImage(uri: Uri?): Either<Failure, Bitmap> {
        if (uri == null) return Either.Left(Failure.FilePickError)

        val filePath = MediaHelper.getPath(context, uri)
        val image = MediaHelper.saveBitmapToFile(File(filePath))

        return if (image == null) {
            Either.Left(Failure.FilePickError)
        } else {
            Either.Right(image)
        }
    }
}