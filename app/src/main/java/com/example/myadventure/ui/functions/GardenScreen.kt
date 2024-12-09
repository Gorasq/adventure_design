package com.example.myadventure.ui.functions


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myadventure.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GardenScreen(navController: NavController) {
    var showDiaryDialog by remember { mutableStateOf(false) }
    var showCapsuleDialog by remember { mutableStateOf(false) }
    var showCapsuleIcon by remember { mutableStateOf(false) }

    Scaffold(

        containerColor = Color(0xFFF2E4DA),
        topBar = {
            TopAppBar(
                title = { Text("캡슐 정원") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "뒤로가기")
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color(0xFFF2E4DA))
            )
        },

        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(Color(0xFFF2E4DA))
            ) {
//                 배경 그림
                Image(
                    painter = painterResource(id = R.drawable.ic_gardenground), // 배경 그림 파일
                    contentDescription = "배경",
                    modifier = Modifier.fillMaxSize()
                )

//                 팻말
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 24.dp, bottom = 80.dp)
                        .scale(0.8f)
                        .clickable { showDiaryDialog = true } // 팻말을 클릭 시 다이얼로그 표시
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_signpost), // 팻말 그림 파일
                        contentDescription = "팻말"
                    )
                }

                // 타임캡슐 아이콘 (선택적으로 표시)
                if (showCapsuleIcon) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_pill), // 타임캡슐 아이콘
                        contentDescription = "타임캡슐",
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .scale(0.4f)
                            .padding(50.dp)
                    )
                }

                // 배경 특정 영역을 클릭하면 타임캡슐 다이얼로그 표시
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { navController.navigate("shop_screen") }
                )
            }

            // 다이어리 페이지 이동 확인 다이얼로그
            if (showDiaryDialog) {
                AlertDialog(
                    onDismissRequest = { showDiaryDialog = false },
                    title = { Text("다이어리를 보시겠습니까?") },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                navController.navigate("diary_screen") // 다이어리 페이지로 이동
                                showDiaryDialog = false
                            }
                        ) {
                            Text("예")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDiaryDialog = false }) {
                            Text("아니오")
                        }
                    }
                )
            }

            // 타임캡슐 넣기 확인 다이얼로그
            if (showCapsuleDialog) {
                AlertDialog(
                    onDismissRequest = { showCapsuleDialog = false },
                    title = { Text("타임캡슐을 넣으시겠습니까?") },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                showCapsuleIcon = true // 타임캡슐 아이콘 표시
                                showCapsuleDialog = false
                            }
                        ) {
                            Text("예")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showCapsuleDialog = false }) {
                            Text("아니dy")
                        }
                    }
                )
            }
        }
    )
}


