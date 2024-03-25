package com.example.m12_mvvm

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.m12_mvvm.databinding.ActivityMainBinding
import com.example.m12_mvvm.ui.main.MainViewModel
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels { MainViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener {
            hideKeyboard()
            viewModel.onSearchButtonClick(binding.searchField.text.toString())
        }

        binding.searchField.doOnTextChanged { text, _, _, _ ->
            viewModel.onEditTextChanged(text?.length!!)
        }

        lifecycleScope.launchWhenResumed {
            viewModel.state.collect { state ->
                when (state) {
                    State.Idle -> {
                        binding.searchButton.isEnabled = false
                        binding.progressBar.isVisible = false
                    }

                    State.Loading -> {
                        binding.searchField.text = null
                        binding.searchButton.isEnabled = false
                        binding.progressBar.isVisible = true
                    }

                    is State.Finish -> {
                        val searchResult = getString(R.string.search_result, state.searchText)
                        binding.textView.text = searchResult
                        binding.progressBar.isVisible = false
                        binding.searchButton.isEnabled = false
                    }
                }
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.buttonVisibility.collect { buttonIsEnabled ->
                binding.searchButton.isEnabled = buttonIsEnabled
            }
        }
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

}
