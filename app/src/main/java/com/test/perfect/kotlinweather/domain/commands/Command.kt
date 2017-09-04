package com.test.perfect.weatherkotlin.domain.commands

/**
 * Created by Administrator on 2017/7/24 0024.
 */

interface  Command<out T>{
    fun execute(): T
}