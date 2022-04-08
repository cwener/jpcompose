package com.example.composeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.composeapp.ui.theme.ComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleLayout()
        }
    }


    @Preview
    @Composable
    fun ConstraintLayoutDemo() {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            // 1. 创建约束引用
            val (loginBtn, userName, loginPic, namePic, centerText, centerHorizontal, guidLine) = createRefs()
            TextField(value = "userName", onValueChange = {

            }, modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                // 2. 将约束引用关联具体控件
                .constrainAs(userName) {
                    // 3. 使用linkTo写具体约束规则
                    top.linkTo(parent.top, margin = 100.dp)
                    start.linkTo(parent.start, margin = 100.dp)
                    end.linkTo(parent.end, margin = 100.dp)
                    width = Dimension.preferredWrapContent
//                    width = Dimension.preferredValue(10000.dp)
//                    width = Dimension.value(10000.dp)
                })

            Button(onClick = { /*TODO*/ }, modifier = Modifier
                .fillMaxWidth()
                .constrainAs(loginBtn) {
                    top.linkTo(userName.bottom, margin = 20.dp)
                    start.linkTo(parent.start, margin = 100.dp)
                    end.linkTo(parent.end, margin = 100.dp)
                    width = Dimension.preferredWrapContent
                }) {
                Text(text = "Login")
            }

//            Image(
//                painter = painterResource(id = R.drawable.ic_launcher_background),
//                modifier = Modifier
//                    .width(50.dp)
//                    .height(50.dp)
//                    .constrainAs(loginPic) {
//                        start.linkTo(loginBtn.end, margin = 20.dp)
//                        top.linkTo(loginBtn.top)
//                        bottom.linkTo(loginBtn.bottom)
//                    },
//                contentDescription = "imageTest"
//            )
//
//            Image(
//                painter = painterResource(id = R.drawable.ic_launcher_background),
//                modifier = Modifier
//                    .width(50.dp)
//                    .height(50.dp)
//                    .constrainAs(namePic) {
//                        // margin负属性生效
//                        start.linkTo(userName.end, margin = 20.dp)
//                        top.linkTo(userName.top)
//                        bottom.linkTo(userName.bottom)
//                    },
//                contentDescription = "imageTest"
//            )


//            val guideline = createGuidelineFromBottom(0.2f)
//            Text(text = "guideLineText", modifier = Modifier.constrainAs(guidLine) {
//                bottom.linkTo(guideline)
//                //居中
////                centerHorizontallyTo(parent)
//            })

//            Text(text = "centerText", modifier = Modifier.constrainAs(centerText) {
//                centerTo(parent)
//            })
//
//            Text(text = "centerHorizontal", modifier = Modifier.constrainAs(centerHorizontal) {
//                centerHorizontallyTo(parent)
//            })


//            val (chain1, chain2, chain3) = createRefs()
//            createHorizontalChain(chain1, chain2, chain3, chainStyle = ChainStyle.Spread)
//            Button(onClick = { }, modifier = Modifier.constrainAs(chain1) {
//            }) {
//                Text(text = "chain 1")
//            }
//
//            Button(onClick = { }, modifier = Modifier.constrainAs(chain2) {
//            }) {
//                Text(text = "chain 2")
//            }
//
//            Button(onClick = { }, modifier = Modifier.constrainAs(chain3) {
//            }) {
//                Text(text = "chain 3")
//            }
        }
    }

    @Composable
    fun TestBarrier() {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            // 1. 创建约束引用
            val (loginBtn, userName, namePic) = createRefs()
            TextField(value = "userName", onValueChange = {

            }, modifier = Modifier
                .width(150.dp)
                .height(50.dp)
                // 2. 将约束引用关联具体控件
                .constrainAs(userName) {
                    // 3. 使用linkTo写具体约束规则
                    top.linkTo(parent.top, margin = 100.dp)
                    start.linkTo(parent.start, margin = 100.dp)
                    end.linkTo(parent.end, margin = 100.dp)
                    width = Dimension.preferredWrapContent
                })

            Button(onClick = { /*TODO*/ }, modifier = Modifier
                .width(100.dp)
                .constrainAs(loginBtn) {
                    top.linkTo(userName.bottom, margin = 20.dp)
                    start.linkTo(parent.start, margin = 100.dp)
                    end.linkTo(parent.end, margin = 100.dp)
                    width = Dimension.preferredWrapContent
                }) {
                Text(text = "Login")
            }

            val barrier = createEndBarrier(userName, loginBtn)
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .constrainAs(namePic) {
                        // margin负属性生效
                        start.linkTo(barrier, margin = 20.dp)
                        top.linkTo(userName.top)
                        bottom.linkTo(userName.bottom)
                    },
                contentDescription = "imageTest"
            )
        }
    }

    @Composable
    fun SimpleLayout() {
        Column {
            TextField(
                value = "userName", onValueChange = {}, modifier = Modifier
                    .padding(start = 50.dp, end = 50.dp, top = 100.dp)
                    .fillMaxWidth()
                    .height(50.dp)
            )

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(start = 50.dp, end = 50.dp, top = 20.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Login")
            }
        }
    }

    @Composable
    fun SimpleLayout2() {
        Column {
            Row(modifier = Modifier.padding(top = 100.dp)) {
                TextField(
                    value = "userName", onValueChange = {}, modifier = Modifier
                        .padding(start = 50.dp)
                        .height(50.dp)
                        .width(200.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .width(50.dp)
                        .height(50.dp),
                    contentDescription = "imageTest"
                )
            }

            Row(modifier = Modifier.padding(top = 20.dp)) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(start = 50.dp)
                        .width(200.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    Text(text = "Login")
                }

                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .width(50.dp)
                        .height(50.dp),
                    contentDescription = "imageTest"
                )
            }
        }
    }

}
