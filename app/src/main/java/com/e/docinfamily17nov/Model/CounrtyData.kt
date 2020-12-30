package com.e.docinfamily17nov.Model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class CounrtyData {
    @SerializedName("countries")
    @Expose
    private var countries: List<CountriesData?>? = null

    fun getCountries(): List<CountriesData?>? {
        return countries
    }

    fun setCountries(countries: List<CountriesData?>?) {
        this.countries = countries
    }

}