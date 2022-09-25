package com.example.trenutnovremeusrbijikotlin

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.trenutnovremeusrbijikotlin.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private var actionbar:ActionBar? = null
    lateinit var toolbar:Toolbar
    private lateinit var binding:ActivityMainBinding
    private var navController:NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        initToolbar()
        initNavControl()
        initDrawer()
    }

    private fun initNavControl() {
        val navHostFrag=supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container)
        navController= navHostFrag?.findNavController()
    }


    private fun initDrawer() {
        val drawerLayout=binding.mainDrawerLayout
        val actionBarDrawerToggle=ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        drawerLayout.setDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        navController?.let {
            NavigationUI.setupWithNavController(binding.navigationView,it)
        }

    }

    private fun select(item: MenuItem): Boolean {
        Toast.makeText(this,"test",Toast.LENGTH_LONG).show()
        return false
    }


    //not needed as closing layout is taken care of by the navigationUi
//    private fun selectItem(item: MenuItem, drawerLayout: DrawerLayout) : Boolean {
//        Toast.makeText(this,"test",Toast.LENGTH_LONG).show()
//        actionbar?.title = item.title
//        drawerLayout.closeDrawers()
//        return true
//    }

    private fun initToolbar() {
        toolbar=binding.toolbarInMainAct as Toolbar
        setSupportActionBar(toolbar)
        actionbar=supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.setHomeButtonEnabled(true)
        actionbar?.title = "Drawer"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_navigation_drawer,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.title){
            "Settings"-> {
                navController?.let { NavigationUI.onNavDestinationSelected(item, it) }
            return false}
            else -> {false}
        }
    }

}