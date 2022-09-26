package com.geno1024.gbuilder.base

import com.geno1024.gbuilder.ds.GlobalConfig
import com.geno1024.gbuilder.ds.User
import com.geno1024.gbuilder.utils.FileUtils
import com.geno1024.gbuilder.utils.StringUtils.toMap
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
    )
    {
        val nextId = User.currentMaxId() + 1

        val user = User(
            id = nextId,
            nickname = nickname,
            email = email,
            website = website,
            motto = motto,
            location = location
        )

        mkdir(user.userDir, (S_IRWXU or S_IRGRP or S_IXGRP).toUInt())
        FileUtils.writeFile("${user.userDir}/${User.userConfigLocation}", user.toString())
        FileUtils.writeFile(user.pubKey, pubKey)
    }

    fun getUser(id: Long): User = User(
        FileUtils.readFile("${GlobalConfig().repositoryHome}/user-$id/${User.userConfigLocation}")
            .toMap()
    )

    fun updateUserInfo(
        id: Long,
        nickname: String = "",

        email: String = "",
        website: String = "",
        motto: String = "",
        location: String = "",
    ) = FileUtils.writeFile(
        "${GlobalConfig().repositoryHome}/user-$id/${User.userConfigLocation}",
        User(
            id = id,
            nickname = nickname,
            email = email,
            website = website,
            motto = motto,
            location = location
        ).toString()
    )

    fun deleteUser(id: Long) = rename(
        "${GlobalConfig().repositoryHome}/user-$id",
        "${GlobalConfig().repositoryHome}/user-$id-deleted"
    )
}
