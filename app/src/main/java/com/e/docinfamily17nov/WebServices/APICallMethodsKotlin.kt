package com.e.docinfamily17nov.WebServices

import com.e.docinfamily17nov.Model.*
import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.http.*

interface APICallMethodsKotlin {
//    @FormUrlEncoded
    @POST("api/login")
    fun Login(@Body requestBody: RequestBody): Single<LoginExample>

    @POST("api/social-login")
    fun SocialLogin(@Body requestBody: RequestBody): Single<LoginExample>

    @FormUrlEncoded
    @POST("api/register")
    fun Register(@Body body: RequestBody): Single<RegisterExample>



    @GET("api/logout")
    fun Logout(
        @Header("Authorization") token: String, @Header("Accept") Accept: String,
        @Header("Content-Type") Content_Type: String
    ): Single<RegisterExample>

    @GET("api/get-auth-user")
    fun GetProfile(
        @Header("Authorization") token: String, @Header("Accept") Accept: String,
        @Header("Content-Type") Content_Type: String
    ): Single<GetProfileExample>

    @GET("api/get-country-state-list")
    fun GetCounrty(): Single<CountryExample>

    @GET
    fun GetState(@Url url: String?): Single<StateExample?>


//    @FormUrlEncoded
    @POST("api/forgot-password")
    fun ForgotPassword(@Body body: RequestBody): Single<RegisterExample>

//    @FormUrlEncoded
    @POST("api/reset-password")
    fun ResetPassword(@Body body: RequestBody): Single<RegisterExample>


//    @FormUrlEncoded
    @POST("api/change-password")
    fun ChangePassword(
    @Header("Authorization") token: String, @Header("Accept") Accept: String,
    @Header("Content-Type") Content_Type: String, @Body requestBody: RequestBody
): Single<RegisterExample>

    @POST("api/update-profile")
    fun update_profile(
        @Header("Authorization") token: String, @Header("Accept") Accept: String,
        @Header("Content-Type") Content_Type: String, @Body requestBody: RequestBody
    ): Single<RegisterExample>
}