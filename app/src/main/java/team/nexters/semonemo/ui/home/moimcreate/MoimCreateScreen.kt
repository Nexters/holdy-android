package team.nexters.semonemo.ui.home.moimcreate

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import team.nexters.semonemo.R
import team.nexters.semonemo.common.Button
import team.nexters.semonemo.common.TextField
import team.nexters.semonemo.extension.drawColoredShadow
import team.nexters.semonemo.extension.noRippleClickable
import team.nexters.semonemo.theme.Danger1
import team.nexters.semonemo.ui.home.moimcreate.component.DoubleTextField
import team.nexters.semonemo.ui.home.moimcreate.component.TrippleTextField

@Preview
@Composable
internal fun MoimCreateScreen() {
    var date by remember { mutableStateOf("") }
    var startTime by remember { mutableStateOf("") }
    var endTime by remember { mutableStateOf("") }
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
            Spacer(modifier = Modifier.height(60.dp))
            Image(
                modifier = Modifier
                    .align(Alignment.End)
                    .noRippleClickable { },
                painter = painterResource(id = R.drawable.close),
                contentDescription = stringResource(id = R.string.close)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = buildAnnotatedString {
                    append(stringResource(id = R.string.title_date))
                    withStyle(
                        SpanStyle(
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        append(stringResource(id = R.string.star))
                    }

                },
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(12.dp))
            TrippleTextField(
                firstText = date,
                onFirstTextChanged = { date = it },
                firstPlaceHolderText = stringResource(id = R.string.placeholder_date),
                secondText = startTime,
                onSecondTextChanged = { startTime = it },
                secondPlaceHolderText = stringResource(id = R.string.placeholder_start_time),
                thirdText = endTime,
                onThirdTextChanged = { endTime = it },
                thirdPlaceHolderText = stringResource(id = R.string.placeholder_end_time),

                )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = buildAnnotatedString {
                    append(stringResource(id = R.string.title_place))
                    withStyle(
                        SpanStyle(
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        append(stringResource(id = R.string.star))
                    }
                },
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
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
            Text(
                text = stringResource(id = R.string.title_place_link),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
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
            Text(
                text = stringResource(id = R.string.link_is_correct),
                style = MaterialTheme.typography.labelLarge.copy(color = Danger1)
            )
            Spacer(modifier = Modifier.height(35.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    disabledContainerColor = MaterialTheme.colorScheme.tertiary,
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                enabled = isMoimCreateAvailabe(date, startTime, endTime, address, detailAddress),
                shape = RoundedCornerShape(8.dp),
                text = stringResource(id = R.string.moim_create),
            )
        }
    }
}

fun isMoimCreateAvailabe(
    date: String,
    startTime: String,
    endTime: String,
    address: String,
    detailAddress: String
) =
    date.isNotEmpty() && startTime.isNotEmpty() && endTime.isNotEmpty() && address.isNotEmpty() && detailAddress.isNotEmpty()