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
}