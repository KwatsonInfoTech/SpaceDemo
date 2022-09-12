package com.example.spacedemo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spacedemo.adapter.SpaceShipAdapter
import com.example.spacedemo.databinding.FragmentFirstBinding
import com.example.spacedemo.rocketList.SpaceShipListVM
import com.example.spacedemo.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private  val viewModel: SpaceShipListVM by viewModels()
    private lateinit var adapter: SpaceShipAdapter


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startObservers()
        adapter = SpaceShipAdapter(SpaceShipAdapter.OnClickListener{
            Toast.makeText(activity, "${it.id}", Toast.LENGTH_LONG).show()
            val bundle = Bundle()
            bundle.putInt("ID", it.id)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,bundle)

        })
        binding?.recyclerView.adapter = adapter
        binding?.recyclerView.layoutManager = LinearLayoutManager(activity)



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    fun startObservers(){
        viewModel.repository.observe(viewLifecycleOwner){
            when(it.status){
                Resource.Status.SUCCESS ->{ //200
                    // pass the data to recyclerview
                   // Log.i("Dataaa", ""+ (it.data))
                    adapter.submitList(it.data)


                    // create a recyclerview adapter
                }

                Resource.Status.ERROR ->{
                    Log.i("Error", it.message.toString())
                }
                Resource.Status.LOADING -> {
                    // Progress Dialog
                }
            }
        }
    }

}


