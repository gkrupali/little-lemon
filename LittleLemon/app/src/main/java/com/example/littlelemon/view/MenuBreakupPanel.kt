package com.example.littlelemon.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.littlelemon.MenuItemDB
import com.example.littlelemon.R
import com.example.littlelemon.view.ui.theme.LittleLemonColor

@Composable
fun MenuBreakupPanel(
    viewModel: MenuDataViewModel
){
    val sortCategory by viewModel.menubrkDownList.observeAsState(emptyList())

    val sortedList = sortCategory.distinctBy { it.category }

    Column {
        Text(
            text = stringResource(id = R.string.str_order_for_delivery),
            fontSize = 25.sp,
            color = Color.Black,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )
        LazyRow(
            contentPadding = PaddingValues(8.dp)
        ) {
            items(items = sortedList) { dish ->
                MenuGridItem(dish = dish,onItemClick = {
                    viewModel.sortMenuItemsByCategory(dish.category)
                })
            }
        }
    }
}


@Composable
private fun  MenuGridItem(dish: MenuItemDB, onItemClick: (MenuItemDB) -> Unit){
    Button(
        modifier = Modifier.padding(start = 10.dp, end=10.dp),
        colors = ButtonDefaults.buttonColors(LittleLemonColor.yellow),
        onClick = {
            onItemClick(dish)
        }
    ) {
        Text(
            text =  dish.category,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        )
    }
}