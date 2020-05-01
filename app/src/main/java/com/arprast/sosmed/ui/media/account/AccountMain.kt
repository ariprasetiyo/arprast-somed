package com.arprast.sosmed.ui.media.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.arprast.sosmed.util.FragmentUtil
import com.example.arprastandroid.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class AccountMain : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_account_main, container, false)
        val bottomNavigationView =
            root.findViewById(R.id.account_bottom_navigation) as BottomNavigationView
        openFragment(AccountList(bottomNavigationView))
        setBottomNavigationView(bottomNavigationView)
        return root
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = getActivity()?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

    private fun setBottomNavigationView( bottomNavigationView : BottomNavigationView) {

        bottomNavigationView.setOnNavigationItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.getItemId()) {
                    R.id.account_bottom_menu_list -> {
                        openFragment(AccountList(bottomNavigationView))
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

