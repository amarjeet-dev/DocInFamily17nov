package com.e.docinfamily17nov.Model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class StateExample {
    @SerializedName("success")
    @Expose
    private var success: Boolean? = null

    @SerializedName("data")
    @Expose
    private var data: StateData? = null

    @SerializedName("message")
    @Expose
    private var message: String? = null

    fun getSuccess(): Boolean? {
        return success
    }

    fun setSuccess(success: Boolean?) {
        this.success = success
    }

    fun getData(): StateData? {
        return data
    }

    fun setData(data: StateData?) {
        this.data = data
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }

}