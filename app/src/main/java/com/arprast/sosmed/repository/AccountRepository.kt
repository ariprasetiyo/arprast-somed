package com.arprast.sosmed.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arprast.sosmed.dao.Dao
import com.arprast.sosmed.dao.RealmLiveDataDao
import com.arprast.sosmed.model.Account
import com.arprast.sosmed.model.UserInterfacing
import com.arprast.sosmed.util.PreferanceVariable.Companion.DEBUG_NAME
import io.realm.Realm
import io.realm.RealmResults
import java.util.*

class AccountRepository : ViewModel() {

    fun Realm.accountDao(): RealmLiveDataDao =
        RealmLiveDataDao(this)

    val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    fun saveUpdateAccount(account: Account, isUpdate: Boolean): Boolean {
        val deleteAccount = Account()
        deleteAccount.id = account.id
        val isSaveSuccess = saveAccount(account)
        if (isSaveSuccess && isUpdate) {
            deleteAccount(deleteAccount)
        }
        return isSaveSuccess
    }

    private fun saveAccount(account: Account): Boolean {
        account.id = Calendar.getInstance().timeInMillis
        account.createDate = Date()
        Log.i(DEBUG_NAME, "dsbe success account=${account.id}")
        return realm.accountDao().saveAccount(account)
    }

    fun getAccounts(account: Account): LiveData<RealmResults<Account>> {
        return realm.accountDao().getAccounts(account)
    }

    fun getAccount(account: Account): Account {
        val account = realm.accountDao().getAccount(account)
        if (account == null || account.id <= 0) {
            return Account()
        }
        return account
    }

    fun deleteAccount(account: Account) {
        realm.accountDao().deleteAccount(account)
    }

    fun updateAccount(account: Account) {
        realm.accountDao().deleteAccount(account)
    }

    fun insertUpdateUserInterfacing(userInterfacing: UserInterfacing) {
        userInterfacing.createDate = Date()
        realm.beginTransaction()
        val interfacing = Dao(realm).getUserInterface(userInterfacing)
        if (interfacing == null || interfacing.menuId.isEmpty()) {
            Dao(realm).saveUserInterfacing(userInterfacing)
        } else {
            Dao(realm).updateUserInterfaceSync(userInterfacing)
        }
        realm.commitTransaction()
    }

    fun updateUserInterface(userInterfacing: UserInterfacing) {
        userInterfacing.createDate = Date()
        realm.beginTransaction()
        Dao(realm).updateUserInterfaceSync(userInterfacing)
        realm.commitTransaction()
    }

    fun updateUserInterfaceLiveData(userInterfacing: UserInterfacing) {
        RealmLiveDataDao(realm).updateUserInterfacing(userInterfacing)
    }

    fun getUserInterfacing(userInterfacing: UserInterfacing): UserInterfacing {
        return Dao(realm).getUserInterface(userInterfacing)
    }

    override fun onCleared() {
        realm.close()
        super.onCleared()
    }

}