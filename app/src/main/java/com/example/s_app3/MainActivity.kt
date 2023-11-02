package com.example.s_app3

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.s_app3.components.InputField
import com.example.s_app3.ui.theme.SApp3Theme
import com.example.s_app3.util.CalculateTotalTip
import com.example.s_app3.util.calculateTotalPerperson
import com.example.s_app3.widgets.RoundedIconButton

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SApp3Theme {
                MyApp {
                   // TopHeader()
                    MainCountent()

                }
                // A surface container using the 'background' color from the theme
            }
        }
    }
}
@Composable
fun MyApp(content:@Composable () -> Unit){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
content()

    }

}
@Composable
fun TopHeader(totalaperPerson:Double=134.0){
    Surface(modifier = Modifier
        .padding(15.dp)
        .fillMaxWidth()
        .height(15.dp)
        .padding(top = 5.dp, bottom = 580.dp)
        .clip(shape = RoundedCornerShape(corner = CornerSize(12.dp))),
        color = Color.Cyan
    ) {
        Column (modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            val total="%.2f".format(totalaperPerson)
            Text(text = "Total per Person",
                style = MaterialTheme.typography.headlineLarge)
            Text(text ="$$total",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold
                )

        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainCountent(){
    BillForm(){billAtm->
        Log.d("billAtm", "MainCountent: ${billAtm.toInt()*100}")
    }
    }

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BillForm(modifier: Modifier=Modifier,
             onValueChange :(String) -> Unit) {
    val totalBillState = remember {
        mutableStateOf("")
    }
    val validState = remember(totalBillState.value) {
        totalBillState.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val sliderpositionState= remember {
        mutableStateOf(0f)
    }
    val tipPersentage=(sliderpositionState.value*100).toInt()
    val splitByState= remember {
        mutableStateOf(1)
    }
    val tipAmountState= remember {
        mutableStateOf(0.0)
    }
    val totalPerPersonState= remember {
        mutableStateOf(0.0)
    }

    val Range= IntRange(start = 1, endInclusive = 100)

    TopHeader(totalaperPerson = totalPerPersonState.value)

    Surface(
        modifier = Modifier
            .padding(top = 210.dp, bottom = 295.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(width = 2.dp, color = Color.LightGray)
    ) {
        Column(
            modifier = Modifier.padding(5.dp), verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            InputField(
                valueState = totalBillState,
                labelId = "Enter Bill",
                enabled = true,
                isSingleLine = true,
                onAction = KeyboardActions {
                    if (!validState) return@KeyboardActions
                    onValueChange(totalBillState.value.trim())

                    keyboardController!!.hide()
                }


            )

            //if (validState){
            Row(
                modifier = Modifier.padding(3.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Split",
                    modifier = Modifier.align(alignment = Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.width(120.dp))
                Row(
                    modifier = Modifier.padding(horizontal = 3.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    RoundedIconButton(modifier = Modifier, imagevactor = Icons.Default.Remove,
                        onClick = {
                            splitByState.value=
                                if (splitByState.value>1) splitByState.value -1

                            else 1

                            totalPerPersonState.value=
                                calculateTotalPerperson(titleBill = totalBillState.value.toDouble(), splitBy = splitByState.value,
                                    tipPersentage=tipPersentage)


                        })
                    Text(
                        text = "${splitByState.value}",
                        modifier = Modifier
                            .align(alignment = Alignment.CenterVertically)
                            .padding(start = 9.dp, end = 9.dp)
                    )
                    RoundedIconButton(modifier = Modifier, imagevactor = Icons.Default.Add,
                        onClick = {
                                if (splitByState.value<Range.last)
                                    splitByState.value=splitByState.value+1

                            totalPerPersonState.value=
                                calculateTotalPerperson(titleBill = totalBillState.value.toDouble(), splitBy = splitByState.value,
                                    tipPersentage=tipPersentage)




                        })


                }

            }
            // }else{
            Row(modifier = Modifier.padding(horizontal = 3.dp, vertical = 3.dp)) {


                Text(
                    text = "Tip",
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                )

                Spacer(modifier = Modifier.width(200.dp))


                Text(
                    text = "$${tipAmountState.value}",
                    modifier = Modifier.align(alignment = Alignment.CenterVertically)
                )
            }
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "$tipPersentage")
                Spacer(modifier = Modifier.height(14.dp))
                Slider(value = sliderpositionState.value, onValueChange = {newVal->
                    sliderpositionState.value=newVal
                    tipAmountState.value=
    CalculateTotalTip(titleBill = totalBillState.value.toDouble(),
        tipPersentage=tipPersentage)

             totalPerPersonState.value=
             calculateTotalPerperson(titleBill = totalBillState.value.toDouble(), splitBy = splitByState.value,
                 tipPersentage=tipPersentage)

                },
                    modifier = Modifier.padding(start =16.dp , end = 16.dp),
                    steps = 5,
                    onValueChangeFinished = {
                        
                    })
            }


        }

    }
}

//  }



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SApp3Theme {
    }
}