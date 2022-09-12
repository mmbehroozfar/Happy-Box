package com.mmb.happybox.domain.usecases

import com.mmb.happybox.domain.baseusecases.NoParamsResultUseCase
import com.mmb.happybox.domain.coroutineUtils.IoDispatcher
import com.mmb.happybox.domain.mappers.toUiModel
import com.mmb.happybox.domain.repositories.HappyThingRepository
import com.mmb.happybox.model.HappyThing
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

class GetHappyThingsUseCase @Inject constructor(
    private val happyThingRepository: HappyThingRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
) : NoParamsResultUseCase<List<HappyThing>>(coroutineDispatcher) {

    override suspend fun execute() = happyThingRepository.getHappyThings().toUiModel()

}