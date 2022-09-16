package com.mmb.happybox.data.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.mmb.happybox.data.daos.HappyThingDao
import com.mmb.happybox.domain.coroutineUtils.IoDispatcher
import com.mmb.moveis.data.model.entities.HappyThingEntity
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

@HiltWorker
class DefaultDataGeneratorWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val happyThingDao: HappyThingDao,
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result = withContext(ioDispatcher) {
        val defaultItems = listOf(
            HappyThingEntity(name = "Go for a walk"),
            HappyThingEntity(name = "Bug an ice cream"),
            HappyThingEntity(name = "Order Pizza"),
            HappyThingEntity(name = "Call nanny"),
            HappyThingEntity(name = "Chill on beach"),
            HappyThingEntity(name = "Order Pasta"),
        )

        happyThingDao.insert(defaultItems)

        Result.success()
    }

    companion object {
        const val TAG = "DefaultDataGeneratorWorker"
    }
}