package com.axel.githubbrowser.ui.screens.search


sealed class SearchIntentions {
  class SearchByName(val query: String) : SearchIntentions()
  class GetById(val id: String) : SearchIntentions()
  object ResetSelectedUser : SearchIntentions()
}