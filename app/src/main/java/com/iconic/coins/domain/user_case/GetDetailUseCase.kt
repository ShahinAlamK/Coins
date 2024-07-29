package com.iconic.coins.domain.user_case

import com.iconic.coins.common.Resource
import com.iconic.coins.domain.model.CoinDetails
import com.iconic.coins.domain.repository.CoinRepository
import com.iconic.coins.mapper.toCoinDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetDetailUseCase @Inject constructor(private val coinRepository: CoinRepository) {

    operator fun invoke(coinId: String): Flow<Resource<CoinDetails>> = flow {
        try {
            emit(Resource.Loading())
            val result = coinRepository.getCoinById(coinId)
            emit(Resource.Success(result.toCoinDetail()))
        } catch (e: HttpException) {
            emit(Resource.Error(e))
        } catch (e: IOException) {
            emit(Resource.Error(e))
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }
    }
}