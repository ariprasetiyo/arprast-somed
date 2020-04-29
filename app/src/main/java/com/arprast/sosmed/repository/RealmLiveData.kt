package com.arprast.sosmed.repository

import androidx.lifecycle.LiveData
import io.realm.RealmChangeListener
import io.realm.RealmModel
import io.realm.RealmResults

class RealmLiveData <T : RealmModel>(val realmResults: RealmResults<T>) : LiveData<RealmResults<T>>() {

    private val listener = RealmChangeListener<RealmResults<T>> { results -> value = results }

    fun <T : RealmModel> RealmResults<T>.asLiveData() =
        RealmLiveData<T>(this)

    override fun onActive() {
        realmResults.addChangeListener(listener)
    }

    override fun onInactive() {
        realmResults.removeChangeListener(listener)
    }
}