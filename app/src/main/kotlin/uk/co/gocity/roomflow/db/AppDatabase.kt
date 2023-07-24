package uk.co.gocity.roomflow.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [
        AttractionEntity::class,
        ItineraryAttractionEntity::class,
        ItineraryEntity::class
    ], version = 1
)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun itineraryDao(): ItineraryDao
    abstract fun attractionDao(): AttractionDao
}

internal fun SupportSQLiteDatabase.prepopulate() {
    beginTransaction()
    execSQL("INSERT INTO itinerary VALUES(1,'My itinerary',1690848000000, 1691193600000)")
    repeat(100) {
        execSQL("INSERT INTO attraction VALUES('$it','Attraction $it')")
    }
    setTransactionSuccessful()
    endTransaction()
}
