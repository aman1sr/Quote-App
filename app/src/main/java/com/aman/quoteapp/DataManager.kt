package com.aman.quoteapp

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.aman.quoteapp.models.Quote
import com.aman.quoteapp.utils.Pages
import com.google.gson.Gson


object DataManager {
    var data = emptyArray<Quote>()
    var currentPage = mutableStateOf(Pages.LISTENING)
     var currentQuote : Quote?  = null
    /*
    * Jetpack Compose recomposes UI elements when the value of a mutableStateOf object changes.
    * This means that when isDataLoaded becomes true (after data is loaded), any parts of the UI that depend on this variable will automatically refresh.
    *       Similarly, changes to currentPage could trigger navigation or view updates within your app.
    * */
    var isDataLoaded = mutableStateOf(false)


    fun loadAssetsFromFile(context: Context){
        val inputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer,Charsets.UTF_8)
        val gson = Gson()
        data = gson.fromJson(json,Array<Quote>::class.java)
        isDataLoaded.value = true
    }

    fun switchPages(quote: Quote?) {
        if (currentPage.value == Pages.LISTENING) {
            currentQuote = quote
            currentPage.value = Pages.DETAIL
        } else {
            currentPage.value = Pages.LISTENING
        }
    }
}