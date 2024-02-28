package com.delighted.sdk.view.fragment

import android.content.Intent
import android.net.Uri
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.delighted.sdk.R
import com.delighted.sdk.core.launchWhenViewCreated
import com.delighted.sdk.core.viewBinding
import com.delighted.sdk.databinding.FragmentThankYouBinding
import com.delighted.sdk.view.theme.ThemeApply.Companion.applyDltTheme
import com.delighted.sdk.view.viewmodel.ThankYouViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ThankYouFragment : Fragment(R.layout.fragment_thank_you) {
    private val binding by viewBinding(FragmentThankYouBinding::bind)
    private val viewModel by viewModels<ThankYouViewModel>()

    init {
        launchWhenViewCreated {
            binding.baseThankYouText.text = viewModel.thankYouText
            binding.closeButton.setOnClickListener { viewModel.close() }
            viewModel.poweredByTextAndUrl.let { poweredBy ->
                binding.poweredByText.text = poweredBy.text
                binding.poweredByText.setOnClickListener {
                    val openUrl = Intent(Intent.ACTION_VIEW)
                    openUrl.data = Uri.parse(poweredBy.url)
                    startActivity(openUrl)
                }
            }
            viewModel.additionalMessage?.let {
                binding.additionalThankYouMessage.text = it
                binding.additionalThankYouMessage.isVisible = true
            }

            viewModel.linkTextAndUrl?.let { textAndUrl ->
                val (text, url) = textAndUrl
                binding.ctaButton.text = text
                binding.ctaButton.isVisible = true
                binding.ctaButton.setOnClickListener {
                    val openUrl = Intent(Intent.ACTION_VIEW)
                    openUrl.data = Uri.parse(url)
                    startActivity(openUrl)
                }
            }

            viewModel.surveyTheme.applyDltTheme {
                applyBackground(view = binding.root)
                applyAllTextViews(parentLayout = binding.thankYouScreen)
                applySecondaryThankYouText(view = binding.additionalThankYouMessage)
                applyCloseButton(closeButton = binding.closeButton)
                applyPrimaryButton(
                    button = binding.ctaButton,
                    context = this@ThankYouFragment.requireContext()
                )
                applyPoweredBy(view = binding.poweredByText)
            }

            viewModel.autoCloseDelay?.let { delaySeconds ->
                launch {
                    delay(delaySeconds * 1000L)
                    viewModel.close()
                }
            }

            requireActivity().onBackPressedDispatcher.addCallback(this@ThankYouFragment) {
                viewModel.close()
            }
        }
    }
}
