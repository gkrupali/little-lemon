package com.example.littlelemon

 import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
 import androidx.compose.foundation.layout.Row
 import androidx.compose.foundation.layout.fillMaxWidth
 import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
 import androidx.compose.material.icons.Icons
 import androidx.compose.material.icons.filled.Search
 import androidx.compose.material3.Icon
 import androidx.compose.material3.Text
 import androidx.compose.material3.TextField
 import androidx.compose.runtime.Composable
 import androidx.compose.runtime.mutableStateOf
 import androidx.compose.runtime.remember
 import androidx.compose.ui.Alignment
 import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
 import androidx.compose.ui.res.painterResource
 import androidx.compose.ui.res.stringResource
 import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
 import androidx.navigation.NavController
 import com.example.littlelemon.view.Header
 import com.example.littlelemon.view.MenuBreakupPanel
 import com.example.littlelemon.view.MenuDataViewModel
 import com.example.littlelemon.view.MenuItemsList
 import com.example.littlelemon.view.ui.theme.LittleLemonColor


@Composable
fun HomeScreen(viewModel: MenuDataViewModel = hiltViewModel(),navController: NavController) {

    viewModel.fetchMenu()

    Column{

        Header(true, navController =navController)

        Column(
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp)
                .background(LittleLemonColor.green)
        ) {
            Text(
                text = stringResource(id = R.string.str_title),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = LittleLemonColor.yellow,
                modifier = Modifier.padding(start = 8.dp, top = 8.dp)
            )
            Text(
                text = "Chicago",
                fontSize = 24.sp,
                color = LittleLemonColor.cloud,
                modifier = Modifier.padding(start = 8.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(top = 20.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .fillMaxWidth(0.6F)
                        .padding(bottom = 28.dp, end = 20.dp),
                    text = stringResource(id = R.string.str_rest_desc),
                    color = LittleLemonColor.cloud,
                )
                Image(
                    painter = painterResource(id = R.drawable.heroimage),
                    contentDescription = "Hero Image",
                    modifier = Modifier.clip(RoundedCornerShape(10.dp))
                )
            }
            SearchBar(viewModel)
        }
        MenuBreakupPanel(viewModel)
        MenuItemsList(viewModel)
    }
}

@Composable
fun SearchBar(viewModel: MenuDataViewModel){

    var searchPhrase = remember {
        mutableStateOf("")
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 10.dp, top = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        Column() {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = searchPhrase.value,
                onValueChange = {
                    searchPhrase.value = it
                    viewModel.searchMenu(searchPhrase.value)
                                },
                placeholder = { Text(text = "Enter Search Phrase") },
                leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
                shape = RoundedCornerShape(10.dp)
            )
        }
    }
}




