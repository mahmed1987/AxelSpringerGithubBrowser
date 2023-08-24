package com.axel.githubbrowser.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axel.githubbrowser.business.user.SearchFilter
import com.axel.githubbrowser.business.user.SearchUsers
import com.axel.githubbrowser.core.structures.Either
import com.axel.githubbrowser.core.structures.Failure
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class GithubRepoViewModel @Inject constructor(private val search: SearchUsers) : ViewModel() {
  private val _uiState: MutableStateFlow<GithubRepoUiState> = MutableStateFlow(GithubRepoUiState())
  val uiState: StateFlow<GithubRepoUiState> = _uiState.asStateFlow()

  private val _loading: MutableStateFlow<Boolean> = MutableStateFlow(false)
  val loading: StateFlow<Boolean> = _loading.asStateFlow()

  private val _failure: MutableStateFlow<Failure> = MutableStateFlow(Failure.Empty)
  val failure: StateFlow<Failure> = _failure.asStateFlow()

  private val event = MutableSharedFlow<SearchIntentions>(replay = 1)

  init {
    viewModelScope.launch {
      event.debounce(300L).collect {
        _loading.emit(true)
        reduce(_uiState.value, it).either({
          _failure.value = it
          _loading.value = false
        }, { searchUiState ->
          _uiState.value = searchUiState
          _loading.value = false
        })
      }
    }
  }

  fun executeIntention(intention: SearchIntentions) {
    viewModelScope.launch {
      event.emit(intention)
    }
  }

  private suspend fun reduce(
    uiState: GithubRepoUiState,
    intention: SearchIntentions
  ): Either<Failure, GithubRepoUiState> {
    return when (intention) {
      is SearchIntentions.SearchByName -> search(uiState, SearchFilter.Name, intention.query)
      is SearchIntentions.GetById -> TODO()
    }
  }

  suspend fun resetError() {
    _failure.emit(Failure.Empty)
  }
}