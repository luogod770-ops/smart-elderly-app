package com.smartelderly.community.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.smartelderly.community.R
import com.smartelderly.community.data.model.Banner
import com.smartelderly.community.ui.activity.*
import com.smartelderly.community.viewmodel.SystemViewModel

/**
 * 首页Fragment
 */
class HomeFragment : Fragment() {

    private lateinit var viewModel: SystemViewModel
    private lateinit var bannerPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_with_banner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initViewModel()
        setupClickListeners()
        loadData()
    }

    private fun initViews(view: View) {
        bannerPager = view.findViewById(R.id.banner_pager)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[SystemViewModel::class.java]

        // 观察Banner列表
        viewModel.bannerList.observe(viewLifecycleOwner) { banners ->
            if (banners.isNotEmpty()) {
                // TODO: 设置Banner适配器
                Toast.makeText(context, "Banner加载: ${banners.size}条", Toast.LENGTH_SHORT).show()
            }
        }

        // 观察加载状态
        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            // TODO: 显示/隐藏加载进度
        }

        // 观察错误信息
        viewModel.error.observe(viewLifecycleOwner) { error ->
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupClickListeners() {
        // SOS按钮点击事件
        view?.findViewById<View>(R.id.btn_sos)?.setOnClickListener {
            startActivity(Intent(activity, SOSActivity::class.java))
        }

        // 房屋绑定
        view?.findViewById<View>(R.id.menu_house)?.setOnClickListener {
            startActivity(Intent(activity, HouseBindingActivity::class.java))
        }

        // 社区
        view?.findViewById<View>(R.id.menu_community)?.setOnClickListener {
            startActivity(Intent(activity, CommunityActivity::class.java))
        }

        // 设备
        view?.findViewById<View>(R.id.menu_device)?.setOnClickListener {
            startActivity(Intent(activity, DeviceManagementActivity::class.java))
        }

        // 会员
        view?.findViewById<View>(R.id.menu_member)?.setOnClickListener {
            startActivity(Intent(activity, MemberCenterActivity::class.java))
        }
    }

    private fun loadData() {
        // 加载Banner列表
        viewModel.loadBannerList()
    }
}
