package com.sk.precticecomposeui.roomdb.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sk.precticecomposeui.roomdb.LoginUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class LoginViewModel(private val repo: LoginRepo) : ViewModel() {
    suspend fun loginInsert(loginUser: LoginUser) = viewModelScope.launch {
        try {
            repo.loginInsert(loginUser)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(userName: String, password: String) {
        viewModelScope.launch {
            val user = repo.getUser(userName, password)
            Log.d("TAG", "login: $user")
            if (user != null) {
                _loginState.value = LoginState.Success
                Log.d("TAG","Login Success")
            } else {
                Log.d("TAG", "login Invalide")
                _loginState.value = LoginState.Error("Invalid username or password")
            }
        }
    }

}

sealed class LoginState {
    object Idle : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}