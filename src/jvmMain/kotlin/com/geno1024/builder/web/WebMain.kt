package com.geno1024.builder.web

import com.sun.net.httpserver.HttpServer
import java.net.InetSocketAddress

object WebMain
{
    @JvmStatic
    fun main(args: Array<String>)
    {
        HttpServer.create(InetSocketAddress(Constants.port), 0).apply {
            APIs.list.forEach {
                it.constructors.first().call().run {
                    createContext(urlPrefix, handler)
                }
            }
            start()
        }
    }
}
