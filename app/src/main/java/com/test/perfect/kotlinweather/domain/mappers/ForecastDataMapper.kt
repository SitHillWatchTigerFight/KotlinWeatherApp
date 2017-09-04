package com.test.perfect.weatherkotlin.domain.mappers

import com.test.perfect.weatherkotlin.data.Forecast
import com.test.perfect.weatherkotlin.data.ForecastResult
import com.test.perfect.weatherkotlin.domain.model.ForecastList
import com.test.perfect.weatherkotlin.domain.model.ModelForecast
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by Administrator on 2017/7/22 0022.
 */

class ForecastDataMapper {

    fun convertFromDataModel(forecast: ForecastResult): ForecastList {
        return ForecastList(forecast.city.name, forecast.city.country, convertForecastListToDomain(forecast.list))
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        return list.mapIndexed { i, forecast ->
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(i.toLong())
            convertForecastItemToDomain(forecast.copy(dt = dt))
        }
    }


    private fun convertForecastItemToDomain(forecast: Forecast): ModelForecast {
        return ModelForecast(convertDate(forecast.dt), forecast.weather[0].description,
                forecast.temp.max.toInt(), forecast.temp.min.toInt(), generateIconUrl(forecast.weather[0].icon))
    }

    private fun convertDate(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date)
    }

    private fun generateIconUrl(iconCode: String): String = "http://openweathermap.org/img/w/$iconCode.png"

}


