package com.example.spacedemo.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "SpaceShips")
data class SpaceShipItem(

    val featured: Boolean,

    @PrimaryKey
    val id: Int,
    val imageUrl: String,
    val newsSite: String,
    val publishedAt: String,
    val summary: String,
    val title: String,
    val updatedAt: String,
    val url: String
)