package com.smartelderly.community.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.smartelderly.community.R
import com.smartelderly.community.data.preference.PreferenceManager
import com.smartelderly.community.ui.fragment.*

/**
 * 主页面
 */
class MainActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView
    private val fragments = mutableListOf<Fragment>()
    private var currentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 检查登录状态
        if (!PreferenceManager.isLogin()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        initViews()
        initFragments()
        setupBottomNav()
    }

    private fun initViews() {
        bottomNav = findViewById(R.id.bottom_navigation)
    }

    private fun initFragments() {
        fragments.clear()
        fragments.add(HomeFragment())
        fragments.add(CommunityFragment())
        fragments.add(DeviceFragment())
        fragments.add(MemberFragment())
        fragments.add(MineFragment())

        // 默认显示首页
        switchFragment(fragments[0])
    }

    private fun setupBottomNav() {
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> switchFragment(fragments[0])
                R.id.nav_community -> switchFragment(fragments[1])
                R.id.nav_device -> switchFragment(fragments[2])
                R.id.nav_member -> switchFragment(fragments[3])
                R.id.nav_mine -> switchFragment(fragments[4])
            }
            true
        }
    }

    private fun switchFragment(fragment: Fragment) {
        if (currentFragment === fragment) return

        supportFragmentManager.beginTransaction()
            .hide(currentFragment ?: fragments[0])
            .show(fragment)
            .commit()

        currentFragment = fragment
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let {
            if (it.getBooleanExtra("SOS_EMERGENCY", false)) {
                startActivity(Intent(this, SOSActivity::class.java))
            }
        }
    }
}
