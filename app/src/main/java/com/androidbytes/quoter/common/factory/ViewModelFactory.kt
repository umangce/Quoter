package com.androidbytes.quoter.common.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.androidbytes.quoter.QuoteApp
import com.androidbytes.quoter.repository.QuoteRepository
import com.androidbytes.quoter.viewmodel.QuoteViewModel

/**
 * Created by umang on 04/03/19.
 */
class ViewModelFactory(private val application: QuoteApp, private val quoteRepository: QuoteRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(QuoteViewModel::class.java)) {
            return QuoteViewModel(application, quoteRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}