package `in`.obvious.android.starter.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.spotify.mobius.Mobius
import com.spotify.mobius.android.MobiusAndroid
import `in`.obvious.android.starter.databinding.ActivitySearchBinding
import android.view.View

class SearchActivity : AppCompatActivity(), SearchUI {

  private val searchUpdate = SearchUpdate()
  private val searchEffectHandler = SearchEffectHandler()
  private val binding by lazy { ActivitySearchBinding.inflate(layoutInflater) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
    startMobiusLoop()
  }

  private fun startMobiusLoop() {
    val searchLoopFactory = Mobius.loop(searchUpdate, searchEffectHandler)
    val mobiusController = MobiusAndroid.controller(searchLoopFactory, SearchModel().initial())
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
}
