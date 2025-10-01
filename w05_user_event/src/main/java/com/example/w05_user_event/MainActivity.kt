package com.example.w05_user_event

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.w05_user_event.ui.theme.App_01Theme
import kotlinx.coroutines.delay
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.clickable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App_01Theme {
                // TODO: 여기에 카운터와 스톱워치 UI를 만들도록 안내
                val count = remember { mutableStateOf(0) }
                Column(
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically) // 간격 축소 + 중앙 정렬
                ) {
                    CounterApp(count)
                    StopWatchApp()
                    ColorToggleButtonApp()
                }
            }
        }
    }
}
@Composable
fun CounterApp(count: MutableState<Int>) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Count: ${count.value}") // TODO: 상태값 표시
        Row {
            Button(onClick = { count.value++ }) { Text("Increase") }
            Button(onClick = { count.value = 0 }) { Text("Reset") }
        }
    }
}
@Composable
fun StopWatchApp() {
    // 스톱워치의 시간(초)을 저장하는 상태 변수: 초깃값=15 * 60 + 22
    var seconds by remember { mutableStateOf(15 * 60 + 22) }
    // 스톱워치가 실행 중인지 여부를 저장하는 상태 변수
    var isRunning by remember { mutableStateOf(false) }

    // isRunning 상태가 변경될 때마다 LaunchedEffect가 실행됨
    LaunchedEffect(isRunning) {
        // isRunning이 true일 때만 아래의 무한 루프를 실행
        if (isRunning) {
            while (true) {
                delay(1000) // 1초 대기
                seconds++   // 1초 증가
            }
        }
    }

    StopWatchScreen(
        seconds = seconds,
        isRunning = isRunning,
        onStartClick = { isRunning = true },
        onStopClick = { isRunning = false },
        onResetClick = {
            seconds = 0
            isRunning = false
        }
    )
}
@Composable
fun StopWatchScreen(
    seconds: Int,
    isRunning: Boolean,
    onStartClick: () -> Unit,
    onStopClick: () -> Unit,
    onResetClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.wrapContentHeight(), // fillMaxSize 제거
        verticalArrangement = Arrangement.spacedBy(12.dp), // 간격 축소
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val minutes = seconds / 60
        val secondsToDisplay = seconds % 60
        val timeFormatted = String.format("%02d:%02d", minutes, secondsToDisplay)

        Text(text = timeFormatted, fontSize = 72.sp, fontWeight = FontWeight.Bold) // 80→72

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp), // 버튼 간격 16→8
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = onStartClick, enabled = !isRunning) { Text("Start") }
            Button(onClick = onStopClick, enabled = isRunning) { Text("Stop") }
            Button(onClick = onResetClick) { Text("Reset") }
        }
    }
}

@Composable
fun ColorToggleButtonApp() {
    var currentColor by remember { mutableStateOf(Color.Red) }

    Box(
        modifier = Modifier
            .size(120.dp)                 // 고정 지름
            .clip(CircleShape)
            .background(currentColor)
            .clickable {
                currentColor = if (currentColor == Color.Red) Color.Blue else Color.Red
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Click Me",
            color = Color.White,
            fontSize = 24.sp,             // 30sp → 24sp 권장
            maxLines = 1,
            softWrap = false
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CounterAppPreview() {
    val count = remember { mutableStateOf(0) }
    CounterApp(count)
}

@Preview(showBackground = true)
@Composable
fun StopWatchPreview() {
    StopWatchApp()
}
@Preview(showBackground = true, widthDp = 300, heightDp = 300)
@Composable
fun ColorToggleButtonAppPreview() {
    ColorToggleButtonApp()
}