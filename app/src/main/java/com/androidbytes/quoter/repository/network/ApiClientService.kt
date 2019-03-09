package com.androidbytes.quoter.repository.network

import com.androidbytes.quoter.repository.network.model.Quote
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

/**
 * Created by umang on 04/03/19.
 */

interface ApiClientService {

    @GET("/wp-json/posts?filter[orderby]=rand")
    fun getRandomQuoteAsync(): Deferred<List<Quote>>
}