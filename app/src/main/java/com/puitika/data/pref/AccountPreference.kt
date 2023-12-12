package com.puitika.data.pref

import android.content.Context
import com.puitika.data.model.AccountModel

class AccountPreference(context: Context) {
    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setLogin(accountModel: AccountModel) {
        val editor = preferences.edit()
        editor.putString(USERNAME, accountModel.username)
        editor.putString(EMAIL, accountModel.email)
        editor.putString(API_KEY, accountModel.apiKey)
        editor.apply()
    }

    fun getAccount(): AccountModel {
        val username = preferences.getString(USERNAME, null)
        val email = preferences.getString(EMAIL, null)
        val apiKey = preferences.getString(API_KEY, null)
        return AccountModel(username, email, apiKey)
    }

    fun removeAccount() {
        val editor = preferences.edit().clear()
        editor.apply()
    }

    fun isLoggedIn(): Boolean {
        val username = preferences.getString(USERNAME, null)
        return username != null
    }

    companion object {
        private const val PREFS_NAME = "account_pref"
        private const val USERNAME = "username"
        private const val EMAIL = "email"
        private const val API_KEY = "api_key"
    }
}
