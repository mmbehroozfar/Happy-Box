package com.mmb.happybox.domain.usecases

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.mmb.happybox.domain.baseusecases.NoParamsResultUseCase
import com.mmb.happybox.domain.coroutineUtils.IoDispatcher
import com.mmb.happybox.domain.mappers.toUiModel
import com.mmb.happybox.domain.repositories.HappyThingRepository
import com.mmb.happybox.model.HappyThing
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ObservePagedHappyThingsUseCase @Inject constructor(
    private val happyThingRepository: HappyThingRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
) : NoParamsResultUseCase<Flow<PagingData<HappyThing>>>(coroutineDispatcher) {

    override suspend fun execute(): Flow<PagingData<HappyThing>> {
        val pagingConfig = PagingConfig(
            pageSize = 5,
            prefetchDistance = 3,
            enablePlaceholders = false,
            initialLoadSize = 15
        )

        return happyThingRepository.observePagedHappyThings(pagingConfig).map { pagingData ->
            pagingData.map {
                it.toUiModel()
            }
        }
    }

}