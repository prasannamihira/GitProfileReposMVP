package com.gapstars.gitprofilemvp.model.data.response

/**
 * Git profile data response class return data object
 *
 * @param data
 */
data class GitProfileDataResponse(val data: Data)

/**
 * Data class return profile User data object
 *
 * @param user
 */
data class Data(var user: User)

/**
 * User class return details of the user profile
 *
 * @param name
 * @param email
 * @param login
 * @param avatarUrl
 * @param followers
 * @param following
 * @param pinnedItems
 * @param repositories
 * @param starredRepositories
 */
data class User(
    val name: String?,
    val email: String?,
    val login: String?,
    val avatarUrl: String?,
    val followers: Followers?,
    val following: Following?,
    val pinnedItems: Node?,
    var repositories: Edges?,
    var starredRepositories: Edges?
)

/**
 * Followers class return total followers count
 *
 * @param totalCount
 */
data class Followers(var totalCount: Int?)

/**
 * Following class return total following count
 *
 * @param totalCount
 */
data class Following(val totalCount: Int?)

/**
 * Node class return the Edges list
 *
 * @param nodes
 */
data class Node(val nodes: Array<Repository>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Node

        if (!nodes.contentEquals(other.nodes)) return false

        return true
    }

    override fun hashCode(): Int {
        return nodes.contentHashCode()
    }
}

/**
 * Edge class return the Edges list
 *
 * @param edges
 */
data class Edges(val edges: Array<Edge>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Edges

        if (!edges.contentEquals(other.edges)) return false

        return true
    }

    override fun hashCode(): Int {
        return edges.contentHashCode()
    }
}

/**
 * Edges class return the repository object
 *
 * @param repository
 */
data class Edge(val repository: Repository?)

/**
 * Repository class return the details of the repository
 *
 * @param name
 * @param description
 * @param forkCount
 * @param primaryLanguage
 */
data class Repository(
    val name: String?,
    val description: String?,
    val forkCount: Int?,
    val primaryLanguage: PrimaryLanguage?,
    var avatarUrl: String?
)

/**
 * PrimaryLanguage class return the language name of the repository
 *
 * @param name
 */
data class PrimaryLanguage(val name: String?)