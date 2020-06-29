package com.alvayonara.outsched.ui.schedule

import androidx.lifecycle.ViewModel
import com.alvayonara.outsched.data.source.ScheduleRepository
import com.alvayonara.outsched.data.source.local.entity.ScheduleEntity

class ScheduleDetailViewModel(private val scheduleRepository: ScheduleRepository) : ViewModel() {

    fun insert(scheduleEntity: ScheduleEntity) = scheduleRepository.insertSchedule(scheduleEntity)

    fun update(scheduleEntity: ScheduleEntity) = scheduleRepository.updateSchedule(scheduleEntity)

    fun delete(scheduleEntity: ScheduleEntity) = scheduleRepository.deleteSchedule(scheduleEntity)
}