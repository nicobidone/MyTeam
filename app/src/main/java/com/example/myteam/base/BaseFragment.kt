package com.example.myteam.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.ref.WeakReference

abstract class BaseFragment<B : ViewBinding, A : BaseActivity<*>> : Fragment() {

    protected lateinit var binding: B
    private lateinit var parentActivity: WeakReference<A>

    abstract fun getBindingClass(): B

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parentActivity = WeakReference(this.activity as A)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = getBindingClass()
        return binding.root
    }

    fun getParentActivity() = parentActivity.get()

    fun showLoader() {
        hideKeyboard()
        parentActivity.get()?.showLoader()
    }

    fun hideKeyboard() {
        parentActivity.get()?.hideKeyboard()
    }

    fun hideLoader() {
        parentActivity.get()?.hideLoader()
    }

    fun isNetworkConnected() = parentActivity.get()?.isNetworkConnected()
}
