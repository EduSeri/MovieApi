package com.example.api.login.responses

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("request_token") val token:String
)