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

    private val _userList=MutableStateFlow<List<LoginUser>>(emptyList())
    val userList:StateFlow<List<LoginUser>> get() = _userList
    fun login(userName: String, password: String) {
        viewModelScope.launch {
            Log.d("TAG", "Attempting login with username: $userName, password: $password")

            val user = repo.getUser(userName, password)

            if (user != null) {
                _loginState.value = LoginState.Success
                Log.d("TAG", "Login successful for username: $userName")
            } else {
                Log.d("TAG", "User not found in database for username: $userName")
                _loginState.value = LoginState.Error("Invalid username or password")
            }
        }
    }

    fun resetLoginState() {
        _loginState.value = LoginState.Idle
    }

    // Function to fetch all users
    fun fetchUsers() {
        viewModelScope.launch {
            _userList.value = repo.getAllUsers()
        }
    }
}

sealed class LoginState {
    object Idle : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}
