package com.voidhash.hearthstonecodex.presentation.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.voidhash.hearthstonecodex.R
import com.voidhash.hearthstonecodex.databinding.FragmentMainBinding
import com.voidhash.hearthstonecodex.framework.util.CollectionUtils
import com.voidhash.hearthstonecodex.framework.viewmodel.MainViewModel
import com.voidhash.hearthstonecodex.presentation.adapter.CardBackAdapter
import com.voidhash.hearthstonecodex.presentation.adapter.StandardAdapter
import com.voidhash.hearthstonecodex.presentation.adapter.WildAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {

    private var fragmentMainBinding: FragmentMainBinding? = null
    private val viewModel: MainViewModel by viewModel()

    private lateinit var binding: FragmentMainBinding
    private lateinit var standardAdapter: StandardAdapter
    private lateinit var wildAdapter: WildAdapter
    private lateinit var cardBackAdapter: CardBackAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        fragmentMainBinding = binding

        standardAdapter = StandardAdapter(listOf())
        binding.rclStandard.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        binding.rclStandard.adapter = standardAdapter

        wildAdapter = WildAdapter(listOf())
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
            //TODO
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
                val standardList = CollectionUtils.getCollectionDrawable(info.standard)
                standardAdapter.updateList(standardList)
                val wildList = CollectionUtils.getCollectionDrawable(info.wild)
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