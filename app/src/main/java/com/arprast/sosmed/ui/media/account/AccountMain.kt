package com.arprast.sosmed.ui.media.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
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

//        listview.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, i, l ->
//            Toast.makeText(
//                activity!!.applicationContext,
//                items[i],
//                Toast.LENGTH_SHORT
//            ).show()
//        })


        //            val account = Account()
//            account.id = 1
//            account.titile = "12121"
//            account.username ="ari prasetiyo"
//            AccountRepository().addKartData(account)

//                    var arrayAdapter = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, username)


//            listView.adapter = arrayAdapter
//            listView.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, i, l ->
//
//                val fragmentTransaction = fragmentManager?.beginTransaction()
//                fragmentTransaction?.replace(R.id.youtube_account_list, mapsFragment)
//                fragmentTransaction?.addToBackStack(null)
//                fragmentTransaction?.commit()
//            })


//            AccountRepository().getKartData().observe(it, Observer<RealmResults<Account>> { t ->
//                val myToast = Toast.makeText(it,"toast message with gravity"+ t, Toast.LENGTH_SHORT)
//                myToast.setGravity(Gravity.LEFT,200,200)
//                myToast.show()
//            })


//        val bottomNavigationView = findViewById(R.id.bottomNavigation) as BottomNavigationView
//        bottomNavigationView.setOnNavigationItemSelectedListener(object :
//            BottomNavigationView.OnNavigationItemSelectedListener {
//            fun onNavigationItemSelected(item: MenuItem): Boolean {
//                when (item.getItemId()) {
//                    R.id.menu_email -> tvLabel.setText("Email")
//                    R.id.menu_info -> tvLabel.setText("Info")
//                    R.id.menu_map -> tvLabel.setText("Map")
//                }
//                return true
//            }
//        })
        //            val myToast = Toast.makeText(it,"toast message with gravity", Toast.LENGTH_SHORT)
//                myToast.setGravity(Gravity.LEFT,200,200)
//                myToast.show()

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

