package com.delighted.sdk.interactor

import com.delighted.sdk.repository.SurveyFlowRepository
import com.delighted.sdk.repository.SurveyFlowRepository.SurveyState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Returns the data repo's [Flow] that emitsi the current survey state
 */
internal class GetSurveyStateUseCase @Inject constructor(private val surveyFlowRepo: SurveyFlowRepository) :
    FlowUseCase<Unit, SurveyState> {
    override fun execute(params: Unit): Flow<SurveyState> {
        return surveyFlowRepo.surveyState
    }
}
