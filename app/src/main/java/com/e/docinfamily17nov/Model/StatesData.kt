package com.e.docinfamily17nov.Model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class StatesData {
    @SerializedName("id")
    @Expose
    private var id: Int? = null

    @SerializedName("country_id")
    @Expose
    private var countryId: Int? = null

    @SerializedName("title")
    @Expose
    private var title: String? = null

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getCountryId(): Int? {
        return countryId
    }

    fun setCountryId(countryId: Int?) {
        this.countryId = countryId
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String?) {
        this.title = title
    }

}