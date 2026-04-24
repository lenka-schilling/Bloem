package com.bloem.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Eco
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bloem.app.R
import com.bloem.app.ui.theme.Eucalyptus
import com.bloem.app.ui.theme.Soot
import com.bloem.app.ui.theme.Plaster

@Composable
fun HomeScreen(onBookSession: () -> Unit, onControlSession: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.bg_home),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 48.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Logo and Title Row - No white background, restored logo size
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(bottom = 24.dp)
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
                        color = Soot,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Tagline - No background, added shadow for "outline" effect
                Text(
                    text = "Your moment of calm.\nRight where you are.",
                    style = TextStyle(
                        fontSize = 32.sp,
                        lineHeight = 40.sp,
                        textAlign = TextAlign.Center,
                        color = Soot,
                        fontWeight = FontWeight.Bold,
                        shadow = Shadow(
                            color = Color.White.copy(alpha = 0.5f),
                            blurRadius = 8f
                        )
                    ),
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
                )

                Spacer(modifier = Modifier.height(48.dp))

                // Middle Info Card - Plaster color
                Card(
                    modifier = Modifier.widthIn(max = 500.dp),
                    shape = RoundedCornerShape(32.dp),
                    colors = CardDefaults.cardColors(containerColor = Plaster.copy(alpha = 0.9f)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        InfoRow(
                            icon = Icons.Outlined.Eco,
                            title = "Relax. Recharge. Reset.",
                            subtitle = "Step into your chill capsule and take a moment just for you."
                        )
                        HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Soot.copy(alpha = 0.2f))
                        InfoRow(
                            icon = Icons.Outlined.DateRange,
                            title = "Book a Session",
                            subtitle = "Choose a time that works for you and escape the noise."
                        )
                        HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Soot.copy(alpha = 0.2f))
                        InfoRow(
                            icon = Icons.Outlined.Schedule,
                            title = "Your time, your space.",
                            subtitle = "Already booked a session? Start or manage it with ease."
                        )
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))

                // Question text with background
                Surface(
                    color = Color.White.copy(alpha = 0.8f),
                    shape = CircleShape
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = "What would you like to do?",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Soot
                        )
                        Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = Soot)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Action Buttons
                Column(
                    modifier = Modifier.widthIn(max = 600.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    ActionButton(
                        icon = Icons.Outlined.DateRange,
                        title = "Book a Session",
                        subtitle = "Schedule a new session for yourself.",
                        containerColor = Soot,
                        contentColor = Color.White,
                        onClick = onBookSession,
                        height = 100.dp
                    )

                    ActionButton(
                        icon = Icons.Outlined.Schedule,
                        title = "Control your Session",
                        subtitle = "Manage your upcoming or active session.",
                        containerColor = Color.White,
                        contentColor = Soot,
                        onClick = onControlSession,
                        height = 100.dp
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Bottom Tip Box - Switched to Plaster
                Surface(
                    modifier = Modifier.widthIn(max = 600.dp),
                    shape = RoundedCornerShape(20.dp),
                    color = Plaster.copy(alpha = 0.9f),
                    shadowElevation = 4.dp
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Outlined.Info, contentDescription = null, tint = Soot, modifier = Modifier.size(28.dp))
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "If you have already booked a session and it is supposed to start now, go to Control your Session.",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Soot
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun InfoRow(icon: ImageVector, title: String, subtitle: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Soot.copy(alpha = 0.05f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = Soot, modifier = Modifier.size(24.dp))
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Soot)
            Text(text = subtitle, fontSize = 13.sp, color = Soot.copy(alpha = 0.8f))
        }
    }
}

@Composable
fun ActionButton(
    icon: ImageVector,
    title: String,
    subtitle: String,
    containerColor: Color,
    contentColor: Color,
    onClick: () -> Unit,
    height: androidx.compose.ui.unit.Dp = 80.dp
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(24.dp),
        color = containerColor,
        modifier = Modifier.fillMaxWidth().height(height),
        shadowElevation = 6.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null, tint = contentColor, modifier = Modifier.size(40.dp))
            Spacer(modifier = Modifier.width(24.dp))
            Column {
                Text(text = title, fontWeight = FontWeight.Bold, fontSize = 22.sp, color = contentColor)
                Text(text = subtitle, fontSize = 14.sp, color = contentColor.copy(alpha = 0.7f))
            }
        }
    }
}
