package com.arprast.sosmed.ui.media.account.youtube

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.arprastandroid.R


class YoutubeMainFragment(username: String, password: String) : Fragment() {

    private lateinit var mapViewModel: MapViewModel
    private val username = username
    private val password = password
    private var onceAccess = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mapViewModel = ViewModelProviders.of(this).get(MapViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_media_youtube, container, false)
        val webViewInstance = root.findViewById(R.id.webview) as WebView
        val webViewSetting = webViewInstance.settings
        webViewInstance.loadUrl("https://accounts.google.com/signin/v2/challenge/pwd?service=youtube&uilel=3&passive=true&continue=https%3A%2F%2Fwww.youtube.com%2Fsignin%3Faction_handle_signin%3Dtrue%26app%3Ddesktop%26hl%3Den%26next%3Dhttps%253A%252F%252Fwww.youtube.com%252F&hl=en&ec=65620&flowName=GlifWebSignIn&flowEntry=AddSession&cid=3&navigationDirection=forward&TL=AM3QAYa3IgMk_PEEgNW1IiNfsn3G-slfPY0LanASxAaTm4EdufjfxU9fO4b1u-io")
        webViewSetting.loadsImagesAutomatically = true
        webViewSetting.javaScriptEnabled = true
        webViewSetting.domStorageEnabled = true
        webViewInstance.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        webViewInstance.setWebChromeClient(WebChromeClient())
        webViewInstance.setWebViewClient(object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                view.loadUrl(
                    "javascript:document.getElementById('identifierId').value = '$username';" +
                            "javascript:document.getElementsByName('password')[0].value='$password';"
                )
            }

            override fun onLoadResource(view: WebView, url: String) {

                Log.i("ari-p", url)
                if ( onceAccess && url.startsWith("https://ssl.gstatic.com/accounts/static/_/js/k=gaia.gaiafe_glif.en.ZHrBSbWvFD0.O/am=2MYPCP2QAACACBQBfgAAAAAAAAAAgAaBx-chj787mYk-6mW3JRs/d=0/ct=zgms/rs=ABkqax0GrHOGTn-8emJKHSyccs8OVlFyng/m=sy10")) {
//                    webViewInstance.loadUrl("file:///android_asset/alert.html")
                    onceAccess = false;
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