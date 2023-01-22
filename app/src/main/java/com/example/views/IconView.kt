package com.example.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.movieapi.R
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.load.DataSource
import com.example.movieapi.databinding.IconViewBinding

class IconView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0):
    FrameLayout(context, attrs, defStyleAttr) {

    private var binding : IconViewBinding?=null

    private val listener: RequestListener<Drawable> = object: RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            binding?.progressBar?.visibility= View.GONE
            binding?.image?.setImageDrawable(
                ResourcesCompat.getDrawable(context.resources, R.drawable.ic_error_load, null))
            return true
        }


        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            binding?.progressBar?.visibility= View.GONE
            return false
        }
    }


        init {
            binding = IconViewBinding.inflate(LayoutInflater.from(context),this)
        }

        fun loadImage(url: String?) {
            binding?.progressBar?.visibility= View.VISIBLE
            binding?.image?.let {
                Glide.with(this)
                    .load("https://image.tmdb.org/t/p/original/$url")
                    .listener(listener)
                    .into(it)
            }

        }

    }