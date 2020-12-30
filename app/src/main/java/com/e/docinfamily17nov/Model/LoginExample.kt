package com.e.docinfamily17nov.Model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class LoginExample {

    @SerializedName("status")
    @Expose
    private var status: Boolean? = null

    @SerializedName("message")
    @Expose
    private var message: String? = null

    @SerializedName("access_token")
    @Expose
    private var accessToken: String? = null

    @SerializedName("token_type")
    @Expose
    private var tokenType: String? = null

    @SerializedName("expires_at")
    @Expose
    private var expiresAt: String? = null

    fun getStatus(): Boolean? {
        return status
    }

    fun setStatus(status: Boolean?) {
        this.status = status
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }

    fun getAccessToken(): String? {
        return accessToken
    }

    fun setAccessToken(accessToken: String?) {
        this.accessToken = accessToken
    }

    fun getTokenType(): String? {
        return tokenType
    }

    fun setTokenType(tokenType: String?) {
        this.tokenType = tokenType
    }

    fun getExpiresAt(): String? {
        return expiresAt
    }

    fun setExpiresAt(expiresAt: String?) {
        this.expiresAt = expiresAt
    }

}