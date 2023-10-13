package com.xtgem.webuild.fstcawka.models.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.xtgem.webuild.fstcawka.models.entities.PaymentDetail
import com.xtgem.webuild.fstcawka.models.entities.StudentBills
import com.xtgem.webuild.fstcawka.models.enums.Bills

data class AllBillsAndSAllPaymentLis(
    @Embedded val bills: List<StudentBills>,
    @Relation(
        parentColumn = "studentId",
        entityColumn = "studentId"
    )
    val paymentList: List<PaymentDetail>
)

data class ABillAndItsPaymentList(
    @Embedded val bill: StudentBills,
    @Relation(
        parentColumn = "billId",
        entityColumn = "billId"
    )
    val paymentList: List<PaymentDetail>
)

