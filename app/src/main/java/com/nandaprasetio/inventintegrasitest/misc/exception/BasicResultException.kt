package com.nandaprasetio.inventintegrasitest.misc.exception

import com.nandaprasetio.inventintegrasitest.domain.entity.BasicResult

class BasicResultException(
    val basicResult: BasicResult<*>
): IllegalStateException(
    "This exception is thrown because something wrong related with basic result ($basicResult)"
)