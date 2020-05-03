package com.arprast.sosmed.dao;

import android.util.Log;

import com.arprast.sosmed.model.UserInterfacing;

import io.realm.Realm;

import static com.arprast.sosmed.util.PreferanceVariable.DEBUG_NAME;
import static com.arprast.sosmed.util.PreferanceVariable.MENU_ID_FIELD;


public class Dao {

    Realm realm;

    public Dao(Realm realm) {
        this.realm = realm;
    }

    /**
     * insert
     *
     * @param userInterfacing
     */
    public void saveUserInterfacing(final UserInterfacing userInterfacing) {
        Log.d(DEBUG_NAME, "init data insert="+userInterfacing.toString());
        realm.copyToRealm(userInterfacing);
    }

    public UserInterfacing getUserInterface(final UserInterfacing userInterfacing) {
        return realm.where(UserInterfacing.class)
                .equalTo(MENU_ID_FIELD, userInterfacing.getMenuId())
                .findFirst();
    }

    /**
     * result can be delivered current thread
     *
     * @param userInterfacing
     */
    public void updateUserInterfaceSync(final UserInterfacing userInterfacing) {
        Log.d(DEBUG_NAME, "init data update="+userInterfacing.toString());
        UserInterfacing model = realm.where(UserInterfacing.class)
                .equalTo(MENU_ID_FIELD, userInterfacing.getMenuId())
                .findFirst();
        model.setDisabled(userInterfacing.isDisabled());
    }

    /**
     * For async queries, you need a looper to make sure the query result can be delivered back on Android.
     *
     * @param userInterfacing
     */
    public void updateUserInterfaceAsync(final UserInterfacing userInterfacing) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                UserInterfacing model = realm.where(UserInterfacing.class)
                        .equalTo(MENU_ID_FIELD, userInterfacing.getMenuId())
                        .findFirst();
                model.setDisabled(userInterfacing.isDisabled());
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.i(DEBUG_NAME, "Update success :" + userInterfacing.toString());
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e(DEBUG_NAME, "Update error: " + userInterfacing.toString(), error);

            }
        });
    }

    // untuk memanggil semua data
//    public List<MahasiswaModel> getAllMahasiswa(){
//        RealmResults<MahasiswaModel> results = realm.where(MahasiswaModel.class).findAll();
//        return results;
//    }

//    // untuk memanggil semua data
//    public List<MahasiswaModel> getAllMahasiswa(){
//        RealmResults<MahasiswaModel> results = realm.where(MahasiswaModel.class).findAll();
//        return results;
//    }
//
//    // untuk meng-update data
//    public void update(final Integer id, final Integer nim, final String nama){
//        realm.executeTransactionAsync(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                MahasiswaModel model = realm.where(MahasiswaModel.class)
//                        .equalTo("id", id)
//                        .findFirst();
//                model.setNim(nim);
//                model.setNama(nama);
//            }
//        }, new Realm.Transaction.OnSuccess() {
//            @Override
//            public void onSuccess() {
//                Log.e("pppp", "onSuccess: Update Successfully");
//            }
//        }, new Realm.Transaction.OnError() {
//            @Override
//            public void onError(Throwable error) {
//                error.printStackTrace();
//            }
//        });
//    }
//
//    // untuk menghapus data
//    public void delete(Integer id){
//        final RealmResults<MahasiswaModel> model = realm.where(MahasiswaModel.class).equalTo("id", id).findAll();
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                model.deleteFromRealm(0);
//            }
//        });
//    }

}
