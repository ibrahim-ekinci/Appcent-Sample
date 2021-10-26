package com.gloorystudio.appcent_sample.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

open class BaseViewModel : ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    suspend fun launchDataLoad(fetch: () -> Unit) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                fetch.invoke()
            }
        }
    }
}