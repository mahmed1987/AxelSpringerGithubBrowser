package com.axel.githubbrowser.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axel.githubbrowser.business.user.SearchUsers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val search: SearchUsers) : ViewModel() {
  init {
    viewModelScope.launch {
      search.invoke()
    }
  }
}