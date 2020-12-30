package com.e.docinfamily17nov.Model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class GetProfileUserData {
    @SerializedName("id")
    @Expose
    private var id: Int? = null

    @SerializedName("email")
    @Expose
    private var email: String? = null

    @SerializedName("first_name")
    @Expose
    private var firstName: String? = null

    @SerializedName("last_name")
    @Expose
    private var lastName: String? = null

    @SerializedName("phone_number")
    @Expose
    private var phoneNumber: String? = null

    @SerializedName("gender")
    @Expose
    private var gender: String? = null

    @SerializedName("prefered_pronoun")
    @Expose
    private var preferedPronoun: String? = null

    @SerializedName("race")
    @Expose
    private var race: String? = null

    @SerializedName("dob")
    @Expose
    private var dob: String? = null

    @SerializedName("state")
    @Expose
    private var state: String? = null

    @SerializedName("country")
    @Expose
    private var country: String? = null

    @SerializedName("user_type")
    @Expose
    private var userType: String? = null

    @SerializedName("full_name")
    @Expose
    private var fullName: String? = null



    @SerializedName("social_history")
    @Expose
    private var socialHistory: String? = null

    @SerializedName("user_notes")
    @Expose
    private var userNotes: List<String?>? = null

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String?) {
        this.email = email
    }

    fun getFirstName(): String? {
        return firstName
    }

    fun setFirstName(firstName: String?) {
        this.firstName = firstName
    }

    fun getLastName(): String? {
        return lastName
    }

    fun setLastName(lastName: String?) {
        this.lastName = lastName
    }

    fun getPhoneNumber(): String? {
        return phoneNumber
    }

    fun setPhoneNumber(phoneNumber: String?) {
        this.phoneNumber = phoneNumber
    }

    fun getGender(): String? {
        return gender
    }

    fun setGender(gender: String?) {
        this.gender = gender
    }

    fun getPreferedPronoun(): String? {
        return preferedPronoun
    }

    fun setPreferedPronoun(preferedPronoun: String?) {
        this.preferedPronoun = preferedPronoun
    }

    fun getRace(): String? {
        return race
    }

    fun setRace(race: String?) {
        this.race = race
    }

    fun getDob(): String? {
        return dob
    }

    fun setDob(dob: String?) {
        this.dob = dob
    }

    fun getState(): String? {
        return state
    }

    fun setState(state: String?) {
        this.state = state
    }

    fun getCountry(): String? {
        return country
    }

    fun setCountry(country: String?) {
        this.country = country
    }

    fun getUserType(): String? {
        return userType
    }

    fun setUserType(userType: String?) {
        this.userType = userType
    }

    fun getFullName(): String? {
        return fullName
    }

    fun setFullName(fullName: String?) {
        this.fullName = fullName
    }


    fun getSocialHistory(): String? {
        return socialHistory
    }

    fun setSocialHistory(socialHistory: String?) {
        this.socialHistory = socialHistory
    }

    fun getUserNotes(): List<String?>? {
        return userNotes
    }

    fun setUserNotes(userNotes: List<String?>?) {
        this.userNotes = userNotes
    }
}