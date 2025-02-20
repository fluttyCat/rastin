package com.rastin.android.core.data.common.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val rastinDispatcher: RastinDispatchers)

enum class RastinDispatchers {
    IO
}
