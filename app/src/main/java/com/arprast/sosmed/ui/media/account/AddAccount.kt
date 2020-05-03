package com.arprast.sosmed.ui.media.account

import android.content.Context
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
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
import com.arprast.sosmed.util.PreferanceVariable.Companion.HIDDEN
import com.arprast.sosmed.util.PreferanceVariable.Companion.SHOW


class AddAccount(accountId: Long) : Fragment() {

    private val accountId = accountId

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_account_add, container, false)

        val context = context as MainActivity
        getAccountTypeList(context, root)

        val inputYoutubePassword = root.findViewById(R.id.input_account_password) as EditText
        val inputYoutubeReEntryPassword =
            root.findViewById(R.id.input_account_re_entry_password) as EditText
        val showHiddenPassword = root.findViewById(R.id.show_hide_password) as Button
        val showHiddenRetryPassword = root.findViewById(R.id.show_hide_retry_password) as Button

        val inputYoutubeTitle = root.findViewById(R.id.input_account_title) as EditText
        val inputYoutubeUsername = root.findViewById(R.id.input_account_username) as EditText
        val inputYoutubeDesc = root.findViewById(R.id.input_account_desc) as EditText
        val inputAccountType =
            root.findViewById(R.id.filled_exposed_dropdown) as AutoCompleteTextView

        /** Show hidden password listener */
        showHiddenPasswordListener(
            inputYoutubePassword,
            inputYoutubeReEntryPassword,
            showHiddenPassword,
            showHiddenRetryPassword
        )
        val button = root.findViewById(R.id.save_account_button) as Button

        /** Edit account */
        var isUpdate = false
        if (accountId >= 0) {
            setEditTextData(
                accountId,
                inputYoutubePassword,
                inputYoutubeReEntryPassword,
                inputYoutubeTitle,
                inputYoutubeUsername,
                inputYoutubeDesc,
                inputAccountType
            )
            isUpdate = true
        }

        /** Save button listener */
        button.setOnClickListener(View.OnClickListener {
            saveAccountYoutube(
                inputYoutubePassword,
                inputYoutubeReEntryPassword,
                inputYoutubeTitle,
                inputYoutubeUsername,
                inputYoutubeDesc,
                inputAccountType,
                isUpdate,
                accountId
            )
        })
        return root
    }

    private fun setEditTextData(
        accountId: Long, inputYoutubePassword: EditText,
        inputYoutubeReEntryPassword: EditText,
        inputYoutubeTitle: EditText,
        inputYoutubeUsername: EditText,
        inputYoutubeDesc: EditText,
        inputAccountType: AutoCompleteTextView
    ) {
        var account = Account()
        account.id = accountId
        account = AccountRepository().getAccount(account)

        inputYoutubePassword.setText(account.password)
        inputYoutubeReEntryPassword.setText(account.password)
        inputYoutubeTitle.setText(account.title)
        inputYoutubeUsername.setText(account.username)
        inputYoutubeDesc.setText(account.description)
        inputAccountType.setText(account.accountType)

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

    private fun showHiddenPasswordListener(
        inputYoutubePassword: EditText,
        inputYoutubeReEntryPassword: EditText,
        showHiddenPassword: Button,
        showHiddenRetryPassword: Button
    ) {

        showHiddenPassword.setOnClickListener {
            if (showHiddenPassword.text.toString().equals(SHOW)) {
                inputYoutubePassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
                showHiddenPassword.text = HIDDEN
            } else {
                inputYoutubePassword.transformationMethod =
                    PasswordTransformationMethod.getInstance()
                showHiddenPassword.text = SHOW
            }
        }

        showHiddenRetryPassword.setOnClickListener {
            if (showHiddenRetryPassword.text.toString().equals(SHOW)) {
                inputYoutubeReEntryPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
                showHiddenRetryPassword.text = HIDDEN
            } else {
                inputYoutubeReEntryPassword.transformationMethod =
                    PasswordTransformationMethod.getInstance()
                showHiddenRetryPassword.text = SHOW
            }
        }
    }

    private fun saveAccountYoutube(
        inputYoutubePassword: EditText,
        inputYoutubeReEntryPassword: EditText,
        inputYoutubeTitle: EditText,
        inputYoutubeUsername: EditText,
        inputYoutubeDesc: EditText,
        inputAccountType: AutoCompleteTextView,
        isUpdate : Boolean,
        accountId : Long

    ) {
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
        account.id = accountId
        if (isValidateInputData(account, inputYoutubeReEntryPassword.text.toString())) {
            if (AccountRepository().saveUpdateAccount(account, isUpdate)) {
                tostText("Save success")
            } else {
                tostText("Save fail")
            }
        }
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