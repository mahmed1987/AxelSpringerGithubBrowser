package com.axel.githubbrowser.ui.screens.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.axel.githubbrowser.ui.styles.*

@Composable
fun SearchScreen() {
  val viewModel = hiltViewModel<SearchViewModel>()
  val searchUiState by viewModel.uiState.collectAsStateWithLifecycle()
  val loading by viewModel.loading.collectAsStateWithLifecycle(false)
  var currentQuery by remember { mutableStateOf("") }
  Surface(color = MaterialTheme.colorScheme.background) {
    Column() {
      SearchBar(query = currentQuery, onQueryChanged = {
        viewModel.executeIntention(SearchIntentions.SearchByName(it))
        currentQuery = it
      })
      AnimatedVisibility(visible = loading) {
        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
      }
      UsersList(searchUiState)
    }
  }
}

@Composable
fun UsersList(searchUiState: SearchUiState) {
  PrimaryCard() {
    Icon(Icons.Filled.VerifiedUser, contentDescription = "")
    Spacer(modifier = Modifier.size(XSmallUnit))
    MediumHeadline(text = searchUiState.viewUsers.totalCount.toString())
  }
}

@Composable
fun SearchBar(query: String, onQueryChanged: (String) -> Unit) {
  TextField(modifier = Modifier.fillMaxWidth(), value = query, onValueChange = onQueryChanged)
}