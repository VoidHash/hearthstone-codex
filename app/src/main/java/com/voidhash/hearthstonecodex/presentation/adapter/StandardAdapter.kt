package com.voidhash.hearthstonecodex.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.voidhash.hearthstonecodex.databinding.RecyclerStandardFormartBinding

class StandardAdapter(private var standardList: List<Int>)
    : RecyclerView.Adapter<StandardAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerStandardFormartBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return standardList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(standardList[position]) {
                val drawable = ContextCompat.getDrawable(context, this)
                binding.imgCollection.setImageDrawable(drawable)
            }
        }
    }

    fun updateList(newList: List<Int?>?) {
        standardList = newList as List<Int>
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: RecyclerStandardFormartBinding)
        : RecyclerView.ViewHolder(binding.root)
}