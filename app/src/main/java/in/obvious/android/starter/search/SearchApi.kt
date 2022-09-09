package `in`.obvious.android.starter.search

object SearchApi {
  fun searchSuccess(query: String) = listOf("craft", "made", "with", "love")

  fun searchFailed(query: String) = IllegalAccessException()
}