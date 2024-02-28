package com.delighted.sdk.view.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.delighted.sdk.R
import com.delighted.sdk.core.launchWhenViewCreated
import com.delighted.sdk.core.viewBinding
import com.delighted.sdk.databinding.FragmentAqSelectBinding
import com.delighted.sdk.domain.response.AdditionalQuestionResponse.AdditionalQuestionType.SELECT_ONE
import com.delighted.sdk.interactor.aq.AnswerParams.AnswerSelect
import com.delighted.sdk.view.fragment.SelectableAdapter.SelectableItem
import com.delighted.sdk.view.theme.ThemeApply.Companion.applyDltTheme
import com.delighted.sdk.view.theme.ThemeApply.Companion.themeApply
import com.delighted.sdk.view.viewmodel.AdditionalQuestionViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AqSelectFragment : Fragment(R.layout.fragment_aq_select) {
    private var binding by viewBinding(FragmentAqSelectBinding::bind)
    private val viewModel by activityViewModels<AdditionalQuestionViewModel>()

    init {
        launchWhenViewCreated {
            val selectableAdapter =
                SelectableAdapter(
                    isSelectOne = viewModel.questionType == SELECT_ONE,
                    themeApply = viewModel.surveyTheme.themeApply()
                )
            binding.selectRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = selectableAdapter
            }

            val selectedIds = when (val answer = viewModel.getAnswer()) {
                is AnswerSelect -> answer.list
                else -> emptyList()
            }
            selectableAdapter.submitList(
                items = viewModel.questionOptions.map {
                    SelectableItem(
                        id = it.id,
                        labelText = it.text,
                        isSelected = it.id in selectedIds
                    )
                }
            )
            viewModel.surveyTheme.applyDltTheme {
                applyAllTextViews(parentLayout = binding.aqSelectScreen)
            }
            launch {
                selectableAdapter.answerData.collect { viewModel.setAnswer(answers = it) }
            }
        }
    }
}
