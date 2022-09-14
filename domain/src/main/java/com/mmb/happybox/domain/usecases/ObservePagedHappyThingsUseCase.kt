package com.mmb.happybox.domain.usecases

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mmb.happybox.domain.baseusecases.NoParamsFlowUseCase
import com.mmb.happybox.domain.coroutineUtils.IoDispatcher
import com.mmb.happybox.domain.extensions.mapPagingData
import com.mmb.happybox.domain.mappers.toUiModel
import com.mmb.happybox.domain.repositories.HappyThingRepository
import com.mmb.happybox.model.HappyThing
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class ObservePagedHappyThingsUseCase @Inject constructor(
    private val happyThingRepository: HappyThingRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
) : NoParamsFlowUseCase<PagingData<HappyThing>>(coroutineDispatcher) {

    override fun execute(): Flow<PagingData<HappyThing>> {
        val pagingConfig = PagingConfig(
            pageSize = 5,
            prefetchDistance = 3,
            enablePlaceholders = false,
            initialLoadSize = 15
        )

        return happyThingRepository.observePagedHappyThings(pagingConfig)
            .mapPagingData {
                it.toUiModel()
            }
    }

}

