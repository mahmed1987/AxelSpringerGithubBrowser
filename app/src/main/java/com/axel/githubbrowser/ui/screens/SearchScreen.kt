package com.axel.githubbrowser.ui.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.axel.githubbrowser.ui.styles.SmallTitle

@Composable
fun SearchScreen() {
  val viewModel = hiltViewModel<SearchViewModel>()
  Surface(color = MaterialTheme.colorScheme.primary) {
    SmallTitle(text = "This is search screen")
  }
}