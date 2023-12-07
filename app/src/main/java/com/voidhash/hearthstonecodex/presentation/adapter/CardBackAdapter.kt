package com.voidhash.hearthstonecodex.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.voidhash.hearthstonecodex.R
import com.voidhash.hearthstonecodex.databinding.RecyclerCardsBackBinding
import com.voidhash.hearthstonecodex.framework.model.CardBackModel

class CardBackAdapter(private var cardBackList: List<CardBackModel>)
    : RecyclerView.Adapter<CardBackAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardBackAdapter.ViewHolder {
        context = parent.context
        val binding = RecyclerCardsBackBinding
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
                    .placeholder(R.drawable.icon_cards_back)
                    .into(binding.imgCardBack);
            }
        }
    }

    override fun getItemCount(): Int {
        return cardBackList.size
    }

    fun updateList(newList: List<CardBackModel>) {
        cardBackList = newList
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding:RecyclerCardsBackBinding)
        : RecyclerView.ViewHolder(binding.root)
}