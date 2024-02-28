package com.delighted.sdk.view.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.delighted.sdk.R
import com.delighted.sdk.core.launchWhenViewCreated
import com.delighted.sdk.core.viewBinding
import com.delighted.sdk.databinding.FragmentAqScaleBinding
import com.delighted.sdk.domain.response.SurveyTypeIdentifier
import com.delighted.sdk.interactor.aq.AnswerParams.AnswerScale
import com.delighted.sdk.view.question.SliderQuestion
import com.delighted.sdk.view.theme.ThemeApply.Companion.applyDltTheme
import com.delighted.sdk.view.viewmodel.AdditionalQuestionViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AqScaleFragment : Fragment(R.layout.fragment_aq_scale) {
    private var binding by viewBinding(FragmentAqScaleBinding::bind)
    private val viewModel by activityViewModels<AdditionalQuestionViewModel>()

    init {
        launchWhenViewCreated {
            val aqScaleSettings = viewModel.scaleQuestionSettings

            binding.widgetView.setSurveyType(
                // this is not an NPS survey but this type will give us numbered buttons or the slider
                surveyTypeIdentifier = SurveyTypeIdentifier.NPS,
                shouldUseSlider = viewModel.shouldUseSlider,
                choiceIndexes = aqScaleSettings.run { scaleMin..scaleMax }.toList(),
                theme = viewModel.surveyTheme,
            )
            binding.labelStart.text = aqScaleSettings.scaleMinLabel
            binding.labelEnd.text = aqScaleSettings.scaleMaxLabel

            binding.widgetView.getScale()?.let {
                val answer = when (val answer = viewModel.getAnswer()) {
                    is AnswerScale -> answer.value
                    else -> SliderQuestion.NO_VALUE_SELECTED
                }
                it.setValue(value = answer)
            }

            viewModel.surveyTheme.applyDltTheme {
                // set labels
                applyLabels(parentLayout = binding.aqScaleScreen)
            }

            launch {
                binding.widgetView.choiceEvent.collect {
                    viewModel.setAnswer(value = it)
                }
            }
        }
    }
}
