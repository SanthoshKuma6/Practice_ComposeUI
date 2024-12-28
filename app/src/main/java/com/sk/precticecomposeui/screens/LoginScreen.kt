package com.sk.precticecomposeui.screens

import android.widget.Toast
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun LoginScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            var userName by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var showToast by remember { mutableStateOf(false) }
            var toastMessage by remember { mutableStateOf("") }

            Column(modifier = Modifier) {
                Text(
                    text = "Login",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.heightIn(20.dp))

                OutlinedTextField(value = userName,
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

                OutlinedTextField(value = password,
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
                            navController.navigate("HomeScreen") {
                                popUpTo("LoginScreen") { inclusive = true }
                            }

                        }


                    }) {
                    Text(text = "Submit")

                }
                if (showToast) {
                    Toast.makeText(LocalContext.current, toastMessage, Toast.LENGTH_SHORT).show()
                    showToast=false
                }

            }


        }

    }

}

@Preview
@Composable
fun LoginScreenPReview() {
    LoginScreen(navController = rememberNavController())
}