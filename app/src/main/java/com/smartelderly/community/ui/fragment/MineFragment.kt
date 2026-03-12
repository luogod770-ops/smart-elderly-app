package com.smartelderly.community.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smartelderly.community.R
import com.smartelderly.community.ui.activity.PersonalCenterActivity

/**
 * 我的Fragment
 */
class MineFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mine, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.layout_personal_center)?.setOnClickListener {
            startActivity(Intent(activity, PersonalCenterActivity::class.java))
        }
    }
}
