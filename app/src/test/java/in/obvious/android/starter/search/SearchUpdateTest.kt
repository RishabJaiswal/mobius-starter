package `in`.obvious.android.starter.search

import com.spotify.mobius.test.NextMatchers.hasEffects
import com.spotify.mobius.test.NextMatchers.hasModel
import com.spotify.mobius.test.UpdateSpec
import com.spotify.mobius.test.UpdateSpec.assertThatNext
import org.junit.Test
import java.lang.NullPointerException

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
                hasEffects(SearchQueryEffect("craft"))
            )
        )
  }

    @Test
    fun `when search is successful, then search is in success state & emits success effect`() {
        val query = "craft"
        val results = listOf("result1", "result2")
        val successModel = SearchModel(
            query = query,
            result = SearchSuccess(results = results)
        )

        spec
            .given(SearchModel().initial())
            .whenEvents(
                TextChanged(query = query),
                SearchSuccessful(results = results)
            )
            .then(
                assertThatNext(
                    hasModel(successModel),
                    hasEffects(SearchSuccessEffect(results = results))
                )
            )
    }

  @Test
  fun `when search failed, then search is in error state & emits error effect`() {
    val query = "craft"
    val error = NullPointerException()
    val errorModel = SearchModel(
        query = query,
        result = SearchError(error = error)
    )

    spec
        .given(SearchModel().initial())
        .whenEvents(
            TextChanged(query = query),
            SearchFailed(error = error)
        )
        .then(
            assertThatNext(
                hasModel(errorModel),
                hasEffects(SearchFailedEffect(error = error))
            )
        )
  }
}
