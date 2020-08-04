package com.example.flow_practice.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Blogs(
    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("realname")
    @Expose
    var realname: String,

    @SerializedName("team")
    @Expose
    var team: String,

    @SerializedName("firstappearance")
    @Expose
    var firstappearance: String,

    @SerializedName("createdby")
    @Expose
    var createdby: String,

    @SerializedName("publisher")
    @Expose
    var publisher: String,

    @SerializedName("imageurl")
    @Expose
    var imageurl: String,

    @SerializedName("bio")
    var bio: String
)