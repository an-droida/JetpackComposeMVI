package com.androida.handlers

import com.androida.eventbus.utils.Event
import com.androida.eventbus.utils.IEventBus
import com.androida.handlers.ext.log
import com.androida.handlers.ext.logMessage
import dagger.Lazy
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SideEffectHandler @Inject constructor() {

    @Inject
    lateinit var eventBus: Lazy<IEventBus>

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        exception.logMessage.log("view model exception")
        executeOnErrorUiCommands(errorMsg = exception.toErrorMsg())
    }

    fun execute(
        scope: CoroutineScope,
        coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
        withLoader: Boolean = false,
        backgroundTask: suspend SideEffectHandler.() -> Unit,
    ): Job {
        return scope.launch(exceptionHandler + coroutineDispatcher) {
            if (withLoader) {
                executeLoaderEvent(true)
                delay(400)
            }
            backgroundTask()
            if (withLoader)
                executeLoaderEvent(false)
        }
    }

    fun <T> ApiResult<T>.handle(): ApiResult<T> {
        when (this) {
            is Success -> Unit
            is Failure -> when (this.error) {
                is NoNetworkError -> {
                }

                is ServerConnectionError -> {
                }

                is GenericError -> {
//               TODO     FirebaseManager.logCrashlyticsException(exception = Exception(this.error.throwable))
                    throw this.error.throwable
                }

                is ApiError -> {
                    executeOnErrorUiCommands(errorMsg = this.error.msg)
                }

                UnauthorizedError -> {}// executeLogoutCommand()
            }
        }
        return this
    }

    fun <T> ApiResult<T>.handleInBackground(): ApiResult<T> {
        when (this) {
            is Success -> Unit
            is Failure -> {
                this.error.log("ExceptionDuringApiCall")
            }
        }
        return this
    }

    private fun executeOnErrorUiCommands(errorMsg: String) {
        executeWarningCommand(errorMsg)
        executeLoaderEvent(show = false)
    }

    private fun executeWarningCommand(errorMsg: String) {
        eventBus.get().produceEvent(Event.ShowWarning(message = errorMsg))
    }


    fun executeLoaderEvent(show: Boolean) = CoroutineScope(Dispatchers.Main).launch {
        eventBus.get().produceEvent(Event.ShowLoader(show = show))
    }

    private fun Throwable.toErrorMsg(): String {
        val defaultMessage = "Something went wrong"
        return if (this is ApiException) message
        else defaultMessage
    }
}