package com.example.guessit.presentation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.guessit.R
import com.example.guessit.presentation.Navigation.HOMESCREEN
import com.example.guessit.presentation.Navigation.SIGNUPSCREEN
import com.example.guessit.presentation.ViewModel.UserAuthenticationViewModel
import com.shashank.sony.fancytoastlib.FancyToast

@Composable
fun LoginScreen(navController: NavController,viewModel: UserAuthenticationViewModel = hiltViewModel()) {
    val loginState = viewModel.loginState.collectAsState()
    val context = LocalContext.current
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    if (loginState.value.isLoading) {
        LoadingBar()
    } else if (loginState.value.error != null) {
        TODO()
    } else if (loginState.value.data != null) {
        LaunchedEffect(Unit) {
            navController.navigate(HOMESCREEN) {
                popUpTo(0)
            }
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.LightTheme)) // Soft pink background
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(0.9f),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White) // Soft contrast card
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Title
                    Text(
                        text = "Welcome Back 👋",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.DarkTheme) // Deep pink for title
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Error Message


                    // Email Input
                    OutlinedTextField(
                        value = email.value,
                        onValueChange = { email.value = it },
                        label = { Text("Email Address") },
                        leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Password Input
                    OutlinedTextField(
                        value = password.value,
                        onValueChange = { password.value = it },
                        label = { Text("Password") },
                        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation()
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Login Button
                    Button(
                        onClick = {
                            if (email.value.isNotEmpty() && password.value.isNotEmpty()){
                                viewModel.login(email = email.value, password = password.value)

                            }else if (password.value.length < 6){
                                FancyToast.makeText(context,"Password should be of 6 character",
                                    FancyToast.LENGTH_SHORT,
                                    FancyToast.WARNING,true).show()
                            }else{
                                FancyToast.makeText(context,"Enter All Fields",FancyToast.LENGTH_SHORT,FancyToast.WARNING,true).show()
                            }


                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        elevation = ButtonDefaults.buttonElevation(6.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.DarkTheme), // Deep pink button
                            contentColor = Color.White // White text
                        )
                    ) {
                        Text(text = "Login", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Signup Text
                    TextButton(
                        onClick = {
                            navController.navigate(SIGNUPSCREEN) {
                                popUpTo(0)
                            }
                        }
                    ) {
                        Text(
                            text = "Don't have an account? Sign up",
                            color = colorResource(id = R.color.DarkTheme) // Deep pink text
                        )
                    }
                }
            }

            // Loading Indicator

        }
    }
}