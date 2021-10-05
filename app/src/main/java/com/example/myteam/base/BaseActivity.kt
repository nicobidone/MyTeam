package com.example.myteam.base

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<B : ViewBinding> : AppCompatActivity() {

    private lateinit var binding: B
    private var loaderDialog = LoaderFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getBindingClass()
        setContentView(binding.root)
    }

    abstract fun getBindingClass(): B

    fun hideKeyboard() {
        currentFocus?.let { view ->
            val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager?
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun showLoader() {
        loaderDialog.show(supportFragmentManager, LOADER_FRAGMENT)
    }

    fun hideLoader() {
        loaderDialog.dismiss()
    }

    fun isNetworkConnected(): Boolean = with(this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager) {
        this.activeNetworkInfo?.isConnectedOrConnecting == true
    }

    companion object {
        private const val LOADER_FRAGMENT = "LOADER_FRAGMENT"
    }
}
