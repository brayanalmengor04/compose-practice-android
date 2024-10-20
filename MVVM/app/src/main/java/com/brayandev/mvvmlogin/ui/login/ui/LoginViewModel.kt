package com.brayandev.mvvmlogin.ui.login.ui

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class  LoginViewModel: ViewModel(){

    private val _email= MutableLiveData<String>()
    val email :LiveData<String> =_email

    private val _password= MutableLiveData<String>()
    val password :LiveData<String> =_password

    private val _loginEnable= MutableLiveData<Boolean>()
    val loginEnable :LiveData<Boolean> =_loginEnable

    private val _isLoading= MutableLiveData<Boolean>()
    val isLoading :LiveData<Boolean> =_isLoading





    fun onLoginChange(it: String, passsword: String) {
        _email.value= it
        _password.value= passsword
        _loginEnable.value = isValidEmail(it) && isValidPassword(passsword)
    }
    // Corregido para pasar el email como un String
    private fun isValidEmail(it: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(it).matches()

    private fun isValidPassword(passsword: String): Boolean = passsword.length>6

    // corrutina
    suspend fun onLoginSelected() {
        _isLoading.value = true
        delay(4000)
        _isLoading.value= false
    }


}