package com.example.spacedemo.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.spacedemo.data.entities.SpaceShipItem

@Dao
interface SpaceShipDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(result:List<SpaceShipItem>)

    // displays items
    @Query("SELECT * FROM SpaceShips")
    fun getAllSpaceShips(): LiveData<List<SpaceShipItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insetDetails(spaceShip: SpaceShipItem)

    @Query("SELECT * FROM SpaceShips WHERE id = :id")

    fun getSpaceShip(id:Int): LiveData<SpaceShipItem>
}