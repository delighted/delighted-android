package com.delighted.sdk.interactor

import com.delighted.sdk.repository.SurveyFlowRepository
import javax.inject.Inject

/**
 * Retrieve the survey token. Throw an exception if it does not exist.
 */
internal class GetTokenFromSurveyUseCase @Inject constructor(private val surveyFlowRepo: SurveyFlowRepository) :
    UseCase<Unit, String> {
    override fun execute(params: Unit): String {
        surveyFlowRepo.getSurveyToken().onSuccess { return it }
        error("No survey token present, has a survey been set?")
    }
}
