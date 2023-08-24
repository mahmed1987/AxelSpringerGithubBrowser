package com.axel.githubbrowser.data.sources.users

import com.axel.githubbrowser.core.structures.Either
import com.axel.githubbrowser.core.structures.Failure
import com.axel.githubbrowser.core.structures.map
import com.axel.githubbrowser.data.network.networkCall
import javax.inject.Inject


class UsersDataSourceImpl @Inject constructor(private val apis: UsersWebServices) :
  UsersDataSource {
  override suspend fun fetchUsers(search: String) =
    networkCall { apis.search(search) }.map { it.toView() }
}