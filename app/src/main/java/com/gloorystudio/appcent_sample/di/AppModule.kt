package com.gloorystudio.appcent_sample.di

import com.gloorystudio.appcent_sample.data.remote.GameApi
import com.gloorystudio.appcent_sample.data.repository.GameRepository
import com.gloorystudio.appcent_sample.util.Constants.BASE_URL
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

object AppModule {


    @Singleton
    @Provides
    fun provideGameRepository(
        api: GameApi
    ) = GameRepository(api)


    @Singleton
    @Provides
    fun provideGameApi(): GameApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(GameApi::class.java)
    }
}