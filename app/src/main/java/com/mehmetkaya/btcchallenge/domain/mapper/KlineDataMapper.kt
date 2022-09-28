package com.mehmetkaya.btcchallenge.domain.mapper

import com.mehmetkaya.btcchallenge.data.model.KlineDataResponse
import com.mehmetkaya.btcchallenge.domain.model.KlineData
import com.mehmetkaya.btcchallenge.utils.Mapper
import javax.inject.Inject

class KlineDataMapper @Inject constructor() : Mapper<KlineDataResponse, KlineData> {

    override fun map(input: KlineDataResponse): KlineData = with(input) {
        return KlineData(
            timestamp = timestamp.orEmpty(),
            close = close.orEmpty()
        )
    }
}
