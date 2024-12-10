package com.example.littlelemon

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity
data class MenuItemDB(
    @PrimaryKey(autoGenerate = true) val id:Int,
    val title:String,
    val price:Double,
    val description:String,
    val image:String,
    val category:String
)

@Dao
interface MenuItemsDao{

    @Query("SELECT * FROM MenuItemDB")
    fun getMenuDetails(): LiveData<List<MenuItemDB>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addMenu(menuItems: List<MenuItemDB>)

    @Query("SELECT (SELECT COUNT(*) FROM MenuItemDB) == 0")
    fun isEmpty(): Boolean
}

@Database(entities = [MenuItemDB::class], version = 1)
abstract class AppDataBase:RoomDatabase(){
    abstract  fun getMenuItemsDao(): MenuItemsDao
}