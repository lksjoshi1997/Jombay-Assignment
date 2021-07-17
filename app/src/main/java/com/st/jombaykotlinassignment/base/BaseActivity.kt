package com.st.jombaykotlinassignment.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity() : FragmentActivity() {

    protected abstract val bindingInflater: (LayoutInflater) -> ViewBinding

    private var _binding: ViewBinding? = null

    protected open val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater(layoutInflater)
        setContentView(binding.root)

        this.startWorking()
    }

    abstract fun startWorking()

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}