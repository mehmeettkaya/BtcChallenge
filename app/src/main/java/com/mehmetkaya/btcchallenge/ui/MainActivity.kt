package com.mehmetkaya.btcchallenge.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.mehmetkaya.btcchallenge.R
import com.mehmetkaya.btcchallenge.databinding.ActivityMainBinding
import com.mehmetkaya.btcchallenge.ui.MainUiEvent.ShowErrorMessage
import com.mehmetkaya.btcchallenge.utils.collectEvent
import com.mehmetkaya.btcchallenge.utils.collectState
import com.mehmetkaya.btcchallenge.utils.showError
import com.mehmetkaya.btcchallenge.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    private val viewModel: MainViewModel by viewModels()

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        collectState(viewModel.uiState, ::renderView)
        collectEvent(viewModel.uiEvent, ::handleEvent)
        initView()
    }

    override fun onSupportNavigateUp(): Boolean {
        navController.navigateUp()
        return super.onSupportNavigateUp()
    }

    private fun initView() = with(binding) {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.findNavController().apply {
            addOnDestinationChangedListener()
            setupActionBarWithNavController(this)
        }
    }

    private fun renderView(uiState: MainUiState) = with(binding) {
        progressLayout.isVisible = uiState.isLoading
    }

    private fun handleEvent(uiEvent: MainUiEvent) {
        when (uiEvent) {
            is ShowErrorMessage -> showError(uiEvent.message)
        }
    }

    private fun NavController.addOnDestinationChangedListener() {
        addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.pair_list_fragment) {
                supportActionBar?.hide()
            } else {
                supportActionBar?.show()
            }
        }
    }
}
