package com.arprast.sosmed.dao

import androidx.lifecycle.LiveData
import com.arprast.sosmed.model.Account
import com.arprast.sosmed.repository.RealmLiveData
import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmResults


class AccountDao(val realm: Realm) {

    fun <T : RealmModel> RealmResults<T>.asLiveData() =
        RealmLiveData<T>(this)

    fun saveAccount(account: Account) {
        realm.executeTransactionAsync {
            it.insert(account)
        }
    }

    fun getAccounts(account : Account): LiveData<RealmResults<Account>> {
        return realm.where(Account::class.java)
//            .equalTo("accountType", account.accountType)
            .findAllAsync().asLiveData()
    }

}