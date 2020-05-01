package com.arprast.sosmed.util

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

class FragmentUtil {

    companion object {
        fun openFragmentByContainer(fragment: Fragment, @IdRes  containerViewId : Int) {
            val transaction = fragment.getActivity()?.supportFragmentManager?.beginTransaction()
            transaction?.replace(containerViewId, fragment)
            transaction?.addToBackStack(null)
            transaction?.commit()
        }
    }

}