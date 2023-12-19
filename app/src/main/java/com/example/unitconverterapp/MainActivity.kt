package com.example.unitconverterapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverterapp.ui.theme.UnitConverterAppTheme
import kotlin.math.roundToInt
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}


@Composable
fun UnitConverter(){

    var inputVariable by remember { mutableStateOf("") }
    var outPutVariable by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outPutUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val iConversionFactor= remember { mutableStateOf(1.00) }
    val oConversionFactor= remember { mutableStateOf(1.00) }

//    val customTextStyle=TextStyle(
//        fontFamily = FontFamily.Monospace,
//        fontSize = 32.sp,
//        color = Color.Red
//    )


    fun convertUnits(){
        val inputVariableDouble=inputVariable.toDoubleOrNull() ?:0.0
        val result=(inputVariableDouble * iConversionFactor.value *100.0/oConversionFactor.value).roundToInt()/100.0

        outPutVariable=result.toString()
    }

    Column(
        modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Unit Converter", style= MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputVariable, onValueChange = {
            inputVariable=it
            convertUnits()
        }, label={ Text("Enter Value")})
        Spacer(modifier = Modifier.height(16.dp))
        Row{
            Box {
                Button(onClick = { iExpanded=true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                }

                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded=false }) {
                    DropdownMenuItem(text = {Text("Centimeters")},
                        onClick = {
                            iExpanded=false
                            inputUnit="Centimeters"
                            iConversionFactor.value=0.01
                            convertUnits()
                        })

                    DropdownMenuItem(text = {Text("Meters")},
                        onClick = {
                            iExpanded=false
                            inputUnit="Meters"
                            iConversionFactor.value=1.00
                            convertUnits()
                        })

                    DropdownMenuItem(text = {Text("Feet")},
                        onClick = {
                            iExpanded=false
                            inputUnit="Feet"
                            iConversionFactor.value=0.3048
                            convertUnits()
                        })

                    DropdownMenuItem(text = {Text("Millimeters")},
                        onClick = {
                            iExpanded=false
                            inputUnit="Millimeters"
                            iConversionFactor.value=0.001
                            convertUnits()
                        })
                }

            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = { oExpanded=true }) {
                    Text(text = outPutUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                }

                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false }) {
                    DropdownMenuItem(text = {Text("Centimeters")},
                        onClick = {
                            oExpanded=false
                            outPutUnit="Centimeters"
                            oConversionFactor.value=0.01
                            convertUnits()
                        })

                    DropdownMenuItem(text = {Text("Meters")},
                        onClick = {
                            oExpanded=false
                            outPutUnit="Meters"
                            oConversionFactor.value=1.00
                            convertUnits() })

                    DropdownMenuItem(text = {Text("Feet")},
                        onClick = {
                            oExpanded=false
                            outPutUnit="Feet"
                            oConversionFactor.value=0.3048
                            convertUnits() })

                    DropdownMenuItem(text = {Text("Millimeters")},
                        onClick = {
                            oExpanded=false
                            outPutUnit="Milliimeters"
                            oConversionFactor.value=0.001
                            convertUnits()
                        })
                }

            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result:  $outPutVariable $outPutUnit",
            style= MaterialTheme.typography.headlineMedium)
    }
}



@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}