package uk.co.gocity.roomflow.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <T, R> Flow<T?>.mapWhenNotNull(function: (T) -> R): Flow<R?> = map { it?.let { function(it) } }

fun <T, R> Flow<List<T>>.mapList(mapper: (T) -> R): Flow<List<R>> =
    map { list -> list.map { item -> mapper(item) } }
