package com.jcgseco.myarmory.core.commons.domain.models

data class Page<DATA>(
    val items: List<DATA>,
    val max: Int,
    val offset: Int,
    val totalItems: Int
)
