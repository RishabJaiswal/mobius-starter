package `in`.obvious.android.starter.search

import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import java.lang.NullPointerException

class SearchViewRendererTest {

  private val searchUI = mock<SearchUI>()
  private val uiRenderer = SearchUIRenderer(ui = searchUI)

  @Test
  fun `when search query is empty, then hide loader, results & error`() {
    // given
    val initialModel = SearchModel().initial()

    // when
    uiRenderer.render(initialModel)

    // then
    verify(searchUI).hideLoader()
    verify(searchUI).hideResults()
    verify(searchUI).hideError()
    verifyNoMoreInteractions(searchUI)

  }

  @Test
  fun `when search is in progress, then show loader and hide results and error`() {
    // given
    val query = "Craft"
    val searchingModel = SearchModel().searching(query = query)

    // when
    uiRenderer.render(searchingModel)

    // then
    verify(searchUI).showLoader(query = query)
    verify(searchUI).hideError()
    verify(searchUI).hideResults()
    verifyNoMoreInteractions(searchUI)

  }

  @Test
  fun `when search is successful, then hide loader & error and show results`() {
    // given
    val results = listOf("result1", "result2")
    val successModel = SearchModel().searchSuccess(results = results)

    // when
    uiRenderer.render(successModel)

    // then
    verify(searchUI).hideLoader()
    verify(searchUI).hideError()
    verify(searchUI).showResults(results =  results)
    verifyNoMoreInteractions(searchUI)

  }

  @Test
  fun `when search fails, then hide loader & results and show error message`() {
    // given
    val error = NullPointerException()
    val errorModel = SearchModel().searchFailed(error = error)

    // when
    uiRenderer.render(errorModel)

    // then
    verify(searchUI).hideLoader()
    verify(searchUI).hideResults()
    verify(searchUI).showError(message = "Error message")
    verifyNoMoreInteractions(searchUI)

  }
}