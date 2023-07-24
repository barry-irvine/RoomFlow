package uk.co.gocity.roomflow.db.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uk.co.gocity.roomflow.db.AppDatabase
import uk.co.gocity.roomflow.db.AttractionDao
import uk.co.gocity.roomflow.db.ItineraryDao
import uk.co.gocity.roomflow.db.prepopulate
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "gocity.db")
        .addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                db.prepopulate()
            }
        }).build()

    @Provides
    fun provideItineraryDao(appDatabase: AppDatabase): ItineraryDao = appDatabase.itineraryDao()

    @Provides
    fun provideAttractionDao(appDatabase: AppDatabase): AttractionDao = appDatabase.attractionDao()
}
