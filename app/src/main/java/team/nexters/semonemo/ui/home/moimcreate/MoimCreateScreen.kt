package team.nexters.semonemo.ui.home.moimcreate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import team.nexters.semonemo.R
import team.nexters.semonemo.common.TextField
import team.nexters.semonemo.common.TextField
import team.nexters.semonemo.extension.drawColoredShadow
import team.nexters.semonemo.ui.home.moimcreate.component.DoubleTextField

@Composable
internal fun MoimCreateScreen() {
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var detailAddress by remember { mutableStateOf("") }
    var placeLink by remember { mutableStateOf("") }
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .background(Color.White),
        ) {
            Spacer(modifier = Modifier.height(56.dp))
            Text(
                modifier = Modifier
                    .align(Alignment.End),
                text = stringResource(id = R.string.moim_create)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = buildString {
                append(stringResource(id = R.string.title_date))
                append(stringResource(id = R.string.star))
            })
            Spacer(modifier = Modifier.height(12.dp))
            DoubleTextField(
                firstText = date,
                onFirstTextChanged = { date = it },
                firstPlaceHolderText = stringResource(id = R.string.placeholder_date),
                secondText = time,
                onSecondTextChanged = { time = it },
                secondPlaceHolderText = stringResource(id = R.string.placeholder_time),
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = buildString {
                append(stringResource(id = R.string.title_place))
                append(stringResource(id = R.string.star))
            })
            Spacer(modifier = Modifier.height(12.dp))
            DoubleTextField(
                firstText = address,
                onFirstTextChanged = { address = it },
                firstPlaceHolderText = stringResource(id = R.string.placeholder_address),
                secondText = detailAddress,
                onSecondTextChanged = { detailAddress = it },
                secondPlaceHolderText = stringResource(id = R.string.placeholder_detail_address),
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = stringResource(id = R.string.title_place_link))
            Spacer(modifier = Modifier.height(12.dp))
            TextField(
                modifier = Modifier.drawColoredShadow(
                    offsetY = 2.dp,
                    blurRadius = 4.dp
                ),
                value = placeLink,
                onValueChanged = { placeLink = it },
                placeHolderText = stringResource(id = R.string.placeholder_address_link)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = stringResource(id = R.string.link_is_correct), fontSize = 14.sp)

        }
    }


}