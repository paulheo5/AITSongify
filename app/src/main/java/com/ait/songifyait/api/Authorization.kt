package com.ait.songifyait.api

import com.ait.songifyait.data.Token
import retrofit2.Call
import retrofit2.http.*

//MjFhNmRjMzgwNTEzNDFkNDgyODZjMjI1YjQxNDc5MjQ6OTJlOGYzZTJhODg4NDQxZDgzYzdkMmJjMmJmMDczYTc=
interface Authorization {
    @Headers("Authorization: Basic MjFhNmRjMzgwNTEzNDFkNDgyODZjMjI1YjQxNDc5MjQ6OTJlOGYzZTJhODg4NDQxZDgzYzdkMmJjMmJmMDczYTc=",
        "Content-Type: application/x-www-form-urlencoded")
    @POST("/api/token")
    fun getToken(
        @Query("grant_type") grantType: String = "client_credentials"): Call<Token>
}