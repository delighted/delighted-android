package com.delighted.sdk.interactor

import kotlinx.coroutines.flow.Flow

/**
 * An interface representing an executable operation that returns a [Flow].
 */
interface FlowUseCase<T, R> : UseCase<T, Flow<R>>
