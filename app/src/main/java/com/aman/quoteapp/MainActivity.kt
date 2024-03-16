package com.aman.quoteapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.aman.quoteapp.MainActivity.Companion.TAG
import com.aman.quoteapp.screens.QuoteDetail
import com.aman.quoteapp.screens.QuoteListItem
import com.aman.quoteapp.screens.QuoteListScreen
import com.aman.quoteapp.ui.theme.QuoteAppTheme
import com.aman.quoteapp.utils.Pages
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/*
* cheezy ved9:  https://www.youtube.com/watch?v=tINJacwQFfQ&list=PLRKyZvuMYSIO9sadcCwR0DR8UPi9bQlev&index=9
* */
class MainActivity : ComponentActivity() {
    companion object {
        val TAG = "QuoteApp_d"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            DataManager.loadAssetsFromFile(applicationContext)
        }
        setContent {
            App()
        }
    }
}

    @Composable
    fun App() {
        if (DataManager.isDataLoaded.value) {
            if (DataManager.currentPage.value == Pages.LISTENING) {
                QuoteListScreen(data = DataManager.data) {
                    DataManager.switchPages(it)
                    Log.d(TAG, "App: Clicked")
                }
            }else{
                DataManager.currentQuote?.let { QuoteDetail(quote = it) {
                } }
            }

        }else{
            Box (
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize(1f)
            ){
                Text(text = "Loading...",
                style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }




