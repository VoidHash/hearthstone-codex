package com.voidhash.hearthstonecodex.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.voidhash.hearthstonecodex.R
import com.voidhash.hearthstonecodex.databinding.FragmentCardBinding
import com.voidhash.hearthstonecodex.framework.model.CardBase
import com.voidhash.hearthstonecodex.framework.util.CollectionUtils
import com.voidhash.hearthstonecodex.framework.util.ImageUtil
import com.voidhash.hearthstonecodex.framework.viewmodel.CardViewModel
import com.voidhash.hearthstonecodex.presentation.adapter.CardAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardFragment : Fragment(R.layout.fragment_card), CardAdapter.CardListener {

    private var fragmentCardFragment: FragmentCardBinding? = null
    private lateinit var binding: FragmentCardBinding
    private val viewModel: CardViewModel by viewModel()
    private lateinit var cardAdapter: CardAdapter
    private lateinit var bundleString: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCardBinding.bind(view)
        fragmentCardFragment = binding
        val layoutManager = GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
        binding.rclCollection.layoutManager = layoutManager
        cardAdapter = CardAdapter(listOf())
        cardAdapter.listener = this
        binding.rclCollection.adapter = cardAdapter
        binding.layoutLookup.setOnClickListener {
            binding.layoutLookup.visibility = View.GONE
        }
        arguments?.let {
            bundleString = CardFragmentArgs.fromBundle(it).collectionName
            viewModel.fetchCards(bundleString)
            observerViewModel()
        }
    }

    private fun observerViewModel() {
        viewModel.cardsCollection.observe(viewLifecycleOwner){ cardList ->
            cardAdapter.updateList(cardList)
            CollectionUtils.getBackgroundByName(bundleString)
                ?.let { resourceId -> binding.imgBackground.setImageResource(resourceId) }
            CollectionUtils.getLogoByName(bundleString)
                ?.let { resourceId -> binding.imgLogo.setImageResource(resourceId) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentCardFragment = null
    }

    override fun onCardSelected(cardBase: CardBase) {
        binding.layoutLookup.visibility = View.VISIBLE
        cardBase.img?.let { ImageUtil.loadRemoteImage(binding.imgCardLookup, it) }
    }
}