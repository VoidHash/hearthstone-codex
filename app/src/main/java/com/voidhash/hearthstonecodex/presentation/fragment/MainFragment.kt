package com.voidhash.hearthstonecodex.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.voidhash.hearthstonecodex.R
import com.voidhash.hearthstonecodex.databinding.FragmentMainBinding
import com.voidhash.hearthstonecodex.framework.util.CollectionUtils
import com.voidhash.hearthstonecodex.framework.viewmodel.MainViewModel
import com.voidhash.hearthstonecodex.presentation.adapter.CardBackAdapter
import com.voidhash.hearthstonecodex.presentation.adapter.CollectionAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {

    private var fragmentMainBinding: FragmentMainBinding? = null
    private val viewModel: MainViewModel by viewModel()

    private lateinit var binding: FragmentMainBinding
    private lateinit var standardAdapter: CollectionAdapter
    private lateinit var wildAdapter: CollectionAdapter
    private lateinit var cardBackAdapter: CardBackAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        fragmentMainBinding = binding

        standardAdapter = CollectionAdapter(listOf())
        binding.rclStandard.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        binding.rclStandard.adapter = standardAdapter

        wildAdapter = CollectionAdapter(listOf())
        binding.rclWild.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        binding.rclWild.adapter = wildAdapter

        cardBackAdapter = CardBackAdapter(listOf())
        binding.rclCardBack.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        binding.rclCardBack.adapter = cardBackAdapter

        binding.buttonSearch.setOnClickListener {
            //TODO
        }

        binding.buttonCollection.setOnClickListener {
            this.findNavController().navigate(R.id.action_mainFragment_to_collectionFragment)
        }

        binding.txtListCardsBack.setOnClickListener {
            this.findNavController().navigate(R.id.action_mainFragment_to_cardBackFragment)
        }

        viewModel.initApp()
        observerViewModel()
    }

    private fun observerViewModel() {

        viewModel.hearthstoneInfo.observe(viewLifecycleOwner) { info ->
            info.let {
                val standardList = CollectionUtils.getCollectionDrawableByName(info.standard).reversed()
                standardAdapter.updateList(standardList)
                val wildList = CollectionUtils.getCollectionDrawableByName(info.wild)
                wildAdapter.updateList(wildList)
            }
        }

        viewModel.backCardsCollection.observe(viewLifecycleOwner) { collection ->
            collection.let {
                cardBackAdapter.updateList(it.toList().reversed().take(20))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentMainBinding = null
    }

}