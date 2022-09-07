package `in`.obvious.android.starter.search

sealed interface SearchEffect

data class SearchQueryEffect(val query: String = ""): SearchEffect

data class SearchSuccessEffect(val results: List<String> = emptyList()) :  SearchEffect
