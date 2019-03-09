package com.androidbytes.quoter.viewmodel

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.androidbytes.quoter.QuoteApp
import com.androidbytes.quoter.repository.QuoteRepository
import com.androidbytes.quoter.repository.network.model.Quote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Created by umang on 04/03/19.
 */
class QuoteViewModel(application: QuoteApp, private val quoteRepository: QuoteRepository) :
    AndroidViewModel(application) {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val errorLiveData = MutableLiveData<String>()
    private val quoteLiveData = MutableLiveData<Quote>()
    private val loadingLiveData = MutableLiveData<Boolean>()

    fun getRandomQuote() {
        loadingLiveData.postValue(true)
        uiScope.launch {
            try {
                val quotes = quoteRepository.getRandomQuoteAsync().await()

                loadingLiveData.postValue(false)
                quoteLiveData.postValue(quotes[0])
            } catch (e: Exception) {
                loadingLiveData.postValue(false)
                errorLiveData.postValue(e.message)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun getQuote() = quoteLiveData

    fun isLoading() = loadingLiveData

    fun getError() = errorLiveData
}