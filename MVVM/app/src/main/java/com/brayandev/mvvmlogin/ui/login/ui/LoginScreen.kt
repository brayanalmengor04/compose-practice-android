package com.brayandev.mvvmlogin.ui.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brayandev.mvvmlogin.R
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Centramos la clumna pasando los parametros
        Login(Modifier.align(Alignment.Center), viewModel)
    }
}

// Le pasamos el centrado a la funcion


@Composable
fun Login(modifier: Modifier, viewModel: LoginViewModel) {
    // Esto es como un estado pero en el viewmodel toma un valor inicial
    val email by viewModel.email.observeAsState(initial = "")
    val passsword by viewModel.password.observeAsState(initial = "")
    val loginEnable: Boolean by viewModel.loginEnable.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val currutineScope = rememberCoroutineScope()

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        Column(modifier = modifier) {
            HeaderImage(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(16.dp))
            EmailField(email, { viewModel.onLoginChange(it, passsword) })
            Spacer(modifier = Modifier.padding(4.dp))
            PasswordField(passsword) { viewModel.onLoginChange(email, it) }
            Spacer(modifier = Modifier.padding(8.dp))
            ForgotPassword(Modifier.align(Alignment.End))
            Spacer(modifier = Modifier.padding(16.dp))

            LoginButton(loginEnable) {
                currutineScope.launch {
                    viewModel.onLoginSelected()
                }
            }

        }
    }


}

@Composable
fun LoginButton(loginEnable: Boolean, onLoginSelected: () -> Unit) {
    Button(
        onClick = { onLoginSelected() }, modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF1E1E1E),
            disabledContainerColor = Color(0xFF424242),
            contentColor = Color.White,
            disabledContentColor = Color.LightGray
        ), enabled = loginEnable
    ) {
        Text(text = "Log in")
    }

}

@Composable
fun ForgotPassword(modifierparam: Modifier) {
    Text(
        text = "Did you forget your password?",
        modifier = modifierparam.clickable { },
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White
    )
}

@Composable
fun PasswordField(passsword: String, onTextFieldChange: (String) -> Unit) {
    TextField(
        value = passsword, onValueChange = { onTextFieldChange(it) },
        placeholder = { Text(text = "Password") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1
    )
}


@Composable
fun EmailField(emailvalor: String, onTextFieldChange: (String) -> Unit) {

    TextField(
        // it hace que tome el valor de textfield que escriba
        value = emailvalor, onValueChange = { onTextFieldChange(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1
    )
}

@Composable
fun HeaderImage(modifier: Modifier) {
    Image(
        painter = painterResource(R.drawable.foodlogin),
        contentDescription = "Imagen login",
        modifier = modifier
    )
}
