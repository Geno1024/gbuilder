package com.geno1024.gbuilder.base

import com.geno1024.gbuilder.ds.GlobalConfig
import com.geno1024.gbuilder.ds.User
import com.geno1024.gbuilder.ds.common.DateTime
import com.geno1024.gbuilder.utils.FileUtils
import com.geno1024.gbuilder.utils.StringUtils.toMap
import com.geno1024.gbuilder.utils.TimeUtils
import platform.posix.*

object UserOp
{
    fun addUser(
        nickname: String,
        pubKey: String,

        email: String = "",
        website: String = "",
        motto: String = "",
        location: String = "",
        motd: String = ""
    ): Long
    {
        val nextId = User.currentMaxId() + 1

        val user = User(
            id = nextId,
            nickname = nickname,
            email = email,
            website = website,
            motto = motto,
            location = location,
            motd = motd,
            createdAt = DateTime(TimeUtils.getCurrentTime()),
            updatedAt = DateTime(TimeUtils.getCurrentTime())
        )

        mkdir(user.userDir, (S_IRWXU or S_IRGRP or S_IXGRP).toUInt())
        FileUtils.writeFile("${user.userDir}/${User.userConfigLocation}", user.toString())
        FileUtils.writeFile(user.pubKeyLocation, pubKey)
        FileUtils.writeFile("${GlobalConfig().repositoryHome}/sequence-id", nextId.toString())
        return nextId
    }

    fun getUser(id: Long): User = User(
        FileUtils.readFile("${GlobalConfig().repositoryHome}/user-$id/${User.userConfigLocation}").apply(::println)
            .toMap()
    )

    fun updateUserInfo(
        id: Long,
        nickname: String = "",

        email: String = "",
        website: String = "",
        motto: String = "",
        location: String = "",
        motd: String = ""
    ) = updateUserInfo(id,
        User(
            id = id,
            nickname = nickname,
            email = email,
            website = website,
            motto = motto,
            location = location,
            motd = motd,
            createdAt = getUser(id).createdAt,
            updatedAt = DateTime(TimeUtils.getCurrentTime())
        )
    )

    fun updateUserInfo(id: Long, user: User) = FileUtils.writeFile(
        "${GlobalConfig().repositoryHome}/user-$id/${User.userConfigLocation}",
        user.toString().apply(::println)
    )

    fun deleteUser(id: Long) = rename(
        "${GlobalConfig().repositoryHome}/user-$id",
        "${GlobalConfig().repositoryHome}/user-$id-deleted"
    )
}
