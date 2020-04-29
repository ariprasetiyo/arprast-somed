package com.arprast.sosmed.ui.media.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.arprast.sosmed.MainActivity
import com.example.arprastandroid.R
import com.arprast.sosmed.model.Account
import com.arprast.sosmed.repository.AccountRepository
import com.arprast.sosmed.type.AccountType
import com.arprast.sosmed.ui.media.account.youtube.YoutubeMainFragment
import io.realm.RealmResults

class AccountList : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        Transformations.map(accountResult, {
//            val sas = it
//            Log.i("ari", it.asJSON())
//            for (i in it.indices ) {
//                val usernameResult = it[i]?.username;
//                val passwordResult = it[i]?.password;
//                val titleResult = it[i]?.title;
//
//                username[i] = if(usernameResult == null) "" else usernameResult
//                password[i]  =if(passwordResult == null) "" else passwordResult
//                title[i]  = if(titleResult == null) "" else titleResult
//            }
//        })


        val root = inflater.inflate(R.layout.fragment_account_list, container, false)
        val listView = root.findViewById<ListView>(R.id.account_list)
        val context = context as MainActivity
        activity?.let {

            val usernameList: ArrayList<String> = ArrayList()
            val passwordList: ArrayList<String> = ArrayList()
            val titleList: ArrayList<String> = ArrayList()
            val description: ArrayList<String> = ArrayList()
            val accountType: ArrayList<AccountType> = ArrayList()

            val account = Account()
            account.accountType = AccountType.YOUTUBE.stringValue

            AccountRepository().getAccounts(account)
                .observe(it, Observer<RealmResults<Account>> { t ->
                    for (accountData in t) {
                        usernameList.add(accountData.username)
                        passwordList.add(accountData.password)
                        titleList.add(accountData.title)
                        description.add(accountData.description)
                        accountType.add(AccountType.valueOfString(accountData.accountType))
                    }
                    listView.adapter =
                        AccountListAdapter(
                            titleList.toTypedArray(),
                            usernameList.toTypedArray(),
                            passwordList.toTypedArray(),
                            description.toTypedArray(),
                            accountType.toTypedArray(),
                            context
                        )
                    listView.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
                        val mapsFragment =
                            YoutubeMainFragment()
                        val fragmentManager = getActivity()?.supportFragmentManager
                        val fragmentTransaction = fragmentManager?.beginTransaction()
                        fragmentTransaction?.replace(android.R.id.content, mapsFragment)
                        fragmentTransaction?.addToBackStack(null)
                        fragmentTransaction?.commit()
                    })
                })
        }
        return root
    }

}