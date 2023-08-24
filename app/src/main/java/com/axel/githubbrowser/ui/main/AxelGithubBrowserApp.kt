package com.axel.githubbrowser.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.axel.githubbrowser.Greeting
import com.axel.githubbrowser.core.state.AppState

@Composable
fun AxelGithubBrowserApp(appState: AppState) {
  Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
    Column(modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)) {
      Greeting("Android")
    }
  }
}