package com.xtgem.webuild.fstcawka.page.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.xtgem.webuild.fstcawka.Repository
import com.xtgem.webuild.fstcawka.models.constants.CustomLiveData
import com.xtgem.webuild.fstcawka.models.entities.DataResult
import com.xtgem.webuild.fstcawka.models.entities.PaymentDetail
import com.xtgem.webuild.fstcawka.models.entities.StudentBills
import com.xtgem.webuild.fstcawka.models.enums.Bills
import com.xtgem.webuild.fstcawka.models.enums.PaymentMethod
import com.xtgem.webuild.fstcawka.models.relations.ABillAndItsPaymentList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.util.UUID

class SchoolBillScreenViewModel(val studentId: UUID, val sessionToken: UUID): ViewModel() {
    val repository = Repository.get()

    val studentBills: CustomLiveData<DataResult<List<StudentBills>?>> = CustomLiveData()
    val billPaymentDetails: CustomLiveData<DataResult<ABillAndItsPaymentList?>> = CustomLiveData()
    val makingPayment: CustomLiveData<DataResult<PaymentDetail>> = CustomLiveData()

    init {
        getAllBills()
    }

    private fun getAllBills() {
      viewModelScope.launch(Dispatchers.IO) {
          val allBills = repository.getAllBills(studentId, sessionToken)
          withContext(Dispatchers.Main) {
              if (allBills != null) {
                  studentBills.updateValue(DataResult(data = allBills, isLoading = false))
              }else {
                  studentBills.updateValue(DataResult(isLoading = false, sessionInvalid = false))
              }
          }
      }

    }

    fun clearBilPaymentDetail() {
        billPaymentDetails.updateValue(DataResult(data = null, success = null, isLoading = false))
    }

    fun getPaymentForABill(bill: Bills) {
        billPaymentDetails.updateValue(DataResult(isLoading = true))
        viewModelScope.launch(Dispatchers.Default) {
            delay(500)
           val details = repository.getAllBillsAndPaymentDetail(studentId, sessionToken, bill)
            withContext(Dispatchers.Main) {
                if (details != null) {
                    billPaymentDetails.updateValue(DataResult(data = details, success = true, isLoading = false))
                }else {
                    billPaymentDetails.updateValue(DataResult(isLoading = false, success = false))
                }
            }
        }
    }

    fun makeAPayment(amount: Double, bill: Bills, paymentMethod: PaymentMethod) {
        val selectedBill = studentBills.value?.data?.find { it.bill == bill }
        if (selectedBill != null && selectedBill.balance >= amount) {
            val newPaymentDetail = PaymentDetail(
                UUID.randomUUID(), selectedBill.billId, studentId, selectedBill.bill, paymentMethod,
                LocalDateTime.now(), amount, selectedBill.balance.toDouble(), selectedBill.total
            )
            val newPaidAmount = selectedBill.paidAmount + amount.toInt()
            val newUnpaidPercentage = (((selectedBill.total - newPaidAmount.toDouble()) / selectedBill.total ) * 100).toInt()
            val studentBills = selectedBill.copy(
                paidAmount = newPaidAmount,
                unpaidPercentage = newUnpaidPercentage,
                balance = selectedBill.total - newPaidAmount
            )
            viewModelScope.launch(Dispatchers.Default) {
                repository.database.studentDao().insertPayment(newPaymentDetail)
                repository.database.studentDao().insertBill(studentBills)
                delay(1000)
                withContext(Dispatchers.Main) {
                    makingPayment.updateValue(DataResult(extra = false))
                    getAllBills()
                }
            }
        }
        makingPayment.updateValue(DataResult(extra = false))
    }

    fun dismissPayment() {
        makingPayment.updateValue(DataResult(extra = false))
    }
    fun requestMakePayment() {
        makingPayment.updateValue(DataResult(extra = true))
    }


}

class SchoolBillScreenViewModelFactory(private val studentId: String, private val sessionToken: String): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SchoolBillScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SchoolBillScreenViewModel(UUID.fromString(studentId), UUID.fromString(sessionToken)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}