package com.delighted.sdk.interactor

/**
 * An interface representing a synchronous executable operation.
 */
interface UseCase<in T, out R> {
    fun execute(params: T): R
}

operator fun <T, R> UseCase<T, R>.invoke(params: T) = execute(params)

operator fun <R> UseCase<Unit, R>.invoke() = execute(Unit)
