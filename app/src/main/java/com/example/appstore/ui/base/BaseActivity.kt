package com.example.appstore.ui.base

import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.appstore.R

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {
    lateinit var viewBinding: T

    abstract fun getActivityViewBinding(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = getActivityViewBinding()
        setContentView(viewBinding.root)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    protected fun initActionBar(
        toolbar: Toolbar?,
        titleString: String? = null,
        subtitleString: String? = null,
        @StringRes titleId: Int? = null,
        @StringRes subtitleId: Int? = null
    ) {
        toolbar?.let {
            setSupportActionBar(toolbar)
            supportActionBar?.let { ab ->
                ab.setDisplayHomeAsUpEnabled(true)
                ab.setHomeButtonEnabled(true)
                titleString?.let {
                    ab.title = titleString
                }
                titleId?.let {
                    ab.setTitle(titleId)
                }
                subtitleString?.let {
                    ab.subtitle = subtitleString
                }
                subtitleId?.let {
                    ab.setSubtitle(subtitleId)
                }
            }
        }
    }

    fun initFragment(fragment: Fragment?, titleString: String?, titleId: Int?) {
        if (fragment == null) return

        val viewBinding = viewBinding
        setContentView(viewBinding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    fun popBackStack(): Boolean {
        return if (supportFragmentManager.backStackEntryCount == 0) {
            false
        } else {
            supportFragmentManager.popBackStack()
            true
        }
    }

    fun hideSoftKeyboard() {
        (getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager)?.hideSoftInputFromWindow(
            currentFocus?.windowToken,
            0
        )
    }
}
