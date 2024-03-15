package com.example.partone.presentation.signup

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
//import androidx.compose.material.icons.filled.Visibility
//import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.partone.R
import com.example.partone.presentation.navgraph.Routes
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch



@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
    navController : NavController,
    viewModel: SignupViewModel = hiltViewModel()
) {


//    val user = Firebase.auth.currentUser
//    if (user != null) {
//        navController.navigate(Routes.SoundNavigatorScreen.route)
//        // User is signed in
//    }


    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.signUpState.collectAsState(initial = null)

    var isPasswordVisible by remember {
        mutableStateOf(false)
    }

    val isFormValid by derivedStateOf {
        email.isNotBlank() && password.length >= 7
    }

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App logo",
            modifier = Modifier
                .weight(1f)
                .size(200.dp),
//            colorFilter = ColorFilter.tint(Color.White)
        )

        Card(
            modifier = Modifier
                .weight(2f)
                .padding(8.dp)
            ,
            shape = RoundedCornerShape(32.dp)
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(32.dp)
            ) {
                Text(
                    text = "Hello there!",
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    color = colorResource(id = R.color.text_medium),
                    style = MaterialTheme.typography.bodyMedium,
                )

                Column(
                    Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.weight(1f))

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = email,
                        textStyle = MaterialTheme.typography.bodyMedium,
                        onValueChange = {email = it} ,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Done
                        ),
                        label = { Text(text = "Email")},
                        trailingIcon = {
                            if (email.isNotBlank()){
                                IconButton(onClick = { email = "" }) {
                                    Icon(imageVector = Icons.Filled.Clear, contentDescription = "Clear field icon")
                                }
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = password,
                        onValueChange = {password = it} ,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        label = { Text(text = "Password")},
                        singleLine = true,
                        trailingIcon = {
                            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                                Icon(
                                    imageVector = if (isPasswordVisible) Icons.Default.Settings else Icons.Default.Home,
                                    contentDescription = "Clear field icon"
                                )
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        enabled = isFormValid,
                        shape = RoundedCornerShape(16.dp)
                        ,onClick = {
                            scope.launch {
                                viewModel.registerUser(email, password )
                            }
                        }
                    ) {
                        Text(
                            text = "Sign up",
                            style = MaterialTheme.typography.bodyMedium,
                            color = colorResource(id = R.color.text_medium)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        if (state.value?.isLoading == true){
                            CircularProgressIndicator()
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        TextButton( onClick = {
                            navController.navigate(Routes.LoginScreen.route)
                        }) {
                            Text(
                                text = "Already have an account? Log in",
                                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                                color = MaterialTheme.colorScheme.primary
                            )
                        }



                    }

                    LaunchedEffect(key1 = state.value?.isSuccess){
                        scope.launch {
                            if (state.value?.isSuccess?.isNotEmpty() == true){
                                val success = state.value?.isSuccess
                                Toast.makeText(context,"$success" , Toast.LENGTH_SHORT).show()
                                navController.navigate(Routes.SoundNavigatorScreen.route)
                            }
                        }
                    }
                    
                    LaunchedEffect(key1 = state.value?.isError){
                        scope.launch {
                            if (state.value?.isError?.isNotEmpty() == true){
                                val error = state.value?.isError
                                Toast.makeText(context,"$error" , Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                }
            }
        }
    }

}

