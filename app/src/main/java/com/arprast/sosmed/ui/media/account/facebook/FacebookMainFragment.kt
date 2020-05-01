package com.arprast.sosmed.ui.media.account.facebook

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.fragment.app.Fragment
import com.arprastandroid.R

class FacebookMainFragment(username: String, password: String) : Fragment() {

    private val username = username
    private val password = password

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_media_facebook, container, false)
        val webViewFacebook = root.findViewById(R.id.webviewFacebook) as WebView
        webViewFacebook.loadUrl("https://m.facebook.com/login/?next&ref=dbl&fl&refid=8")
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
                view.loadUrl(
                    "javascript:var yy =document.getElementById('m_login_email').value = '$username';" +
                            "var xx = document.getElementById('m_login_password').value='$password';"
                )
            }
        })

        return root
    }
}