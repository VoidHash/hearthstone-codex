package com.voidhash.hearthstonecodex.presentation.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.voidhash.hearthstonecodex.R
import com.voidhash.hearthstonecodex.databinding.FragmentMainBinding
import com.voidhash.hearthstonecodex.framework.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private var fragmentMainBinding: FragmentMainBinding? = null
    private val viewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMainBinding.bind(view)
        fragmentMainBinding = binding

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
                Log.e("DBG", "info loaded")
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