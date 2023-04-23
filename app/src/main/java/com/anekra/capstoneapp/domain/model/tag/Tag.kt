package com.anekra.capstoneapp.domain.model.tag

data class Tag(
    val next: String? = null,
    val previous: String? = null,
    val count: Int? = null,
    val results: List<TagItem>? = null,
)
