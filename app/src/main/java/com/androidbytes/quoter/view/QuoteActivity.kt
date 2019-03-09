package com.androidbytes.quoter.view

import android.arch.lifecycle.Observer
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.text.Spannable
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.androidbytes.quoter.R
import com.androidbytes.quoter.common.base.BaseActivity
import com.androidbytes.quoter.common.utils.BASE_URL
import com.androidbytes.quoter.common.utils.SPACE
import com.androidbytes.quoter.common.utils.fromHtml
import com.androidbytes.quoter.common.utils.getColoredSpannable
import com.androidbytes.quoter.repository.network.model.Quote
import com.androidbytes.quoter.viewmodel.QuoteViewModel


class QuoteActivity : BaseActivity<QuoteViewModel>(), Observer<Quote> {

    override fun getViewModelClass() = QuoteViewModel::class.java

    private var isRestarted = false

    private lateinit var btnGetQuote: Button
    private lateinit var textViewQuote: TextView
    private lateinit var textViewAuthor: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.androidbytes.quoter.R.layout.activity_quote)

        progressBar = findViewById(com.androidbytes.quoter.R.id.progress_bar)
        btnGetQuote = findViewById(com.androidbytes.quoter.R.id.btn_get_quote)
        textViewQuote = findViewById(com.androidbytes.quoter.R.id.text_view_quote)
        textViewAuthor = findViewById(com.androidbytes.quoter.R.id.text_view_author)

        val textViewLink = findViewById<TextView>(R.id.text_view_link)
        textViewLink.text = getSpannableText()
        textViewLink.setOnClickListener { openBrowser() }

        btnGetQuote.setOnClickListener { viewModel.getRandomQuote() }

        viewModel.getQuote().observe(this, this)

        viewModel.isLoading().observe(this, Observer {
            showLoadingView(it ?: false)
        })

        viewModel.getError().observe(this, Observer {
            Snackbar.make(textViewLink, it ?: "Something went wrong", Snackbar.LENGTH_LONG).show()
        })
    }

    override fun onResume() {
        super.onResume()
        if (!isRestarted) {
            btnGetQuote.performClick()
        }
    }

    override fun onChanged(quote: Quote?) {
        val content = quote?.getContent ?: ""
        val author = quote?.title ?: getString(com.androidbytes.quoter.R.string.lbl_unknown)

        textViewQuote.text = content.fromHtml()
        textViewAuthor.text = "- $author"
    }

    private fun showLoadingView(isLoading: Boolean) {
        when (isLoading) {
            true -> {
                progressBar.visibility = View.VISIBLE
                btnGetQuote.visibility = View.GONE
            }

            false -> {
                progressBar.visibility = View.GONE
                btnGetQuote.visibility = View.VISIBLE
            }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        isRestarted = true
    }

    private fun openBrowser() {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(BASE_URL))
        if (browserIntent.resolveActivity(packageManager) != null) {
            startActivity(browserIntent)
        }
    }

    private fun getSpannableText(): Spannable {
        return getColoredSpannable(
            getString(R.string.lbl_grateful) + SPACE, BASE_URL,
            Color.parseColor("#3232ff")
        )
    }
}
