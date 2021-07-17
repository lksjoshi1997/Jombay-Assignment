package com.st.jombaykotlinassignment.model

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("char_id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("img")
    val imagUrl: String,
    @SerializedName("birthday")
    val dob: String,
)
