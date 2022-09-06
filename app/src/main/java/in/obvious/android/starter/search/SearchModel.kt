package `in`.obvious.android.starter.search

data class SearchModel(val query: String = "", val result: SearchResult = NoSearch) {
    fun initial(): SearchModel {
        return copy(query = "", result = NoSearch)
    }

    fun searching(query: String): SearchModel {
        return copy(query = query, result = Searching)
    }
}

sealed class SearchResult

object NoSearch: SearchResult()
object Searching: SearchResult()