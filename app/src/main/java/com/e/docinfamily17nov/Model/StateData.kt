package com.e.docinfamily17nov.Model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class StateData {

    @SerializedName("states")
    @Expose
    private var states: List<StatesData?>? = null

    fun getStates(): List<StatesData?>? {
        return states
    }

    fun setStates(states: List<StatesData?>?) {
        this.states = states
    }

}