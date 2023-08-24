package com.axel.githubbrowser.ui.screens.search

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.axel.githubbrowser.R
import com.axel.githubbrowser.core.state.SnackbarManager
import com.axel.githubbrowser.core.structures.Failure
import com.axel.githubbrowser.ui.styles.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen() {
  val viewModel = hiltViewModel<SearchViewModel>()
  val keyboardController = LocalSoftwareKeyboardController.current
  val searchUiState by viewModel.uiState.collectAsStateWithLifecycle()
  val loading by viewModel.loading.collectAsStateWithLifecycle(false)
  val failure by viewModel.failure.collectAsStateWithLifecycle(Failure.Empty)

  Log.d("Failure", failure.toString())

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
      Spacer(modifier = Modifier.size(largeUnit))
      UsersList(searchUiState)
    }
  }

  LaunchedEffect(key1 = failure) {
    if (failure != Failure.Empty) {
      viewModel.resetError()
      SnackbarManager.showMessage(R.string.error_occurred)
      keyboardController?.hide()
    }
  }
}

@Composable
fun UsersList(searchUiState: SearchUiState) {
  PrimaryCard() {
    PrimaryContainerCard() {
      SmallTitle(text = "Total Count ")
      MediumHeadline(text = searchUiState.viewUsers.totalCount.toString())
    }
  }
}

@Composable
fun SearchBar(query: String, onQueryChanged: (String) -> Unit) {
  TextField(modifier = Modifier.fillMaxWidth(), value = query, onValueChange = onQueryChanged)
}