package com.mmb.happybox.domain.usecases

import com.mmb.happybox.domain.baseusecases.NoParamsFlowUseCase
import com.mmb.happybox.domain.coroutineUtils.IoDispatcher
import com.mmb.happybox.domain.extensions.shuffleAndJoinToString
import com.mmb.happybox.domain.repositories.HappyThingRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.map

class GetHappyThingsNamesUseCase @Inject constructor(
    private val happyThingRepository: HappyThingRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
) : NoParamsFlowUseCase<String>(coroutineDispatcher) {

    override fun execute() = happyThingRepository.getHappyThings()
        .map {
            it.shuffleAndJoinToString(separator = " - ") { item -> item.name }
        }

}