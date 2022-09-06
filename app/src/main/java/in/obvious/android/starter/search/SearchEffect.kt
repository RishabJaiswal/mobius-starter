package `in`.obvious.android.starter.search

sealed interface SearchEffect

data class SearchQueryEffect(val query: String = ""): SearchEffect
