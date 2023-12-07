package com.voidhash.hearthstonecodex.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.voidhash.hearthstonecodex.databinding.RecyclerWildFormartBinding

class WildAdapter(private var wildList: List<Int>)
    : RecyclerView.Adapter<WildAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WildAdapter.ViewHolder {
        context = parent.context
        val binding = RecyclerWildFormartBinding
            .inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(wildList[position]) {
                val drawable = ContextCompat.getDrawable(context, this)
                binding.imgCollection.setImageDrawable(drawable)
            }
        }
    }

    override fun getItemCount(): Int {
        return wildList.size
    }

    fun updateList(newList: List<Int?>?) {
        wildList = newList as List<Int>
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: RecyclerWildFormartBinding)
        : RecyclerView.ViewHolder(binding.root)
}