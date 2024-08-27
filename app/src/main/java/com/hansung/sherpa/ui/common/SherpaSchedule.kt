package com.hansung.sherpa.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hansung.sherpa.sherpares.PretendardVariable

@Composable
fun SherpaSchedule(
    title: String,
    description: String,
    dateBegin: String,
    dateEnd: String,
    onConfirmation: () -> Unit
) {
    Card(
        modifier = Modifier.size(320.dp, 220.dp),
        colors = CardColors(backgroundCardColor, backgroundCardColor, backgroundCardColor, backgroundCardColor),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp, bottom = 10.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                color = nomalTextColor,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = PretendardVariable
            )

            Text(
                modifier = Modifier.fillMaxWidth(0.8f),
                text = description,
                color = lightTextColor,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = PretendardVariable
            )

            Row {
                Text(dateBegin)
                Box(modifier = Modifier.size(2.dp, 30.dp).clip(CircleShape).background(Color.Black))
                Text(dateEnd)
            }

            SherpaButton2(
                confirmButtonText = "확인",
                onConfirmation = onConfirmation
            )
        }
    }
}

@Preview
@Composable
fun SherpaSchedulePreview(){
    SherpaSchedule("일정 제목", "일정 내용 사과사기", "2024-08-12   22:24", "2024-08-12   23:24") {}
}