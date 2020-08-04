package com.example.flow_practice.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface NetworkService {
    @GET("marvel")
    suspend fun getBlogs(): List<Blogs>
}