package com.xtgem.webuild.fstcawka.page.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.gandiva.neumorphic.neu
import com.gandiva.neumorphic.shape.RoundedCorner
import com.xtgem.webuild.fstcawka.misc.Uncategorized
import com.xtgem.webuild.fstcawka.models.entities.PaymentDetail
import com.xtgem.webuild.fstcawka.models.entities.StudentBills
import com.xtgem.webuild.fstcawka.models.relations.ABillAndItsPaymentList
import com.xtgem.webuild.fstcawka.ui.theme.custom.MyFonts
import java.text.DecimalFormat
import java.time.LocalDateTime

@Composable
fun BillDetailDialog(bill: ABillAndItsPaymentList, onDismissRequest: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
    ) {
        Dialog(onDismissRequest = onDismissRequest) {
            Card {
                Column(modifier = Modifier
                    .padding(start = 5.dp, end = 5.dp, top = 5.dp, bottom = 10.dp)
                    .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    LazyColumn{
                        // Header
                        item {
                            BillDetailHeader(bill = bill.bill)
                        }

                        // payment detail list
                        items(bill.paymentList) { payment ->
                            SinglePaymentDetail(payment)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun BillDetailHeader(bill: StudentBills) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(shape = RoundedCornerShape(corner = CornerSize(8.dp)))
    ) {
        Column(modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(10.dp)
        ) {
            // Image with title and date
            Row {
                // Image with title
                Row(modifier = Modifier
                    .weight(0.7f)
                    .padding(start = 5.dp, end = 5.dp)) {
                    // Image
                    Column(modifier = Modifier
                        .weight(0.3f)
                        .padding(top = 5.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Card(modifier = Modifier
                            .size(50.dp)
                            .padding(5.dp)
                                .neu(
                                    com.xtgem.webuild.fstcawka.ui.theme.custom.defaultPressedNetAttrs(
                                        cornerShape = RoundedCorner(24.dp)
                                    )
                                ),
                            shape = CircleShape,
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.background
                            )
                        ) {
                            Icon(bill.bill.icon, contentDescription = bill.bill.toString(),
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                        }
                    }

                    // Title
                    Column(modifier = Modifier
                        .weight(0.8f),
                        horizontalAlignment = Alignment.Start) {
                        Text(text = bill.bill.fullName,
                            color = MaterialTheme.colorScheme.onBackground,
                            style = TextStyle(fontWeight = FontWeight.Bold,
                                fontSize = 20.sp, fontFamily = MyFonts.customFontFamily[0]),
                            modifier = Modifier.padding(top = 16.dp))
                        Text(text = "Payment breakdown",
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                            style = TextStyle(fontWeight = FontWeight.Bold,
                                fontSize = 12.sp, fontFamily = MyFonts.customFontFamily[3]),
                            modifier = Modifier.padding(bottom = 0.dp, top = 0.dp))
                    }
                }

                // Date
                Column(modifier = Modifier.weight(0.3f),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(text = Uncategorized().simpleDateFormatter(LocalDateTime.now()),
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                        style = TextStyle(fontWeight = FontWeight.Bold,
                            fontSize = 15.sp, fontFamily = MyFonts.customFontFamily[3]),
                        modifier = Modifier.padding(end = 10.dp, top = 16.dp))
                }


            }

            // Balance
            Column(modifier = Modifier.padding(start = 16.dp, bottom = 16.dp, top = 10.dp),
                horizontalAlignment = Alignment.Start) {
                Text(text = "${Uncategorized().nairaSign}${bill.balance}",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = TextStyle(fontWeight = FontWeight.Bold,
                        fontSize = 30.sp, fontFamily = MyFonts.customFontFamily[0]))
            }
        }

    }
}


@Composable
fun SinglePaymentDetail(payment: PaymentDetail) {
    val showFullId = remember {
        mutableStateOf(false)
    }
    val decimalFormat = DecimalFormat("#.##")
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 10.dp, end = 10.dp)
        .clip(shape = RoundedCornerShape(corner = CornerSize(8.dp))),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp,
            pressedElevation = 0.dp, hoveredElevation = 12.dp)) {
        Column {
            Row(modifier = Modifier
                .padding(top = 12.dp, bottom = 12.dp, start = 16.dp, end = 16.dp)) {
                Column(modifier = Modifier
                    .weight(0.6f)
                ) {
                    Row(modifier = Modifier
                        .padding(end = 1.dp)
                        .clickable { showFullId.value = !showFullId.value }) {
                        Text(text = if (showFullId.value) payment.paymentId.toString() else payment.paymentId.toString().take(8),
                            color = MaterialTheme.colorScheme.primary,
                            style = TextStyle(fontWeight = FontWeight.Bold,
                                fontSize = 15.sp, fontFamily = MyFonts.customFontFamily[2]),
                            modifier = Modifier
                                .padding(end = 1.dp))
                        if (!showFullId.value) {
                            Icon(Icons.Filled.KeyboardArrowDown, contentDescription = "Show Full Payment Id",
                                modifier = Modifier
                                    .size(15.dp))
                        }else if (showFullId.value) {
                            Icon(Icons.Filled.KeyboardArrowUp, contentDescription = "Show less Payment Id",
                                modifier = Modifier
                                    .size(15.dp),
                                tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f))
                        }
                    }
                }

                Column(modifier = Modifier.weight(0.4f), horizontalAlignment = Alignment.End) {
                    // 0xFF444343
                    Text(text = Uncategorized().simpleDateFormatter(payment.dateOfPayment),
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                        style = TextStyle(fontWeight = FontWeight.Bold,
                            fontSize = 15.sp, fontFamily = MyFonts.customFontFamily[2]))
                }
            }
            Divider(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp),
                color = Color.LightGray)

            Row(modifier = Modifier
                .padding(top = 16.dp, bottom = 5.dp, start = 16.dp, end = 16.dp)) {
                Column(modifier = Modifier
                    .weight(0.6f)) {
                    Text(text = "Old Balance",
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                        style = TextStyle(fontWeight = FontWeight.Bold,
                            fontSize = 14.sp, fontFamily = MyFonts.customFontFamily[2]))
                }

                Column(modifier = Modifier.weight(0.4f), horizontalAlignment = Alignment.End) {
                    Text(text = "${Uncategorized().nairaSign}${decimalFormat.format(payment.oldBalance)}",
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                        style = TextStyle(fontWeight = FontWeight.Bold,
                            fontSize = 14.sp, fontFamily = MyFonts.customFontFamily[2]))
                }
            }
            Row(modifier = Modifier
                .padding(top = 5.dp, bottom = 5.dp, start = 16.dp, end = 16.dp)) {
                Column(modifier = Modifier
                    .weight(0.6f)) {
                    Text(text = "Paid Amount",
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                        style = TextStyle(fontWeight = FontWeight.Bold,
                            fontSize = 14.sp, fontFamily = MyFonts.customFontFamily[2]))
                }

                Column(modifier = Modifier.weight(0.4f), horizontalAlignment = Alignment.End) {
                    Text(text = "${Uncategorized().nairaSign}${decimalFormat.format(payment.paidAmount)}",
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                        style = TextStyle(fontWeight = FontWeight.Bold,
                            fontSize = 14.sp, fontFamily = MyFonts.customFontFamily[2]))
                }
            }
            Row(modifier = Modifier
                .padding(top = 5.dp, bottom = 5.dp, start = 16.dp, end = 16.dp)) {
                Column(modifier = Modifier
                    .weight(0.6f)) {
                    Text(text = "Percentage Paid",
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                        style = TextStyle(fontWeight = FontWeight.Bold,
                            fontSize = 14.sp, fontFamily = MyFonts.customFontFamily[2]))
                }

                Column(modifier = Modifier.weight(0.4f), horizontalAlignment = Alignment.End) {
                    Text(text = "${(100 - payment.unpaidPercentage)}%",
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                        style = TextStyle(fontWeight = FontWeight.Bold,
                            fontSize = 14.sp, fontFamily = MyFonts.customFontFamily[2]))
                }
            }
            Row(modifier = Modifier
                .padding(top = 5.dp, bottom = 5.dp, start = 16.dp, end = 16.dp)) {
                Column(modifier = Modifier
                    .weight(0.6f)) {
                    Text(text = "New Balance",
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                        style = TextStyle(fontWeight = FontWeight.Bold,
                            fontSize = 14.sp, fontFamily = MyFonts.customFontFamily[2]))
                }

                Column(modifier = Modifier.weight(0.4f), horizontalAlignment = Alignment.End) {
                    Text(text = "${Uncategorized().nairaSign}${decimalFormat.format(payment.balance)}",
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                        style = TextStyle(fontWeight = FontWeight.Bold,
                            fontSize = 14.sp, fontFamily = MyFonts.customFontFamily[2]))
                }
            }
            Row(modifier = Modifier
                .padding(top = 5.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)) {
                Column(modifier = Modifier
                    .weight(0.6f)) {
                    Text(text = "Payment Method",
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                        style = TextStyle(fontWeight = FontWeight.Bold,
                            fontSize = 14.sp, fontFamily = MyFonts.customFontFamily[2]))
                }

                Column(modifier = Modifier.weight(0.4f), horizontalAlignment = Alignment.End) {
                    Text(text = payment.paymentMethod.fullName,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                        style = TextStyle(fontWeight = FontWeight.Bold,
                            fontSize = 14.sp, fontFamily = MyFonts.customFontFamily[2]))
                }
            }


            Divider(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp),
                color = Color.LightGray)

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp, end = 16.dp),
                horizontalAlignment = Alignment.End) {
                Text(text = "${Uncategorized().nairaSign}${decimalFormat.format(payment.paidAmount)}",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = TextStyle(fontWeight = FontWeight.Bold,
                        fontSize = 23.sp, fontFamily = MyFonts.customFontFamily[0]))
            }
        }
    }
}