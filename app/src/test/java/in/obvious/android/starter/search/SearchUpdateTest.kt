package `in`.obvious.android.starter.search

import com.spotify.mobius.test.NextMatchers.hasEffects
import com.spotify.mobius.test.NextMatchers.hasModel
import com.spotify.mobius.test.UpdateSpec
import com.spotify.mobius.test.UpdateSpec.assertThatNext
import org.junit.Test

class SearchUpdateTest {

  private val searchUpdate = SearchUpdate()
  private val spec = UpdateSpec(searchUpdate)

  @Test
  fun `when search is empty, then search is in its initial state`() {
    val searchModel = SearchModel()
    val initialModel = searchModel.initial()

    spec
        .given(initialModel)
        .whenEvent(TextChanged(""))
        .then(assertThatNext(hasModel(initialModel)))
  }

  @Test
  fun `when text is changed, then search is in searching state & search query is fired`() {
    val initialModel = SearchModel().initial()

    spec
        .given(initialModel)
        .whenEvent(TextChanged("craft"))
        .then(
            assertThatNext(
                hasModel(initialModel.searching(query = "craft")),
                hasEffects(SearchQueryEffect("craft"))))
  }
}
