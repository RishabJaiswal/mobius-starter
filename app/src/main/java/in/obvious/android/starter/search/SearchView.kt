package `in`.obvious.android.starter.search

interface SearchView {
  fun hideLoader()
  fun hideError()
  fun hideResults()
  fun showLoader(query: String)
  fun showResults(results: List<String>)
  fun showError(message: String)
}