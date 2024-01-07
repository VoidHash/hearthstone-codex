package com.voidhash.hearthstonecodex.presentation.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.voidhash.hearthstonecodex.R
import com.voidhash.hearthstonecodex.databinding.FragmentCollectionBinding
import com.voidhash.hearthstonecodex.framework.util.CollectionUtils
import com.voidhash.hearthstonecodex.framework.viewmodel.CollectionViewModel
import com.voidhash.hearthstonecodex.presentation.adapter.CollectionAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class CollectionFragment : Fragment(R.layout.fragment_collection),
    CollectionAdapter.CollectionListener {

    private var fragmentCollectionFragment: FragmentCollectionBinding? = null
    private lateinit var binding: FragmentCollectionBinding
    private lateinit var collectionAdapter: CollectionAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCollectionBinding.bind(view)
        fragmentCollectionFragment = binding

        collectionAdapter = CollectionAdapter(CollectionUtils.getCollectionDrawables())
        collectionAdapter.listener = this
        binding.rclCollection.layoutManager = GridLayoutManager(requireContext(), 2,
            GridLayoutManager.VERTICAL, false)
        binding.rclCollection.adapter = collectionAdapter
    }

    override fun onCollectionSelected(collection: String) {
        val action = CollectionFragmentDirections.actionCollectionFragmentToCardFragment(collection)
        findNavController().navigate(action)
    }
}