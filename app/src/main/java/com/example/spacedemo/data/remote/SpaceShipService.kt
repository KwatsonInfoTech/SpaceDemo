package com.example.spacedemo.data.remote

import com.example.spacedemo.data.entities.SpaceShip
import com.example.spacedemo.data.entities.SpaceShipItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SpaceShipService{

    // List of characters
    @GET("articles")
    suspend fun getAllSpaceShips() : Response<SpaceShip>

    @GET("articles/{ID}")
    suspend fun getSpaceshipByID(@Path("id")id:Int): Response<SpaceShipItem>


}