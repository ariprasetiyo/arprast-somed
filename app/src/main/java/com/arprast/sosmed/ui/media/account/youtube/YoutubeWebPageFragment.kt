package com.arprast.sosmed.ui.media.account.youtube

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.arprastandroid.R

class YoutubeWebPageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_media_youtube, container, false)
        val webView = rootView.findViewById(R.id.webview) as WebView
        val settingsWebView = webView.settings
        webView.loadUrl("https://youtube.com")
        settingsWebView.javaScriptEnabled = true
        settingsWebView.domStorageEnabled = true
        settingsWebView.loadsImagesAutomatically = true
        settingsWebView.setCacheMode(WebSettings.LOAD_DEFAULT)
        webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        webView.setWebChromeClient(WebChromeClient())
        webView.webViewClient = WebViewClient()

        return rootView
    }
}