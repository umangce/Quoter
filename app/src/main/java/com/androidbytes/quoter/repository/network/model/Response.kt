package com.androidbytes.quoter.repository.network.model

/**
 * Created by umang on 04/03/19.
 */

data class Quote(val ID: String, val title: String, private val content: String, val link: String) {
    val getContent: String
        get() = content.trim().replace("<p>", "").replace("</p>", "")

}