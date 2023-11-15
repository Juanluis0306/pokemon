package com.jl.pokemon.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.MotionEvent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog
import com.jl.pokemon.R
import com.jl.pokemon.database.entities.UserEntity
import com.jl.pokemon.databinding.ActivityLoginBinding
import com.jl.pokemon.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private val listUsersDefaults: ArrayList<UserEntity> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observers()
        listeners()
    }

    private fun observers() {
        viewModel.countUser.observe(this) {
            if (it == 0) {
                createList()
                viewModel.insertUsersDefault(listUsersDefaults)
            }
        }

        viewModel.userEntity.observe(this) {
            if (it == null) {
                showErrorUser()
            } else {
                val editor =
                    getSharedPreferences(getString(R.string.save_session_data), MODE_PRIVATE).edit()
                editor.putBoolean(getString(R.string.save_session), true)
                editor.apply()
                val mainIntent = Intent(this@LoginActivity, MainActivity::class.java)
                this@LoginActivity.startActivity(mainIntent)
                finish()
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun listeners() {
        viewModel.countUser()
        var showPassword = false
        binding.editTextPassword.setOnTouchListener { v, event ->
            val DRAWABLE_RIGHT = 2
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= binding.editTextPassword.right - binding.editTextPassword.compoundDrawables[DRAWABLE_RIGHT].bounds.width()
                ) {
                    showPassword = !showPassword
                    if (showPassword) {
                        binding.editTextPassword.transformationMethod = null
                    } else {
                        binding.editTextPassword.transformationMethod =
                            PasswordTransformationMethod.getInstance()
                    }
                    return@setOnTouchListener true
                }
            }
            false
        }

        binding.btnLogin.setOnClickListener {
            if (validateLabels()) {
                viewModel.login(
                    binding.editTextUser.text.toString().uppercase().trim(),
                    binding.editTextPassword.text.toString()
                )
            }
        }
    }

    fun validateLabels(): Boolean {
        if (binding.editTextUser.text.isEmpty()) {
            showErrorUser(getString(R.string.requiered_user))
            return false
        }
        if (binding.editTextPassword.text.isEmpty()) {
            showErrorUser(getString(R.string.requiered_password))
            return false
        }
        return true
    }

    private fun createList() {
        listUsersDefaults.add(UserEntity(email = "jlhm@gmail.com", password = "1234"))
        listUsersDefaults.add(UserEntity(email = "juan@gmail.com", password = "1234"))
    }

    private fun showErrorUser(message: String? = null) {
        SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
            .setTitleText("Oops...")
            .setContentText(message ?: getString(R.string.error_user))
            .show()
    }
}