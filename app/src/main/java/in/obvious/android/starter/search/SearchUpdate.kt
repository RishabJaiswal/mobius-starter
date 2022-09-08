package `in`.obvious.android.starter.search

import com.spotify.mobius.Next
import com.spotify.mobius.Next.next
import com.spotify.mobius.Next.noChange
import com.spotify.mobius.Update

class SearchUpdate : Update<SearchModel, SearchEvent, SearchEffect> {

  private fun search(model: SearchModel, query: String): Next<SearchModel, SearchEffect> {
    return if (query.isEmpty()) next(model.initial())
    else next(model.searching(query), setOf(SearchQueryEffect(query)))
  }

  override fun update(model: SearchModel, event: SearchEvent): Next<SearchModel, SearchEffect> {
    return when (event) {
      is TextChanged -> search(
          model = model,
          query = event.query
      )
      is SearchSuccessful -> next(
          model.searchSuccess(results = event.results),
          setOf(SearchSuccessEffect(results = event.results))
      )
      is SearchFailed -> next(
          model.searchFailed(error = event.error),
          setOf(SearchFailedEffect(error = event.error))
      )
    }
  }
}
