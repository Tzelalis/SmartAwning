package com.example.smartawning.ui.addawning

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.smartawning.R
import com.example.smartawning.databinding.ItemDetectAwningBinding
import com.example.smartawning.domain.entity.DetectAwning
import com.example.smartawning.ui.diffutil.DETECT_AWNING_ITEM_DIFF_UTILS


class DetectAwningAdapter(private val listener: DetectAwningListener) :
    ListAdapter<DetectAwning, DetectAwningAdapter.DetectAwningViewHolder>(DETECT_AWNING_ITEM_DIFF_UTILS) {

    interface DetectAwningListener {
        fun onDetectAwningClick(detectAwning: DetectAwning)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetectAwningViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemDetectAwningBinding.inflate(layoutInflater, parent, false)
        return DetectAwningViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetectAwningViewHolder, position: Int) {
            holder.bindTo(getItem(position))
    }


    inner class DetectAwningViewHolder(private val binding: ItemDetectAwningBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(detectAwning: DetectAwning) {
            with(binding) {
                root.setOnClickListener { listener.onDetectAwningClick(detectAwning) }
            }
        }
    }
}