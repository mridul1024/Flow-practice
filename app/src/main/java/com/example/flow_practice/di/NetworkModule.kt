package com.example.flow_practice.di

import com.example.flow_practice.network.NetworkService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    private const val BASE_URL : String = "https://www.simplifiedcoding.net/demos/"

//    @Singleton
//    @Provides
//    fun provideGson(): Gson{
//        return GsonBuilder().create()
//    } gson : Gson

    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Singleton
    @Provides
    fun provideNetworkService(retrofit: Retrofit.Builder): NetworkService{
        return retrofit.build().create(NetworkService::class.java)
    }
}