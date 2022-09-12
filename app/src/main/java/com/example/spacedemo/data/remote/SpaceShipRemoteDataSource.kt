package com.example.spacedemo.data.remote

import javax.inject.Inject

class SpaceShipRemoteDataSource @Inject constructor(private val spaceShipService: SpaceShipService): BaseDataSource() {

    suspend fun getSpaceShips() = getResult { spaceShipService.getAllSpaceShips() }

    suspend fun getSpaceShipByID(id:Int) = getResult { spaceShipService.getSpaceshipByID(id)}


}