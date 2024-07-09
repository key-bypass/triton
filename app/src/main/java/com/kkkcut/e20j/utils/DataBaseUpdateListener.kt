package com.kkkcut.e20j.utils

interface DataBaseUpdateListener {
    fun error(th: Throwable?)

    fun finish()

    fun progress(i: Int)

    fun start()
}