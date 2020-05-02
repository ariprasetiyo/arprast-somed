package com.arprast.sosmed.ui.media.account

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.arprast.sosmed.MainActivity
import com.arprastandroid.R
import com.arprast.sosmed.model.Account
import com.arprast.sosmed.repository.AccountRepository
import com.arprast.sosmed.type.AccountType


class AddAccount : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_account_add, container, false)

        val context = context as MainActivity
        getAccountTypeList(context, root)

        val button = root.findViewById(R.id.save_account_button) as Button
        button.setOnClickListener(View.OnClickListener {
            saveAccountYoutube(root)
        })
        return root
    }

    private fun getAccountTypeList(context: Context, root: View) {
        val adapter = ArrayAdapter(
            context,
            R.layout.account_type_dropdown_menu,
            AccountType.values()
        )
        val editTextFilledExposedDropdown =
            root.findViewById(R.id.filled_exposed_dropdown) as AutoCompleteTextView
        editTextFilledExposedDropdown.setAdapter(adapter)
    }

    private fun saveAccountYoutube(root: View) {

        val inputYoutubeTitle = root.findViewById(R.id.input_account_title) as EditText
        val inputYoutubeUsername = root.findViewById(R.id.input_account_username) as EditText
        val inputYoutubePassword = root.findViewById(R.id.input_account_password) as EditText
        val inputYoutubeReEntryPassword =
            root.findViewById(R.id.input_account_re_entry_password) as EditText
        val inputYoutubeDesc = root.findViewById(R.id.input_account_desc) as EditText
        val inputAccountType =
            root.findViewById(R.id.filled_exposed_dropdown) as AutoCompleteTextView

        val youtubeTitle = inputYoutubeTitle.text.toString()
        val youtubeUsername = inputYoutubeUsername.text.toString()
        val youtubePassword = inputYoutubePassword.text.toString()
        val youtubeDescription = inputYoutubeDesc.text.toString()

        val account = Account()
        account.title = youtubeTitle
        account.username = youtubeUsername
        account.password = youtubePassword
        account.description = youtubeDescription
        account.accountType = inputAccountType.text.toString()
        if (isValidateInputData(account, inputYoutubeReEntryPassword.text.toString())) {
            if(AccountRepository().saveAccount(account)){
                tostText("Save success")
            }else{
                tostText("Save fail")
            }
        }
    }

    fun deleteAccountYoutube(root: View) {

    }

    private fun isValidateInputData(account: Account, reEntryPassword: String): Boolean {
        if (!isValidateYoutubeAccount(
                account.title,
                account.username,
                account.password,
                account.description
            )
        ) return false

        if (!account.password.equals(reEntryPassword)) {
            tostText("Password not match. Please check your password !")
            return false
        }
        return true
    }

    private fun isValidateYoutubeAccount(vararg textInputs: String): Boolean {
        for (textInput in textInputs) {
            if (textInput.isNullOrEmpty()) {
                tostText("Please check your data input!")
                return false
            }
        }
        return true
    }

    private fun tostText(text: String) {
        activity.let {
            val invalidateInputMesssage =
                Toast.makeText(it, text, Toast.LENGTH_LONG)
            invalidateInputMesssage.setGravity(Gravity.CENTER, 0, 0)
            invalidateInputMesssage.show()
        }
    }

}