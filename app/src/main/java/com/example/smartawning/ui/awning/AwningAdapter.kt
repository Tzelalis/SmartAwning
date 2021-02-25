package com.example.smartawning.ui.awning

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.smartawning.databinding.ItemAwningBinding
import com.example.smartawning.domain.entity.AwningEntity
import com.example.smartawning.ui.diffutil.AWNING_ITEM_DIFF_UTILS
import okhttp3.internal.threadName

class AwningAdapter(private val listener : AwningListener) : ListAdapter<AwningEntity, AwningAdapter.AwningViewHolder>(AWNING_ITEM_DIFF_UTILS) {

    interface AwningListener{
        fun onAwningItemClick(awning: AwningEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AwningViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAwningBinding.inflate(layoutInflater, parent, false)
        return AwningViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AwningViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    inner class AwningViewHolder(private val binding : ItemAwningBinding) : RecyclerView.ViewHolder(binding.root){
        fun bindTo(item : AwningEntity)    {
            with(binding)   {
                nameTextView.text = item.name

                root.setOnClickListener { listener.onAwningItemClick(item) }
            }
        }
    }
}