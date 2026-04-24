package com.bloem.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.FilterTiltShift
import androidx.compose.material.icons.filled.WbSunny
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
fun ControlSessionScreen(onBack: () -> Unit) {
    var lightsOn by remember { mutableStateOf(true) }
    var soundOn by remember { mutableStateOf(true) }
    var brightness by remember { mutableFloatStateOf(0.6f) }
    var selectedMood by remember { mutableStateOf("Calm") }
    var selectedSoundType by remember { mutableStateOf("Sounds") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Plaster
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(40.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Soot)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                    Text("Your Environment", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, color = Soot)
                    Text("Tailor the space to how you feel.", color = Color.Gray, fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.width(48.dp))
            }

            Spacer(modifier = Modifier.height(32.dp))

            LazyColumn(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(32.dp)) {
                item {
                    Column {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                            Text("Lights", fontWeight = FontWeight.Bold, fontSize = 24.sp, color = Soot)
                            Switch(checked = lightsOn, onCheckedChange = { lightsOn = it }, colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = Moss))
                        }
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Mood", color = Soot, fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            MoodCard("Calm", Icons.Default.WbSunny, selectedMood == "Calm") { selectedMood = "Calm" }
                            MoodCard("Focus", Icons.Default.FilterTiltShift, selectedMood == "Focus") { selectedMood = "Focus" }
                            MoodCard("Energy", Icons.Default.Bolt, selectedMood == "Energy") { selectedMood = "Energy" }
                            MoodCard("Custom", Icons.Default.AutoAwesome, selectedMood == "Custom") { selectedMood = "Custom" }
                        }

                        Spacer(modifier = Modifier.height(24.dp))
                        Text("Brightness", color = Soot, fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.WbSunny, contentDescription = null, tint = Soot, modifier = Modifier.size(20.dp))
                            Slider(value = brightness, onValueChange = { brightness = it }, modifier = Modifier.weight(1f).padding(horizontal = 16.dp), colors = SliderDefaults.colors(thumbColor = Soot, activeTrackColor = Soot))
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
                }

                item {
                    Column {
                        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), thickness = 1.dp, color = Color.LightGray.copy(alpha = 0.5f))
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                            Text("Sound", fontWeight = FontWeight.Bold, fontSize = 24.sp, color = Soot)
                            Switch(checked = soundOn, onCheckedChange = { soundOn = it }, colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = Moss))
                        }
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(Color.LightGray.copy(alpha = 0.2f))) {
                            TabButton("Sounds", selectedSoundType == "Sounds") { selectedSoundType = "Sounds" }
                            TabButton("Music", selectedSoundType == "Music") { selectedSoundType = "Music" }
                            TabButton("Silence", selectedSoundType == "Silence") { selectedSoundType = "Silence" }
                        }

                        Spacer(modifier = Modifier.height(24.dp))
                        SoundItem("Ocean Waves", "Gentle surf", Icons.Outlined.Water)
                        Spacer(modifier = Modifier.height(12.dp))
                        SoundItem("Forest Rain", "Rain in the woods", Icons.Outlined.Park)
                        Spacer(modifier = Modifier.height(12.dp))
                        SoundItem("Soft Wind", "Peaceful breeze", Icons.Outlined.Air)
                    }
                }
            }
        }
    }
}
