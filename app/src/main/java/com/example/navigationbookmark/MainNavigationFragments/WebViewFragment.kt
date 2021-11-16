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
        webView.settings.userAgentString = System.getProperty("http.agent")

//        Mozilla/5.0 (Linux; Android 5.1.1; Nexus 5 Build/LMY48B; wv)
//        AppleWebKit/537.36 (KHTML, like Gecko)
//        Version/4.0 Chrome/43.0.2357.65 Mobile Safari/537.36

        webView.webViewClient = WebViewClient()

        webView.loadUrl(args.url)

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
        when(item.itemId ){
            R.id.browser_view -> openUrl()
            R.id.shareWebViewUrl -> shareUrl()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun shareUrl() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, args.url)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    fun openUrl()
    {
        val openUrl = Intent(Intent.ACTION_VIEW, Uri.parse(args.url))
        openUrl.data = Uri.parse(args.url)
        requireContext().startActivity(openUrl)
        Log.i("webview","Url open in browser")
    }
}