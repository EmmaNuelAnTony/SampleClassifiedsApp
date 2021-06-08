package com.emmanuel.sampleclassifiedsapp

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.util.LruCache
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.emmanuel.sampleclassifiedsapp.network.ApiClient
import com.emmanuel.sampleclassifiedsapp.utils.Constants
import com.emmanuel.sampleclassifiedsapp.utils.ProgressHUD
import com.emmanuel.sampleclassifiedsapp.utils.Utility
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

abstract class BaseActivity : AppCompatActivity() {
    internal var progressHUD: ProgressHUD? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()



    }


    fun navigateToNextScreen(current: Context, next: Class<*>, isFinish: Boolean) {
        val intent = Intent(current, next)
        startActivity(intent)
        if (isFinish) {
            finish()
        }
        overridePendingTransition(0, 0)
    }

    fun navigateToNextScreen(current: Context, next: Class<*>, bundle: Bundle, isFinish: Boolean) {
        val intent = Intent(current, next)
        intent.putExtras(bundle)
        startActivity(intent)
        if (isFinish) {
            finish()
        }
        overridePendingTransition(0, 0)
    }


    fun finishActivity() {
        finish()
        overridePendingTransition(0, 0)
    }


    fun hitGetApiWithToken(type: String, showLoader: Boolean, url: String) {
        if (showLoader) {
            progressHUD = ProgressHUD.show(
                this@BaseActivity,
                resources.getString(R.string.please_wait),
                false,
                false
            )
        }

        ApiClient.api!!.hitGetApiWithoutToken(url)
            .enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    getData(response, type, showLoader)
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    if (showLoader) {
                        progressHUD!!.dismiss()
                    }

                    Toast.makeText(
                        this@BaseActivity,
                        resources.getString(R.string.something_went_wrong),
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            })

    }

    //get api response
    fun getData(response: Response<JsonObject>, type: String, showLoader: Boolean) {
        if (response.isSuccessful && response.code() == 200) {


            getResponse(type, response.body() as JsonObject)


        } else if (response.code() == 400) {
            try {
                val jObjError = JSONObject(response.errorBody()!!.string())
                Utility.showToast(this@BaseActivity, jObjError.getString(Constants.MESSAGE))
            } catch (e: Exception) {
            }
        } else if (response.code() == 401) {
            try {
                val jObjError = JSONObject(response.errorBody()!!.string())
                Utility.showToast(this@BaseActivity, jObjError.getString(Constants.MESSAGE))
            } catch (e: Exception) {
            }
        } else {
            Toast.makeText(
                this@BaseActivity,
                resources.getString(R.string.something_went_wrong),
                Toast.LENGTH_SHORT
            ).show()
        }
        if (showLoader) {
            try {
                if (progressHUD != null && progressHUD!!.isShowing) {
                    progressHUD!!.dismiss()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

    }

    abstract fun getResponse(apiType: String, respopnse: JsonObject)


    companion object {

        @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        fun changeLanguage(
            language: String?,
            context: Context
        ) {

            val locale = Locale(language!!)
            Locale.setDefault(locale)
            val config = Configuration()
            config.locale = locale
            config.setLayoutDirection(locale)
            context.resources
                .updateConfiguration(config, null)


        }
    }
}
