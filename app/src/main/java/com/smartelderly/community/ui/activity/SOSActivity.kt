package com.smartelderly.community.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.smartelderly.community.R

/**
 * SOS紧急求助Activity
 */
class SOSActivity : AppCompatActivity() {

    private lateinit var btnSendSOS: Button
    private lateinit var btnCancel: Button
    private lateinit var tvStatus: TextView

    private val REQUEST_CODE_PERMISSIONS = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sos)

        initViews()
        checkPermissions()
    }

    private fun initViews() {
        btnSendSOS = findViewById(R.id.btn_send_sos)
        btnCancel = findViewById(R.id.btn_cancel)
        tvStatus = findViewById(R.id.tv_status)

        btnSendSOS.setOnClickListener {
            showSOSConfirmDialog()
        }

        btnCancel.setOnClickListener {
            finish()
        }
    }

    private fun checkPermissions() {
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        val deniedPermissions = permissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }

        if (deniedPermissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, deniedPermissions.toTypedArray(), REQUEST_CODE_PERMISSIONS)
        }
    }

    private fun showSOSConfirmDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.sos)
            .setMessage(R.string.sos_confirm)
            .setPositiveButton(R.string.sos_send) { _, _ ->
                sendSOS()
            }
            .setNegativeButton(R.string.sos_cancel, null)
            .show()
    }

    private fun sendSOS() {
        // TODO: 发送SOS求救
        tvStatus.text = "正在发送求救信号..."
        tvStatus.postDelayed({
            tvStatus.text = "求救信号已发送,请等待救援"
            Toast.makeText(this, "SOS求救已发送", Toast.LENGTH_SHORT).show()
        }, 2000)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            val allGranted = grantResults.all { it == PackageManager.PERMISSION_GRANTED }
            if (!allGranted) {
                Toast.makeText(this, "需要位置权限才能发送位置信息", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
