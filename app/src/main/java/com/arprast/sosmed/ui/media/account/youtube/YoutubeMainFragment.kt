package com.arprast.sosmed.ui.media.account.youtube

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.arprast.sosmed.util.ShowTextUtil
import com.arprastandroid.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class YoutubeMainFragment(username: String, password: String) : Fragment() {

    private val username = username
    private val password = password
    private var onceAccess = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_media_youtube, container, false)

        val fab: FloatingActionButton = root.findViewById(R.id.float_show_password)
        fab.setOnClickListener { view ->
            ShowTextUtil.showTextUtil("Credential account !", "Username: $username\nPassword: $password", context)

        }

        val webViewInstance = root.findViewById(R.id.webview) as WebView
        val webViewSetting = webViewInstance.settings
        webViewInstance.loadUrl("https://accounts.google.com/signin/v2/challenge/pwd?service=youtube&uilel=3&passive=true&continue=https%3A%2F%2Fwww.youtube.com%2Fsignin%3Faction_handle_signin%3Dtrue%26app%3Ddesktop%26hl%3Den%26next%3Dhttps%253A%252F%252Fwww.youtube.com%252F&hl=en&ec=65620&flowName=GlifWebSignIn&flowEntry=AddSession&cid=3&navigationDirection=forward&TL=AM3QAYa3IgMk_PEEgNW1IiNfsn3G-slfPY0LanASxAaTm4EdufjfxU9fO4b1u-io")
        webViewSetting.loadsImagesAutomatically = true
        webViewSetting.javaScriptEnabled = true
        webViewSetting.domStorageEnabled = true
        webViewSetting.cacheMode = WebSettings.LOAD_DEFAULT
        webViewInstance.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        webViewInstance.setWebChromeClient(WebChromeClient())
        webViewInstance.setWebViewClient(object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                view.loadUrl(
                    "javascript:var x = document.getElementById('identifierId').value = '$username';" +
                            "javascript:document.getElementsByName('password')[0].value='$password';"
                )
            }

            //webViewInstance.loadUrl("file:///android_asset/alert.html")
            override fun onLoadResource(view: WebView, url: String) {

                Log.d("ari-p", url)
                if (onceAccess
                    && (url.startsWith("https://accounts.google.com/_/lookup/accountlookup?"))
                    || (url.startsWith("https://accounts.google.com/_/signin/chooseaccount?"))
                ) {
                    onceAccess = false
                    ShowTextUtil.showTextUtil("Copy password below !", password, context)
                }

                if (url.startsWith("https://ssl.gstatic.com/accounts/static/_/js/k=gaia.gaiafe_glif.en.ZHrBSbWvFD0.O/am=2MYPCP2QAACACBQBfgAAAAAAAAAAgAaBx-chj787mYk-6mW3JRs/d=0/ct=zgms/rs=ABkqax0GrHOGTn-8emJKHSyccs8OVlFyng/m=sy5a,sy5w,sy65,sy5b,sy5x,sy66,sy61,sy6b,emf,emg,ems,emh,emi,emj,emk,eml,emm,emn,emo,emp,emq,emr,identifier_view")) {
                    ShowTextUtil.showTextUtil("Copy username below !", username, context)
                }
            }

        })
        return root
    }

}