package `in`.obvious.android.starter.search

import org.junit.Test
import org.mockito.kotlin.mock

class SearchEffectHandlerTest {
  private val uiRenderer = mock<SearchUIRenderer>()
  private val effectHandler = SearchEffectHandler()

  @Test
  fun `when search query effect is emitted, then search api makes a query`() {
    // given
  }
}