package com.axel.githubbrowser.data.sources.users

import com.axel.githubbrowser.core.structures.Either
import com.axel.githubbrowser.core.structures.Failure
import com.axel.githubbrowser.models.view.ViewUsers

interface UsersDataSource {
  suspend fun fetchUsers(search: String): Either<Failure, ViewUsers>
}