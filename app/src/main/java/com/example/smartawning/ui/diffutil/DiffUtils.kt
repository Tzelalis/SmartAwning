package com.example.smartawning.ui.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.smartawning.data.awning.RemoteDetectAwning
import com.example.smartawning.domain.entity.AwningEntity
import com.example.smartawning.domain.entity.DetectAwning

val AWNING_ITEM_DIFF_UTILS = object : DiffUtil.ItemCallback<AwningEntity>() {
    override fun areItemsTheSame(oldItem: AwningEntity, newItem: AwningEntity): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: AwningEntity, newItem: AwningEntity): Boolean = oldItem == newItem
}

val DETECT_AWNING_ITEM_DIFF_UTILS = object : DiffUtil.ItemCallback<DetectAwning>() {
    override fun areItemsTheSame(oldItem: DetectAwning, newItem: DetectAwning): Boolean = oldItem.macAddress == newItem.macAddress
    override fun areContentsTheSame(oldItem: DetectAwning, newItem: DetectAwning): Boolean = oldItem == newItem
}