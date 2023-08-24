package com.axel.githubbrowser.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.axel.githubbrowser.ui.screens.SearchScreen

@Composable
fun MainNavHost(modifier: Modifier, navController: NavHostController) {
  NavHost(modifier = modifier, navController = navController, startDestination = "search") {
    composable("search") {
      SearchScreen()
    }
  }
}
