package com.voidhash.hearthstonecodex.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.voidhash.hearthstonecodex.R
import com.voidhash.hearthstonecodex.databinding.FragmentSearchBinding
import com.voidhash.hearthstonecodex.framework.model.CardBase
import com.voidhash.hearthstonecodex.framework.util.ImageUtil
import com.voidhash.hearthstonecodex.framework.viewmodel.CardViewModel
import com.voidhash.hearthstonecodex.presentation.adapter.CardAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : Fragment(R.layout.fragment_search), CardAdapter.CardListener {

    private var fragmentSearchFragment: FragmentSearchBinding? =null
    private lateinit var binding: FragmentSearchBinding
    private val viewModel: CardViewModel by viewModel()
    private lateinit var cardAdapter: CardAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)
        fragmentSearchFragment = binding
        val layoutManager = GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
        binding.rclCard.layoutManager = layoutManager
        cardAdapter = CardAdapter(listOf())
        cardAdapter.listener = this
        binding.rclCard.adapter = cardAdapter
        binding.layoutLookup.setOnClickListener {
            binding.layoutLookup.visibility = View.GONE
        }
        binding.btnSearch.setOnClickListener {
            if(!binding.edtSearch.text.isNullOrEmpty()) {
                viewModel.fetchCardSearched(binding.edtSearch.text.toString())
            }
        }

        viewModel.cardsCollection.observe(viewLifecycleOwner) { cardList ->
            cardAdapter.updateList(cardList)
        }
    }

    override fun onCardSelected(cardBase: CardBase) {
        binding.layoutLookup.visibility = View.VISIBLE
        cardBase.img?.let { ImageUtil.loadRemoteImage(binding.imgCardLookup, it) }
    }
}