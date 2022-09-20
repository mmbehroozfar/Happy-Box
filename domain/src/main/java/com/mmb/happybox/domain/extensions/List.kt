package com.mmb.happybox.domain.extensions

fun <T> List<T>.shuffleAndJoinToString(
    separator: CharSequence = " - ",
    transform: ((T) -> CharSequence)? = null,
) = shuffled().joinToString(separator = separator, transform = transform)
