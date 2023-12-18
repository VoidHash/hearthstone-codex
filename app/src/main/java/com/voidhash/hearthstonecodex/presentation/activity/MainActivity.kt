package com.voidhash.hearthstonecodex.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.navigation.NavigationView
import com.voidhash.hearthstonecodex.R
import com.voidhash.hearthstonecodex.databinding.ActivityMainBinding
import com.voidhash.hearthstonecodex.framework.di.NetworkModule
import com.voidhash.hearthstonecodex.framework.di.PersistenceModule
import com.voidhash.hearthstonecodex.framework.di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var barDrawer: ActionBarDrawerToggle
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.navigationView.setNavigationItemSelectedListener(this)

        barDrawer = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(barDrawer)
        binding.drawerLayout.bringToFront()
        barDrawer.syncState()

        navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        setContentView(view)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(barDrawer.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.item_home -> navController.navigate(R.id.mainFragment)
            R.id.item_search_cards -> navController.navigate(R.id.searchFragment)
            R.id.item_collection -> navController.navigate(R.id.collectionFragment)
            R.id.item_cards_back -> navController.navigate(R.id.cardBackFragment)

        }
        binding.drawerLayout.closeDrawers()
        return true
    }
}