package com.bloem.app.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Air
import androidx.compose.material.icons.outlined.Park
import androidx.compose.material.icons.outlined.Water
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bloem.app.ui.components.*
import com.bloem.app.ui.theme.*

@Composable
fun ViewSessionsScreen(onBack: () -> Unit) {
    var isSidebarVisible by remember { mutableStateOf(false) }

    Row(modifier = Modifier.fillMaxSize().background(Soot)) {
        // Sidebar with toggle animation
        AnimatedVisibility(
            visible = isSidebarVisible,
            enter = expandHorizontally(),
            exit = shrinkHorizontally()
        ) {
            Sidebar(
                onHomeClick = onBack,
                currentRoute = "view_sessions",
                onViewSessionsClick = {},
                onBookClick = {}
            )
        }

        // Main Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(
                    topStart = if (isSidebarVisible) 40.dp else 0.dp,
                    bottomStart = if (isSidebarVisible) 40.dp else 0.dp
                ))
                .background(Plaster)
                .padding(40.dp)
        ) {
            // Header with toggle button
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { isSidebarVisible = !isSidebarVisible }) {
                    Icon(
                        imageVector = if (isSidebarVisible) Icons.Default.MenuOpen else Icons.Default.Menu,
                        contentDescription = "Toggle Menu",
                        tint = Soot,
                        modifier = Modifier.size(32.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                    Text("Explore Sessions", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold, color = Soot)
                    Text("Preview the environments available in the capsule.", color = Color.Gray, fontSize = 18.sp)
                }
                Spacer(modifier = Modifier.width(48.dp))
            }

            Spacer(modifier = Modifier.height(32.dp))

            LazyColumn(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(32.dp)) {
                item {
                    Text("Featured Environments", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Soot)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        EnvironmentCard("Calm", "Soft lighting, gentle sounds, total relaxation.", Icons.Default.WbSunny, Color(0xFF2E3B2E), modifier = Modifier.weight(1f))
                        EnvironmentCard("Focus", "Neutral tones and soothing sounds to help you concentrate.", Icons.Default.FilterTiltShift, Color(0xFF232D3F), modifier = Modifier.weight(1f))
                        EnvironmentCard("Energy", "Bright lights and upbeat sounds to boost your energy.", Icons.Default.Bolt, Color(0xFF4E342E), modifier = Modifier.weight(1f))
                    }
                }

                item {
                    Text("Lighting Options (Preview)", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Soot)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        MoodCard("Calm", Icons.Default.WbSunny, true) {}
                        MoodCard("Focus", Icons.Default.FilterTiltShift, false) {}
                        MoodCard("Energy", Icons.Default.Bolt, false) {}
                        MoodCard("Custom", Icons.Default.AutoAwesome, false) {}
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    Text("Brightness", color = Soot, fontWeight = FontWeight.SemiBold)
                    Slider(value = 0.6f, onValueChange = {}, colors = SliderDefaults.colors(thumbColor = Soot, activeTrackColor = Soot))
                    
                    Text("Color", color = Soot, fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        ColorOption(Soot)
                        ColorOption(Moss)
                        ColorOption(Eucalyptus)
                        ColorOption(Color.White, true)
                        ColorOption(Mist)
                        ColorOption(Color(0xFFB39DDB))
                        ColorOption(Color(0xFFEF9A9A))
                    }
                }

                item {
                    Text("Sound Options (Preview)", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Soot)
                    Spacer(modifier = Modifier.height(16.dp))
                    SoundItem("Ocean Waves", "Gentle surf", Icons.Outlined.Water)
                    Spacer(modifier = Modifier.height(8.dp))
                    SoundItem("Forest Rain", "Rain in the woods", Icons.Outlined.Park)
                    Spacer(modifier = Modifier.height(8.dp))
                    SoundItem("Soft Wind", "Peaceful breeze", Icons.Outlined.Air)
                }

                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(24.dp))
                            .background(Color.LightGray.copy(alpha = 0.2f))
                            .padding(24.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier.size(64.dp).clip(androidx.compose.foundation.shape.CircleShape).background(Color.White), contentAlignment = Alignment.Center) {
                                Icon(Icons.Default.Eco, contentDescription = null, tint = Moss, modifier = Modifier.size(32.dp))
                            }
                            Spacer(modifier = Modifier.width(24.dp))
                            Column {
                                Text("How it works", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Soot)
                                Text("During a session, you can customize the lighting, sounds and mood to create your ideal environment.", color = Color.Gray)
                            }
                        }
                    }
                }
            }
        }
    }
}
