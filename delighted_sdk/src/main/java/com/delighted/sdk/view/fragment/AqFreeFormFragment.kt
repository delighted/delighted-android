package com.delighted.sdk.view.fragment

import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.delighted.sdk.R
import com.delighted.sdk.core.launchWhenViewCreated
import com.delighted.sdk.core.showKeyboard
import com.delighted.sdk.core.viewBinding
import com.delighted.sdk.databinding.FragmentAqFreeFormBinding
import com.delighted.sdk.interactor.aq.AnswerParams.AnswerFreeForm
import com.delighted.sdk.view.theme.ThemeApply.Companion.applyDltTheme
import com.delighted.sdk.view.viewmodel.AdditionalQuestionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AqFreeFormFragment : Fragment(R.layout.fragment_aq_free_form) {
    private var binding by viewBinding(FragmentAqFreeFormBinding::bind)
    private val viewModel by activityViewModels<AdditionalQuestionViewModel>()

    init {
        launchWhenViewCreated {
            viewModel.surveyTheme.applyDltTheme {
                applyAllTextViews(parentLayout = binding.aqFreeFormScreen)
                applyTextInput(
                    inputArea = binding.freeFormAnswerInput,
                    border = binding.freeFormAnswerInputBorder
                )
            }
            val answer = when (val answer = viewModel.getAnswer()) {
                is AnswerFreeForm -> answer.content
                else -> ""
            }
            binding.freeFormAnswerInput.setText(answer)
            if (binding.freeFormAnswerInput.requestFocus()) {
                binding.freeFormAnswerInput.showKeyboard()
            }
            binding.freeFormAnswerInput.doAfterTextChanged {
                viewModel.setAnswer(value = binding.freeFormAnswerInput.text.toString())
            }
        }
    }
}
