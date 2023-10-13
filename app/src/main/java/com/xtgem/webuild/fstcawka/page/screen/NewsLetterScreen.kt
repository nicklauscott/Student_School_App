package com.xtgem.webuild.fstcawka.page.screen

import android.content.Context
import android.widget.ImageView
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HorizontalRule
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gandiva.neumorphic.neu
import com.xtgem.webuild.fstcawka.R
import com.xtgem.webuild.fstcawka.models.entities.News
import com.xtgem.webuild.fstcawka.models.enums.NewsCategory
import com.xtgem.webuild.fstcawka.models.enums.Screens
import com.xtgem.webuild.fstcawka.models.enums.Subjects
import com.xtgem.webuild.fstcawka.page.component.NavFooter
import com.xtgem.webuild.fstcawka.page.component.defaultFlatNeuAttrs1
import com.xtgem.webuild.fstcawka.ui.theme.custom.MyFonts
import com.xtgem.webuild.fstcawka.ui.theme.custom.defaultFlatNeuAttrs
import com.xtgem.webuild.fstcawka.ui.theme.custom.defaultPressedNetAttrs
import java.time.LocalDateTime
import java.util.UUID

@Preview
@Composable
fun NewsLetterScreenPreview() {
    NewsLetterScreen()
}


val TOP_BAR_HEIGHT = 60.dp
val LazyGridState.isScrolled: Boolean
    get() = firstVisibleItemIndex > 0 || firstVisibleItemScrollOffset > 0


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsLetterScreen(userId: String = "", navController: NavController = rememberNavController()) {
    val lazyListState = rememberLazyGridState()

    val selectedIndex = remember {
        mutableIntStateOf(0)
    }
    val category = remember {
        mutableStateOf(NewsCategory.values()[selectedIndex.intValue])
    }


    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NewsLetterHeader(lazyListState = lazyListState)
        },
        bottomBar = {
            Column(modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(animationSpec = tween(durationMillis = 500))
                .height(if (lazyListState.isScrollInProgress) 0.dp else TOP_BAR_HEIGHT)
            ) {
                NavFooter(screen = Screens.NewsLetter, onHomeClick = {
                    navController.navigate(Screens.Home.withArg(userId))
                }) {
                    // Go to profile
                    navController.navigate(Screens.Profile.withArg(userId))
                }
            }
        }
    ) { scaffoldPadding ->
        Column(modifier = Modifier.padding(scaffoldPadding)) {
            CategorySelector(lazyListState,
                selectedIndex.intValue) { clickedIndex ->
                selectedIndex.intValue = clickedIndex
            }
            LazyVerticalGrid(modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                state = lazyListState,
                columns = GridCells.Adaptive(minSize = 128.dp)) {
                item {
                    NewsBanner()
                }

                items(Subjects.values()) {
                    NewsBanner(wideBanner = false)
                }
            }
        }
    }
}


@Composable
fun NewsBanner(
    wideBanner: Boolean = false
) {
    val padding = if (wideBanner) PaddingValues(top = 16.dp, bottom = 16.dp)
        else PaddingValues(top = 0.dp, bottom = 0.dp)
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(if (wideBanner) 320.dp else 230.dp)
        .padding(padding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                //.neu(defaultFlatNeuAttrs())
                .clickable {

                }
                .clipToBounds(),
            shape = RoundedCornerShape(if (wideBanner) 24.dp else 12.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        ){
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(if (wideBanner) 8.dp else 4.dp)) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(if (wideBanner) 200.dp else 175.dp)
                    .clip(RoundedCornerShape(if (wideBanner) 8.dp else 4.dp))) {
                    // Add child components here.
                    Image(
                        painter = painterResource(id = R.drawable.img),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize(),
                        colorFilter = ColorFilter.tint(
                            Color(0xFF6F4329).copy(alpha = 0.1f),
                            blendMode = BlendMode.Multiply)
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "The Statue of Liberty, officially ",
                    fontSize = if (wideBanner) 18.sp else 10.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontFamily = MyFonts.customFontFamily[5],
                    modifier = Modifier.fillMaxWidth())

                Spacer(modifier = Modifier.height(3.dp))
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End) {
                    Text(text = "08-03-2023", fontSize = if (wideBanner) 18.sp else 10.sp,
                        style = MaterialTheme.typography.headlineSmall)
                }
            }

        }


    }
}


@Composable
fun NewsLetterHeader(
    lazyListState: LazyGridState
) {
    Column(modifier = Modifier
        .background(MaterialTheme.colorScheme.background)
        .fillMaxWidth()
        .animateContentSize(animationSpec = tween(durationMillis = 500))
        .height(if (lazyListState.isScrolled) 0.dp else TOP_BAR_HEIGHT)
        .padding(top = 16.dp),
        ) {
        Column(modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "News", fontSize = 35.sp, fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineLarge)
        }
    }
}

@Composable
fun CategorySelector(
    lazyListState: LazyGridState,
    selectedIndex: Int?, onClick: (Int) -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(
            if (lazyListState.isScrolled) Color.Transparent
            else MaterialTheme.colorScheme.background
        )) {
        Column(modifier = Modifier.fillMaxWidth()) {
            LazyRow(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
                items(NewsCategory.values()) { category ->
                    CategoryCell(
                        lazyListState = lazyListState,
                        index = NewsCategory.values().indexOf(category),
                        selectedIndex = selectedIndex,
                        newsCategory = category) {
                            onClick(it)
                    }
                }
            }
            Divider(modifier = Modifier
                .height(0.5.dp),
                color = MaterialTheme.colorScheme.onBackground)
        }
    }
}

@Composable
fun CategoryCell(lazyListState: LazyGridState, index: Int, selectedIndex: Int?,
                 newsCategory: NewsCategory = NewsCategory.Housing,
                 onClick: (Int) -> Unit) {
    Column(modifier = Modifier
        .background(
            if (lazyListState.isScrolled) Color.Transparent
            else MaterialTheme.colorScheme.background
        )
        .padding(start = 8.dp, end = 8.dp, top = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Column(modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
            .clickable {
                onClick(index)
            }
            .clipToBounds(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Column(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = newsCategory.newsName,
                    color = if (index == selectedIndex) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.headlineSmall)
            }
            Column(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(painter =

                rememberVectorPainter(image = Icons.Default.HorizontalRule).apply {
                    ColorFilter.tint(MaterialTheme.colorScheme.primary)
                },
                    contentDescription = null,
                    modifier = Modifier
                        .width(80.dp)
                        .offset(0.dp, 11.dp),
                    tint = if  (index == selectedIndex) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.background)
            }

        }
    }
}






