package com.voidhash.hearthstonecodex.presentation.fragment

import android.os.Bundle
import android.text.style.LineBackgroundSpan.Standard
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.voidhash.hearthstonecodex.R
import com.voidhash.hearthstonecodex.databinding.FragmentMainBinding
import com.voidhash.hearthstonecodex.framework.util.CollectionUtils
import com.voidhash.hearthstonecodex.framework.viewmodel.MainViewModel
import com.voidhash.hearthstonecodex.presentation.adapter.StandardAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {

    private var fragmentMainBinding: FragmentMainBinding? = null
    private val viewModel: MainViewModel by viewModel()

    private lateinit var binding: FragmentMainBinding
    private lateinit var standardAdapter: StandardAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        fragmentMainBinding = binding

        standardAdapter = StandardAdapter(listOf())
        binding.rclStandard.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        binding.rclStandard.adapter = standardAdapter

        binding.buttonSearch.setOnClickListener {
            //TODO
        }

        binding.buttonCollection.setOnClickListener {
            //TODO
        }

        viewModel.initApp()
        observerViewModel()
    }

    private fun observerViewModel() {

        viewModel.hearthstoneInfo.observe(viewLifecycleOwner) { info ->
            info.let {
                val drawableList = CollectionUtils.getStandCollectionDrawable(info.standard)
                standardAdapter.updateList(drawableList)
            }
        }

        viewModel.backCardsCollection.observe(viewLifecycleOwner) { collection ->
            collection.let {
                Log.e("DBG", "deu certo")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentMainBinding = null
    }

}