package com.anekra.capstoneapp.data.local.entity.paging

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remoteKeysTable")
data class RemoteKeysEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)
