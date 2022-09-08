package `in`.obvious.android.starter.search

import java.lang.Error
import java.lang.Exception

sealed class SearchEvent

data class TextChanged(val query: String): SearchEvent()

data class SearchSuccessful(val results: List<String>): SearchEvent()

data class SearchFailed(val error: Exception): SearchEvent()