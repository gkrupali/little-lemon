package com.example.littlelemon

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
 import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.littlelemon.common.Constants
import com.example.littlelemon.view.Header
import com.example.littlelemon.view.ui.theme.LittleLemonColor


@Composable
fun Onboarding(navController: NavController)  {

    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences(Constants.LittleLemon, Context.MODE_PRIVATE)


    var firstname = remember {
        mutableStateOf("")
    }

    var lastName = remember {
        mutableStateOf("")
    }

    var email = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Header(false, navController = navController)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(LittleLemonColor.green)
                .padding(top = 16.dp, bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.str_know_abt_you),
                fontWeight = FontWeight.Bold,
                color = LittleLemonColor.cloud
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Column() {
                    Text(
                        text = stringResource(id = R.string.str_presonal_info),
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Column() {
                    Text(
                        text = stringResource(id = R.string.str_first_name)
                    )
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = firstname.value,
                        onValueChange = { firstname.value = it }
                    )
                }

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(id = R.string.str_last_name),
                    )
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = lastName.value,
                        onValueChange = {
                            lastName.value = it
                        }
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(id = R.string.str_email),
                    )
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = email.value,
                        onValueChange = { email.value = it }
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(LittleLemonColor.yellow),
                        onClick = {
                            if (email.toString().isBlank() || firstname.toString()
                                    .isBlank() || lastName.toString().isBlank()
                            ) {
                                Toast.makeText(context,context.getString(R.string.str_reg_failure) , Toast.LENGTH_LONG)
                                    .show()
                            } else {
                                with(sharedPref.edit()) {

                                    putString(Constants.firstName, firstname.value)
                                    putString(Constants.lastName, lastName.value)
                                    putString(Constants.email, email.value )
                                }.commit()
                                Toast.makeText(context, context.getString(R.string.str_reg_success), Toast.LENGTH_LONG)
                                    .show()
                                navController.navigate(HomeScreen.route)
                            }
                        }
                    ) {
                        Text(
                            text = context.getString(R.string.str_register),
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}
