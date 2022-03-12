package org.ferdyhaspin.topstories.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * Created by ferdyhaspin 02/11/21.
 * Copyright (c) 2021 Qarib Apps All rights reserved.
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