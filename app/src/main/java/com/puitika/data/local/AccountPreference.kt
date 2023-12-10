package com.puitika.data.local

import android.content.Context
import com.puitika.data.local.Preference
import com.puitika.data.model.AccountModel

class AccountPreference(context: Context) {
    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setLogin(value: AccountModel) {
        val editor = preferences.edit()
        editor.putString(USERNAME, value.username)
        editor.putString(EMAIL, value.email)
        editor.putString(TOKEN, value.token)
        editor.apply()
    }

    fun getUser(): AccountModel {
        val username = preferences.getString(USERNAME, null)
        val email = preferences.getString(EMAIL, null)
        val token = preferences.getString(TOKEN, null)
        return AccountModel(username, email, token)
    }

    fun removeUser() {
        val editor = preferences.edit().clear()
        editor.apply()
    }

    fun isLoggedIn(): Boolean {
        val username = preferences.getString(USERNAME, null)
        return username != null
    }

    companion object {
        private const val PREFS_NAME = "account_pref"
        private const val EMAIL = "email"
        private const val USERNAME = "username"
        private const val TOKEN = "token"
    }
}