package com.arprast.sosmed.ui.media.account.youtube

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.arprastandroid.R


class YoutubeMainFragment : Fragment() {

    private lateinit var mapViewModel: MapViewModel

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
//        webViewInstance.loadUrl("https://accounts.google.com/ServiceLogin/identifier?continue=https%3A%2F%2Fwww.youtube.com%2Fsignin%3Faction_handle_signin%3Dtrue%26app%3Ddesktop%26hl%3Den%26next%3Dhttps%253A%252F%252Fwww.youtube.com%252F&hl=en&service=youtube&flowName=GlifWebSignIn&flowEntry=AddSession")
        webViewSetting.loadsImagesAutomatically = true
        webViewSetting.javaScriptEnabled = true
        webViewSetting.domStorageEnabled = true
        webViewInstance.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        webViewInstance.setWebViewClient(object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {

                Log.i("arprast1url", url)
                ///https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fwww.youtube.com%2Fsignin%3Faction_handle_signin%3Dtrue%26app%3Ddesktop%26hl%3Den%26next%3Dhttps%253A%252F%252Fwww.youtube.com%252F&hl=en&service=youtube&flowName=GlifWebSignIn&flowEntry=ServiceLogin
                val user = "prasetiyooo@gmail.com"
                val pwd = "p"
//                view.loadUrl(
//                    "javascript:document.getElementById('identifierId').value = '$user';"+
//                            "javascript:document.getElementsByName('password')[0].value='$pwd';"
//                )
            }

            override fun onLoadResource(view: WebView, url: String) {
//
                Log.i("arprast1url", url)

//              view.
////
//                 if (url.startsWith("https://accounts.google.com/signin/v2/challenge/pwd")) { Log.i("arprast1", "coba1")
//                     val pwd = "123"
//                     val user = "prasetiyooo@gmail.com"
//                     view.loadUrl("javascript:document.getElementById('identifierId').value = '$user';" +
//                             "javascript:document.getElementsByName('password')[0].value = '$pwd';")
//                }
            }

//            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//            override fun shouldInterceptRequest(
//                view: WebView,
//                request: WebResourceRequest
//            ): WebResourceResponse? {
//                val url = request.url.toString()
//                var response = super.shouldInterceptRequest(view, request)
//                // load native js
////                if (url != null && url.contains(INJECTION_TOKEN/* scheme define */)) {
////
////                    response = WebResourceResponse(
////                        "text/javascript",
////                        "utf-8",
////                        loadJsInputStream(url, JsCache.getJsFilePath(path) /* InputStream */)
////                    )
////                }
//                Log.i("arprast1", url+"\n"+response.toString())
//                return response
//            }

        })

        return root
    }

}