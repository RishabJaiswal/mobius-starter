package `in`.obvious.android.starter.search

sealed class SearchModel

data class InitialSearchModel(val query: String = ""): SearchModel()