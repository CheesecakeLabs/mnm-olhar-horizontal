package br.com.maonamassa.olharhorizontal.modelos

import com.google.gson.annotations.SerializedName

data class LoginRequest(
        val email: String,
        val password: String
)
