package com.example.navigationbookmark.MainNavigationFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.navigationbookmark.R
import kotlinx.android.synthetic.main.fragment_web_view.*


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
        webView.settings.builtInZoomControls = true
        webView.settings.displayZoomControls = false
        webView.scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY
        webView.isScrollbarFadingEnabled = false
        true.also { webView.settings.javaScriptEnabled = it }
        webView.settings.allowFileAccess = true
        webView.webViewClient = WebViewClient()
        webView.settings.useWideViewPort = true;


        val url = args.url

        if (url != null) {
            webView.loadUrl(url)
        }else {
            Toast.makeText(requireContext(),"Wrong Address",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.webViewFragment_to_mainFragment)
        }
        return view
    }


}