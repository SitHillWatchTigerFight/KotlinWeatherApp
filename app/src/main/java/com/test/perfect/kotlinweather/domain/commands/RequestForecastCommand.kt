package com.test.perfect.weatherkotlin.domain.commands

import com.test.perfect.weatherkotlin.data.ForecastRequest
import com.test.perfect.weatherkotlin.domain.mappers.ForecastDataMapper
import com.test.perfect.weatherkotlin.domain.model.ForecastList

/**
 * Created by Administrator on 2017/7/24 0024.
 */

class RequestForecastCommand(val zipCode: String): Command<ForecastList>{
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }
}