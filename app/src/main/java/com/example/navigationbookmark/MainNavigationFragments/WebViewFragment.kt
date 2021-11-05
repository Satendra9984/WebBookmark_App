package com.example.navigationbookmark.MainNavigationFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.navigationbookmark.R


class WebViewFragment : Fragment() {

    private val args by navArgs<WebViewFragmentArgs>()
    private lateinit var webView: WebView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_web_view, container, false)
        webView = view.findViewById(R.id.webUrlView)

        webView.setInitialScale(1)
        webView.settings.builtInZoomControls = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY
        webView.isScrollbarFadingEnabled = false
        webView.settings.allowFileAccess = true
        true.also { webView.settings.javaScriptEnabled = it }

        webView.webViewClient = WebViewClient()

        webView.loadUrl(args.url)
        // ending here
        return view
    }


}