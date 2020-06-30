package com.example.simplenoteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.simplenoteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration:AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityMainBinding=
            DataBindingUtil.setContentView(this,R.layout.activity_main)
            val navController=this.findNavController(R.id.nav_host_fragment)
        appBarConfiguration= AppBarConfiguration(navController.graph)
        NavigationUI.setupActionBarWithNavController(this,navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController=this.findNavController(R.id.nav_host_fragment)
        return NavigationUI.navigateUp(navController,appBarConfiguration)
    }
}
