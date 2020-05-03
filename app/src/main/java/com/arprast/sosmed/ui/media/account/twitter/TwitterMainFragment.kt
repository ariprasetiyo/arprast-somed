package com.arprast.sosmed.ui.media.account.twitter

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.arprast.sosmed.model.UserInterfacing
import com.arprast.sosmed.repository.AccountRepository
import com.arprast.sosmed.type.UserInterfaceType
import com.arprast.sosmed.util.PreferanceVariable
import com.arprast.sosmed.util.PreferanceVariable.Companion.DEBUG_NAME
import com.arprast.sosmed.util.ShowTextUtil
import com.arprastandroid.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class TwitterMainFragment(username: String, password: String) : Fragment() {

    private val username = username
    private val password = password
    private var showPassword = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_media_youtube, container, false)

        val fab: FloatingActionButton = root.findViewById(R.id.float_show_password)
        fab.setOnClickListener {
            ShowTextUtil.showTextUtil(
                "Credential account !",
                "Username: $username\nPassword: $password",
                context
            )
        }

        val webViewFacebook = root.findViewById(R.id.webview) as WebView
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
                    DEBUG_NAME, "${consoleMessage.message()} -- From line "
                            + "${consoleMessage.lineNumber()}  of ${consoleMessage.sourceId()}"
                )
                return super.onConsoleMessage(consoleMessage)
            }

            override fun onProgressChanged(view: WebView, newProgress: Int) {
                if (newProgress == 100 && showPassword == true) {
                    showPassword = false
                    ShowTextUtil.showTextUtil("Copy password below !", password, context)
                }
            }
        })

        webViewFacebook.setWebViewClient(object : WebViewClient() {

            //webViewInstance.loadUrl("file:///android_asset/alert.html")
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun shouldInterceptRequest(
                webview: WebView,
                webrequest: WebResourceRequest
            ): WebResourceResponse? {
                val origin =
                    webrequest.requestHeaders.get(PreferanceVariable.REFERER).toString()
                Log.d(DEBUG_NAME, "${webrequest.requestHeaders}")
                if (origin.startsWith("https://mobile.twitter.com/home")) {
                    val userInterfacing = UserInterfacing()
                    userInterfacing.menuId = UserInterfaceType.SHOW_CREDENTIAL.stringValue
                    userInterfacing.isDisabled = false
                    AccountRepository().updateUserInterface(userInterfacing)
                }

//                return super.shouldInterceptRequest(webview, webrequest);
                return null
            }

            override fun onPageFinished(view: WebView, url: String) {
                if(url.endsWith("https://mobile.twitter.com/")
                    || url.endsWith("https://mobile.twitter.com")){
                    fab.visibility = View.GONE
                }
            }
        })

        return root
    }
}