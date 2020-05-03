package com.arprast.sosmed.ui.media.account.instagram

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.fragment.app.Fragment
import com.arprast.sosmed.util.ShowTextUtil
import com.arprastandroid.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class InstagramMainFragment(username: String, password: String) : Fragment() {

    private val username = username
    private val password = password

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_media_youtube, container, false)

        val fab: FloatingActionButton = root.findViewById(R.id.float_show_password)
        fab.setOnClickListener { view ->
            ShowTextUtil.showTextUtil(
                "Credential account !",
                "Username: $username\nPassword: $password",
                context
            )

        }

        val webViewFacebook = root.findViewById(R.id.webview) as WebView
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

                if (url.startsWith("https://www.instagram.com/accounts/login/")) {
//                    Log.d("ari-p", url)
                    view.loadUrl(
                        "javascript:var yy =document.getElementsByName('username')[0].value = '$username';" +
                                "var xx = document.getElementsByName('password')[0].value='$password';"
                    )
//                    ShowTextUtil.showTextUtil("Copy password below !", password, context)
                }
            }

            override fun onLoadResource(view: WebView, url: String) {
                Log.d("ari-p", url)
                if (url.endsWith("https://www.instagram.com/") ||
                    url.endsWith("https://www.instagram.com")
                ) {
                    fab.visibility = View.GONE
                }
            }
        })

        return root
    }
}