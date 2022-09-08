package `in`.obvious.android.starter.search

class SearchViewRenderer(private val view: SearchView) {

  private fun renderNoSearch() {
    view.apply {
      hideLoader()
      hideResults()
      hideError()
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
      is SearchSuccess -> renderSearchSuccess()
      is SearchError -> TODO()
      Searching -> TODO()
    }
  }
}
