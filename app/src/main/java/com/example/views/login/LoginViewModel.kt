package com.example.views.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.login.LoginWebService
import com.example.api.login.request.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel:ViewModel(){

    private val loginWebService = LoginWebService()

    val authTokenLiveData = MutableLiveData<String?>()
    val authenticationLiveData = MutableLiveData<Boolean>()


    //Llamada a webService pidiendo el token
    fun getAuthToken(){
       viewModelScope.launch(Dispatchers.IO) {
            val call = loginWebService.getAuthToken()?.execute()
            val body = call?.body()

            if (call?.isSuccessful == true) {
                authTokenLiveData.postValue(body?.token)
            } else {
                authTokenLiveData.postValue( null)
            }
        }
    }

    fun authenticate(username : String, password : String, authToken : String?){

        viewModelScope.launch(Dispatchers.IO) {
            val call = loginWebService.authenticate(
                request = LoginRequest(
                    username,
                    password,
                    authToken ?: ""
                )
            )?.execute()

            if(call?.isSuccessful == true){
                authenticationLiveData.postValue(true)
            }else{
                authenticationLiveData.postValue(false)

            }

        }


    }
}