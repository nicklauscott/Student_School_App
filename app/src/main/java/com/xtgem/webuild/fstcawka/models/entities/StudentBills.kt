package com.xtgem.webuild.fstcawka.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.xtgem.webuild.fstcawka.models.enums.Bills
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
)