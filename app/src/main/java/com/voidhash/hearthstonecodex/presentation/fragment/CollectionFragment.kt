package com.voidhash.hearthstonecodex.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.voidhash.hearthstonecodex.R
import com.voidhash.hearthstonecodex.databinding.FragmentCollectionBinding
import com.voidhash.hearthstonecodex.framework.util.CollectionUtils
import com.voidhash.hearthstonecodex.framework.viewmodel.CollectionViewModel
import com.voidhash.hearthstonecodex.presentation.adapter.CollectionAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class CollectionFragment : Fragment(R.layout.fragment_collection) {

    private var fragmentCollectionFragment: FragmentCollectionBinding? = null
    private lateinit var binding: FragmentCollectionBinding
    private lateinit var collectionAdapter: CollectionAdapter
    private val viewModel: CollectionViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCollectionBinding.bind(view)
        fragmentCollectionFragment = binding

        collectionAdapter = CollectionAdapter(CollectionUtils.getCollectionDrawables())
        binding.rclCollection.layoutManager = GridLayoutManager(requireContext(), 2,
            GridLayoutManager.VERTICAL, false)
        binding.rclCollection.adapter = collectionAdapter

        viewModel.getCardsFromCollection("TITANS")
    }
}