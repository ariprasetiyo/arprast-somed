package com.arprast.sosmed.ui.media.account.instagram

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.arprastandroid.R

class InstagramMainFragment(username: String, password: String) : Fragment() {

    private val username = username
    private val password = password

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_media_facebook, container, false)
        val webViewFacebook = root.findViewById(R.id.webviewFacebook) as WebView
        webViewFacebook.loadUrl("https://www.instagram.com/accounts/login/")
        val webViewSetting = webViewFacebook.settings
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
        })
        webViewFacebook.setWebViewClient(object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
//                view.loadUrl(
//                    "javascript:var yy =document.getElementsByName('username')[0].value = '$username';" +
//                            "var xx = document.getElementsByName('password')[0].value='$password';"
//                )
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
        })

        return root
    }
}