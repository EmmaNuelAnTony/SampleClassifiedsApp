package com.emmanuel.sampleclassifiedsapp.utils

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.emmanuel.sampleclassifiedsapp.R
import kotlinx.android.synthetic.main.progress_hud.*


class ProgressHUD(context: Context, theme: Int) : Dialog(context, theme) {

    companion object {

        fun show(
            context: Context, message: CharSequence?, indeterminate: Boolean, cancelable: Boolean
        ): ProgressHUD {
            val dialog = ProgressHUD(context, R.style.ProgressHUD)
            dialog.setTitle("")
            dialog.setContentView(R.layout.progress_hud)
            if (message == null || message.isEmpty()) {
                dialog.message.visibility = View.GONE
            } else {
                val txt = dialog.findViewById(R.id.message) as TextView
                txt.text = message
            }
            dialog.setCancelable(cancelable)
            dialog.setCanceledOnTouchOutside(cancelable)
            dialog.window!!.attributes.gravity = Gravity.CENTER
            val lp = dialog.window!!.attributes
            lp.dimAmount = 0.2f
            dialog.window!!.attributes = lp
            dialog.show()
            return dialog
        }

    }
}
