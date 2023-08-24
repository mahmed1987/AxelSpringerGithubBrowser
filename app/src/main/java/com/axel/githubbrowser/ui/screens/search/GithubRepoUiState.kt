package com.axel.githubbrowser.ui.screens.search

import com.axel.githubbrowser.models.view.ViewSearchResults
import com.axel.githubbrowser.models.view.ViewUser

data class GithubRepoUiState(
  val viewSearchResults: ViewSearchResults = ViewSearchResults(),
  val selectedUser: ViewUser? = null
)