package com.example.interviewapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.interviewapp.databinding.ActivityLoginUserBinding
import com.example.interviewapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {


        binding.apply {


            btnLogin.setOnClickListener {

                var userName = etUserName.text.toString().trim()
                var password = etPassword.text.toString().trim()

                if (userName.isNotEmpty() || password.isNotEmpty()) {
                    if (isUsernameValid(userName) && isPasswordValid(password)) {
                        Snackbar.make(it, "Login successful", Snackbar.LENGTH_SHORT).show()
                        var intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Snackbar.make(it, "Invalid username or password", Snackbar.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    private fun isUsernameValid(username: String): Boolean {
        return username.length >= 10
    }

    private fun isPasswordValid(password: String): Boolean {
        val passwordRegex = Regex("^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{7,}\$")
        return passwordRegex.matches(password)
    }
}