package com.geno1024.gbuilder.ds

import com.geno1024.gbuilder.ds.common.DateTime
import com.geno1024.gbuilder.utils.TimeUtils

data class Repo(
    val id: Long,
    var name: String = "",
    var description: String = "",

    var belongsTo: Long,
    val createdAt: DateTime = DateTime(TimeUtils.getCurrentTime()),
    var updatedAt: DateTime = DateTime(TimeUtils.getCurrentTime())
)
{
    constructor(map: Map<String, String>) : this(
        id = map["id"]?.toLongOrNull()?:-1,
        name = map["name"]?:"",
        description = map["description"]?:"",

        belongsTo = map["belongsTo"]?.toLongOrNull()?:-1,
        createdAt = DateTime(map["createdAt"]?.toLongOrNull()?:-1),
        updatedAt = DateTime(map["updatedAt"]?.toLongOrNull()?:-1),
    )

    val repoDir: String = "${GlobalConfig().repositoryHome}/user-$id/$name"
}
