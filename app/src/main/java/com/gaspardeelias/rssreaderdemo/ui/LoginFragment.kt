package com.gaspardeelias.rssreaderdemo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.gaspardeelias.rssreaderdemo.R
import com.gaspardeelias.rssreaderdemo.databinding.LoginFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginVM by viewModels()

    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!

    init {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { uiState ->
                when (uiState) {
                    is LoginStateUi.Success -> showNext()
                    is LoginStateUi.Error -> showError(uiState.msg)
                    is LoginStateUi.Loading -> showLoading(uiState.loading)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        binding.login.setOnClickListener {
            binding.user.clearFocus()
            binding.pass.clearFocus()
            viewModel.login(
                binding.user.text.toString(),
                binding.pass.text.toString()
            )
        }
        binding.signup.setOnClickListener {
            viewModel.register(binding.user.text.toString(), binding.user.text.toString())
        }
    }

    private fun showNext() {
        parentFragmentManager.beginTransaction().replace(R.id.fragment_container, FeedListFragment())
            .commit()
    }

    private fun showError(msg: String?) {
        binding.user.clearFocus()
        binding.pass.clearFocus()
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
    }

    private fun showLoading(loading: Boolean) {
        binding.loading.isVisible = loading
    }

}