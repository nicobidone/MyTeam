package com.example.domain.extension

fun String.cleanStringList(): List<String> =
    this.replace(" ", "").removePrefix("[").removeSuffix("]").split(",")
