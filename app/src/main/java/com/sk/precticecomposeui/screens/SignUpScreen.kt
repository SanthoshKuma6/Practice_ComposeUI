package com.sk.precticecomposeui.screens

import android.graphics.Paint.Align
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sk.precticecomposeui.roomdb.AppDatabase
import com.sk.precticecomposeui.roomdb.LoginUser
import com.sk.precticecomposeui.roomdb.mvvm.LoginRepo
import com.sk.precticecomposeui.roomdb.mvvm.LoginViewModel
import com.sk.precticecomposeui.roomdb.mvvm.ViewModelFactory
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            var userName by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var showToast by remember { mutableStateOf(false) }
            var toastMessage by remember { mutableStateOf("") }

            val context = LocalContext.current
            val repo = remember { LoginRepo(AppDatabase.getDatabase(context)) }
            val factory = remember { ViewModelFactory(repo) }
            val viewModel: LoginViewModel = viewModel(factory = factory)
            val coroutineScope = rememberCoroutineScope()
            Column(modifier = Modifier) {
                Text(
                    text = "Sign Up",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold

                )
                Spacer(modifier = Modifier.heightIn(20.dp))

                OutlinedTextField(
                    value = userName,
                    singleLine = true,
                    placeholder = { Text(text = "UserName") },
                    onValueChange = {
                        userName = it
                    },
                    modifier = Modifier
                        .fillMaxWidth(1.0f)
                        .padding(horizontal = 16.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.heightIn(20.dp))

                OutlinedTextField(
                    value = password,
                    singleLine = true,
                    placeholder = { Text(text = "Password") },
                    onValueChange = {
                        password = it
                    },
                    modifier = Modifier
                        .fillMaxWidth(1.0f)
                        .padding(horizontal = 16.dp)
                        .align(Alignment.CenterHorizontally)
                )


                Spacer(modifier = Modifier.heightIn(50.dp))

                Button(modifier = Modifier
                    .width(150.dp)
                    .align(Alignment.CenterHorizontally),
                    onClick = {
                        if (userName.isEmpty()) {
                            toastMessage = "enter the valid username"
                            showToast = true
                        } else if (password.isEmpty()) {
                            toastMessage = "enter the valid password"
                            showToast = true
                        } else {
                            val userLogin = LoginUser(userName = userName, password = password)
                            coroutineScope.launch {
                                try {
                                    viewModel.loginInsert(userLogin)
                                    showToast = true
                                    toastMessage = "Sign Up Successfully"
                                    navController.navigate("LoginScreen")

                                } catch (e: Exception) {
                                    e.printStackTrace()
                                    showToast = true
                                    toastMessage = "Something went wrong"
                                }
                            }
                        }
                    }) {
                    Text(text = "Submit")

                }
            }

        }

    }
}

@Preview
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(navController = rememberNavController())
}