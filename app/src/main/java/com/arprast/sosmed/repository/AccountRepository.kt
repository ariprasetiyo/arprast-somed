package com.arprast.sosmed.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arprast.sosmed.dao.AccountDao
import com.arprast.sosmed.model.Account
import io.realm.Realm
import io.realm.RealmResults
import java.util.*

class AccountRepository : ViewModel() {

    fun Realm.accountDao() : AccountDao =
        AccountDao(this)

    val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    fun saveAccount(account: Account) : Boolean {
        account.id = Calendar.getInstance().timeInMillis
        account.createDate = Date()
        return realm.accountDao().saveAccount(account)
    }

    fun getAccounts(account : Account): LiveData<RealmResults<Account>> {
        return realm.accountDao().getAccounts(account)
    }
//
//    fun deleteAccount(): LiveData<RealmResults<Account>> {
//        return realm.accountDao().getAccounts()
//    }
//
//    fun editAccount(): LiveData<RealmResults<Account>> {
//        return realm.accountDao().getAccounts()
//    }

    override fun onCleared() {
        realm.close()
        super.onCleared()
    }

}