package com.arprast.sosmed.ui.media.account.facebook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.arprastandroid.R

class FacebookMainFragment(username : String, password : String) : Fragment() {

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
        webViewFacebook.setWebChromeClient(WebChromeClient())

        return root
    }
}