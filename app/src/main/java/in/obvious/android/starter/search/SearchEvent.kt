package `in`.obvious.android.starter.search

sealed class SearchEvent

data class TextChanged(val query: String): SearchEvent()
data class SearchSuccessful(val results: List<String>): SearchEvent()