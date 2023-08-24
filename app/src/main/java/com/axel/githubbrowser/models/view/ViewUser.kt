package com.axel.githubbrowser.models.view

data class ViewUsers(val totalCount:Int = 0, val users:List<ViewUser> = emptyList())
data class ViewUser(val name: String)