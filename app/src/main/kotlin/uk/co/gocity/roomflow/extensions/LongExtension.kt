package uk.co.gocity.roomflow.extensions

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
Converts Long to Kotlin LocalDateTime UTC
 **/
fun Long.asLocalDateTime() =
    Instant.fromEpochMilliseconds(this).toLocalDateTime(TimeZone.UTC)
