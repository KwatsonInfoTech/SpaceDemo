package com.example.spacedemo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.spacedemo.data.entities.SpaceShipItem
import com.example.spacedemo.databinding.FragmentSecondBinding
import com.example.spacedemo.spaceshipDetails.SpaceShipDetailsVM
import com.example.spacedemo.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val viewmodel: SpaceShipDetailsVM by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * handle incoming data to this fragment
         */
        arguments?.getInt("ID").let {
            // let keyword is a form of null checking, if null has been returned
            //  the code inside the let body wont execute
            Log.i("ID", "" + arguments?.getInt("ID"))

            viewmodel.startDetailsCall(it!!)

        }

        startObserverGetDetails()


    }

    private fun bindDetailsData(spaceship:SpaceShipItem){

        binding.DescriptionTextView.text = spaceship.summary
        binding.TitleTextView.text = spaceship.title
        Glide.with(binding.root)
            .load(spaceship.imageUrl)
            .transform(CircleCrop())
            .into(binding.imageView)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    fun startObserverGetDetails() {
        viewmodel.spaceShip.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> { //200
                    // pass the data to recyclerview
                    Log.i("Data", "" + (it.data))
                    bindDetailsData(it.data!!)

                    // create a recyclerview adapter
                }
                Resource.Status.ERROR -> {
                    Log.i("Error", it.message.toString())
                }
                Resource.Status.LOADING -> {
                    // Progress Dialog
                }
            }
        }
    }
}