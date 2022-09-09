package `in`.obvious.android.starter.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.spotify.mobius.Mobius
import com.spotify.mobius.android.MobiusAndroid
import `in`.obvious.android.starter.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

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
}
