package `in`.obvious.android.starter.search

sealed class SearchEvent

data class TextChanged(val query: String): SearchEvent()