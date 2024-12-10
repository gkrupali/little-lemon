package com.example.littlelemon.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.littlelemon.AppDataBase
import com.example.littlelemon.MenuItemDB
import com.example.littlelemon.MenuItemNetwork
import com.example.littlelemon.MenuNetworkData
import com.example.littlelemon.common.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.switchMap
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuDataViewModel @Inject constructor(private val database: AppDataBase) : ViewModel(){

    var _itemList: MutableLiveData<List<MenuItemDB>> =MutableLiveData<List<MenuItemDB>>()
    var itemList:LiveData<List<MenuItemDB>> = _itemList

    var _menubrkDownList: MutableLiveData<List<MenuItemDB>> =MutableLiveData<List<MenuItemDB>>()
    var menubrkDownList:LiveData<List<MenuItemDB>> = _menubrkDownList

    var _orignalList: MutableLiveData<List<MenuItemDB>> =MutableLiveData<List<MenuItemDB>>()


    //first state whether the search is happening or not
    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    //second state the text typed by the user
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()




    init {
         database.getMenuItemsDao().getMenuDetails().observeForever { menuList ->
            _itemList.value = menuList
             _menubrkDownList.value = menuList
             _orignalList.value = menuList
        }
    }


    private val httpClient = HttpClient(Android){
         install(ContentNegotiation) {
             json(contentType = ContentType("text", "plain"))
         }
     }

    fun fetchMenu(){
        viewModelScope.launch(Dispatchers.IO) {
            val response =
                httpClient.get(Constants.API_URL)
            saveMenuToDB(response.body<MenuNetworkData>().menu)
        }
    }

    private fun saveMenuToDB(menuItemsNetwork: List<MenuItemNetwork>){
        val menuItemsRoom = menuItemsNetwork.map {
            it.toRoomMenuItem()
        }
        database.getMenuItemsDao().addMenu(menuItemsRoom)
    }


    fun sortMenuItemsByCategory(category: String){
        _itemList.postValue(_orignalList.value?.filter {
                it.category == category
        })
    }

    fun searchMenu(value: String) {
        _itemList.postValue(_orignalList.value?.filter {
            it.title.contains(value, ignoreCase = true)
        })
    }
}


