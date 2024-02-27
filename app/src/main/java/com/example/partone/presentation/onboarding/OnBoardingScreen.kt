package com.example.partone.presentation.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.partone.presentation.common.SoundButton
import com.example.partone.presentation.common.SoundTextButton
import com.example.partone.presentation.onboarding.Dimens.MediumPadding2
import com.example.partone.presentation.onboarding.Dimens.pageIndicatorWidth
import com.example.partone.presentation.onboarding.components.OnBoardingPage
import com.example.partone.presentation.onboarding.components.PageIndicator
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(
    event: (OnBoardingEvent) -> Unit
){
    Column(modifier = Modifier.fillMaxSize()) {

        val pagerState = rememberPagerState(initialPage = 0)
        //        Work needs to be done here
//        {
//            pages.size
//        }

        val buttonState = remember{
            derivedStateOf {
                when (pagerState.currentPage){
                    0 -> listOf("","Next")
                    1 -> listOf("Back","Next")
                    2 -> listOf("Back","Get Started")

                    else -> listOf("","")
                }
            }
        }

        HorizontalPager(count = pages.size,state = pagerState) {index ->
            OnBoardingPage(page = pages[index])
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding2)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PageIndicator(modifier = Modifier.width(pageIndicatorWidth),pageSize = pages.size, selectedPage = pagerState.currentPage)

//        Spacer(modifier = Modifier.weight(.4f))

        Row(verticalAlignment = Alignment.CenterVertically) {
            val scope = rememberCoroutineScope()

            if (buttonState.value[0].isNotEmpty()) {
                SoundTextButton(
                    text = buttonState.value[0],
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                        }
                    }
                )
            }
            SoundButton(
                text = buttonState.value[1],
                onClick = {
                    scope.launch {
                        if (pagerState.currentPage == 2){
                            event(OnBoardingEvent.SaveAppEntry)
                        }else{
                            pagerState.animateScrollToPage(
                                page = pagerState.currentPage + 1
                            )
                        }
                }
            }
            )

        }
        }
        Spacer(modifier = Modifier.weight(.4f))

    }

}

//@Preview
//@Composable
//fun OnBoardingScreenPreview(){
//    OnBoardingScreen()
//}