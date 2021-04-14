package com.gaspardeelias.rssreaderdemo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.gaspardeelias.rssreaderdemo.databinding.FeedListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class FeedListFragment : Fragment() {

    private val viewModel: FeedListVM by viewModels()

    private var _binding: FeedListFragmentBinding? = null
    private val binding get() = _binding!!

    private var adapter : FeedListAdapter? = null

    init {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { uiState ->
                when (uiState) {
                    is ListStateUi.Error -> showError(uiState.msg)
                    is ListStateUi.Loading -> showLoading(uiState.loading)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FeedListFragmentBinding.inflate(inflater, container, false)
        binding.feedList.layoutManager = LinearLayoutManager(activity)
        adapter = FeedListAdapter(requireContext())
        binding.feedList.adapter = adapter
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.data.observe(viewLifecycleOwner) {
            adapter?.list = it
            adapter?.notifyDataSetChanged()
        }
        viewModel.loadList()
    }

    fun showError(msg: String) = Toast.makeText(activity, "Error: $msg", Toast.LENGTH_LONG).show()

    fun showLoading(loading: Boolean) {}

}