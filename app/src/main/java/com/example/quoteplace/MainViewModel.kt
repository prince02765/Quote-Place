package com.example.quoteplace

import android.content.Context
import android.text.style.QuoteSpan
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import java.nio.charset.Charset

class MainViewModel(val context: Context) : ViewModel() {

    private var quoteList: Array<QuoteModel> = emptyArray()
    private var index = 0

    init {
        quoteList = loadQuoteFormJSON()
    }

    private fun loadQuoteFormJSON(): Array<QuoteModel> {
        val inputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<QuoteModel>::class.java)
    }


    fun getQuote() = quoteList[index]

    fun nextQuote(): QuoteModel {
        ++index
        return quoteList[index % quoteList.size]
    }

    fun previousQuote(): QuoteModel {
        ++index
        return quoteList[index % quoteList.size]
    }
}