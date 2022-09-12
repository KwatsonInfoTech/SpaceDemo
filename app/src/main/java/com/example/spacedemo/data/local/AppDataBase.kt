package com.example.spacedemo.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.spacedemo.data.entities.SpaceShip
import com.example.spacedemo.data.entities.SpaceShipItem

@Database(entities = [SpaceShipItem::class], version = 1)
abstract class AppDataBase: RoomDatabase() {

    abstract fun SpaceShipDAO(): SpaceShipDAO

    companion object{

        @Volatile private var INSTANCE: AppDataBase?= null

        fun getDatabase(context: Context) : AppDataBase =
            INSTANCE ?: synchronized(this){
                INSTANCE?: buildDatabase(context).also{ INSTANCE = it}}

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDataBase::class.java, "newSpaceShipDB2")
                .fallbackToDestructiveMigration()
                .build()

    }



}