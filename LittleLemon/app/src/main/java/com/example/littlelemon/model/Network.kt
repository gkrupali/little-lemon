package com.example.littlelemon

import kotlinx.serialization.Serializable

@Serializable
data class MenuNetworkData(
    val menu:List<MenuItemNetwork>
)


@Serializable
data class MenuItemNetwork(
    val id:Int,
    val title:String,
    val price:Double,
    val description:String,
    val image:String,
    val category:String
){
  fun toRoomMenuItem() = MenuItemDB(id,title, price,description,image,category)
}
