package com.arprast.sosmed.ui.media.account.twitter

import android.app.AlertDialog
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.arprastandroid.R


class TwitterMainFragment(username: String, password: String) : Fragment() {

    private val username = username
    private val password = password
    private var showPassword = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_media_facebook, container, false)
        val webViewFacebook = root.findViewById(R.id.webviewFacebook) as WebView
        val webViewSetting = webViewFacebook.settings
        webViewFacebook.loadUrl("https://mobile.twitter.com/login/error?username_or_email=$username&redirect_after_login=%2F")
        webViewSetting.loadsImagesAutomatically = true
        webViewSetting.javaScriptEnabled = true
        webViewSetting.domStorageEnabled = true
        webViewSetting.cacheMode = WebSettings.LOAD_DEFAULT
        webViewFacebook.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        webViewFacebook.setWebChromeClient(object : WebChromeClient() {
            override fun onConsoleMessage(consoleMessage: ConsoleMessage): Boolean {
                Log.d(
                    "debug-arprast", consoleMessage.message() + " -- From line "
                            + consoleMessage.lineNumber() + " of "
                            + consoleMessage.sourceId()
                )
                return super.onConsoleMessage(consoleMessage)
            }

            override fun onProgressChanged(view: WebView, newProgress: Int) {
                if (newProgress == 100 && showPassword == true) {
                    showPassword = false
                    val showText = TextView(context)
                    showText.text = "$password"
                    showText.setTextIsSelectable(true)
                    showText.gravity = Gravity.CENTER
                    showText.setTextColor(Color.BLACK)
                    showText.textSize = 20.0F
                    val builder = AlertDialog.Builder(context)
                    builder.setView(showText)
                        .setTitle("Copy password below !")
                        .setCancelable(true)
                        .show()
                }
            }
        })

        return root
    }
}