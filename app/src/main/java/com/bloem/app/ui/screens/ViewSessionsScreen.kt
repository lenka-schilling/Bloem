package com.bloem.app.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
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
    var brightness by remember { mutableFloatStateOf(0.6f) }
    var selectedMood by remember { mutableStateOf("Calm") }

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
                // How it works panel at the top
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(24.dp))
                            .background(Eucalyptus.copy(alpha = 0.2f))
                            .padding(24.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier.size(64.dp).clip(CircleShape).background(Color.White), contentAlignment = Alignment.Center) {
                                Icon(Icons.Default.Eco, contentDescription = null, tint = Moss, modifier = Modifier.size(32.dp))
                            }
                            Spacer(modifier = Modifier.width(24.dp))
                            Column {
                                Text("How it works", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Soot)
                                Text("During a session, you can customize the lighting, sounds and mood to create your ideal environment.", color = Soot.copy(alpha = 0.7f))
                            }
                        }
                    }
                }

                item {
                    Text("Featured Environments", fontWeight = FontWeight.Bold, fontSize = 22.sp, color = Soot)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        EnvironmentCard("Calm", "Soft lighting and soothing sounds to help you relax.", Icons.Default.WbSunny, Color(0xFF2E3B2E), modifier = Modifier.weight(1f))
                        EnvironmentCard("Ocean", "Gentle waves and cool tones to wash away the stress.", Icons.Default.Water, Color(0xFF232D3F), modifier = Modifier.weight(1f))
                        EnvironmentCard("Rain", "The peaceful rhythm of rain for deep relaxation.", Icons.Default.Thunderstorm, Color(0xFF2C3E50), modifier = Modifier.weight(1f))
                        EnvironmentCard("Energetic", "Warm, vibrant lighting and uplifting sounds to boost your mood.", Icons.Default.Bolt, Color(0xFF4E342E), modifier = Modifier.weight(1f))
                    }
                }

                // Restore Lighting Options (Preview)
                item {
                    Text("Lighting Options (Preview)", fontWeight = FontWeight.Bold, fontSize = 22.sp, color = Soot)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        MoodCard("Calm", Icons.Default.WbSunny, selectedMood == "Calm") { selectedMood = "Calm" }
                        MoodCard("Ocean", Icons.Default.Water, selectedMood == "Ocean") { selectedMood = "Ocean" }
                        MoodCard("Rain", Icons.Default.Thunderstorm, selectedMood == "Rain") { selectedMood = "Rain" }
                        MoodCard("Energetic", Icons.Default.Bolt, selectedMood == "Energetic") { selectedMood = "Energetic" }
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    Text("Brightness", color = Soot, fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.WbSunny, contentDescription = null, tint = Soot, modifier = Modifier.size(20.dp))
                        Slider(
                            value = brightness,
                            onValueChange = { brightness = it },
                            modifier = Modifier.weight(1f).padding(horizontal = 16.dp),
                            colors = SliderDefaults.colors(thumbColor = Soot, activeTrackColor = Soot)
                        )
                        Icon(Icons.Default.WbSunny, contentDescription = null, tint = Soot, modifier = Modifier.size(28.dp))
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    Text("Color", color = Soot, fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
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
                    Text("Sound Options (Preview)", fontWeight = FontWeight.Bold, fontSize = 22.sp, color = Soot)
                    Spacer(modifier = Modifier.height(16.dp))
                    SoundItem("Ocean Waves", "Gentle surf", Icons.Default.Waves)
                    Spacer(modifier = Modifier.height(12.dp))
                    SoundItem("Forest Rain", "Rain in the woods", Icons.Default.Cloud)
                    Spacer(modifier = Modifier.height(12.dp))
                    SoundItem("Soft Wind", "Peaceful breeze", Icons.Default.Air)
                }
            }
        }
    }
}
