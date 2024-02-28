package com.delighted.sdk.view.fragment

import android.content.Intent
import android.net.Uri
import android.view.View.OnClickListener
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commitNow
import androidx.navigation.fragment.findNavController
import com.delighted.sdk.R
import com.delighted.sdk.core.expandHitTarget
import com.delighted.sdk.core.launchWhenViewCreated
import com.delighted.sdk.core.viewBinding
import com.delighted.sdk.databinding.FragmentAqBinding
import com.delighted.sdk.domain.response.AdditionalQuestionResponse.AdditionalQuestionType.FREE_RESPONSE
import com.delighted.sdk.domain.response.AdditionalQuestionResponse.AdditionalQuestionType.SCALE
import com.delighted.sdk.domain.response.AdditionalQuestionResponse.AdditionalQuestionType.SELECT_MANY
import com.delighted.sdk.domain.response.AdditionalQuestionResponse.AdditionalQuestionType.SELECT_ONE
import com.delighted.sdk.view.theme.ThemeApply.Companion.applyDltTheme
import com.delighted.sdk.view.viewmodel.AdditionalQuestionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdditionalQuestionFragment : Fragment(R.layout.fragment_aq) {
    private var binding by viewBinding(FragmentAqBinding::bind)
    private val viewModel by activityViewModels<AdditionalQuestionViewModel>()

    init {
        launchWhenViewCreated {
            binding.closeButton.setOnClickListener { viewModel.close() }
            binding.closeButton.expandHitTarget(10f)
            binding.aqBottom.primaryButton.setOnClickListener(onClickPrimaryButton)
            binding.aqBottom.secondaryButton.setOnClickListener(onClickSecondaryButton)
            binding.aqBottom.secondaryButton.text = viewModel.secondaryButtonText

            viewModel.poweredByTextAndUrl.let { poweredBy ->
                binding.aqBottom.poweredByText.text = poweredBy.text
                binding.aqBottom.poweredByText.setOnClickListener {
                    val openUrl = Intent(Intent.ACTION_VIEW)
                    openUrl.data = Uri.parse(poweredBy.url)
                    startActivity(openUrl)
                }
            }

            viewModel.questionIndex.observe(this@AdditionalQuestionFragment) {
                binding.aqBottom.primaryButton.text = viewModel.primaryButtonText
                binding.aqBottom.secondaryButton.isVisible = viewModel.showPreviousButton()
                binding.questionText.text = viewModel.questionText
                val instructionText = viewModel.questionInstructionText
                binding.questionInstructionText.text = instructionText
                binding.questionInstructionText.isVisible = instructionText != null

                viewModel.questionType?.let { questionType ->
                    val fragment = when (questionType) {
                        FREE_RESPONSE -> AqFreeFormFragment()
                        SCALE -> AqScaleFragment()
                        SELECT_ONE -> AqSelectFragment()
                        SELECT_MANY -> AqSelectFragment()
                    }
                    childFragmentManager.commitNow {
                        replace(R.id.question_fragment_container_view, fragment)
                    }
                }
            }

            requireActivity().onBackPressedDispatcher.addCallback(this@AdditionalQuestionFragment) {
                viewModel.previousQuestion()
            }

            viewModel.surveyTheme.applyDltTheme {
                applyCloseButton(closeButton = binding.closeButton)
                applyAllTextViews(parentLayout = binding.aqScreen)
                applyAqBottom(aqBottom = binding.aqBottom, context = requireContext())
                applyInstructionText(view = binding.questionInstructionText)
            }
        }
    }

    private val onClickPrimaryButton =
        OnClickListener {
            if (!viewModel.nextQuestion()) {
                findNavController().navigate(R.id.action_to_thank_you)
            }
        }

    private val onClickSecondaryButton =
        OnClickListener { viewModel.previousQuestion() }
}
