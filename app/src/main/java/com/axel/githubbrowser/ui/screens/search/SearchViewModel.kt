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
class SearchViewModel @Inject constructor(private val search: SearchUsers) : ViewModel() {
  private val _uiState: MutableStateFlow<SearchUiState> = MutableStateFlow(SearchUiState())
  val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

  private val _loading: MutableSharedFlow<Boolean> = MutableSharedFlow(replay = 0)
  val loading: SharedFlow<Boolean> = _loading.asSharedFlow()

  private val _failure: MutableSharedFlow<Failure> = MutableSharedFlow(replay = 1)
  val failure: SharedFlow<Failure> = _failure.asSharedFlow()

  private val event = MutableSharedFlow<SearchIntentions>(replay = 1)

  init {
    viewModelScope.launch {
      event.debounce(500L).collect {
        _loading.emit(true)
        reduce(_uiState.value, it).eitherAsync({
          _failure.emit(it)
          _loading.emit(false)
        }, { searchUiState ->
          _uiState.value = searchUiState
          _loading.emit(false)
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
    uiState: SearchUiState,
    intention: SearchIntentions
  ): Either<Failure, SearchUiState> {
    return when (intention) {
      is SearchIntentions.SearchByName -> search(uiState, SearchFilter.Name, intention.query)
    }
  }

  suspend fun resetError() {
    _failure.emit(Failure.Empty)
  }
}