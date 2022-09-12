package com.mmb.happybox.domain.usecases

import com.mmb.happybox.domain.baseusecases.ResultUseCase
import com.mmb.happybox.domain.coroutineUtils.IoDispatcher
import com.mmb.happybox.domain.mappers.toUiModel
import com.mmb.happybox.domain.repositories.HappyThingRepository
import com.mmb.happybox.model.HappyThing
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

class GetHappyThingUseCase @Inject constructor(
    private val happyThingRepository: HappyThingRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
) : ResultUseCase<Long, HappyThing>(coroutineDispatcher) {

    override suspend fun execute(params: Long) =
        happyThingRepository.getHappyThing(params).toUiModel()

}