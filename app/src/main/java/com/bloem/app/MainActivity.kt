package com.bloem.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bloem.app.ui.screens.*
import com.bloem.app.ui.theme.BloemTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BloemTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(
                            onBookSession = { navController.navigate("booking") },
                            onControlSession = { navController.navigate("control_session") }
                        )
                    }
                    composable("booking") {
                        BookingScreen(
                            onBack = { navController.popBackStack() },
                            onViewSessions = { navController.navigate("view_sessions") }
                        )
                    }
                    composable("control_session") {
                        ControlSessionScreen(onBack = { navController.popBackStack() })
                    }
                    composable("view_sessions") {
                        ViewSessionsScreen(onBack = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}
