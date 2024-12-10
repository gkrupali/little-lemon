package com.example.littlelemon.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
 import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
 import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.littlelemon.ProfileScreen
import com.example.littlelemon.R

@Composable
fun Header(profile:Boolean, navController: NavController){

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(top=40.dp, bottom = 20.dp)
    ) {
        IconButton(onClick = { }) {

        }
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier.fillMaxWidth(0.5F)
                .padding(horizontal = 20.dp)
        )

        IconButton(
            onClick = { navController.navigate(ProfileScreen.route) },
            modifier = Modifier.padding(end = 20.dp)
        ) {
            if(profile){
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile",
                modifier = Modifier.size(100.dp)
            )
            }
        }
    }
}
