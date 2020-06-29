package com.alvayonara.outsched.data.source

import androidx.lifecycle.LiveData
import com.alvayonara.outsched.data.source.local.LocalDataSource
import com.alvayonara.outsched.data.source.local.entity.ScheduleEntity
import com.alvayonara.outsched.data.source.local.entity.item.ScheduleListItem
import com.alvayonara.outsched.data.source.remote.RemoteDataSource
import com.alvayonara.outsched.utils.AppExecutors

class ScheduleRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    ScheduleDataSource {

    companion object {
        @Volatile
        private var instance: ScheduleRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): ScheduleRepository =
            instance ?: synchronized(this) {
                instance ?: ScheduleRepository(remoteDataSource, localDataSource, appExecutors)
            }
    }

    override fun getWeathersData(
        latitude: String,
        longitude: String,
        address: String
    ): LiveData<List<ScheduleListItem>> =
        remoteDataSource.getWeathersData(latitude, longitude, address)

    override fun getAllUpcomingSchedules(): LiveData<List<ScheduleEntity>> =
        localDataSource.getAllUpcomingSchedules()

    override fun getAllPastSchedules(): LiveData<List<ScheduleEntity>> =
        localDataSource.getAllPastSchedules()

    override fun insertSchedule(scheduleEntity: ScheduleEntity) =
        appExecutors.diskIO().execute { localDataSource.insertSchedule(scheduleEntity) }

    override fun updateSchedule(scheduleEntity: ScheduleEntity) =
        appExecutors.diskIO().execute { localDataSource.updateSchedule(scheduleEntity) }

    override fun deleteSchedule(scheduleEntity: ScheduleEntity) =
        appExecutors.diskIO().execute { localDataSource.deleteSchedule(scheduleEntity) }
}