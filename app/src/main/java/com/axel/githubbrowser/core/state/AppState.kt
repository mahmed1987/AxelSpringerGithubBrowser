package com.axel.githubbrowser.core.state

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope


@Composable
fun rememberAppState(
  snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
  navController: NavHostController = rememberNavController(),
  snackbarManager: SnackbarManager = SnackbarManager,
  coroutineScope: CoroutineScope = rememberCoroutineScope()
) =
  remember(snackBarHostState, navController, snackbarManager, coroutineScope) {
    AppState(snackBarHostState, navController, snackbarManager, coroutineScope)
  }


@Stable
class AppState(
   val snackBarHostState: SnackbarHostState,
   val navController: NavHostController,
  private val snackbarManager: SnackbarManager,
  private val coroutineScope: CoroutineScope
)