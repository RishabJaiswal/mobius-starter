package `in`.obvious.android.starter.search

import java.lang.Exception

sealed interface SearchEffect

data class SearchQueryEffect(val query: String = ""): SearchEffect

data class SearchSuccessEffect(val results: List<String> = emptyList()) :  SearchEffect

data class SearchFailedEffect(val error: Exception): SearchEffect
