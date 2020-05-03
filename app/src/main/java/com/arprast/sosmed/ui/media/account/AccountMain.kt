package com.arprast.sosmed.ui.media.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.arprastandroid.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class AccountMain : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_account_main, container, false)
        activity?.let {
            val bottomNavigationView =
                root.findViewById(R.id.account_bottom_navigation) as BottomNavigationView
            openFragment(AccountList(bottomNavigationView, it))
            setBottomNavigationView(bottomNavigationView, it)

        }
        return root

    }

    private fun openFragment(fragment: Fragment) {
        val transaction = getActivity()?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

    private fun setBottomNavigationView(bottomNavigationView: BottomNavigationView, it : FragmentActivity) {

        bottomNavigationView.setOnNavigationItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.getItemId()) {
                    R.id.account_bottom_menu_list -> {
                        openFragment(AccountList(bottomNavigationView, it))
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

