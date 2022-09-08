package `in`.obvious.android.starter.search

import java.lang.Exception

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

  private fun renderSearchSuccess(results: List<String>) {
    view.apply {
      hideLoader()
      hideError()
      showResults(results = results)
    }
  }

  private fun renderSearchError(error: Exception) {
    view.apply {
      hideLoader()
      hideResults()
      showError(
        message = getErrorMessage(error = error)
      )
    }
  }

  private fun getErrorMessage(error: Exception): String {
    return "Error message"
  }

  fun render(model: SearchModel) {
    when (model.result) {
      is NoSearch -> renderNoSearch()
      Searching -> renderSearching(query = model.query)
      is SearchSuccess -> renderSearchSuccess(results = model.result.results)
      is SearchError -> renderSearchError(error = model.result.error)
    }
  }
}
