package com.example.spacedemo.injection

import android.content.Context
import com.example.spacedemo.data.local.AppDataBase
import com.example.spacedemo.data.local.SpaceShipDAO
import com.example.spacedemo.data.remote.SpaceShipRemoteDataSource
import com.example.spacedemo.data.remote.SpaceShipService
import com.example.spacedemo.data.repository.SpaceShipRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    /**
     * 1 Retrofit
     * 2 APIService
     * 3 Remote DataSource
     * 4 Local Datasource
     * 5 Repository
     *
     */


    /**
     * 1 Retrofit
     */
    @Provides
    fun provideGSON(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://api.spaceflightnewsapi.net/v3/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    //==============================================
    /**
     * 2 APIService
     */
    @Provides
    fun provideSpaceShipService(retrofit: Retrofit) : SpaceShipService
            = retrofit.create(SpaceShipService::class.java)

    //===============================================

    /**
     * 3 Remote DataSource
     */
    @Singleton
    @Provides
    fun provideSpaceShipRemoteDataSource(characterService: SpaceShipService)
            = SpaceShipRemoteDataSource(characterService)

    //=================================

    /**
     * 4 Local DataSource
     */

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context)
            = AppDataBase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideSpaceShipDAO(appDatabase: AppDataBase)
            = appDatabase.SpaceShipDAO()

    //=====================================

    /**
     * 5 Repository
     */

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: SpaceShipRemoteDataSource, localDataSource: SpaceShipDAO)
            = SpaceShipRepository(remoteDataSource, localDataSource)
}