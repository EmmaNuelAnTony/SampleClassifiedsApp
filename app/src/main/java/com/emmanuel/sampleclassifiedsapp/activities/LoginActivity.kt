package com.emmanuel.sampleclassifiedsapp.activities

import android.os.Bundle
import com.emmanuel.sampleclassifiedsapp.BaseActivity
import com.emmanuel.sampleclassifiedsapp.R
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            if (EdtLogin.text.isNullOrEmpty()) {
                EdtLogin.error = "Please enter your name"

            } else {
                navigateToNextScreen(this@LoginActivity, ListActivity::class.java, true)
            }
        }

    }

    override fun getResponse(apiType: String, respopnse: JsonObject) {
        TODO("Not yet implemented")
    }


}