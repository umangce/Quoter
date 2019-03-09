package com.androidbytes.quoter.repository

import com.androidbytes.quoter.repository.network.ApiClientService
import com.androidbytes.quoter.repository.network.model.Quote
import kotlinx.coroutines.Deferred

/**
 * Created by umang on 04/03/19.
 */
class QuoteRepository(private val apiClientService: ApiClientService) {

    fun getRandomQuoteAsync(): Deferred<List<Quote>> {
        return apiClientService.getRandomQuoteAsync()
    }
}