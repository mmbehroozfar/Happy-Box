package com.mmb.happybox.domain.extensions

import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

inline fun <T, R> Flow<List<T>>.mapList(crossinline transform: suspend (value: T) -> R): Flow<List<R>> =
    map { list ->
        list.map {
            transform(it)
        }
    }

inline fun <T : Any, R : Any> Flow<PagingData<T>>.mapPagingData(
    crossinline transform: suspend (T) -> R
) = map { pagingData ->
    pagingData.map { data ->
        transform(data)
    }
}

