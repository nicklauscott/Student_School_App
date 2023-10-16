package com.xtgem.webuild.fstcawka.page.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HorizontalRule
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.xtgem.webuild.fstcawka.R
import com.xtgem.webuild.fstcawka.misc.Uncategorized
import com.xtgem.webuild.fstcawka.models.entities.DataResult
import com.xtgem.webuild.fstcawka.models.entities.News
import com.xtgem.webuild.fstcawka.models.enums.NewsCategory
import com.xtgem.webuild.fstcawka.models.enums.Screens
import com.xtgem.webuild.fstcawka.models.enums.Subjects
import com.xtgem.webuild.fstcawka.page.component.ErrorMessage
import com.xtgem.webuild.fstcawka.page.component.NavFooter
import com.xtgem.webuild.fstcawka.page.viewmodel.NewsLetterScreenViewModel
import com.xtgem.webuild.fstcawka.ui.theme.custom.MyFonts
import java.util.UUID

@Preview
@Composable
fun NewsLetterScreenPreview() {
    NewsLetterScreen()
}


val TOP_BAR_HEIGHT = 60.dp
val LazyListState.isScrolled: Boolean
    get() = firstVisibleItemIndex > 0 || firstVisibleItemScrollOffset > 0

val LazyGridState.isScrolling: Boolean
    get() = firstVisibleItemIndex > 1 || firstVisibleItemScrollOffset > 1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsLetterScreen(navController: NavController = rememberNavController()) {
    val viewModel = viewModel<NewsLetterScreenViewModel>()
    val allNews = remember { mutableStateOf(DataResult<List<News>>(isLoading = true)) }
    LaunchedEffect(Unit) {
        viewModel.allNews.observeForever { data ->
            allNews.value = data
        }
    }

    val lazyListState = rememberLazyListState()
    val lazyGridState = rememberLazyGridState()

    val selectedIndex = remember {
        mutableIntStateOf(0)
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
                .height(
                    if (lazyListState.isScrollInProgress
                        || lazyGridState.isScrollInProgress
                    ) 0.dp else TOP_BAR_HEIGHT
                )
            ) {
                NavFooter(screen = Screens.NewsLetter, onHomeClick = {
                    navController.navigate(Screens.Home.route)
                }) {
                    // Go to profile
                    navController.navigate(Screens.Profile.route)
                }
            }
        }
    ) { scaffoldPadding ->
        Column(modifier = Modifier.padding(scaffoldPadding)) {
            when {
                allNews.value.isLoading -> {
                    Column(modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator(modifier = Modifier.size(45.dp), color = Color.Black)
                    }
                }
                allNews.value.error != null -> {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 280.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Oops. Bills Not Available",
                            color = Color(0xFF444343),
                            style = TextStyle(fontWeight = FontWeight.Light,
                                fontSize = 20.sp, fontFamily = MyFonts.customFontFamily[2]),
                            modifier = Modifier.padding(top = 3.dp))
                    }
                }
                else -> {
                    val data = allNews.value.data
                    if (data!!.isNotEmpty()) {
                        val selectedCategory = if (selectedIndex.intValue != 0)
                            allNews.value.data?.filter { it.category == NewsCategory.values()[selectedIndex.intValue] }?.toMutableList()
                        else allNews.value.data?.toMutableList()

                        CategorySelector(lazyListState,
                            selectedIndex.intValue) { clickedIndex ->
                            selectedIndex.intValue = clickedIndex
                        }
                        LazyColumn(modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(),
                            state = lazyListState) {
                            item {
                                val news = selectedCategory?.find { it.wideBanner }
                                if (news != null) {
                                    WideNewsBanner(news = news, lazyGridState) { newsId ->
                                        navController.navigate(Screens.NewsLetterDetail.withArg(newsId.toString()))
                                    }
                                }
                            }

                            item {
                                Divider(modifier = Modifier
                                    .height(0.5.dp),
                                    color = MaterialTheme.colorScheme.onBackground)
                            }

                            item {
                                LazyVerticalGrid(modifier = Modifier
                                    .height(800.dp)
                                    .padding(start = 16.dp, end = 16.dp),
                                    columns = GridCells.Adaptive(minSize = 188.dp),
                                    state = lazyGridState) {
                                    //val otherNews = selectedCategory?.filter { !it.wideBanner }
                                    if (selectedCategory != null) {
                                        items(selectedCategory) {
                                            NewsBanner(it) { newsId ->
                                                navController.navigate(Screens.NewsLetterDetail.withArg(newsId.toString()))
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }else {
                        ErrorMessage(delay = 2000L, message = "Oops. News Not Available", load = true)
                    }
                }

            }
        }
    }
}


@Composable
fun WideNewsBanner(
    news: News,
    lazyGridState: LazyGridState,
    onClick: (UUID) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(if (lazyGridState.isScrolling) 0.dp else 280.dp)
        .padding(top = 16.dp, bottom = 0.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    onClick(news.newsId)
                }
                .clipToBounds(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        ){
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))) {
                    // Add child components here.
                    AsyncImage(model = news.imageLink, contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize())
                }

                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "${news.title.take(35)}...",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontFamily = MyFonts.customFontFamily[5],
                    modifier = Modifier.fillMaxWidth())

                Spacer(modifier = Modifier.height(3.dp))
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End) {
                    Text(text = Uncategorized().simpleDateFormatter(news.createdDate), fontSize = 18.sp,
                        style = MaterialTheme.typography.headlineSmall)
                }
            }

        }


    }
}


@Composable
fun NewsBanner(news: News, onClick: (UUID) -> Unit) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(230.dp)
            .padding(top = 16.dp, bottom = 0.dp, end = 4.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        onClick(news.newsId)
                    }
                    .clipToBounds(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            ){
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .height(175.dp)
                        .clip(RoundedCornerShape(4.dp))) {
                        // Add child components here.
                        AsyncImage(model = news.imageLink, contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize())
                    }

                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = "${news.title.take(30)}...",
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontFamily = MyFonts.customFontFamily[5],
                        modifier = Modifier.fillMaxWidth())

                    Spacer(modifier = Modifier.height(3.dp))
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End) {
                        Text(text = Uncategorized().simpleDateFormatter(news.createdDate), fontSize = 10.sp,
                            style = MaterialTheme.typography.headlineSmall)
                    }
                }

            }
        }

}


@Composable
fun NewsLetterHeader(
    lazyListState: LazyListState
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
    lazyListState: LazyListState,
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
fun CategoryCell(lazyListState: LazyListState, index: Int, selectedIndex: Int?,
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






