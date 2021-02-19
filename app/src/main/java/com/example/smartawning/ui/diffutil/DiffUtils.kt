package com.example.smartawning.ui.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.smartawning.domain.entity.AwningEntity
import com.example.smartawning.ui.awning.AwningItem

val AWNING_ITEM_DIFF_UTILS = object : DiffUtil.ItemCallback<AwningEntity>() {
    override fun areItemsTheSame(oldItem: AwningEntity, newItem: AwningEntity): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: AwningEntity, newItem: AwningEntity): Boolean = oldItem == newItem
}