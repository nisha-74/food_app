
package com.example.foodapp.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.foodapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Apply edge-to-edge padding to the main layout view
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        // Setup NavController with BottomNavigationView
        findViewById<View>(R.id.main).post {
            val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_nav)
            // Retrieve NavController using NavHostFragment
            val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.containerView) as NavHostFragment
            val navController = navHostFragment.navController
            // Setup BottomNavigationView with NavController
            NavigationUI.setupWithNavController(bottomNavigation, navController)
        }
    }
}
