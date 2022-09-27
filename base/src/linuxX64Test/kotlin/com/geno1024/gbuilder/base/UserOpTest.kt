package com.geno1024.gbuilder.base

import com.geno1024.gbuilder.ds.User
import com.geno1024.gbuilder.utils.FileUtils
import kotlin.test.Test
import kotlin.test.assertEquals

object UserOpTest
{
    private const val nickname = "test-a"
    private const val pubKey = "pub"

    private const val email = "test@example.com"

    val id = User.currentMaxId() + 1

    @Test
    fun create()
    {
        val userId = UserOp.addUser(nickname, pubKey)
    }

    @Test
    fun update()
    {
        val user = UserOp.getUser(id)
        user.email = email
        UserOp.updateUserInfo(id, user)
    }

    @Test
    fun retrieve()
    {
        val user = UserOp.getUser(id)
        assertEquals(pubKey, FileUtils.readFile(user.pubKeyLocation))
        assertEquals(nickname, user.nickname)
        assertEquals(email, user.email)
    }

    @Test
    fun delete()
    {
        UserOp.deleteUser(id)
    }
}
