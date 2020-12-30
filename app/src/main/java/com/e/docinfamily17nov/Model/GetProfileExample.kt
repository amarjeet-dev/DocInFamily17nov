package com.e.docinfamily17nov.Model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class GetProfileExample {
    @SerializedName("success")
    @Expose
    private var success: Boolean? = null

    @SerializedName("data")
    @Expose
    private var data: GetProfileData? = null

    @SerializedName("message")
    @Expose
    private var message: String? = ""

    fun getSuccess(): Boolean? {
        return success
    }

    fun setSuccess(success: Boolean?) {
        this.success = success
    }

    fun getData(): GetProfileData? {
        return data
    }

    fun setData(data: GetProfileData?) {
        this.data = data
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }

}