package com.iconic.coins.mapper

import com.iconic.coins.data.dto.CoinDetailDto
import com.iconic.coins.domain.model.CoinDetails


fun CoinDetailDto.toCoinDetail(): CoinDetails {
    return CoinDetails(
        description = this.description,
        developmentStatus = this.developmentStatus,
        firstDataAt = this.firstDataAt,
        hardwareWallet = this.hardwareWallet,
        hashAlgorithm = this.hashAlgorithm,
        id = this.id,
        isActive = this.isActive,
        isNew = this.isNew,
        lastDataAt = this.lastDataAt,
        links = this.links,
        linksExtended = this.linksExtended,
        logo = this.logo,
        message = this.message,
        name = this.name,
        openSource = this.openSource,
        orgStructure = this.orgStructure,
        proofType = this.proofType,
        rank = this.rank,
        startedAt = this.startedAt,
        symbol = this.symbol,
        tags = this.tags,
        team = this.team,
        type = this.type,
        whitepaper = this.whitepaper,
    )
}