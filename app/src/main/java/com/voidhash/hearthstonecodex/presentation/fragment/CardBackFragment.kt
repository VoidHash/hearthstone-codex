package com.voidhash.hearthstonecodex.presentation.fragment

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.voidhash.hearthstonecodex.R
import com.voidhash.hearthstonecodex.databinding.FragmentCardBackBinding
import com.voidhash.hearthstonecodex.framework.model.CardBackModel
import com.voidhash.hearthstonecodex.framework.viewmodel.CardBackViewModel
import com.voidhash.hearthstonecodex.presentation.adapter.CardBackAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardBackFragment : Fragment(R.layout.fragment_card_back), CardBackAdapter.CardBackListener {

    private var fragmentCardBackFragment: FragmentCardBackBinding? = null
    private lateinit var binding: FragmentCardBackBinding
    private val viewModel: CardBackViewModel by viewModel()
    private lateinit var cardBackAdapter: CardBackAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCardBackBinding.bind(view)
        fragmentCardBackFragment = binding

        cardBackAdapter = CardBackAdapter(listOf())
        cardBackAdapter.listener = this
        binding.rclCardBack.layoutManager =
            GridLayoutManager(this.context, 3, GridLayoutManager.VERTICAL, false)
        binding.rclCardBack.adapter = cardBackAdapter

        viewModel.initApp()

        observerViewModel()
    }

    private fun observerViewModel() {

        viewModel.backCardsCollection.observe(viewLifecycleOwner) { collection ->
            collection.let {
                cardBackAdapter.updateList(it.toList())
            }
        }
    }

    override fun onCardBackSelected(cardBackModel: CardBackModel) {
        binding.layoutCardBackDetail.visibility = View.VISIBLE
        binding.txtCardName.text = cardBackModel.name
        binding.txtDescription.text = cardBackModel.description
        binding.txtHowToGet.text = cardBackModel.howToGet
        Glide
            .with(requireContext())
            .load(cardBackModel.img)
            .centerCrop()
            .placeholder(R.drawable.icon_loading)
            .into(binding.imgCardBackDetail);
        binding.layoutCardBackDetail.setOnClickListener {
            binding.layoutCardBackDetail.visibility = View.GONE
        }
    }
}