package com.arprast.sosmed.ui.media.account

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.arprastandroid.R
import com.arprast.sosmed.type.AccountType
import org.w3c.dom.Text

class AccountListAdapter(
    val titleC: Array<String>,
    val usernameC: Array<String>,
    val passwordC: Array<String>,
    val descriptionC: Array<String>,
    val accountTypeC: Array<AccountType>,
    val contextC: Context
) : BaseAdapter() {

    private val title: Array<String> = titleC
    private val username: Array<String> = usernameC
    private val password: Array<String> = passwordC
    private val description: Array<String> = descriptionC
    private val accountType: Array<AccountType> = accountTypeC
    private val context: Context = contextC
    private val inflter: LayoutInflater = (LayoutInflater.from(contextC))

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = inflter.inflate(R.layout.account_layout_row_list, null);
        view.findViewById<TextView>(R.id.account_row_list_title)
            .setText("${title[position]} ${username[position]}")
        view.findViewById<TextView>(R.id.account_row_list_description)
            .setText(description[position])

        when (accountType[position]) {
            AccountType.YOUTUBE ->
                view.findViewById<ImageView>(R.id.account_favicon_social_media).setImageResource(R.drawable.ic_youtube_icon)
            AccountType.TWITTER ->
                view.findViewById<ImageView>(R.id.account_favicon_social_media).setImageResource(R.drawable.ic_twitter_icon)
            AccountType.INSTAGRAM ->
                view.findViewById<ImageView>(R.id.account_favicon_social_media).setImageResource(R.drawable.ic_instagram_icon)
            AccountType.FACEBOOK ->
                view.findViewById<ImageView>(R.id.account_favicon_social_media).setImageResource(R.drawable.ic_facebook_icon)
            else ->
                view.findViewById<ImageView>(R.id.account_favicon_social_media).setImageResource(R.drawable.ic_unknown_icon)
        }

        val ttt = view.findViewById<TextView>(R.id.hidden_username_and_password)
        ttt.setText(username[position])
        ttt.setTag(password[position])

//        view.findViewById<TextView>(R.id.youtube_row_list_password).setText(password[position])
        return view
    }

    override fun getItem(position: Int): Any {
        return titleC[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return title.size
    }

}