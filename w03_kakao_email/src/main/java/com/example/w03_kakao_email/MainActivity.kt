package com.example.w03_kakao_email

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w03_kakao_email.ui.theme.App_01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App_01Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFFFEBEE) // 연한 분홍
                ) {
                    KakaoEmailScreen()
                }
            }
        }
    }
}

@Composable
fun KakaoEmailScreen() {
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues())
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "카카오 계정에 로그인합니다.",
            fontSize = 17.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "uziyon1220@gmail.com",
            color = Color(0xFFCFCFCE),
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
        )

        Spacer(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(Color(0xFFD4D4D3))
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("비밀번호") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrectEnabled = false,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "비밀번호를 입력하세요.",
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
        )

        Button(
            onClick = { /* TODO: 로그인 처리 */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text("확인")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KakaoEmailPreview() {
    App_01Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFFFFEBEE)
        ) {
            KakaoEmailScreen()
        }
    }
}