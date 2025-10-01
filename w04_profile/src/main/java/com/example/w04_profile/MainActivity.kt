package com.example.w04_profile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.w04_profile.ui.theme.App_01Theme
import androidx.compose.ui.graphics.Color

data class Profile(val name: String, val description: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App_01Theme {
                val profile = Profile("우지연", "Android 개발자 & Compose 학습자")
                ProfileScreen(profile)
            }
        }
    }
}
// 프로필을 조금 내려서 시작
@Composable
fun ProfileScreen(profile: Profile) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(70.dp))           // ↓ 프로필 살짝 내림
        ProfileCard(profile)
        Spacer(Modifier.height(20.dp))

        val line = Color(0xFFE5E5E5)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 200.dp)         // 살짝 낮춤
        ) {
            Row(
                modifier = Modifier.weight(1f).fillMaxWidth()
            ) {
                GridCell(Modifier.weight(1f), dense = true) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        InfoItem(R.drawable.hobby, "hobby")
                        Text("영화 보기", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                    }
                }
                Box(Modifier.width(0.5.dp).fillMaxHeight().background(line))
                GridCell(Modifier.weight(1f), dense = true) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        InfoItem(R.drawable.food, "favorite food")
                        Text(text ="짜장면", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                    }
                }
            }
            Box(Modifier.fillMaxWidth().height(0.5.dp).background(line))
            Row(
                modifier = Modifier.weight(1f).fillMaxWidth()
            ) {
                GridCell(Modifier.weight(1f), dense = true){
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        InfoItem(R.drawable.music, "favorite music")
                        Text(
                            text = "115万キロのフィルム",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
                }
                Box(Modifier.width(0.5.dp).fillMaxHeight().background(line))
                GridCell(Modifier.weight(1f), dense = true) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        InfoItem(R.drawable.game, "favorite game")
                        Text(
                            text = "Clash of Clans",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun GridCell(
    modifier: Modifier = Modifier,
    dense: Boolean = false,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                horizontal = if (dense) 8.dp else 12.dp,
                vertical = if (dense) 8.dp else 12.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        content = content
    )
}

@Composable
fun ProfileCard(profile: Profile) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(R.drawable.profile),
            contentDescription = "프로필 사진",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.width(12.dp))
        Column {
            Text(
                text = profile.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = profile.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
fun InfoItem(imageRes: Int, label: String) {
    // 정사각형 + 크기 업
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = label,
        modifier = Modifier.size(140.dp),   // 96→112로 확대
        contentScale = ContentScale.Crop
    )
    Spacer(Modifier.height(6.dp))
    Text(
        text = label,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewProfile() {
    App_01Theme {
        ProfileScreen(Profile("우지연", "Android 개발자 & Compose 학습자"))
    }
}