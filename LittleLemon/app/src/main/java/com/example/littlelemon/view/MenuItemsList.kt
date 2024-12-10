package com.example.littlelemon.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.MenuItemDB
import com.example.littlelemon.view.ui.theme.LittleLemonColor


@Composable
fun MenuItemsList(viewModel: MenuDataViewModel) {

    val data by viewModel.itemList.observeAsState(emptyList())
    var menus = data

    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 20.dp)
    ) {
        items(items = menus){
            MenuDish(dish = it)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuDish(dish: MenuItemDB) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)){
        Column() {
            Text(
                text = "${dish.title}",
                style = MaterialTheme.typography.headlineMedium

            )
            Text(
                text = "${dish.description}",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .padding(top = 8.dp, bottom = 8.dp)
            )
            Text(
                text = "$${dish.price}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }
        GlideImage(
            model = dish.image,
            contentDescription = dish.title,
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
        )
    }
    Divider(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        thickness = 1.dp,
        color = LittleLemonColor.yellow
    )
}