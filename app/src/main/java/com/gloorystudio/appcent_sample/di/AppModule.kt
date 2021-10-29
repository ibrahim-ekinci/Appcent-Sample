package com.gloorystudio.appcent_sample.di

import android.app.Application
import android.content.Context
import com.gloorystudio.appcent_sample.data.local.db.service.GameDao
import com.gloorystudio.appcent_sample.data.local.db.service.GameDatabase
import com.gloorystudio.appcent_sample.data.remote.GameApi
import com.gloorystudio.appcent_sample.data.repository.GameRepository
import com.gloorystudio.appcent_sample.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGameRepository(
        api: GameApi,
        dao: GameDao
    ) = GameRepository(api,dao)


    @Singleton
    @Provides
    fun provideGameApi(): GameApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(GameApi::class.java)
    }

    @Singleton
    @Provides
    fun getGameDatabase(context: Application): GameDatabase {
        return GameDatabase.invoke(context)
    }

    @Singleton
    @Provides
    fun getGameDao(gameDatabase: GameDatabase): GameDao {
        return gameDatabase.gameDao()
    }
}