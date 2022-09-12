package com.mmb.happybox.domain.usecases

import com.mmb.happybox.domain.baseusecases.ResultUseCase
import com.mmb.happybox.domain.coroutineUtils.IoDispatcher
import com.mmb.happybox.domain.repositories.HappyThingRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

class DeleteHappyThingUseCase @Inject constructor(
    private val happyThingRepository: HappyThingRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
) : ResultUseCase<Long, Unit>(coroutineDispatcher) {

    override suspend fun execute(params: Long) =
        happyThingRepository.delete(params)

}