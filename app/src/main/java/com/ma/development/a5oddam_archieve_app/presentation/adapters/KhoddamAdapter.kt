package com.ma.development.a5oddam_archieve_app.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ma.development.a5oddam_archieve_app.databinding.ItemLayoutBinding
import com.ma.development.a5oddam_archieve_app.domain.model.Khadem

class KhoddamAdapter(private val onPhoneClick: OnPhoneClickListener) :
    ListAdapter<Khadem, KhoddamAdapter.MyViewHolder>(DiffCallback()) {

    class MyViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(khadem: Khadem) {
            binding.khadem = khadem
            if (khadem.phone == null)
                binding.callIcon.visibility = View.GONE
            else
                binding.callIcon.visibility = View.VISIBLE

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = MyViewHolder(binding)

        binding.callIcon.setOnClickListener {
            val khadem = getItem(holder.adapterPosition)
            onPhoneClick.call("0${khadem.phone}", phone2 = khadem.phone2, khadem.name!!)
        }
        return holder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(getItem(position))

    }


    interface OnPhoneClickListener {
        fun call(phone: String, phone2: Int?, receiverName: String)

    }
}