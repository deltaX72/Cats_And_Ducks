package com.litil.catsandducks.domain.models.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.litil.catsandducks.domain.models.db.ImageModel.Companion.tableName

@Entity(tableName = tableName, primaryKeys = ["image"])
data class ImageModel(
    val id: Long = 0,
    val image: ByteArray,
    val name: String
) {
    companion object {
        const val tableName = "SavedImages"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ImageModel

        if (name != other.name) return false
        if (!image.contentEquals(other.image)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + image.contentHashCode()
        return result
    }
}
