package com.axel.githubbrowser.business.user

import com.axel.githubbrowser.core.structures.Either
import com.axel.githubbrowser.core.structures.Failure
import com.axel.githubbrowser.core.structures.map
import com.axel.githubbrowser.data.sources.users.UsersDataSourceImpl
import com.axel.githubbrowser.ui.screens.search.GithubRepoUiState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUserById @Inject constructor(private val usersDataSourceImpl: UsersDataSourceImpl) {
  suspend operator fun invoke(
    currentUiState: GithubRepoUiState,
    filter: SearchFilter,
    query: String
  ): Either<Failure, GithubRepoUiState> {
    return usersDataSourceImpl.fetchUsers(query).map { currentUiState.copy(viewSearchResults = it) }
  }
}
