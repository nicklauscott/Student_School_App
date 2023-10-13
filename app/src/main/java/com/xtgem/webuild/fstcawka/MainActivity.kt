package com.xtgem.webuild.fstcawka

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Profile
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.xtgem.webuild.fstcawka.models.entities.Semester
import com.xtgem.webuild.fstcawka.models.entities.Student
import com.xtgem.webuild.fstcawka.models.entities.Subject
import com.xtgem.webuild.fstcawka.models.enums.Gender
import com.xtgem.webuild.fstcawka.models.enums.Grade
import com.xtgem.webuild.fstcawka.models.enums.Screens
import com.xtgem.webuild.fstcawka.models.enums.Semesters
import com.xtgem.webuild.fstcawka.models.enums.Subjects
import com.xtgem.webuild.fstcawka.page.navigation.Navigation
import com.xtgem.webuild.fstcawka.page.screen.HomeScreen
import com.xtgem.webuild.fstcawka.page.screen.LoginScreen
import com.xtgem.webuild.fstcawka.page.screen.NewsLetterScreen
import com.xtgem.webuild.fstcawka.page.screen.ProfileScreen
import com.xtgem.webuild.fstcawka.ui.theme.FSTCAWKATheme
import com.xtgem.webuild.fstcawka.ui.theme.custom.Theme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.UUID

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var data: Screens = Screens.Login
        val id = intent.getStringExtra(ARG_ID)
        try {
             data = intent.getSerializableExtra(ARG_DATA) as Screens
        }catch (_: Exception) { }

        setContent {
            Theme {
                //Navigation()

                NewsLetterScreen()

//                when (data) {
//                    Screens.Login -> LoginScreen()
//                    Screens.Home -> HomeScreen()
//                    Screens.NewsLetter -> NewsLetterScreen()
//                    Screens.Profile -> ProfileScreen()
//                }
            }
        }
    }

    companion object {
        private const val ARG_DATA = "data"
        private const val ARG_ID = ""

        fun newInstance(context: Context, data: Screens, id: UUID? = null): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(ARG_DATA, data)
            intent.putExtra(ARG_ID, id.toString())
            return intent
        }
    }
}
