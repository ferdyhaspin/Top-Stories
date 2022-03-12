package org.ferdyhaspin.topstories.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * Created by ferdyhaspin on 12/03/22.
 */
abstract class BaseActivity : AppCompatActivity() {

    abstract val binding: ViewBinding

    abstract fun onReadyAction()

    abstract fun onObserveAction()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        onReadyAction()
        onObserveAction()
    }

}