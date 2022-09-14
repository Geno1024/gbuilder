package com.geno1024.builder.commands

import com.geno1024.builder.Command
import kotlinx.cinterop.alloc
import kotlinx.cinterop.cstr
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.placeTo
import platform.posix.*

object SystemInit: Command
{
    override fun help()
    {
        println("systeminit [username] [homedir]")
    }

    private fun adduser(username: String, home: String = "/home/$username")
    {
        memScoped {
            val pwd = alloc<passwd>()
            pwd.pw_name = username.cstr.placeTo(this)
            pwd.pw_dir = home.cstr.placeTo(this)
            pwd.pw_shell = strdup("/bin/bash")
            pwd.pw_gecos = username.cstr.placeTo(this)
            val f = fopen("/etc/passwd", "a")
            
            free(pwd.pw_shell)
        }
//        val pwd =
    }

    // https://stackoverflow.com/a/69603011/9035237
    /**
     *
    static void createUser(char *userName, char *homeDir, int uid) {
    struct passwd * pwd = getpwent ();
    struct passwd pwd2;

    pwd =  getpwnam(userName);
    if (pwd != NULL) {
    return;
    }
    pwd2.pw_dir = homeDir;
    pwd2.pw_gecos=userName;
    pwd2.pw_name=userName;
    pwd2.pw_gid=uid;
    pwd2.pw_uid=uid;
    pwd2.pw_shell=strdup("/bin/bash");
    FILE *f = fopen("/etc/passwd", "a");
    if (f != NULL) {
    putpwent(&pwd2, f);
    fclose(f);
    }
    free (pwd2.pw_shell);
    }
     */
    override fun invoke(parameters: List<String>)
    {
        if (parameters[0] in listOf("--help", "-h", "help"))
        {
            help()
            return
        }

        val username = parameters.getOrElse(0) { "git" }
        val gitUserExists = getpwnam(username)
        if (gitUserExists != null)
        {
            println("You have created a user called \"$username\".")
            println("Do you want to abort or overwrite? [A/o]")
            var choice = readln()
            while (true)
            {
                when (choice)
                {
                    "A", "a" -> return
                    "O", "o" -> break
                    else -> choice = readln()
                }
            }
        }
        val home = parameters.getOrElse(1) { "/home/git" }
    }
}
