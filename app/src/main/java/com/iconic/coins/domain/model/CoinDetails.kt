package com.iconic.coins.domain.model

import com.google.gson.annotations.SerializedName
import com.iconic.coins.data.dto.Links
import com.iconic.coins.data.dto.LinksExtended
import com.iconic.coins.data.dto.Tag
import com.iconic.coins.data.dto.Team
import com.iconic.coins.data.dto.Whitepaper

data class CoinDetails(
    val description: String?,
    val developmentStatus: String?,
    val firstDataAt: String?,
    val hardwareWallet: Boolean?,
    val hashAlgorithm: String?,
    val id: String?,
    val isActive: Boolean?,
    val isNew: Boolean?,
    val lastDataAt: String?,
    val links: Links?,
    val linksExtended: List<LinksExtended?>?,
    val logo: String?,
    val message: String?,
    val name: String?,
    val openSource: Boolean?,
    val orgStructure: String?,
    val proofType: String?,
    val rank: Int?,
    val startedAt: String?,
    val symbol: String?,
    val tags: List<Tag?>?,
    val team: List<Team?>?,
    val type: String?,
    val whitepaper: Whitepaper?
)