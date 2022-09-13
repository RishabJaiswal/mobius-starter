package `in`.obvious.android.starter.search

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.spotify.mobius.Connectable
import com.spotify.mobius.Connection
import com.spotify.mobius.Mobius
import com.spotify.mobius.android.MobiusAndroid
import com.spotify.mobius.functions.Consumer
import `in`.obvious.android.starter.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity(), SearchUI, Connectable<SearchModel, SearchEvent> {

  private val searchUpdate = SearchUpdate()
  private val searchEffectHandler = SearchEffectHandler()
  private val binding by lazy { ActivitySearchBinding.inflate(layoutInflater) }
  private val searchUIRenderer by lazy { SearchUIRenderer(ui = this) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
    startMobiusLoop()
  }

  private fun startMobiusLoop() {
    val searchLoopFactory = Mobius.loop(searchUpdate, searchEffectHandler)
    val mobiusController = MobiusAndroid.controller(searchLoopFactory, SearchModel().initial())
    mobiusController.connect(this)
    mobiusController.start()
  }

  override fun hideLoader() {
    binding.searchProgress.visibility = View.GONE
  }

  override fun hideResults() {
    binding.results.visibility = View.GONE
  }

  override fun hideError() {
    binding.errorMessage.visibility = View.GONE
  }

  override fun showLoader(query: String) {
    binding.searchProgress.visibility = View.VISIBLE
  }

  override fun showResults(results: List<String>) {
    binding.results.apply {
      visibility = View.VISIBLE
      this.text = results.first()
    }
  }

  override fun showError(message: String) {
    binding.errorMessage.visibility = View.VISIBLE
  }

  private fun connectSearchBar(eventConsumer: Consumer<SearchEvent>) {
    binding.search.setOnQueryTextListener(
        object : SearchView.OnQueryTextListener {

          override fun onQueryTextSubmit(query: String?): Boolean {
            eventConsumer.accept(TextChanged(query = query ?: ""))
            return true
          }

          override fun onQueryTextChange(newText: String?): Boolean {
            eventConsumer.accept(TextChanged(query = newText ?: ""))
            return true
          }
        })
  }

  // connect search activity to SearchUpdate Mobius loop
  override fun connect(eventConsumer: Consumer<SearchEvent>): Connection<SearchModel> {
    connectSearchBar(eventConsumer)
    return SearchUiConnection(eventConsumer = eventConsumer)
  }

  inner class SearchUiConnection(private val eventConsumer: Consumer<SearchEvent>) :
      Connection<SearchModel> {

    override fun accept(model: SearchModel) {
      searchUIRenderer.render(model = model)
    }

    override fun dispose() {
    }
  }
}
