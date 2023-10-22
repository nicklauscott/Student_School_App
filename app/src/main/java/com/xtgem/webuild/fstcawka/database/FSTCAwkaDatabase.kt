package com.xtgem.webuild.fstcawka.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.xtgem.webuild.fstcawka.database.dao.OtherDao
import com.xtgem.webuild.fstcawka.database.dao.StudentDao
import com.xtgem.webuild.fstcawka.models.entities.Assignment
import com.xtgem.webuild.fstcawka.models.entities.AssignmentContent
import com.xtgem.webuild.fstcawka.models.entities.AssignmentResult
import com.xtgem.webuild.fstcawka.models.entities.CourseReactions
import com.xtgem.webuild.fstcawka.models.entities.Courses
import com.xtgem.webuild.fstcawka.models.entities.News
import com.xtgem.webuild.fstcawka.models.entities.PaymentDetail
import com.xtgem.webuild.fstcawka.models.entities.Student
import com.xtgem.webuild.fstcawka.models.entities.StudentBills
import com.xtgem.webuild.fstcawka.models.entities.StudentResult
import com.xtgem.webuild.fstcawka.models.entities.UserSession

@Database(
    entities = [
        Student::class, StudentResult::class,
        StudentBills::class, PaymentDetail::class,
        Assignment::class, AssignmentContent::class, AssignmentResult::class,
        Courses::class, News::class, CourseReactions::class, UserSession::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class FSTCAwkaDatabase: RoomDatabase() {

    abstract fun studentDao(): StudentDao

    abstract fun otherDao(): OtherDao


}