package com.puitika.data.pref

data class UserModel(
    val email: String,
    val username:String,
    val api: String,
    val isLogin: Boolean = false
)