package com.e.docinfamily17nov.Model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class RegisterExample {

    @SerializedName("success")
    @Expose
    private var success: Boolean? = null


    @SerializedName("message")
    @Expose
    private var message: String? = null

    fun getSuccess(): Boolean? {
        return success
    }

    fun setSuccess(success: Boolean?) {
        this.success = success
    }
    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }

}