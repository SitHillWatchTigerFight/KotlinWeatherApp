package com.test.perfect.weatherkotlin.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.test.perfect.weatherkotlin.R
import com.test.perfect.weatherkotlin.domain.model.ForecastList
import com.test.perfect.weatherkotlin.domain.model.ModelForecast
import com.test.perfect.weatherkotlin.ui.utils.ctx
import org.jetbrains.anko.find
import kotlinx.android.synthetic.main.item_forecast.view.*

/**
 * Created by Administrator on 2017/7/14 0014.
 */
class ForecastListAdapter(val weekForecast: ForecastList, val itemClick: ForecastListAdapter.OnItemClickListener) :
        RecyclerView.Adapter<ForecastListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    override fun getItemCount(): Int = weekForecast.size()

    class ViewHolder(view: View, val itemClick: OnItemClickListener) : RecyclerView.ViewHolder(view){

        fun bindForecast(forecast: ModelForecast){
            with(forecast){
                Picasso.with(itemView.ctx).load(iconUrl).into(itemView.icon)
                itemView.date.text = date
                itemView.description.text = description
                itemView.maxTemperature.text = "${high}ºC"
                itemView.minTemperature.text = "${low}ºC"
                itemView.setOnClickListener{itemClick.invoke(this)}
            }
        }
    }

    interface OnItemClickListener{
        operator fun invoke(forecast: ModelForecast)
    }
}