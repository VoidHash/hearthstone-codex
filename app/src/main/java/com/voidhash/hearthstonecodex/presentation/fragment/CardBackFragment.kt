package com.voidhash.hearthstonecodex.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.voidhash.hearthstonecodex.R
import com.voidhash.hearthstonecodex.databinding.FragmentCardBackBinding
import com.voidhash.hearthstonecodex.framework.model.CardBackModel
import com.voidhash.hearthstonecodex.framework.util.ImageUtil
import com.voidhash.hearthstonecodex.framework.viewmodel.CardBackViewModel
import com.voidhash.hearthstonecodex.presentation.adapter.CardBackAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardBackFragment : Fragment(), CardBackAdapter.CardBackListener {

    private var fragmentCardBackFragment: FragmentCardBackBinding? = null
    private lateinit var binding: FragmentCardBackBinding
    private val viewModel: CardBackViewModel by viewModel()
    private lateinit var cardBackAdapter: CardBackAdapter
    private lateinit var cardBackModel: CardBackModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_card_back, container, false)
        fragmentCardBackFragment = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        this.cardBackModel = cardBackModel
        binding.model = this.cardBackModel
        binding.executePendingBindings()

        binding.layoutCardBackDetail.visibility = View.VISIBLE
        this.cardBackModel.name = cardBackModel.name
        this.cardBackModel.description = cardBackModel.description
        this.cardBackModel.howToGet = cardBackModel.howToGet
        cardBackModel.img?.let { ImageUtil.loadRemoteImage(binding.imgCardBackDetail, it) }
        binding.layoutCardBackDetail.setOnClickListener {
            binding.layoutCardBackDetail.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentCardBackFragment = null
    }
}