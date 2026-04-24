package com.bloem.app.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MenuOpen
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
import com.bloem.app.ui.theme.Soot
import com.bloem.app.ui.theme.Plaster
import com.bloem.app.ui.theme.Moss

@Composable
fun BookingScreen(onBack: () -> Unit, onViewSessions: () -> Unit) {
    var isSidebarVisible by remember { mutableStateOf(false) }
    var selectedDuration by remember { mutableIntStateOf(15) }
    var selectedTime by remember { mutableStateOf("10:00 AM") }

    Row(modifier = Modifier.fillMaxSize().background(Soot)) {
        AnimatedVisibility(visible = isSidebarVisible, enter = expandHorizontally(), exit = shrinkHorizontally()) {
            Sidebar(onHomeClick = onBack, currentRoute = "booking", onViewSessionsClick = onViewSessions, onBookClick = {})
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = if (isSidebarVisible) 40.dp else 0.dp, bottomStart = if (isSidebarVisible) 40.dp else 0.dp))
                .background(Plaster)
                .padding(48.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { isSidebarVisible = !isSidebarVisible }) {
                    Icon(imageVector = if (isSidebarVisible) Icons.Default.MenuOpen else Icons.Default.Menu, contentDescription = "Toggle Menu", tint = Soot, modifier = Modifier.size(32.dp))
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text("Book a Session", style = MaterialTheme.typography.displaySmall, fontWeight = FontWeight.Bold)
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            Text("Step into your chill capsule.\nTake a moment for yourself.", color = Color.Gray, fontSize = 20.sp)
            
            Spacer(modifier = Modifier.height(48.dp))
            Text("Select Duration", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                listOf(10, 15, 20).forEach { duration ->
                    DurationCard(duration = duration, isSelected = selectedDuration == duration, onClick = { selectedDuration = duration })
                }
            }
            
            Spacer(modifier = Modifier.height(48.dp))
            Text("Choose a Time", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(16.dp))
            val times = listOf("9:00 AM", "9:15 AM", "9:30 AM", "9:45 AM", "10:00 AM", "10:15 AM", "10:30 AM", "10:45 AM", "11:00 AM", "11:15 AM", "11:30 AM", "11:45 AM")
            LazyVerticalGrid(columns = GridCells.Fixed(4), horizontalArrangement = Arrangement.spacedBy(12.dp), verticalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.weight(1f)) {
                items(times) { time ->
                    TimeCard(time = time, isSelected = selectedTime == time, onClick = { selectedTime = time })
                }
            }
            
            Button(onClick = {}, modifier = Modifier.fillMaxWidth().height(64.dp), shape = RoundedCornerShape(16.dp), colors = ButtonDefaults.buttonColors(containerColor = Moss)) {
                Text("Continue", fontSize = 20.sp, color = Color.White)
            }
        }
    }
}
