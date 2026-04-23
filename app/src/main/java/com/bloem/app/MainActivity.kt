package com.bloem.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bloem.app.ui.theme.BloemTheme
import com.bloem.app.ui.theme.Soot
import com.bloem.app.ui.theme.Plaster
import com.bloem.app.ui.theme.Moss

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BloemTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(onBookSession = { navController.navigate("booking") })
                    }
                    composable("booking") {
                        BookingScreen(onBack = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(onBookSession: () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bloem),
                    contentDescription = "Bloem Logo",
                    modifier = Modifier
                        .size(160.dp)
                        .clip(RoundedCornerShape(32.dp))
                )
                Spacer(modifier = Modifier.width(32.dp))
                Text(
                    text = "Bloem",
                    style = MaterialTheme.typography.displayLarge,
                    fontSize = 110.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = "Your moment of calm.\nRight where you are.",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 32.sp,
                lineHeight = 40.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
            )
            Spacer(modifier = Modifier.height(100.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterHorizontally)
            ) {
                Button(
                    onClick = onBookSession,
                    modifier = Modifier.height(80.dp).weight(1f),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text(text = "Book a Session", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier.height(80.dp).weight(1f),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(text = "Sessions", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun BookingScreen(onBack: () -> Unit) {
    var isSidebarVisible by remember { mutableStateOf(false) }
    var selectedDuration by remember { mutableIntStateOf(15) }
    var selectedTime by remember { mutableStateOf("10:00 AM") }

    Row(modifier = Modifier.fillMaxSize().background(Soot)) {
        // Sidebar
        AnimatedVisibility(
            visible = isSidebarVisible,
            enter = expandHorizontally(),
            exit = shrinkHorizontally()
        ) {
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
                    
                    SidebarItem(icon = Icons.Default.Home, label = "Home", onClick = onBack)
                    SidebarItem(icon = Icons.Default.DateRange, label = "Book", isSelected = true)
                    SidebarItem(icon = Icons.Default.ListAlt, label = "Sessions")
                }
                
                SidebarItem(icon = Icons.Default.Settings, label = "Settings")
            }
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
                .padding(48.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { isSidebarVisible = !isSidebarVisible }) {
                    Icon(
                        imageVector = if (isSidebarVisible) Icons.Default.MenuOpen else Icons.Default.Menu,
                        contentDescription = "Toggle Menu",
                        tint = Soot,
                        modifier = Modifier.size(32.dp)
                    )
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
                // Restricted to 10, 15, or 20 minutes
                listOf(10, 15, 20).forEach { duration ->
                    DurationCard(
                        duration = duration,
                        isSelected = selectedDuration == duration,
                        onClick = { selectedDuration = duration }
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(48.dp))
            
            Text("Choose a Time", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(16.dp))
            
            val times = listOf("9:00 AM", "9:15 AM", "9:30 AM", "9:45 AM", "10:00 AM", "10:15 AM", "10:30 AM", "10:45 AM", "11:00 AM", "11:15 AM", "11:30 AM", "11:45 AM")
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(times) { time ->
                    TimeCard(time = time, isSelected = selectedTime == time, onClick = { selectedTime = time })
                }
            }
            
            Button(
                onClick = { /* Handle Continue */ },
                modifier = Modifier.fillMaxWidth().height(64.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Moss)
            ) {
                Text("Continue", fontSize = 20.sp, color = Color.White)
            }
        }
    }
}

@Composable
fun SidebarItem(icon: ImageVector, label: String, isSelected: Boolean = false, onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(if (isSelected) Color.White.copy(alpha = 0.1f) else Color.Transparent)
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = label, tint = Color.White, modifier = Modifier.size(28.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(label, color = Color.White, fontSize = 20.sp)
    }
}

@Composable
fun DurationCard(duration: Int, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .size(width = 80.dp, height = 100.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) Soot else Color.LightGray.copy(alpha = 0.3f))
            .clickable(onClick = onClick)
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(duration.toString(), fontWeight = FontWeight.Bold, fontSize = 24.sp, color = if (isSelected) Color.White else Soot)
        Text("min", fontSize = 14.sp, color = if (isSelected) Color.White else Soot)
    }
}

@Composable
fun TimeCard(time: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(if (isSelected) Soot else Color.LightGray.copy(alpha = 0.3f))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(time, fontWeight = FontWeight.Medium, fontSize = 16.sp, color = if (isSelected) Color.White else Soot)
    }
}

@Preview(showBackground = true, device = "spec:width=1280dp,height=800dp,orientation=landscape")
@Composable
fun BookingPreview() {
    BloemTheme {
        BookingScreen {}
    }
}
