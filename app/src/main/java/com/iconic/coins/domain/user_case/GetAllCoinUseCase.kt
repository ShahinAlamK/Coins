package com.iconic.coins.domain.user_case

import com.iconic.coins.common.Resource
import com.iconic.coins.domain.model.Coin
import com.iconic.coins.domain.repository.CoinRepository
import com.iconic.coins.mapper.toCoin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllCoinUseCase @Inject constructor(private val coinRepository: CoinRepository) {

    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val result = coinRepository.getAllCoins()
            emit(Resource.Success(result.map { it.toCoin() }))
        } catch (e: HttpException) {
            emit(Resource.Error(e))
        } catch (e: IOException) {
            emit(Resource.Error(e))
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }
    }
}