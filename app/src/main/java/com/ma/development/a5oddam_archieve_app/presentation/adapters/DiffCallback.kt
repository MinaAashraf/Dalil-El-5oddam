package com.ma.development.a5oddam_archieve_app.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.ma.development.a5oddam_archieve_app.domain.model.Khadem

class DiffCallback : DiffUtil.ItemCallback<Khadem>() {
    override fun areItemsTheSame(oldItem: Khadem, newItem: Khadem): Boolean = oldItem.key == newItem.key

    override fun areContentsTheSame(oldItem: Khadem, newItem: Khadem): Boolean = oldItem == newItem
}