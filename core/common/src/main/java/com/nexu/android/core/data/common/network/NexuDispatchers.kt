package com.nexu.android.core.data.common.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val nexuDispatcher: NexuDispatchers)

enum class NexuDispatchers {
    IO
}
