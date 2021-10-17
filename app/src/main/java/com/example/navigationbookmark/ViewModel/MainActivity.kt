package com.example.navigationbookmark.ViewModel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.navigationbookmark.MainNavigationFragments.MainFragment
import com.example.navigationbookmark.MainNavigationFragments.MainFragmentDirections
import com.example.navigationbookmark.MainNavigationFragments.SaveFragment
import com.example.navigationbookmark.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (intent?.action == Intent.ACTION_SEND) {
            if ("text/plain" == intent.type) {
                intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
                   val navHost = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
                    val navContoller = navHost.findNavController()
                    val bundle = Bundle()
                    bundle.putString("url",it)
                    navContoller.navigate(R.id.saveFragment,bundle)
                }
            }
        }
    }
}
