package com.voidhash.hearthstonecodex.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.voidhash.hearthstonecodex.R
import com.voidhash.hearthstonecodex.databinding.RecyclerCardBackBinding
import com.voidhash.hearthstonecodex.framework.model.CardBackModel

class CardBackAdapter(private var cardBackList: List<CardBackModel>)
    : RecyclerView.Adapter<CardBackAdapter.ViewHolder>() {

    private lateinit var context: Context
    var listener: CardBackListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardBackAdapter.ViewHolder {
        context = parent.context
        val binding = RecyclerCardBackBinding
            .inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardBackAdapter.ViewHolder, position: Int) {
        with(holder) {
            with(cardBackList[position]) {
                Glide
                    .with(context)
                    .load(this.img)
                    .centerCrop()
                    .placeholder(R.drawable.icon_loading)
                    .into(binding.imgCardBack);

                binding.imgCardBack.setOnClickListener {
                    listener?.onCardBackSelected(this)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return cardBackList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<CardBackModel>) {
        cardBackList = newList
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding:RecyclerCardBackBinding)
        : RecyclerView.ViewHolder(binding.root)

    interface CardBackListener {
        fun onCardBackSelected(cardBackModel: CardBackModel)
    }
}