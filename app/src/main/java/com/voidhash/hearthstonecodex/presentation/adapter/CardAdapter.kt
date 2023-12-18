package com.voidhash.hearthstonecodex.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.voidhash.hearthstonecodex.R
import com.voidhash.hearthstonecodex.databinding.RecyclerCardBackBinding
import com.voidhash.hearthstonecodex.framework.model.CardBase
import com.voidhash.hearthstonecodex.framework.util.ImageUtil

class CardAdapter(private var cardList: List<CardBase>)
    : RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    private lateinit var context: Context
    var listener: CardListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardAdapter.ViewHolder {
        context = parent.context
        val binding = RecyclerCardBackBinding
            .inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardAdapter.ViewHolder, position: Int) {
        with(holder) {
            with(cardList[position]) {
                this.img?.let { ImageUtil.loadRemoteImage(binding.imgCardBack, it) }

                binding.imgCardBack.setOnClickListener {
                    listener?.onCardSelected(this)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<CardBase>) {
        cardList = newList
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: RecyclerCardBackBinding)
        : RecyclerView.ViewHolder(binding.root)

    interface CardListener {
        fun onCardSelected(cardBase: CardBase)
    }
}