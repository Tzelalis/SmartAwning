package com.example.smartawning.domain.entity

import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize

@Database(entities = [AwningEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): AwningDao
}

@Parcelize
@Entity(tableName = "awning")
data class AwningEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "ip_address") var localIp: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "public_ip") var publicIp : String,
    @ColumnInfo(name = "public_port") var publicPort : String,
    @ColumnInfo(name = "mac_address") val macAddress: String
) : Parcelable

@Dao
interface AwningDao {
    @Query("SELECT * FROM awning")
    suspend fun getAllAwning(): List<AwningEntity>

    @Query("SELECT * FROM awning WHERE id = :mac")
    suspend fun loadAllByMac(mac: String): List<AwningEntity>

    @Insert
    suspend fun insertAwning(awning: AwningEntity)

    @Delete
    suspend fun deleteAwning(awning: AwningEntity)

    @Update
    suspend fun updateAwning(awning: AwningEntity)
}
