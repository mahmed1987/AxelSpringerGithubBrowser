package com.axel.githubbrowser.business.user

import android.util.Log
import com.axel.githubbrowser.core.structures.Either
import com.axel.githubbrowser.core.structures.Failure
import com.axel.githubbrowser.core.structures.map
import com.axel.githubbrowser.data.sources.users.UsersDataSourceImpl
import com.axel.githubbrowser.ui.screens.search.SearchUiState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchUsers @Inject constructor(private val usersDataSourceImpl: UsersDataSourceImpl) {
  suspend operator fun invoke(
    currentUiState: SearchUiState,
    filter: SearchFilter,
    query: String
  ): Either<Failure, SearchUiState> {
    return usersDataSourceImpl.fetchUsers(query).map { currentUiState.copy(viewUsers = it) }
  }
}

enum class SearchFilter {
  Name,
  Age,
  Class,
  Nationality
}