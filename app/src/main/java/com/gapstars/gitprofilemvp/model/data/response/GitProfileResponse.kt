package com.gapstars.gitprofilemvp.model.data.response

data class GitProfileDataResponse(val data: Data)

data class Data(var viewer: Viewer)

data class Viewer(
    val name: String?,
    val email: String?,
    val login: String?,
    val avatarUrl: String?,
    val followers: Followers?,
    val following: Following?,
    var repositories: Edge?,
    var starredRepositories: Edge?
)

data class Followers(val totalCount: Int?)

data class Following(val totalCount: Int?)

data class Edge(val edges: Array<Edges>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Edge

        if (!edges.contentEquals(other.edges)) return false

        return true
    }

    override fun hashCode(): Int {
        return edges.contentHashCode()
    }
}

data class Edges(val repository: Repository?)

data class Repository(
    val name: String?,
    val description: String?,
    val forkCount: Int?,
    val primaryLanguage: PrimaryLanguage?,
    val login: String?,
    val avatarUrl: String?
)

data class PrimaryLanguage(val name: String?)