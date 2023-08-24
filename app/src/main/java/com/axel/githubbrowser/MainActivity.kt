package com.axel.githubbrowser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.axel.githubbrowser.core.state.rememberAppState
import com.axel.githubbrowser.ui.main.AxelGithubBrowserApp
import com.axel.githubbrowser.ui.theme.AxelGithubBrowserTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    /* Go full screen */
    WindowCompat.setDecorFitsSystemWindows(window, false)
    setContent {
      AxelGithubBrowserTheme() {
        
        val appState = rememberAppState()
        // A surface container using the 'background' color from the theme
        AxelGithubBrowserApp(appState = appState)
        
      }
    }
  }
}

@Composable
fun Greeting(name: String) {
  Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  AxelGithubBrowserTheme() {
    Greeting("Android")
  }
}