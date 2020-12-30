package com.e.docinfamily17nov.Model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class GetProfileData {
    @SerializedName("user")
    @Expose
    private var user: GetProfileUserData? = null

    fun getUser(): GetProfileUserData? {
        return user
    }

    fun setUser(user: GetProfileUserData?) {
        this.user = user
    }
}