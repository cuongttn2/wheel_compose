package com.qsd.wheelcompose.utils

infix fun <T> Boolean.then(param: T): T? = if (this) param else null