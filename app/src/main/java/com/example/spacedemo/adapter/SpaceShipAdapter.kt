package com.example.spacedemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.spacedemo.data.entities.SpaceShipItem
import com.example.spacedemo.databinding.RowSpaceshipBinding


class SpaceShipAdapter constructor(private val onClickListener: OnClickListener): ListAdapter<SpaceShipItem, SpaceShipAdapter.MyViewHolder>(SampleItemDiffCallback()){

    class OnClickListener(val clickListener: (SpaceShip: SpaceShipItem)-> Unit){
        //delegate
        fun onClick(SpaceShip: SpaceShipItem) = clickListener(SpaceShip)

    }


    class MyViewHolder (val itemBinding: RowSpaceshipBinding): RecyclerView.ViewHolder(itemBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = RowSpaceshipBinding.inflate(view,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val spaceShipList = getItem(position)

        holder.itemBinding.SpaceshiptextView.text = spaceShipList.title
        Glide.with(holder.itemView.context)
            .load(spaceShipList.imageUrl)
            .into(holder.itemBinding.SpaceshipimageView)
        holder.itemBinding.SpaceshipDescriptiontxt.text = spaceShipList.summary


        holder.itemView.setOnClickListener{
            onClickListener.onClick(spaceShipList)
        }


    }


}




class SampleItemDiffCallback : DiffUtil.ItemCallback<SpaceShipItem>() {
    override fun areItemsTheSame(oldItem: SpaceShipItem, newItem: SpaceShipItem): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: SpaceShipItem, newItem: SpaceShipItem): Boolean = oldItem == newItem

}