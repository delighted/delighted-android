package com.delighted.sdk.interactor.aq

import com.delighted.sdk.interactor.UseCase
import com.delighted.sdk.interactor.aq.GetAqPositionUseCase.AqPosition
import com.delighted.sdk.interactor.aq.GetAqPositionUseCase.AqPosition.FirstAq
import com.delighted.sdk.interactor.aq.GetAqPositionUseCase.AqPosition.InvalidPosition
import com.delighted.sdk.interactor.aq.GetAqPositionUseCase.AqPosition.LastAq
import com.delighted.sdk.interactor.aq.GetAqPositionUseCase.AqPosition.MiddleAq
import com.delighted.sdk.interactor.aq.GetAqPositionUseCase.AqPosition.OnlyAq
import com.delighted.sdk.interactor.invoke
import com.delighted.sdk.repository.SurveyFlowRepository
import javax.inject.Inject

internal class GetAqPositionUseCase @Inject constructor(
    private val surveyFlowRepo: SurveyFlowRepository,
    private val getAqsByGroupUseCase: GetAqsByGroupUseCase,
) :
    UseCase<String, AqPosition> {
    override fun execute(params: String): AqPosition =
        surveyFlowRepo.survey?.let {
            val additionalQuestions = getAqsByGroupUseCase()
            if (additionalQuestions.isEmpty()) return InvalidPosition
            val index = additionalQuestions.indexOfFirst { it.id == params }
            if (additionalQuestions.size == 1 && index == 0) return OnlyAq
            return when (index) {
                additionalQuestions.indices.last -> LastAq
                additionalQuestions.indices.first -> FirstAq
                in additionalQuestions.indices -> MiddleAq
                else -> InvalidPosition
            }
        } ?: InvalidPosition

    sealed class AqPosition {
        object FirstAq : AqPosition()
        object MiddleAq : AqPosition()
        object LastAq : AqPosition()
        object OnlyAq : AqPosition()
        object InvalidPosition : AqPosition()
    }
}
