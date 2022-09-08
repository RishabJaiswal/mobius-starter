package `in`.obvious.android.starter.search

class SearchViewRenderer(private val view: SearchView) {
  fun renderNoSearch() {
    view.apply {
      hideLoader()
      hideResults()
      hideError()
    }
  }

  fun render(model: SearchModel) {
    when (model.result) {
      is NoSearch -> renderNoSearch()
      is SearchError -> TODO()
      is SearchSuccess -> TODO()
      Searching -> TODO()
    }
  }
}
