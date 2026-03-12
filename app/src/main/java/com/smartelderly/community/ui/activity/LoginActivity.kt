package com.smartelderly.community.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.TextUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.smartelderly.community.R
import com.smartelderly.community.data.network.RetrofitClient
import com.smartelderly.community.data.network.RetrofitClient.createService
import com.smartelderly.community.data.preference.PreferenceManager
import com.smartelderly.community.data.network.*
import kotlinx.coroutines.*
import retrofit2.Retrofit

/**
 * 登录页面
 */
class LoginActivity : AppCompatActivity() {

    private lateinit var etPhone: EditText
    private lateinit var etCode: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnGetCode: Button
    private lateinit var btnLogin: Button
    private lateinit var tvRegister: TextView
    private lateinit var tvPasswordLogin: TextView

    private var isPasswordLogin = false
    private var countdownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViews()
        setupClickListeners()
    }

    private fun initViews() {
        etPhone = findViewById(R.id.et_phone)
        etCode = findViewById(R.id.et_code)
        etPassword = findViewById(R.id.et_password)
        btnGetCode = findViewById(R.id.btn_get_code)
        btnLogin = findViewById(R.id.btn_login)
        tvRegister = findViewById(R.id.tv_register)
        tvPasswordLogin = findViewById(R.id.tv_password_login)

        etPassword.visibility = android.view.View.GONE
    }

    private fun setupClickListeners() {
        // 获取验证码
        btnGetCode.setOnClickListener {
            val phone = etPhone.text.toString().trim()
            if (TextUtils.isEmpty(phone) || phone.length != 11) {
                Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            sendSmsCode(phone)
        }

        // 登录
        btnLogin.setOnClickListener {
            val phone = etPhone.text.toString().trim()
            if (TextUtils.isEmpty(phone) || phone.length != 11) {
                Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (isPasswordLogin) {
                val password = etPassword.text.toString().trim()
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                loginByPassword(phone, password)
            } else {
                val code = etCode.text.toString().trim()
                if (TextUtils.isEmpty(code)) {
                    Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                loginBySms(phone, code)
            }
        }

        // 切换登录方式
        tvPasswordLogin.setOnClickListener {
            isPasswordLogin = !isPasswordLogin
            if (isPasswordLogin) {
                etCode.visibility = android.view.View.GONE
                etPassword.visibility = android.view.View.VISIBLE
                btnGetCode.visibility = android.view.View.GONE
                tvPasswordLogin.text = "验证码登录"
            } else {
                etCode.visibility = android.view.View.VISIBLE
                etPassword.visibility = android.view.View.GONE
                btnGetCode.visibility = android.view.View.VISIBLE
                tvPasswordLogin.text = "密码登录"
            }
        }

        // 注册
        tvRegister.setOnClickListener {
            Toast.makeText(this, "请使用验证码登录,首次登录自动注册", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendSmsCode(phone: String) {
        // TODO: 调用发送短信验证码接口
        Toast.makeText(this, "验证码已发送", Toast.LENGTH_SHORT).show()
        startCountdown()
    }

    private fun loginBySms(phone: String, code: String) {
        // TODO: 调用短信登录接口
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show()
        PreferenceManager.saveToken("test_token")
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun loginByPassword(phone: String, password: String) {
        // TODO: 调用密码登录接口
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show()
        PreferenceManager.saveToken("test_token")
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun startCountdown() {
        countdownTimer?.cancel()
        countdownTimer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                btnGetCode.isEnabled = false
                btnGetCode.text = "${millisUntilFinished / 1000}秒"
            }

            override fun onFinish() {
                btnGetCode.isEnabled = true
                btnGetCode.text = "获取验证码"
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        countdownTimer?.cancel()
    }
}
