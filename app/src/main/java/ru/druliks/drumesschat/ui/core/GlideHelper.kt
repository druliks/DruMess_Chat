package ru.druliks.drumesschat.ui.core

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import ru.druliks.drumesschat.remote.service.ServiceFactory

object GlideHelper {
    fun loadImage(context: Context, path: String?, iv: ImageView, placeholder: Drawable = iv.drawable) {
        val imgPath = ServiceFactory.SERVER_URL + path?.replace("..", "")

        Glide.with(context)
            .load(imgPath)
            .placeholder(placeholder)
            .error(placeholder)
            .into(iv)
    }

    fun loadImage(context: Context, path: String?, iv: ImageView, placeholder: Int) {
        loadImage(context, path, iv, context.resources.getDrawable(placeholder))
    }

    @JvmStatic
    @BindingAdapter("profileImage")
    fun ImageView.loadImage(image: String?) {
        loadImage(this.context, image, this)
    }
}