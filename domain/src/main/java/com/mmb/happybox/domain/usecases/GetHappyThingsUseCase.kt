package com.mmb.happybox.domain.usecases

import com.mmb.happybox.domain.baseusecases.NoParamsFlowUseCase
import com.mmb.happybox.domain.coroutineUtils.IoDispatcher
import com.mmb.happybox.domain.extensions.mapList
import com.mmb.happybox.domain.mappers.toUiModel
import com.mmb.happybox.domain.repositories.HappyThingRepository
import com.mmb.happybox.model.HappyThing
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

class GetHappyThingsUseCase @Inject constructor(
    private val happyThingRepository: HappyThingRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
) : NoParamsFlowUseCase<List<HappyThing>>(coroutineDispatcher) {

    override fun execute() = happyThingRepository.getHappyThings()
        .mapList {
            it.toUiModel()
        }

}