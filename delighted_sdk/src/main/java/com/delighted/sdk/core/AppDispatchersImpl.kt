package com.delighted.sdk.core

import com.delighted.sdk.AppDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class AppDispatchersImpl @Inject constructor() : AppDispatchers {
    override val io: CoroutineDispatcher get() = Dispatchers.IO
    override val mainThread: CoroutineDispatcher get() = Dispatchers.Main
    override val computation: CoroutineDispatcher get() = Dispatchers.Default
}
