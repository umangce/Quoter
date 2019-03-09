package com.androidbytes.quoter.common.utils

import android.os.Build
import android.text.Html
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan

/**
 * Created by umang on 04/03/19.
 */

val BASE_URL = "http://quotesondesign.com"
val SPACE = " "

fun String.fromHtml() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
} else {
    Html.fromHtml(this)
}

fun getColoredSpannable(text: String, coloredText: String, color: Int): Spannable {
    val wordToSpan = SpannableString(text + coloredText)

    wordToSpan.setSpan(
        ForegroundColorSpan(color),
        text.length,
        text.length + coloredText.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    wordToSpan.setSpan(
        RelativeSizeSpan(1.05f),
        text.length,
        text.length + coloredText.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    return wordToSpan
}