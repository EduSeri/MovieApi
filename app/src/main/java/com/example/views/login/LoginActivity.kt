package com.example.views.login

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.api.RetrofitManager
import com.example.movieapi.R
import com.example.movieapi.databinding.ActivityLoginBinding
import com.example.views.home.HomeActivity
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {

    companion object{
        const val USERNAME="username"
    }
    //Controlador de la vista
    private val viewModel = LoginViewModel()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Inicializa retrofit
        RetrofitManager().initiateRetrofit(this)

        binding.loginButton.setOnClickListener{
            viewModel.getAuthToken()
        }

        binding.continueButton.setOnClickListener{
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in,R.anim.slide_out)
            clearFields()

        }
        binding.registerButton.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.themoviedb.org/signup"))
            startActivity(intent)
        }

        viewModel.authTokenLiveData.observeForever { authToken ->
            if (checkValues()) {
                viewModel.authenticate(
                    binding.userNameText.text.toString(),
                    binding.userPasswordText.text.toString(),
                    authToken
                )
            }
            else{
                Toast.makeText(this,"No puede haber campos vacios", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.authenticationLiveData.observeForever { success ->
            if(success){
                val intent = Intent(this,HomeActivity::class.java)
                intent.putExtra(USERNAME, binding.userNameText.text.toString())
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out)
                clearFields()
            }else{
                Toast.makeText(this,"Campos incorrectos", Toast.LENGTH_SHORT).show()

            }

        }

    }

    private fun clearFields()= with(binding){
        userNameText.text?.clear()
        userPasswordText.text?.clear()
        userNameText.clearFocus()
        passwordInputLayout.clearFocus()
    }

    private fun checkValues() = with(binding){
        userNameText.text.toString().isNotEmpty() &&
        userPasswordText.text.toString().isNotEmpty()
    }


}