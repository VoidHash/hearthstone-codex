package com.voidhash.hearthstonecodex.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.voidhash.hearthstonecodex.databinding.RecyclerCollectionBinding
import com.voidhash.hearthstonecodex.framework.util.CollectionUtils

class CollectionAdapter(private var collectionList: List<Int>)
    : RecyclerView.Adapter<CollectionAdapter.ViewHolder>() {

    private lateinit var context: Context
    var listener: CollectionListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    : CollectionAdapter.ViewHolder {
        this.context = parent.context
        val binding = RecyclerCollectionBinding
                .inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CollectionAdapter.ViewHolder, position: Int) {
        with(holder) {
            with(collectionList[position]) {
                val drawable = androidx.core.content.ContextCompat.getDrawable(context, this)
                binding.imgCollection.setImageDrawable(drawable)
                binding.imgCollection.setOnClickListener {
                    CollectionUtils.getCollectionNameByDrawable(this)
                        ?.let { listener?.onCollectionSelected(it) }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return collectionList.size
    }

    fun updateList(newList: List<Int?>?) {
        collectionList = newList as List<Int>
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: RecyclerCollectionBinding)
        : RecyclerView.ViewHolder(binding.root)

    interface CollectionListener {
        fun onCollectionSelected(collection: String)
    }
}