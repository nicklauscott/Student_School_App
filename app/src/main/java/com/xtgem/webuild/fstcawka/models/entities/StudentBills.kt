package com.xtgem.webuild.fstcawka.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.xtgem.webuild.fstcawka.misc.Uncategorized
import com.xtgem.webuild.fstcawka.models.enums.Bills
import com.xtgem.webuild.fstcawka.models.enums.PaymentMethod
import java.time.LocalDateTime
import java.util.UUID

@Entity
data class StudentBills(
    @PrimaryKey val billId: UUID,
    val studentId: UUID,
    val bill: Bills,
    var paidAmount: Int,
    var balance: Int,
    var unpaidPercentage: Int,
    val total: Int = 10000,
    val paymentListIDs: UUID
) {
    fun generatePaymentDetail(): List<PaymentDetail>?{
        return when (unpaidPercentage) {
            in 0..25 -> {
                generatePaymentList((10..15).random(), this)
            }

            in 26..50 -> {
                generatePaymentList((6..9).random(), this)
            }

            in 51..75 -> {
                generatePaymentList((3..5).random(), this)
            }

            in 76..100 -> {
                generatePaymentList((1..3).random(), this)
            }

            else -> null
        }
    }


    private fun generatePaymentList(size: Int, bill: StudentBills): List<PaymentDetail>{
        val paymentList = mutableListOf<PaymentDetail>()

        val dates = mutableListOf<LocalDateTime>()
        repeat(size) {
            dates.add(Uncategorized().generateRandomLocalDateTime(true))
        }
        dates.sortWith(compareBy<LocalDateTime> { it.monthValue }.thenBy { it.dayOfMonth })

        val values = mutableListOf<Double>()
        repeat(size) {
            values.add((2..7).random().toDouble())
        }

        var balance = bill.total.toDouble()
        for (i in divideNumberIntoFiveParts(bill.paidAmount.toDouble(), values)) {
            paymentList.add(
                PaymentDetail(
                    paymentId = UUID.randomUUID(),
                    billId = bill.billId,
                    studentId = bill.studentId,
                    bill = bill.bill,
                    paymentMethod = PaymentMethod.values().random(),
                    dateOfPayment = dates[0],
                    paidAmount = i,
                    oldBalance = balance,
                    allTotal = bill.total
                ))
            balance -= i
            dates.removeAt(0)
        }
        return paymentList
    }

    private fun divideNumberIntoFiveParts(number: Double, values: List<Double>): List<Double> {
        // Calculate the sum of all values
        val totalSum = values.sum()

        // Calculate the parts based on their relative values
        return values.map { it / totalSum * number }
    }
}