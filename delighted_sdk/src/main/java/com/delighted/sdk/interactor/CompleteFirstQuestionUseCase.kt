package com.delighted.sdk.interactor

import com.delighted.sdk.interactor.aq.GetAqsByGroupUseCase
import com.delighted.sdk.repository.SurveyFlowRepository
import javax.inject.Inject

/**
 * Alerts the data layer that the first question has been completed
 */
internal class CompleteFirstQuestionUseCase @Inject constructor(
    private val surveyFlowRepo: SurveyFlowRepository,
    private val getAqsByGroupUseCase: GetAqsByGroupUseCase,

) :
    UseCase<Unit, Unit> {
    override fun execute(params: Unit) {
        surveyFlowRepo.completedFirstQuestion(
            isAdditionalQuestions = getAqsByGroupUseCase().isNotEmpty()
        )
    }
}
