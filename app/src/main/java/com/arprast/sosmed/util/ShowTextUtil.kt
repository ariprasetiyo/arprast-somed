package com.arprast.sosmed.util

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.widget.RelativeLayout.LayoutParams
import android.widget.TextView


class ShowTextUtil {
    companion object {
        fun showTextUtil(title : String, message : String, context : Context?){
            val showText = TextView(context)
            showText.text = "$message"
            showText.setTextIsSelectable(true)
            showText.gravity = Gravity.LEFT
            showText.setTextColor(Color.parseColor("#FFFFFF"))
            showText.setBackgroundColor(Color.parseColor("#070707"))
            showText.textSize = 16.0F
            showText.setPadding(50, 20, 10, 30)
            val lp = LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            )
            showText.setLayoutParams(lp);
            val builder = AlertDialog.Builder(context)
            builder.setView(showText)
                .setTitle(title)
                .setCancelable(true)
                .show()
        }
    }
}