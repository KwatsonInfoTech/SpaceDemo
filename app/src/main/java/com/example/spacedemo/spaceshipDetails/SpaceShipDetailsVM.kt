package com.example.spacedemo.spaceshipDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.spacedemo.data.entities.SpaceShipItem
import com.example.spacedemo.data.repository.SpaceShipRepository
import com.example.spacedemo.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SpaceShipDetailsVM @Inject constructor
    (private val repository: SpaceShipRepository): ViewModel() {

    /**
     * pass the ID
     * response from backend
     */

    private val _id = MutableLiveData<Int>()
    private val _spaceship =_id.switchMap { id -> repository.getSpaceShipDetailsData(id) }

    val spaceShip : LiveData<Resource<SpaceShipItem>> = _spaceship

    /**
     * function to be used by fragment to pass id to viewmodel
     */

    fun startDetailsCall(id:Int){
        _id.value = id
    }
}