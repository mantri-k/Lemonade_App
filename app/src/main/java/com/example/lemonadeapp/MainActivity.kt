package com.example.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeAppTheme{
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonJuice(onImageClick: () -> Unit, textLabel: Int, drawableId: Int, descId: Int) {
    Box(
        modifier = Modifier.background(Color.White)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Button(onClick = onImageClick,
                shape = RoundedCornerShape(size = 0.00092.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier.weight(0.75f)
                ) {
                Image(
                    painter = painterResource(drawableId),
                    contentDescription = stringResource(descId),
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.BottomCenter)
                        .weight(4f)
                        .size(240.dp)
                        .padding(bottom = 16.dp)
                )
            }
            //Spacer(modifier = Modifier
              //  .height(dimensionResource(R.dimen.padding_vertical))
                //.weight(.45f))
            Box(modifier = Modifier.wrapContentSize(Alignment.TopCenter)) {
                Text(
                    text = stringResource(textLabel),
                    style = TextStyle(
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.Default,
                    ),
                    modifier = Modifier.padding(bottom = 300.dp)
                )
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp() {
    var currentStep by remember { mutableIntStateOf(1)}
    var squeezeCount by remember {mutableIntStateOf(0)}
    Scaffold(
       topBar = {
           CenterAlignedTopAppBar(
               title = {
                   Text(
                       text = "Lemonade",
                       style = TextStyle(
                       fontSize = 28.sp,
                       fontWeight = FontWeight.Bold,
                       textAlign = TextAlign.Center
                       )
                   )
               },
               colors = TopAppBarDefaults.largeTopAppBarColors(
                   containerColor = Color.Yellow
               )
           )
       }
    ) {innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ){

        when (currentStep) {
            1 -> {
                LemonJuice(
                    onImageClick = {
                        currentStep = 2
                        squeezeCount = (2..4).random()
                    }, textLabel = R.string.lemon_select,
                    drawableId = R.drawable.lemon_tree,
                    descId = R.string.first
                )
            }

            2 -> {
                LemonJuice(
                    onImageClick =
                    {
                        squeezeCount--
                        if (squeezeCount == 0) {
                            currentStep = 3
                        }
                    }, textLabel = R.string.lemon_squeeze,
                    drawableId = R.drawable.lemon_squeeze,
                    descId = R.string.second
                )
            }

            3 -> {
                LemonJuice(
                    onImageClick = {
                        currentStep = 4
                    }, textLabel = R.string.lemon_drink,
                    drawableId = R.drawable.lemon_drink,
                    descId = R.string.third
                )
            }

            4 -> {
                LemonJuice(
                    onImageClick = {
                        currentStep = 1
                    }, textLabel = R.string.lemon_empty_glass,
                    drawableId = R.drawable.lemon_restart,
                    descId = R.string.fourth
                )
            }
        }
    }
}}
@Preview
@Composable
fun LemonPreview() {
    LemonadeAppTheme {
        LemonadeApp()
    }
}