package com.anekra.capstoneapp.data.local.entity.platform

import androidx.room.Entity

@Entity(tableName = "requirementsEntity")
data class RequirementsEntity(
    val minimum: String? = null,
    val recommended: String? = null
)
