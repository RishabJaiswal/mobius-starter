package `in`.obvious.android.starter.search

import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions

class SearchViewRendererTest {

  private val searchView = mock<SearchView>()
  private val viewRenderer = SearchViewRenderer(view = searchView)

  @Test
  fun `when search query is empty, then hide loader, results & error`() {
    // given
    val initialModel = SearchModel().initial()

    // when
    viewRenderer.render(initialModel)

    // then
    verify(searchView).hideLoader()
    verify(searchView).hideResults()
    verify(searchView).hideError()
    verifyNoMoreInteractions(searchView)

  }

  @Test
  fun `when search is in progress, then show loader and hide results and error`() {
    // given
    val query = "Craft"
    val searchingModel = SearchModel().searching(query = query)

    // when
    viewRenderer.render(searchingModel)

    // then
    verify(searchView).showLoader(query = query)
    verify(searchView).hideError()
    verify(searchView).hideResults()
    verifyNoMoreInteractions(searchView)

  }

  @Test
  fun `when search is successful, then hide loader & error and show results`() {
    // given
    val results = listOf("result1", "result2")
    val successModel = SearchModel().searchSuccess(results = results)

    // when
    viewRenderer.render(successModel)

    // then
    verify(searchView).hideLoader()
    verify(searchView).hideError()
    verify(searchView).showResults()
    verifyNoMoreInteractions(searchView)

  }
}