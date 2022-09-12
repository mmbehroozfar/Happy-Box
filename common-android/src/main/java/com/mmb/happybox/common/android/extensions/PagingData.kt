package com.mmb.happybox.common.android.extensions

import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <T : Any, R : Any> Flow<PagingData<T>>.mapPagingData(
    transform: suspend (T) -> R
) = map { pagingData ->
    pagingData.map { data ->
        transform(data)
    }
}

