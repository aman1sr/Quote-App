package com.aman.quoteapp.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.aman.quoteapp.models.Quote

@Preview
@Composable
fun QuoteList(data: Array<Quote>, onClick: (quote:Quote) -> Unit) {
    LazyColumn(content = {
        items(data){quote->
            QuoteListItem(quote = quote) {
                onClick.invoke(it)
            }
        }
    })
}