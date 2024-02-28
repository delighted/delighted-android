package com.delighted.sdk.interactor

import com.delighted.sdk.network.PusherService
import javax.inject.Inject

/**
 * Sends a notification to the Pusher service that a user is or has very recently made input
 * on a text input question.
 */
internal class NotifyUserActivityUseCase @Inject constructor(
    private val pusherService: PusherService,
) :
    UseCase<Unit, Unit> {
    override fun execute(params: Unit) {
        pusherService.sendTypingNotification()
    }
}
