package com.example.navigationbookmark.MainNavigationFragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
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

        // setting menu
        setHasOptionsMenu(true)

        webView = view.findViewById(R.id.webUrlView)

        webView.setInitialScale(1)
//        webView.settings.builtInZoomControls = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY
        webView.isScrollbarFadingEnabled = false
        webView.settings.allowFileAccess = true
        webView.settings.domStorageEnabled = true
        true.also { webView.settings.javaScriptEnabled = it }

        webView.webViewClient = WebViewClient()

        webView.loadUrl(args.url)
        // ending here
        return view
    }
    // when menu is selected
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater){
        inflater.inflate(R.menu.webview_menu,menu)
        Log.i("webview","Url open in browser on createOptionsMenu")
        super.onCreateOptionsMenu(menu, inflater)
    }
    // when browser_view of menu selected
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.browser_view){

            val openUrl = Intent(Intent.ACTION_VIEW, Uri.parse(args.url))
            openUrl.data = Uri.parse(args.url)
            requireContext().startActivity(openUrl)
            Log.i("webview","Url open in browser")
        }
        return super.onOptionsItemSelected(item)
    }
}