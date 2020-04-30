package com.arprast.sosmed.ui.media.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.arprastandroid.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class AccountMain : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_account_main, container, false)
        openFragment(AccountList())
        setBottomNavigationView(root)
        return root
    }

    fun openFragment(fragment: Fragment) {
        val transaction = getActivity()?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

    fun setBottomNavigationView(root: View) {
        val bottomNavigationView =
            root.findViewById(R.id.account_bottom_navigation) as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.getItemId()) {
                    R.id.account_bottom_menu_list -> {
                        openFragment(AccountList())
                    }
                    R.id.account_bottom_menu_add -> {
                        openFragment(AddAccount())
                    }
                    R.id.account_bottom_menu_help -> {

                    }
                }
                return true
            }
        })
    }
}

