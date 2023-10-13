package com.xtgem.webuild.fstcawka.models.enums

import android.database.Cursor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart

enum class Bills(val icon: androidx.compose.ui.graphics.vector.ImageVector, val fullName: String) {
    SchoolFees(Icons.Filled.List, "School Fees"),
    HostelFees(Icons.Filled.Home, "Hostel Fees"),
    ClubFees(Icons.Filled.Info, "Club Fees"),
    SubjectFees(Icons.Filled.Place, "Subject Fees"),
    AdminFees(Icons.Filled.AccountBox, "Admin Fees"),
    SecurityFees(Icons.Filled.Face, "Security Fees"),
    ExamFees(Icons.Filled.Person, "Exam Fees"),
    CleaningFees(Icons.Filled.Menu, "Cleaning Fees"),
    FeedingFees(Icons.Filled.ShoppingCart, "Cleaning Fees"),
    UniformFees(Icons.Filled.AccountCircle, "Uniform Fees"),
    TextBookFees(Icons.Filled.AddCircle, "TextBook Fees"),
    LockerFees(Icons.Filled.Check, "Locker Fees"),
    QuizFees(Icons.Filled.MailOutline, "Quiz Fees"),
    PracticalFees(Icons.Filled.List, "Practical Fees"),
    EquipmentFees(Icons.Filled.Face, "Equipment Fees"),
    ComputerFees(Icons.Filled.Settings, "Computer Fees"),
    Miscellaneous(Icons.Filled.Email, "Miscellaneous")
}

