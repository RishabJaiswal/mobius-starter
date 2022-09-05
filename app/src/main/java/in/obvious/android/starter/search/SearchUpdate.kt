package `in`.obvious.android.starter.search

import com.spotify.mobius.Next
import com.spotify.mobius.Next.next
import com.spotify.mobius.Update

class SearchUpdate : Update<SearchModel, SearchEvent, Any> {
  override fun update(model: SearchModel, event: SearchEvent): Next<SearchModel, Any> {
      return when(event) {
          is TextChanged -> next(model.initialState())
      }
  }
}
