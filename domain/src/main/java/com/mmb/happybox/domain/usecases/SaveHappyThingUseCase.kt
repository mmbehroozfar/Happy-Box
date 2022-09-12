package com.mmb.happybox.domain.usecases

import com.mmb.happybox.domain.baseusecases.ResultUseCase
import com.mmb.happybox.domain.coroutineUtils.IoDispatcher
import com.mmb.happybox.domain.mappers.toDomainModel
import com.mmb.happybox.domain.repositories.HappyThingRepository
import com.mmb.happybox.model.HappyThing
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

class SaveHappyThingUseCase @Inject constructor(
    private val happyThingRepository: HappyThingRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
) : ResultUseCase<HappyThing, Unit>(coroutineDispatcher) {

    override suspend fun execute(params: HappyThing) =
        happyThingRepository.save(params.toDomainModel())

}