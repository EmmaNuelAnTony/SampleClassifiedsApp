package com.emmanuel.sampleclassifiedsapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.text.SimpleDateFormat
import java.util.*


object Utility {


    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun showImage(imageUrl: String, imageView: ImageView) {
        try {
            Picasso.get().load(imageUrl).resize(300, 300).centerInside()
                .into(imageView)
        } catch (e: Exception) {
        }
    }

    fun formatDate(date: Date): String {
        var format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

        format = SimpleDateFormat("dd MMM yyyy", Locale.US)
        return format.format(date)
    }


    fun convertStringToDate(stringDate: String): Date {

        val format = SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSSSSS");


        return format.parse(stringDate)
    }
}
