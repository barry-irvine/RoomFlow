package uk.co.gocity.roomflow.db.mapper

interface Mapper<T, R> {

    fun map(from: T): R

}
