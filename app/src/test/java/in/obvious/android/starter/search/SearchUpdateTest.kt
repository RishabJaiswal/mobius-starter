package `in`.obvious.android.starter.search

import com.spotify.mobius.test.NextMatchers.hasModel
import com.spotify.mobius.test.UpdateSpec
import com.spotify.mobius.test.UpdateSpec.assertThatNext
import org.junit.Test

class SearchUpdateTest {
    @Test
    fun `when search is empty, then search is in its initial state`() {
        val searchUpdate = SearchUpdate()
        val spec = UpdateSpec(searchUpdate)
        val initialModel = InitialSearchModel()

        spec.given(initialModel)
            .whenEvent( TextChanged(""))
            .then(assertThatNext(hasModel(initialModel)))
    }
}