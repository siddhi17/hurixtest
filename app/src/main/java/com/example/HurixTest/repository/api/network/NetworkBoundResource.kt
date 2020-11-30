package com.example.HurixTest

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

abstract class NetworkResource<RequestType> @MainThread constructor() {

    private val result = MediatorLiveData<Resource<RequestType>>()

    init {
        // Send loading state to UI
        result.value = Resource.loading()
        fetchFromNetwork()
    }

    private fun fetchFromNetwork() {
        val apiResponse = createCall()
        // Make the network call
        result.addSource(apiResponse) { response ->

            result.removeSource(apiResponse)
            // Dispatch the result
            response?.apply {
                when {
                    // Can be done with if statement if status is successful set this otherwise set error message
                    status.isSuccessful() -> {
                        setValue(this)
                    }
                    else -> {
                        setValue(Resource.error(errorMessage))
                    }
                }
            }
        }
    }

    fun asLiveData(): LiveData<Resource<RequestType>> {
        return result
    }

    @MainThread
    private fun setValue(newValue: Resource<RequestType>) {
        if (result.value != newValue)
            result.value = newValue
    }

    @MainThread
    protected abstract fun createCall(): LiveData<Resource<RequestType>>
}