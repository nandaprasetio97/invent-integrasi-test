package com.nandaprasetio.inventintegrasitest.presentation.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.nandaprasetio.inventintegrasitest.R

abstract class BaseActivity<T: ViewBinding>: AppCompatActivity() {
    protected var viewBinding: T? = null

    protected abstract fun setViewBinding(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = setViewBinding()
        this.setContentView(viewBinding?.root)

        viewBinding?.root?.also { _ ->
            this.supportActionBar?.also {
                setActivityConfiguration().also { config ->
                    val enableBack = config.enableBack
                    it.title = config.title
                    it.setDisplayShowTitleEnabled(false)
                    it.setHomeButtonEnabled(enableBack)
                    it.setDisplayShowHomeEnabled(enableBack)
                    it.setDisplayHomeAsUpEnabled(enableBack)
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> { this.onBackPressed(); true }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }

    protected open fun setActivityConfiguration(): ActivityConfiguration = ActivityConfiguration(
        true, this.getString(R.string.app_name)
    )
}