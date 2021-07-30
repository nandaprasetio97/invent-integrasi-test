package com.nandaprasetio.inventintegrasitest.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.nandaprasetio.inventintegrasitest.databinding.ActivityMainBinding
import com.nandaprasetio.inventintegrasitest.domain.entity.EmployeeLoginRequestBody
import com.nandaprasetio.inventintegrasitest.misc.LoadDataResult
import com.nandaprasetio.inventintegrasitest.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: BaseActivity<ActivityMainBinding>() {
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding?.also {
            it.textViewError.isVisible = false
            it.progressCircular.isVisible = false
            it.buttonLogin.isEnabled = true
            loginViewModel.loginLiveData.observe(this) { result ->
                it.textViewError.isVisible = result is LoadDataResult.Error
                it.progressCircular.isVisible = result is LoadDataResult.Loading
                it.buttonLogin.isEnabled = result !is LoadDataResult.Loading
                if (result is LoadDataResult.Error) {
                    it.textViewError.text = "Error: ${result.e.toString()}"
                } else if (result is LoadDataResult.Success) {
                    if (result.tag == 1) {
                        return@observe
                    }

                    result.tag = 1
                    this@MainActivity.also { activity ->
                        activity.startActivity(Intent(this@MainActivity, HomeActivity::class.java))
                        activity.finish()
                    }
                }
            }
            it.buttonLogin.setOnClickListener { _ ->
                loginViewModel.loginAsEmployee(
                    EmployeeLoginRequestBody(
                        it.editTextPhone.text.toString(), it.editTextPassword.text.toString()
                    )
                )
            }
        }
    }

    override fun setViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(this.layoutInflater)
    }

    override fun setActivityConfiguration(): ActivityConfiguration {
        return super.setActivityConfiguration().copy(enableBack = false, title = "Login")
    }
}