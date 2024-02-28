package com.delighted.sdk.view.activity

import android.os.Bundle
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.delighted.sdk.R
import com.delighted.sdk.core.viewBinding
import com.delighted.sdk.databinding.ActivityDelightedBinding
import com.delighted.sdk.view.viewmodel.DelightedViewModel
import com.delighted.sdk.view.viewmodel.DelightedViewModel.ViewState.Finish
import com.delighted.sdk.view.viewmodel.DelightedViewModel.ViewState.ShowAdditionalQuestions
import com.delighted.sdk.view.viewmodel.DelightedViewModel.ViewState.ShowFirstQuestion
import com.delighted.sdk.view.viewmodel.DelightedViewModel.ViewState.ShowThankYou
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DelightedActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityDelightedBinding::inflate)
    private val viewModel by viewModels<DelightedViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        overridePendingTransition(R.anim.fade_in, 0)
        setContentView(binding.root)

        viewModel.run {
            viewState.observe(this@DelightedActivity) { viewState ->
                when (viewState) {
                    ShowFirstQuestion -> { /* first popup is already default home of nav graph */
                    }
                    ShowAdditionalQuestions ->
                        findNavController(R.id.nav_host_fragment)
                            .navigate(R.id.action_StartModal_to_AdditionalQuestionScreen)
                    ShowThankYou ->
                        findNavController(R.id.nav_host_fragment).navigate(R.id.action_to_thank_you)
                    Finish -> {
                        finish()
                        val exitTransition =
                            when (findNavController(R.id.nav_host_fragment).currentDestination?.id) {
                                R.id.StartModal -> R.anim.fade_out
                                else -> R.anim.slide_down
                            }

                        overridePendingTransition(0, exitTransition)
                    }
                }
            }
        }

        onBackPressedDispatcher.addCallback(this) {
            if (!findNavController(R.id.nav_host_fragment).popBackStack()) {
                finish()
            }
        }
    }
}
