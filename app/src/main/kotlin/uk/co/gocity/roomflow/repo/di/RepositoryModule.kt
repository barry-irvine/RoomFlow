package uk.co.gocity.roomflow.repo.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uk.co.gocity.roomflow.repo.AttractionRepository
import uk.co.gocity.roomflow.repo.AttractionRepositoryImpl
import uk.co.gocity.roomflow.repo.ItineraryRepository
import uk.co.gocity.roomflow.repo.ItineraryRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    fun bindItineraryRepository(impl: ItineraryRepositoryImpl): ItineraryRepository

    @Binds
    fun bindAttractionRepository(impl: AttractionRepositoryImpl): AttractionRepository

}
