package `in`.obvious.android.starter.search

import java.lang.Exception

data class SearchModel(val query: String = "", val result: SearchResult = NoSearch) {
    fun initial(): SearchModel {
        return copy(query = "", result = NoSearch)
    }

    fun searching(query: String): SearchModel {
        return copy(query = query, result = Searching)
    }

    fun searchSuccess(results: List<String>): SearchModel {
        return copy(result = SearchSuccess(results = results))
    }

    fun searchFailed(error: Exception): SearchModel {
        return copy(result = SearchError(error = error))
    }
}

sealed class SearchResult

object NoSearch: SearchResult()
object Searching: SearchResult()
data class SearchSuccess(val results: List<String>): SearchResult()
data class SearchError(val error: Exception): SearchResult()