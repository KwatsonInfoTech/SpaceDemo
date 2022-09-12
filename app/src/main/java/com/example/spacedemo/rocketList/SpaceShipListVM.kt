package com.example.spacedemo.rocketList

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.spacedemo.data.repository.SpaceShipRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SpaceShipListVM @Inject constructor(repository: SpaceShipRepository):ViewModel() {


    val repository =  repository.getSpaceShips()




}