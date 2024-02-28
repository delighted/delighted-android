package com.delighted.sdk.interactor.aq

import com.delighted.sdk.interactor.UseCase
import com.delighted.sdk.interactor.invoke
import javax.inject.Inject

internal class GetAqIdByIndexUseCase @Inject constructor(
    private val aqsByGroupUseCase: GetAqsByGroupUseCase,
) :
    UseCase<Int, String> {
    override fun execute(params: Int): String {
        val aqs = aqsByGroupUseCase().getOrNull(params)
        return requireNotNull(aqs) { "No additional question with index of $params" }.id
    }
}
