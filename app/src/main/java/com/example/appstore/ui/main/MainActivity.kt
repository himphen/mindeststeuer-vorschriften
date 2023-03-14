package com.example.appstore.ui.main

import android.os.Bundle
import com.example.appstore.R
import com.example.appstore.databinding.ActivityContainerBinding
import com.example.appstore.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityContainerBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActionBar(viewBinding.toolbar.root, titleId = R.string.app_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setHomeButtonEnabled(false)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, MainFragment())
            .commit()

    }

    override fun onResume() {
        super.onResume()
    }

    override fun getActivityViewBinding(): ActivityContainerBinding =
        ActivityContainerBinding.inflate(layoutInflater)

}
