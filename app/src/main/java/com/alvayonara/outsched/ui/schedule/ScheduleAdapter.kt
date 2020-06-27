package com.alvayonara.outsched.ui.schedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alvayonara.outsched.R
import com.alvayonara.outsched.ui.schedule.ScheduleListItem.Companion.TYPE_DATE
import com.alvayonara.outsched.ui.schedule.ScheduleListItem.Companion.TYPE_GENERAL
import com.alvayonara.outsched.ui.schedule.item.DateItem
import com.alvayonara.outsched.ui.schedule.item.ScheduleItem
import com.alvayonara.outsched.utils.ConvertUtils
import kotlinx.android.synthetic.main.item_date_section.view.*
import kotlinx.android.synthetic.main.item_row_select_schedule.view.*

class ScheduleAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listItem: MutableList<ScheduleListItem> = mutableListOf()

    fun setSchedules(schedules: List<ScheduleListItem>?) {
        if (schedules == null) return
        listItem.clear()
        listItem.addAll(schedules)
    }

    override fun getItemViewType(position: Int): Int = listItem[position].getType()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_GENERAL -> {
                ScheduleViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_row_select_schedule, parent, false)
                )
            }
            TYPE_DATE -> {
                DateViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_date_section, parent, false)
                )
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        when (viewHolder.itemViewType) {
            TYPE_GENERAL -> {
                val holder: ScheduleViewHolder = viewHolder as ScheduleViewHolder
                holder.bindItem(listItem[position] as ScheduleItem)
            }
            TYPE_DATE -> {
                val holder: DateViewHolder = viewHolder as DateViewHolder
                holder.bindItem(listItem[position] as DateItem)
            }
        }
    }

    class ScheduleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(schedule: ScheduleItem) {
            with(itemView) {
                when {
                    schedule.scheduleEntity!!.icon.startsWith("clear") -> {
                        iv_weather.setImageResource(R.drawable.ic_clear)
                    }
                    schedule.scheduleEntity!!.icon.startsWith("cloudy") -> {
                        iv_weather.setImageResource(R.drawable.ic_clouds)
                    }
                    schedule.scheduleEntity!!.icon.startsWith("partly-cloudy") -> {
                        iv_weather.setImageResource(R.drawable.ic_partly_cloudy)
                    }
                    schedule.scheduleEntity!!.icon.contains("rain") -> {
                        iv_weather.setImageResource(R.drawable.ic_rain)
                    }
                }

                tv_weather.text = schedule.scheduleEntity!!.summary
                tv_date.text = ConvertUtils.convertTimeToDate(schedule.scheduleEntity!!.time)
                tv_hour.text = ConvertUtils.convertTimeToHour(schedule.scheduleEntity!!.time)
                tv_temperature.text =
                    context.getString(
                        R.string.temperature,
                        ConvertUtils.convertTemperatureRound(schedule.scheduleEntity!!.temperature)
                            .toString()
                    )
            }
        }
    }

    class DateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(date: DateItem) {
            with(itemView) {
                tv_section_date.text = date.dateList
            }
        }
    }
}