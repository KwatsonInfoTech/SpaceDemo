package com.example.spacedemo.data.repository

import com.example.spacedemo.data.local.SpaceShipDAO
import com.example.spacedemo.data.remote.SpaceShipRemoteDataSource
import com.example.spacedemo.utils.performGetOperation
import javax.inject.Inject

class SpaceShipRepository @Inject constructor(
    private val remoteDataSource: SpaceShipRemoteDataSource,
    private val localDataSource: SpaceShipDAO) {


    fun getSpaceShips() = performGetOperation(
        databaseQuery = {localDataSource.getAllSpaceShips()},
        networkCall = {remoteDataSource.getSpaceShips()},
        saveCallResult = {localDataSource.insertAll(it)}

    )

    fun getSpaceShipDetailsData(id:Int) = performGetOperation(
        databaseQuery = {localDataSource.getSpaceShip(id)},
        networkCall = {remoteDataSource.getSpaceShipByID(id)},
        saveCallResult = {localDataSource.insetDetails(it)}

    )

}