package com.xtgem.webuild.fstcawka.models.constants

data class AssignmentHolder(val question: String, val options: List<String>,
                            val answersIndex: String, val point: Int? = (1..3).random())