package com.arprast.sosmed.dao

import androidx.lifecycle.LiveData
import com.arprast.sosmed.model.Account
import com.arprast.sosmed.model.UserInterfacing
import com.arprast.sosmed.repository.RealmLiveData
import com.arprast.sosmed.util.PreferanceVariable.Companion.ID
import com.arprast.sosmed.util.PreferanceVariable.Companion.MENU_ID_FIELD
import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmResults


/**
 * https://proandroiddev.com/the-realm-of-kotlin-and-live-data-using-mvp-architecture-f04fc41c914e
 */
class RealmLiveDataDao(val realm: Realm) {

    fun <T : RealmModel> RealmResults<T>.asLiveData() =
        RealmLiveData<T>(this)

    fun saveAccount(account: Account): Boolean {
        return !realm.executeTransactionAsync {
            it.insert(account)
        }.isCancelled
    }

    /**
     * Get all accounts
     */
    fun getAccounts(account: Account): LiveData<RealmResults<Account>> {
        return realm.where(Account::class.java)
//            .equalTo("accountType", account.accountType)
            .findAllAsync().asLiveData()
    }

    fun updateUserInterfacing(userInterfacing: UserInterfacing) {

        val userinterface = realm.where(UserInterfacing::class.java)
            .equalTo(MENU_ID_FIELD, userInterfacing.menuId).findFirstAsync()
        userinterface.isDisabled = userInterfacing.isDisabled

    }

    fun getUserInterfacing(userInterfacing: UserInterfacing): LiveData<RealmResults<UserInterfacing>> {
        return realm.where(UserInterfacing::class.java)
            .equalTo(MENU_ID_FIELD, userInterfacing.menuId)
            .findAllAsync().asLiveData()
    }

    fun deleteAccount(account: Account) {
        realm.executeTransactionAsync {
            val result = it.where(account::class.java).equalTo(ID, account.id).findFirst()
            result?.deleteFromRealm()
        }
    }

}