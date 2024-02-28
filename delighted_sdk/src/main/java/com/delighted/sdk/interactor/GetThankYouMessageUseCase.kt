package com.delighted.sdk.interactor

import com.delighted.sdk.domain.fromResponse
import com.delighted.sdk.domain.response.SurveyRequestResponse
import com.delighted.sdk.interactor.GetThankYouMessageUseCase.ThankYouMessage
import com.delighted.sdk.repository.SurveyFlowRepository
import javax.inject.Inject

/**
 * Retrieve thank you message and behavior data from survey
 */
internal class GetThankYouMessageUseCase @Inject constructor(
    private val surveyFlowRepo: SurveyFlowRepository,
    private val getGroupForUserScoreUseCase: GetGroupForUserScoreUseCase,
) :
    UseCase<Unit, ThankYouMessage> {
    override fun execute(params: Unit): ThankYouMessage {
        val survey: SurveyRequestResponse =
            surveyFlowRepo.survey ?: return ThankYouMessage("")
        val thankYou =
            fromResponse(thankYouResponse = survey.thankYou)

        val baseMessage =
            ThankYouMessage(baseMessage = thankYou.text, autoCloseDelay = thankYou.autoCloseDelay)
        val userGroup = getGroupForUserScoreUseCase()

        return if (userGroup == null) {
            baseMessage
        } else {
            thankYou.thankYouMessageGroups.find { it.name == userGroup.name }
                ?.let { thankYouMessageGroup ->
                    val linkTextAndUrl =
                        if (thankYouMessageGroup.linkText == null && thankYouMessageGroup.linkUrl == null) {
                            null
                        } else {
                            Pair(
                                thankYouMessageGroup.linkText.orEmpty(),
                                thankYouMessageGroup.linkUrl.orEmpty()
                            )
                        }
                    baseMessage.copy(
                        additionalMessage = thankYouMessageGroup.messageText,
                        linkTextAndUrl = linkTextAndUrl,
                    )
                } ?: baseMessage
        }
    }

    data class ThankYouMessage(
        val baseMessage: String,
        val additionalMessage: String? = null,
        val linkTextAndUrl: Pair<String, String>? = null,
        val autoCloseDelay: Int? = null,
    )
}
