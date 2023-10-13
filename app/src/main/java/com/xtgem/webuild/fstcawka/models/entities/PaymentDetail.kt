package com.xtgem.webuild.fstcawka.models.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.xtgem.webuild.fstcawka.models.enums.Bills
import com.xtgem.webuild.fstcawka.models.enums.PaymentMethod
import java.time.LocalDateTime
import java.util.UUID

@Entity
data class PaymentDetail(
    @PrimaryKey val paymentId: UUID,
    val billId: UUID,
    val studentId: UUID,
    val bill: Bills,
    val paymentMethod: PaymentMethod,
    val dateOfPayment: LocalDateTime,
    val paidAmount: Double,
    val oldBalance: Double,
    val allTotal: Int,
) {
    @Ignore
    val balance: Double = oldBalance - paidAmount

    @Ignore
    val unpaidPercentage = (((oldBalance - paidAmount) / oldBalance ) * 100).toInt()
}