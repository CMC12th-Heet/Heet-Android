package org.heet.domain.interfaces

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherInterface {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}
