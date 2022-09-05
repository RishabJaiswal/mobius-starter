package `in`.obvious.android.starter.search

data class SearchModel(val query: String) {
    fun initialState(): SearchModel {
        return copy(query = "")
    }
}