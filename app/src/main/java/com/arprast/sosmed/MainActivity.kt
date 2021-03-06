package com.arprast.sosmed

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.arprast.sosmed.model.UserInterfacing
import com.arprast.sosmed.repository.AccountRepository
import com.arprast.sosmed.type.UserInterfaceType
import com.arprastandroid.R
import com.google.android.material.navigation.NavigationView
import io.realm.Realm
import io.realm.RealmConfiguration


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRealm()
        initData()

        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_share,
                R.id.nav_send,
                R.id.nav_facebook,
                R.id.nav_twitter,
                R.id.nav_instagram,
                R.id.nav_youtube,
                R.id.nav_account
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    private fun initRealm(){
        Realm.init(this)
        val configuration = RealmConfiguration.Builder()
            .name("arprast.db")
            .schemaVersion(0)
            .build()
        Realm.setDefaultConfiguration(configuration)
    }

    private fun initData(){
        val userInterfacing = UserInterfacing()
        userInterfacing.menuId = UserInterfaceType.SHOW_CREDENTIAL.stringValue
        userInterfacing.isDisabled = true
        AccountRepository().insertUpdateUserInterfacing(userInterfacing)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
