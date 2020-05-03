package com.arprast.sosmed.ui.media.account

import android.os.Bundle
import android.view.*
import android.widget.Toast
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
                        openFragment(AddAccount(-1))
                    }
                    R.id.account_bottom_menu_help -> {
                        tostText("Not yet support")
                    }
                }
                return true
            }
        })
    }

    private fun tostText(text: String) {
        activity.let {
            val invalidateInputMesssage =
                Toast.makeText(it, text, Toast.LENGTH_LONG)
            invalidateInputMesssage.setGravity(Gravity.CENTER, 0, 0)
            invalidateInputMesssage.show()
        }
    }
}

