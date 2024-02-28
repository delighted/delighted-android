package com.delighted.sdk.view.fragment

import android.animation.LayoutTransition
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.doOnLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.delighted.sdk.R
import com.delighted.sdk.core.dipToPx
import com.delighted.sdk.core.expandHitTarget
import com.delighted.sdk.core.launchWhenViewCreated
import com.delighted.sdk.core.showKeyboard
import com.delighted.sdk.core.viewBinding
import com.delighted.sdk.databinding.FragmentStartModalBinding
import com.delighted.sdk.databinding.ViewStartSurveyBinding
import com.delighted.sdk.interactor.FirstQuestionTextOptionsUseCase.FirstQuestionTextOptions.None
import com.delighted.sdk.interactor.FirstQuestionTextOptionsUseCase.FirstQuestionTextOptions.ThreeLabels
import com.delighted.sdk.interactor.FirstQuestionTextOptionsUseCase.FirstQuestionTextOptions.TwoLabels
import com.delighted.sdk.interactor.GetSurveyDisplayUseCase.SurveyDisplay.Card
import com.delighted.sdk.view.theme.ThemeApply.Companion.applyDltTheme
import com.delighted.sdk.view.viewmodel.StartSurveyViewModel
import com.delighted.sdk.view.viewmodel.StartSurveyViewModel.ViewState.Expanded
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@SuppressLint("ClickableViewAccessibility")
@AndroidEntryPoint
class StartModalFragment : Fragment(R.layout.fragment_start_modal) {
    private val binding by viewBinding(FragmentStartModalBinding::bind)
    private lateinit var modalContentBinding: ViewStartSurveyBinding

    private val viewModel by viewModels<StartSurveyViewModel>()

    init {
        launchWhenViewCreated {
            modalContentBinding = ViewStartSurveyBinding.bind(binding.root)

            // approximate dialog box behavior
            binding.firstQuestionScreen.setOnTouchListener(dismissOnOutsideTouch)

            // adjust modal vs. card size
            if (viewModel.modalOrCard == Card) {
                binding.startSurvey.apply {
                    (layoutParams as FrameLayout.LayoutParams).apply {
                        marginStart = 0
                        marginEnd = 0
                    }
                }
            }

            // corner radius
            binding.startSurvey.background =
                GradientDrawable().apply {
                    cornerRadius = viewModel.surveyTheme.containerCornerRadius.toFloat()
                    setColor(Color.WHITE)
                }

            with(modalContentBinding) {
                closeButton.setOnClickListener { viewModel.close() }
                closeButton.expandHitTarget(10f)

                firstQuestionText.text = viewModel.firstQuestionPrompt
                firstQuestionView.setSurveyType(
                    surveyTypeIdentifier = viewModel.surveyType,
                    shouldUseSlider = viewModel.shouldUseSlider,
                    choiceIndexes = viewModel.choiceIndexes,
                    theme = viewModel.surveyTheme,
                )

                when (val options = viewModel.firstQuestionTextOptions) {
                    is ThreeLabels -> {
                        threeLabels.isVisible = true
                        twoLabels.isGone = true
                        threeLabelStart.text = options.start
                        threeLabelMiddle.text = options.middle
                        threeLabelEnd.text = options.end
                    }
                    is TwoLabels -> {
                        twoLabels.isVisible = true
                        threeLabels.isGone = true
                        twoLabelStart.text = options.notLikely
                        twoLabelEnd.text = options.likely
                    }
                    None -> {
                        threeLabels.isGone = true
                        twoLabels.isGone = true
                    }
                }
                with(actionButton) {
                    text = viewModel.actionButtonLabel
                    setOnClickListener {
                        viewModel.answerFirstQuestion(comment = answerInput.text.toString())
                        viewModel.completeFirstQuestion()
                    }
                }

                viewModel.poweredByTextAndUrl.let { poweredBy ->
                    poweredByText.text = poweredBy.text
                    poweredByText.setOnClickListener {
                        val openUrl = Intent(Intent.ACTION_VIEW)
                        openUrl.data = Uri.parse(poweredBy.url)
                        startActivity(openUrl)
                    }
                }

                applyTheme()
            }

            launch {
                modalContentBinding.firstQuestionView.choiceEvent.collect { choice ->
                    viewModel.answerFirstQuestion(index = choice)
                }
            }

            viewModel.viewState.observe(this@StartModalFragment) { viewState ->
                when (viewState) {
                    Expanded -> {
                        onBeforeExpand()
                        if (viewModel.modalOrCard == Card) {
                            binding.startSurvey.translationY = 0f
                        }
                        binding.startSurvey.apply {
                            layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
                            (layoutParams as FrameLayout.LayoutParams).apply {
                                height = ConstraintLayout.LayoutParams.MATCH_PARENT
                                setMargins(0, 0, 0, 0)
                                setPadding(0, 0, 0, 0)
                            }
                            viewModel.surveyTheme.applyDltTheme {
                                applyBackground(this@apply)
                            }
                            requestLayout()
                        }
                        modalContentBinding.answerInput.doAfterTextChanged {
                            viewModel.notifyUserActivity()
                        }
                    }
                }
            }
            viewModel.commentPrompt.observe(this@StartModalFragment) { commentPrompt ->
                modalContentBinding.pleaseTellMoreText.text = commentPrompt
            }

            binding.root.doOnLayout {
                if (viewModel.modalOrCard == Card) {
                    val distance =
                        binding.root.bottom - binding.startSurvey.top - getBottomOfLabel()
                    binding.startSurvey.translationY = distance.toFloat()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.close()
    }

    private fun onBeforeExpand() {
        with(modalContentBinding) {
            firstQuestionText.isGone = true
            twoLabels.isGone = true
            threeLabels.isGone = true

            pleaseTellMoreText.isVisible = true
            answerInput.isVisible = true
            answerInputBorder.isVisible = true
            actionButton.isVisible = true
            poweredByText.isVisible = true

            // Focus on input
            if (answerInput.requestFocus()) {
                answerInput.showKeyboard()
            }
        }
    }

    private fun applyTheme() {
        viewModel.surveyTheme.applyDltTheme {
            with(modalContentBinding) {
                // set text color on anything that has text color
                applyAllTextViews(parentLayout = binding.firstQuestionScreen)
                // set labels
                applyLabels(parentLayout = modalContentBinding.twoLabels)
                applyLabels(parentLayout = modalContentBinding.threeLabels)
                // root background
                applyBackgroundTint(binding.startSurvey)
                // action (primary) button
                applyPrimaryButton(
                    button = actionButton,
                    context = this@StartModalFragment.requireContext()
                )
                // answer input field
                applyTextInput(
                    inputArea = answerInput,
                    border = answerInputBorder
                )
                // close button
                applyCloseButton(closeButton = closeButton)
                // powered by
                applyPoweredBy(view = poweredByText)
            }
        }
    }

    private fun getBottomOfLabel(): Int {
        // if survey type has no labels to display, measure from bottom of question widget
        // otherwise measure to bottom of whatever labels are displayed
        var height = when (viewModel.firstQuestionTextOptions) {
            None -> modalContentBinding.firstQuestionView.bottom
            is TwoLabels -> modalContentBinding.twoLabels.bottom
            is ThreeLabels -> modalContentBinding.threeLabels.bottom
        }
        height += requireContext().dipToPx(BOTTOM_MARGIN_FOR_UNEXPANDED)
        return height
    }

    private val dismissOnOutsideTouch = View.OnTouchListener { _, event ->
        val startRect = Rect()
        binding.startSurvey.getGlobalVisibleRect(startRect)
        if (event.action == MotionEvent.ACTION_DOWN &&
            !startRect.contains(event.rawX.toInt(), event.rawY.toInt())
        ) {
            viewModel.close()
        }
        false
    }

    companion object {
        private const val BOTTOM_MARGIN_FOR_UNEXPANDED = 16f
    }
}
