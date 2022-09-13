package `in`.obvious.android.starter.search

import com.spotify.mobius.Connectable
import com.spotify.mobius.Connection
import com.spotify.mobius.functions.Consumer

class SearchEffectHandler : Connectable<SearchEffect, SearchEvent> {

  override fun connect(output: Consumer<SearchEvent>): Connection<SearchEffect> {
    return object : Connection<SearchEffect> {
      override fun accept(effect: SearchEffect) {
        when(effect) {
          is SearchQueryEffect ->  {
            output.accept(
              SearchSuccessful(
                results = SearchApi.searchSuccess(query = "craft")
              )
            )
          }
          is SearchSuccessEffect -> TODO()
          is SearchFailedEffect -> TODO()
        }
      }

      override fun dispose() {
        TODO("Not yet implemented")
      }
    }
  }
}
