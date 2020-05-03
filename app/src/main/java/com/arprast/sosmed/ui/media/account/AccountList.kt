package com.arprast.sosmed.ui.media.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import com.arprast.sosmed.MainActivity
import com.arprast.sosmed.model.Account
import com.arprast.sosmed.repository.AccountRepository
import com.arprast.sosmed.type.AccountType
import com.arprast.sosmed.ui.media.account.facebook.FacebookMainFragment
import com.arprast.sosmed.ui.media.account.instagram.InstagramMainFragment
import com.arprast.sosmed.ui.media.account.twitter.TwitterMainFragment
import com.arprast.sosmed.ui.media.account.youtube.YoutubeMainFragment
import com.arprastandroid.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.realm.RealmResults

class AccountList(bottomNavigationView: BottomNavigationView?, it : FragmentActivity) : Fragment() {

    private val bottomNavigationView = bottomNavigationView
    private val it = it

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_account_list, container, false)

            val listView = root.findViewById<ListView>(R.id.account_list)
            val context = context as MainActivity

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

                        bottomNavigationView?.visibility = View.GONE

                        val hiddenField =
                            view.findViewById<TextView>(R.id.hidden_username_and_password)
                        val usernameFromList = hiddenField.text.toString()
                        val passwordFromList = hiddenField.getTag().toString()
                        val accountTypeView =
                            view.findViewById<TextView>(R.id.account_row_list_title).getTag()
                                .toString()
                        val accountType = AccountType.valueOfString(accountTypeView)


                        when (accountType) {
                            AccountType.FACEBOOK ->
                                openFragment(
                                    FacebookMainFragment(
                                        usernameFromList,
                                        passwordFromList
                                    )
                                )
                            AccountType.YOUTUBE ->
                                openFragment(
                                    YoutubeMainFragment(
                                        usernameFromList,
                                        passwordFromList
                                    )
                                )
                            AccountType.INSTAGRAM ->
                                openFragment(
                                    InstagramMainFragment(
                                        usernameFromList,
                                        passwordFromList
                                    )
                                )
                            AccountType.TWITTER ->
                                openFragment(
                                    TwitterMainFragment(
                                        usernameFromList,
                                        passwordFromList
                                    )
                                )
                            else -> ""
                        }

                    })
                })
        return root
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = getActivity()?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

}