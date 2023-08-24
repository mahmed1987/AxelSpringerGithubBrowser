package com.axel.githubbrowser.ui.screens.search


sealed class SearchIntentions {
  class SearchByName(val query: String) : SearchIntentions()
}