package com.geno1024.gbuilder.ds

import com.geno1024.gbuilder.ds.common.DateTime
import com.geno1024.gbuilder.utils.FileUtils
import com.geno1024.gbuilder.utils.StringUtils.toMap

data class User(
    val id: Long,
    var nickname: String = "",
    var email: String = "",
    var website: String = "",
    var motto: String = "",
    var location: String = "",
    var motd: String = "",

    val createdAt: DateTime = DateTime(0),
    var updatedAt: DateTime = DateTime(0)
)
{
    constructor(map: Map<String, String>) : this(
        id = map["id"]?.toLongOrNull()?:-1,
        nickname = map["nickname"]?:"",
        email = map["email"]?:"",
        website = map["website"]?:"",
        motto = map["motto"]?:"",
        location = map["location"]?:"",
        motd = map["motd"]?:"",
        createdAt = DateTime(map["createdAt"]?.toLongOrNull()?:-1),
        updatedAt = DateTime(map["updatedAt"]?.toLongOrNull()?:-1)
    )

    val userDir: String = "${GlobalConfig().repositoryHome}/user-$id"
    val pubKeyLocation: String = "$userDir/${User.pubKeyLocation}"
    val configLocation: String = "$userDir/$userConfigLocation"

    companion object
    {
        const val pubKeyLocation = "key"
        const val userConfigLocation = "me.conf"

        fun fromFile(userDir: String): User = User(
            FileUtils.readFile("$userDir/$userConfigLocation")
                .toMap()
        )

        fun currentMaxId() =
            try
            {
                FileUtils.readFile("${GlobalConfig().repositoryHome}/sequence-id").toLong()
            }
            catch (e: Exception)
            {
                FileUtils.writeFile("${GlobalConfig().repositoryHome}/sequence-id", "1")
                1
            }
    }

    override fun toString(): String = mapOf(
        "id" to id.toString(),
        "nickname" to nickname,
        "email" to email,
        "website" to website,
        "motto" to motto,
        "location" to location,
        "motd" to motd,
        "createdAt" to createdAt.toString(),
        "updatedAt" to updatedAt.toString()
    ).entries.joinToString(separator = "\n") { (key, value) ->
        "$key = $value"
    }
}
