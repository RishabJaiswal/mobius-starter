package `in`.obvious.android.starter.search

class SearchViewRenderer(private val view: SearchView) {

  private fun renderNoSearch() {
    view.apply {
      hideLoader()
      hideResults()
      hideError()
    }
  }

  private fun renderSearching(query: String) {
    view.apply {
      showLoader(query = query)
      hideError()
      hideResults()
    }
  }

  private fun renderSearchSuccess() {
    view.apply {
      hideLoader()
      hideError()
      showResults()
    }
  }

  fun render(model: SearchModel) {
    when (model.result) {
      is NoSearch -> renderNoSearch()
      Searching -> renderSearching(query = model.query)
      is SearchSuccess -> renderSearchSuccess()
      is SearchError -> TODO()
    }
  }
}
