package com.axel.githubbrowser.business.user

import android.util.Log
import com.axel.githubbrowser.data.sources.users.UsersDataSourceImpl
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchUsers @Inject constructor(private val usersDataSourceImpl: UsersDataSourceImpl) {
  init {
    Log.d("Ad", "Asdf")
  }

  suspend operator fun invoke() {
    val abc = usersDataSourceImpl.fetchUsers("ahmed")
    Log.d("as","as")
  }
}