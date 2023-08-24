package com.axel.githubbrowser.models.network

import com.axel.githubbrowser.models.view.ViewUser
import com.google.gson.annotations.SerializedName
import kotlin.math.log

data class GithubResponse(
    @SerializedName("total_count")
    val totalCount: Int,

    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,

    val items: List<NetworkUserItem>
)

data class NetworkUserItem(
    val login: String,
    val id: Int,

    @SerializedName("node_id")
    val nodeId: String,

    @SerializedName("avatar_url")
    val avatarUrl: String,

    @SerializedName("gravatar_id")
    val gravatarId: String,

    val url: String,

    @SerializedName("html_url")
    val htmlUrl: String,

    @SerializedName("followers_url")
    val followersUrl: String,

    @SerializedName("subscriptions_url")
    val subscriptionsUrl: String,

    @SerializedName("organizations_url")
    val organizationsUrl: String,

    @SerializedName("repos_url")
    val reposUrl: String,

    @SerializedName("received_events_url")
    val receivedEventsUrl: String,

    val type: String,
    val score: Int,

    @SerializedName("following_url")
    val followingUrl: String,

    @SerializedName("gists_url")
    val gistsUrl: String,

    @SerializedName("starred_url")
    val starredUrl: String,

    @SerializedName("events_url")
    val eventsUrl: String,

    @SerializedName("site_admin")
    val siteAdmin: Boolean
){
    fun toUser() = ViewUser(name = login)
}
