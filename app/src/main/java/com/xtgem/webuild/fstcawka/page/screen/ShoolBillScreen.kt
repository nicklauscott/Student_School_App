package com.xtgem.webuild.fstcawka.page.screen


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gandiva.neumorphic.neu
import com.gandiva.neumorphic.shape.RoundedCorner
import com.xtgem.webuild.fstcawka.models.entities.DataResult
import com.xtgem.webuild.fstcawka.models.entities.PaymentDetail
import com.xtgem.webuild.fstcawka.models.entities.StudentBills
import com.xtgem.webuild.fstcawka.models.enums.Bills
import com.xtgem.webuild.fstcawka.models.enums.PaymentMethod
import com.xtgem.webuild.fstcawka.models.relations.ABillAndItsPaymentList
import com.xtgem.webuild.fstcawka.page.component.BillDetailDialog
import com.xtgem.webuild.fstcawka.page.component.ErrorMessage
import com.xtgem.webuild.fstcawka.page.component.InvalidSessionFrame
import com.xtgem.webuild.fstcawka.page.component.LoadingData
import com.xtgem.webuild.fstcawka.page.component.LoadingDialog
import com.xtgem.webuild.fstcawka.page.viewmodel.SchoolBillScreenViewModel
import com.xtgem.webuild.fstcawka.page.viewmodel.SchoolBillScreenViewModelFactory
import com.xtgem.webuild.fstcawka.page.widget.CustomButton1
import com.xtgem.webuild.fstcawka.page.widget.InputText
import com.xtgem.webuild.fstcawka.ui.theme.custom.MyFonts.customFontFamily
import com.xtgem.webuild.fstcawka.ui.theme.custom.defaultPressedNetAttrs

@Composable
fun SchoolBillScreen(userId: String, sessionToken: String) {
    val viewModel: SchoolBillScreenViewModel =
        viewModel(factory = SchoolBillScreenViewModelFactory(userId, sessionToken))
    val getStudent = remember { mutableStateOf(DataResult<List<StudentBills>?>(isLoading = true)) }
    val billDetail = remember { mutableStateOf(DataResult<ABillAndItsPaymentList?>()) }
    val makingPayment = remember { mutableStateOf(DataResult<PaymentDetail>()) }
    LaunchedEffect(Unit) {
        viewModel.studentBills.observeForever { data ->
            getStudent.value = data
        }
        viewModel.billPaymentDetails.observeForever { detailData ->
            billDetail.value = detailData
        }
        viewModel.makingPayment.observeForever { paymentData ->
            makingPayment.value = paymentData
        }
    }

    val context = LocalContext.current

    val importantBills = remember {
        mutableStateOf(true)
    }

    if (billDetail.value.data != null) {
        BillDetailDialog(bill = billDetail.value.data!!) {
            viewModel.clearBilPaymentDetail()
        }
    }

    if (billDetail.value.isLoading) {
        LoadingDialog()
    }

    if (makingPayment.value.extra == true) {
        PayBillDialog(context = context, viewModel = viewModel) {
            viewModel.dismissPayment()
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {

            LazyColumn(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                // Header
                item {
                    BillHeader(getStudent.value)
                }


                when {
                    getStudent.value.isLoading -> {
                        item {
                            LoadingData()
                        }
                    }

                    getStudent.value.sessionInvalid == false -> {
                        item {
                            InvalidSessionFrame()
                        }
                    }

                    else -> {
                        val data = getStudent.value.data
                        if (data != null) {

                            // Bills
                            items(data) {
                                if (importantBills.value) {
                                    if (it.unpaidPercentage > 25) {
                                        SingleBill(bill = it) { studentBill ->
                                            viewModel.getPaymentForABill(studentBill.bill)
                                        }
                                    }
                                } else {
                                    SingleBill(bill = it) { studentBill ->
                                        viewModel.getPaymentForABill(studentBill.bill)
                                    }
                                }
                            }

                            // Total
                            item {
                                TotalBill(bill = data)
                            }

                            // Footer
                            item {
                                BillFooter(
                                    important = importantBills.value,
                                    showAllBillOnclick = {
                                        importantBills.value = !importantBills.value
                                    }) {
                                    // make payment
                                    viewModel.requestMakePayment()
                                }
                            }

                        } else {
                            item {
                                ErrorMessage(
                                    delay = 2000L,
                                    message = "Oops. Bills Not Available",
                                    load = true
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PayBillDialog(context: Context, viewModel: SchoolBillScreenViewModel, onDismiss: () -> Unit) {
    val amount = remember {
        mutableStateOf("")
    }
    val interactAmount = remember {
        MutableInteractionSource()
    }

    val selectedBill = remember {
        mutableStateOf<Bills?>(null)
    }

    val selectedPaymentMethod = remember {
        mutableStateOf<PaymentMethod?>(null)
    }

    val error = remember {
        mutableStateOf(false)
    }
    val billError = remember {
        mutableStateOf(false)
    }
    val paymentMethodError = remember {
        mutableStateOf(false)
    }

    val validatingPayment = remember {
        mutableStateOf(false)
    }

    val toast = Toast.makeText(context, "Require all fields", Toast.LENGTH_SHORT)

    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(modifier = Modifier
            .fillMaxWidth()
            .heightIn()
            .neu(defaultPressedNetAttrs()),
            shape = RoundedCornerShape(12.dp),
            contentColor = Color.Transparent) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
                val inputFieldsModifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(
                        shape = RoundedCornerShape(corner = CornerSize(6.dp)),
                        color = Color.Transparent
                    )

                Spacer(modifier = Modifier.height(10.dp))

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Make a payment", fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                        style = MaterialTheme.typography.headlineSmall)
                }

                Spacer(modifier = Modifier.height(10.dp))

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .border(
                        width = if (billError.value) 1.5.dp else 0.5.dp,
                        color = if (billError.value) Color.Red.copy(alpha = 0.6f)
                        else MaterialTheme.colorScheme.onBackground
                    )) {
                    BillsSpinnerPicker(label = "Select a bill", bills = Bills.values()) {
                        selectedBill.value = it
                        billError.value = false
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .border(
                        width = if (paymentMethodError.value) 1.5.dp else 0.5.dp,
                        color = if (paymentMethodError.value) Color.Red.copy(alpha = 0.6f)
                        else MaterialTheme.colorScheme.onBackground
                    )) {
                    PaymentMethodSpinnerPicker(label = "Select payment Method",
                        paymentMethod = PaymentMethod.values()) {
                        selectedPaymentMethod.value = it
                        paymentMethodError.value = false
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)) {
                    val keyboardType = KeyboardType.Number
                    InputText(text = amount.value,
                        label = if (amount.value.isEmpty()) "Enter Amount" else "",
                        keyboardType = keyboardType,
                        modifier = inputFieldsModifier,
                        interactionSource = interactAmount,
                        isFieldBlank = error.value,
                        onTextChange = {
                            if (it.all { char ->
                                    char.isDigit()
                                }) amount.value = it; error.value = false
                        })
                }

                Spacer(modifier = Modifier.height(20.dp))

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)) {
                    if (!validatingPayment.value) {
                        val buttonModifier = Modifier.height(60.dp)
                        CustomButton1(buttonText = "Done",
                            modifier = buttonModifier) {
                            if (amount.value.isEmpty()) {
                                error.value = true
                                toast?.cancel()
                                toast.show()
                            }
                            if (selectedBill.value == null) {
                                billError.value = true
                                toast?.cancel()
                                toast.show()
                            }
                            if (selectedPaymentMethod.value == null) {
                                paymentMethodError.value = true
                                toast?.cancel()
                                toast.show()
                            }
                            if (amount.value.isNotBlank() && selectedBill.value != null) {
                                viewModel.makeAPayment(amount.value.toDouble(), selectedBill.value!!,
                                    selectedPaymentMethod.value!!)
                                validatingPayment.value = true
                                //onDismiss()
                            }
                        }
                    }else {
                        LoadingData()
                    }
                }
            }
        }
    }
}



@Composable
fun BillsSpinnerPicker(label: String,
                  bills: Array<Bills>,
                  onClick: (Bills) -> Unit) {
    val expanded = remember {
        mutableStateOf(false)
    }

    val selectedItem = remember {
        mutableStateOf(label)
    }

    Column {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp)
            .clickable { expanded.value = true }) {
            Column(modifier = Modifier
                .weight(0.7f)
                .fillMaxHeight(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center) {
                Text(text = selectedItem.value, fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium, fontFamily = customFontFamily[3])
            }
            Column(modifier = Modifier
                .weight(0.3f)
                .fillMaxHeight(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center) {
                Icon(imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Show more")
            }
        }

    }
    DropdownMenu(expanded = expanded.value,
        onDismissRequest = { expanded.value = false },
        modifier = Modifier.fillMaxWidth()) {
        // bills
        bills.forEach { subject ->
            DropdownMenuItem(text = { Text(text = subject.fullName,
                style = MaterialTheme.typography.headlineSmall) },
                onClick = {
                    selectedItem.value = subject.name
                    expanded.value = false
                    onClick(Bills.valueOf(selectedItem.value))
                })
        }
    }
}

@Composable
fun PaymentMethodSpinnerPicker(label: String,
                       paymentMethod: Array<PaymentMethod>,
                       onClick: (PaymentMethod) -> Unit) {
    val expanded = remember {
        mutableStateOf(false)
    }

    val selectedItem = remember {
        mutableStateOf(label)
    }

    Column {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp)
            .clickable { expanded.value = true }) {
            Column(modifier = Modifier
                .weight(0.7f)
                .fillMaxHeight(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center) {
                Text(text = selectedItem.value, fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium, fontFamily = customFontFamily[3])
            }
            Column(modifier = Modifier
                .weight(0.3f)
                .fillMaxHeight(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center) {
                Icon(imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Show more")
            }
        }

    }
    DropdownMenu(expanded = expanded.value,
        onDismissRequest = { expanded.value = false },
        modifier = Modifier.fillMaxWidth()) {
        // bills
        paymentMethod.forEach { paymentMethod ->
            DropdownMenuItem(text = { Text(text = paymentMethod.fullName,
                style = MaterialTheme.typography.headlineSmall) },
                onClick = {
                    selectedItem.value = paymentMethod.name
                    expanded.value = false
                    onClick(PaymentMethod.valueOf(selectedItem.value))
                })
        }
    }
}

@Composable
fun BillHeader(billsState: DataResult<List<StudentBills>?>) {
    val unComplete = billsState.data?.filter {
        it.unpaidPercentage > 25
    }
    Column(
        modifier = Modifier
            .height(100.dp)
            .padding(top = 20.dp, bottom = 16.dp, start = 10.dp)
    ) {
        Text(
            text = if (billsState.data != null) "Unpaid Bills"
            else if (billsState.isLoading) "Bills Loading..." else if (billsState.sessionInvalid == false) "Couldn't load bills" else "",
            color = MaterialTheme.colorScheme.onBackground,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp, fontFamily = customFontFamily[0]
            )
        )

        if (unComplete != null) {
            Text(
                text = if (unComplete.isNotEmpty()) "You have ${unComplete.size} important bills"
                else "",
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                style = TextStyle(
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp, fontFamily = customFontFamily[2]
                ),
                modifier = Modifier.padding(top = 3.dp)
            )
        }
    }
}

@Composable
fun SingleBill(
    bill: StudentBills,
    onClick: (StudentBills) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(top = 5.dp, bottom = 5.dp, start = 5.dp, end = 5.dp)
            .clickable { onClick(bill) },
        shape = RoundedCornerShape(corner = CornerSize(20.dp)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp,
            pressedElevation = 0.dp,
            focusedElevation = 24.dp
        )
    ) {
        Row(modifier = Modifier) {
            Column(
                modifier = Modifier
                    .weight(0.2f),
                horizontalAlignment = Alignment.Start
            ) {
                Card(
                    modifier = Modifier
                        .size(70.dp)
                        .padding(12.dp)
                        .neu(
                            defaultPressedNetAttrs(
                                cornerShape = RoundedCorner(24.dp)
                            )
                        ),
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                ) {
                    Icon(
                        bill.bill.icon, contentDescription = bill.bill.toString(),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
            Column(
                modifier = Modifier
                    .weight(0.8f),
                horizontalAlignment = Alignment.Start
            ) {
                Row {
                    Column(
                        modifier = Modifier
                            .weight(0.7f)
                            .padding(top = 15.dp, bottom = 10.dp)
                    ) {
                        Text(
                            text = bill.bill.fullName,
                            color = MaterialTheme.colorScheme.onBackground,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp, fontFamily = customFontFamily[5]
                            )
                        )
                        Text(
                            text = "${bill.unpaidPercentage}%",
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                            style = TextStyle(
                                fontWeight = FontWeight.Light,
                                fontSize = 16.sp, fontFamily = customFontFamily[3]
                            ),
                            modifier = Modifier.padding(top = 3.dp)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(0.3f)
                            .padding(top = 20.dp, bottom = 10.dp, end = 10.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = "₦${bill.balance}",
                            color = MaterialTheme.colorScheme.onBackground,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 22.sp, fontFamily = customFontFamily[5]
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TotalBill(bill: List<StudentBills>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp, bottom = 20.dp, start = 10.dp, end = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(0.6f),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Total Bill",
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp, fontFamily = customFontFamily[3]
                )
            )
        }
        Column(
            modifier = Modifier
                .weight(0.4f),
            horizontalAlignment = Alignment.End
        ) {
            val total = bill.sumOf { it.balance }
            Text(
                text = "₦$total",
                color = MaterialTheme.colorScheme.onBackground,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp, fontFamily = customFontFamily[0]
                )
            )
        }
    }
}

@Composable
fun BillFooter(important: Boolean, showAllBillOnclick: () -> Unit, payBillOnclick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(bottom = 30.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            CustomButton1(
                buttonText = "PayBill",
                modifier = Modifier.height(55.dp)
            ) {
                payBillOnclick()
            }
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            val buttonText = if (important) "Show All" else "Show Less"
            val buttonBackgroundColor =
                if (isSystemInDarkTheme()) Color(0xFFFFFFFF) else Color(0xFF000000)
            val buttonTextColor =
                if (isSystemInDarkTheme()) Color(0xFF000000) else Color(0xFFE4EAEB)
            CustomButton1(
                buttonText = buttonText,
                backgroundColor = buttonBackgroundColor,
                textColor = buttonTextColor,
                modifier = Modifier.height(55.dp)
            ) {
                showAllBillOnclick()
            }
        }
    }
}
