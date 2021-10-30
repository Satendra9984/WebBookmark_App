package com.example.navigationbookmark.ViewModel

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.navigationbookmark.R

class MainActivity : AppCompatActivity() {

    private lateinit var navHost: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navHost = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHost.findNavController()

        // For setting up the <- direction symbol at actionBar
        setupActionBarWithNavController(navController)

        // Getting the url send from the other apps or text
        if (intent?.action == Intent.ACTION_SEND) {
            if ("text/plain" == intent.type) {
                intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
                    // For finding navHostFragment and navController in avtivity
//                    val navHost = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
//                    val navContoller = navHost.findNavController()
                    // Sendign data through bundle
                    val bundle = Bundle()
                    bundle.putString("url",it)
                    navController.navigate(R.id.saveFragment,bundle)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}
