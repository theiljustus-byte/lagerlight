package com.example.lagerlite
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.lagerlite.ui.LagerApp
import com.example.lagerlite.ui.theme.LagerTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LagerTheme { LagerApp() }
        }
    }
}