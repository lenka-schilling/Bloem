package com.bloem.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bloem.app.R
import com.bloem.app.ui.theme.Soot
import com.bloem.app.ui.theme.Moss

@Composable
fun Sidebar(onHomeClick: () -> Unit, currentRoute: String, onBookClick: () -> Unit, onViewSessionsClick: () -> Unit) {
    Column(
        modifier = Modifier
            .width(280.dp)
            .fillMaxHeight()
            .padding(vertical = 48.dp, horizontal = 24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.bloem),
                    contentDescription = "Logo",
                    modifier = Modifier.size(48.dp).clip(RoundedCornerShape(12.dp))
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text("Bloem", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Bold)
            }
            
            Spacer(modifier = Modifier.height(64.dp))
            
            SidebarItem(icon = Icons.Default.Home, label = "Home", isSelected = currentRoute == "home", onClick = onHomeClick)
            SidebarItem(icon = Icons.Default.DateRange, label = "Book", isSelected = currentRoute == "booking", onClick = onBookClick)
            SidebarItem(icon = Icons.Default.ListAlt, label = "Sessions", isSelected = currentRoute == "view_sessions", onClick = onViewSessionsClick)
        }
        
        SidebarItem(icon = Icons.Default.Settings, label = "Settings")
    }
}

@Composable
fun SidebarItem(icon: ImageVector, label: String, isSelected: Boolean = false, onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(if (isSelected) Color.White.copy(alpha = 0.1f) else Color.Transparent).clickable(onClick = onClick).padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = label, tint = Color.White, modifier = Modifier.size(28.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(label, color = Color.White, fontSize = 20.sp)
    }
}

@Composable
fun MoodCard(label: String, icon: ImageVector, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) Soot else Color.LightGray.copy(alpha = 0.4f))
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(icon, contentDescription = null, tint = if (isSelected) Color.White else Soot, modifier = Modifier.size(32.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Text(label, color = if (isSelected) Color.White else Soot, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun ColorOption(color: Color, hasBorder: Boolean = false) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(color)
            .then(if (hasBorder) Modifier.background(Color.LightGray.copy(alpha = 0.5f)).padding(1.dp).clip(CircleShape).background(color) else Modifier)
    )
}

@Composable
fun RowScope.TabButton(label: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier.weight(1f).height(48.dp).clip(RoundedCornerShape(12.dp)).background(if (isSelected) Soot else Color.Transparent).clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(label, color = if (isSelected) Color.White else Soot, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun SoundItem(title: String, subtitle: String, icon: ImageVector) {
    Row(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(Color.LightGray.copy(alpha = 0.3f)).padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = Soot, modifier = Modifier.size(32.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.Bold, color = Soot, fontSize = 18.sp)
            Text(subtitle, color = Color.Gray, fontSize = 14.sp)
        }
        Icon(Icons.Default.PlayCircle, contentDescription = "Play", tint = Moss, modifier = Modifier.size(32.dp))
    }
}

@Composable
fun DurationCard(duration: Int, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        modifier = Modifier.size(width = 80.dp, height = 100.dp).clip(RoundedCornerShape(16.dp)).background(if (isSelected) Soot else Color.LightGray.copy(alpha = 0.3f)).clickable(onClick = onClick).padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Text(duration.toString(), fontWeight = FontWeight.Bold, fontSize = 24.sp, color = if (isSelected) Color.White else Soot)
        Text("min", fontSize = 14.sp, color = if (isSelected) Color.White else Soot)
    }
}

@Composable
fun TimeCard(time: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth().height(60.dp).clip(RoundedCornerShape(12.dp)).background(if (isSelected) Soot else Color.LightGray.copy(alpha = 0.3f)).clickable(onClick = onClick), contentAlignment = Alignment.Center) {
        Text(time, fontWeight = FontWeight.Medium, fontSize = 16.sp, color = if (isSelected) Color.White else Soot)
    }
}

@Composable
fun EnvironmentCard(title: String, description: String, icon: ImageVector, bgColor: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(280.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(bgColor)
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Box(modifier = Modifier.size(64.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.1f)), contentAlignment = Alignment.Center) {
                Icon(icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(32.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 22.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(description, color = Color.White.copy(alpha = 0.8f), textAlign = TextAlign.Center, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Soot),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.height(40.dp)
            ) {
                Text("View details", fontSize = 12.sp)
            }
        }
    }
}
