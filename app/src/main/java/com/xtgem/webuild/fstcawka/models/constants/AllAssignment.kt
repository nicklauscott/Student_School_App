package com.xtgem.webuild.fstcawka.models.constants

import com.xtgem.webuild.fstcawka.models.enums.Grade
import com.xtgem.webuild.fstcawka.models.enums.Subjects


data class AssignmentHoldersModel(
    val subjects: Subjects,
    val description: String,
    val grade: List<Grade>,
    val assignment: List<AssignmentHolder>
)

fun getAllAssignmentHoldersModel(): List<AssignmentHoldersModel>{
    return listOf(
        englishQuizQuestions, englishQuizQuestions2, scienceQuizQuestions, currentAffairsQuizQuestions,
        currentAffairsQuizQuestions2, geographyQuizQuestions, businessStudiesQuiz , civicEducationQuestions,
        biologyQuestions, basicTechnology, arts, physics, physics2, chemistry, chemistry, french,
    )

}

val mathsAssignmentHolderList = mutableListOf(
    AssignmentHolder(
        question = "121 Divided by 11 is",
        options = listOf("11", "10", "19", "18"),
        answersIndex = "0",
        point = (1..3).random()
    ),
    AssignmentHolder(
        question = "60 Times of 8 Equals to",
        options = listOf("480", "300", "250", "400"),
        answersIndex = "0",
        point = (1..3).random()
    ),
    AssignmentHolder(
        question = "Find the Missing Term in Multiples of 6 : 6, 12, 18, 24, _, 36, 42, _ 54, 60.",
        options = listOf("32, 45", "30, 48", "24, 40", "25, 49"),
        answersIndex = "1",
        point = (1..3).random()
    ),
    AssignmentHolder(
        question = "What is the Next Prime Number after 7?",
        options = listOf("13", "12", "14", "11"),
        answersIndex = "1",
        point = (1..3).random()
    ),
    AssignmentHolder(
        question = "The Product of 131 × 0 × 300 × 4",
        options = listOf("11", "0", "46", "45"),
        answersIndex = "1",
        point = (1..3).random()
    ),
    AssignmentHolder(
        question = "Solve 3 + 6 × ( 5 + 4) ÷ 3 - 7",
        options = listOf("11", "16", "14", "15"),
        answersIndex = "1",
        point = (1..3).random()
    ),
    AssignmentHolder(
        question = "Solve 23 + 3 ÷ 3",
        options = listOf("24", "25", "26", "27"),
        answersIndex = "0",
        point = (1..3).random()
    ),
    AssignmentHolder(
        question = "What is 6% Equals to",
        options = listOf("0.06", "0.6", "0.006", "0.0006"),
        answersIndex = "0",
        point = (1..3).random()
    ),
    AssignmentHolder(
        question = "How Many Years are there in a Decade?",
        options = listOf("5 years", "10 years", "15 years", "20 years"),
        answersIndex = "1",
        point = (1..3).random()
    ),
    AssignmentHolder(
        question = "How Many Months Make a Century?",
        options = listOf("12", "120", "1200", "12000"),
        answersIndex = "2",
        point = (1..3).random()
    ),
    AssignmentHolder(
        question = "Priya had 16 Red Balls, 2 Green Balls, 9 Blue Balls, and 1 Multicolor Ball. If He Lost 9 Red Balls, 1 Green Ball, and 3 Blue Balls. How Many Balls would be Left?",
        options = listOf("15", "11", "28", "39"),
        answersIndex = "0",
        point = (1..3).random()
    ),
    AssignmentHolder(
        question = "Add the Decimals 5.23 + 8.79",
        options = listOf("14.02", "14.19", "14.11", "14.29"),
        answersIndex = "0", ///////////////////////////////////////////////////////////////////////////////////////
        point = (1..3).random()
    ),
    AssignmentHolder(
        question = "How Many Months Have 120 Days?",
        options = listOf("2 months", "11 months", "12 months", "4 months"),
        answersIndex = "3",
        point = (1..3).random()
    ),
    AssignmentHolder(
        question = "How Many Sides are there in a Decagon?",
        options = listOf("7", "8", "9", "10"),
        answersIndex = "3",
        point = (1..3).random()
    ),
    AssignmentHolder(
        question = "What Number Comes Before 9019?",
        options = listOf("9099", "9091", "None of these", "9109"),
        answersIndex = "2",
        point = (1..3).random()
    ),
    AssignmentHolder(
        question = "What is the product of 121 x 0 x 20 x 2.5 -",
        options = listOf("6050", "0", "2,420", "None of the above"),
        answersIndex = "1",
        point = (1..3).random()
    ),
    AssignmentHolder(
        question = "Look at this series: 36, 34, 30, 28, …, 22 What number should come to fill in the blank space",
        options = listOf("25", "24", "26", "None of the above"),
        answersIndex = "1",
        point = (1..3).random()
    ),
    AssignmentHolder(
        question = "27 is a perfect cube. If true then what is the perfect cube of 27?",
        options = listOf("9", "6", "3", "27 is not a perfect cube"),
        answersIndex = "2",
        point = (1..3).random()
    )
)

val mathsAssignmentHolderList2 = AssignmentHoldersModel(
    Subjects.Maths,
    "A math quiz is a short test that assesses a student's knowledge of math " +
            "concepts and skills. Math quizzes can be used to measure a student's " +
            "progress in a math course, to identify areas where they need additional" +
            " support, or to prepare them for a larger exam.",
    Grade.values().toList(),
    listOf(
        AssignmentHolder(
            question = "121 Divided by 11 is",
            options = listOf("11", "10", "19", "18"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "60 Times of 8 Equals to",
            options = listOf("480", "300", "250", "400"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Find the Missing Term in Multiples of 6 : 6, 12, 18, 24, _, 36, 42, _ 54, 60.",
            options = listOf("32, 45", "30, 48", "24, 40", "25, 49"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the Next Prime Number after 7?",
            options = listOf("13", "12", "14", "11"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "The Product of 131 × 0 × 300 × 4",
            options = listOf("11", "0", "46", "45"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Solve 3 + 6 × ( 5 + 4) ÷ 3 - 7",
            options = listOf("11", "16", "14", "15"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Solve 23 + 3 ÷ 3",
            options = listOf("24", "25", "26", "27"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is 6% Equals to",
            options = listOf("0.06", "0.6", "0.006", "0.0006"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "How Many Years are there in a Decade?",
            options = listOf("5 years", "10 years", "15 years", "20 years"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "How Many Months Make a Century?",
            options = listOf("12", "120", "1200", "12000"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Priya had 16 Red Balls, 2 Green Balls, 9 Blue Balls, and 1 Multicolor Ball. If He Lost 9 Red Balls, 1 Green Ball, and 3 Blue Balls. How Many Balls would be Left?",
            options = listOf("15", "11", "28", "39"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Add the Decimals 5.23 + 8.79",
            options = listOf("14.02", "14.19", "14.11", "14.29"),
            answersIndex = "0", ///////////////////////////////////////////////////////////////////////////////////////
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "How Many Months Have 120 Days?",
            options = listOf("2 months", "11 months", "12 months", "4 months"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "How Many Sides are there in a Decagon?",
            options = listOf("7", "8", "9", "10"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What Number Comes Before 9019?",
            options = listOf("9099", "9091", "None of these", "9109"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the product of 121 x 0 x 20 x 2.5 -",
            options = listOf("6050", "0", "2,420", "None of the above"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Look at this series: 36, 34, 30, 28, …, 22 What number should come to fill in the blank space",
            options = listOf("25", "24", "26", "None of the above"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "27 is a perfect cube. If true then what is the perfect cube of 27?",
            options = listOf("9", "6", "3", "27 is not a perfect cube"),
            answersIndex = "2",
            point = (1..3).random()
        )
    )
)

val englishQuizQuestions = AssignmentHoldersModel(
    Subjects.English,
    "Analyze and evaluate current events and developments " +
            "through the lens of English language and literature.",
    Grade.values().toList(),
    listOf(
        AssignmentHolder(
            question = "I live in Amsterdam.",
            options = listOf("In", "Live", "Amsterdam", "I"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "I visited the Eiffel Tower in Paris.",
            options = listOf("The", "Paris", "Eiffel Tower and Paris", "Eiffel Tower"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Bhutan is a beautiful country.",
            options = listOf("A", "Country", "Beautiful", "Bhutan"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Summer is very hot.",
            options = listOf("Summer", "Very", "Hot", "Is"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "The moon looks so beautiful.",
            options = listOf("Looks", "Moon", "The", "Beautiful"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "The doctor is an expert in his field.",
            options = listOf("His", "Expert", "Field", "Doctor"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "The mailman Mr. Joe was carrying postcards.",
            options = listOf("Mailman", "Mr.Joe", "Postcards", "Mailman, Mr. Joe and postcards"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Are these mangoes tasty?",
            options = listOf("Tasty", "Are", "These", "Mangoes"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Hello Sophie! Will you play football with us as the climate is beautiful and the team is one player short?",
            options = listOf("Football", "Sophie, football, climate, team, player", "Team", "Climate"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Mike’s birthday party at the Sheraton was a success.",
            options = listOf("Sheraton", "Mike", "Mike, party, Sheraton", "Party"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "They were having dinner.",
            options = listOf("Dinner", "They", "Were", "Having"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "I want to sleep.",
            options = listOf("To", "Sleep", "I", "Want"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Is that my laptop?",
            options = listOf("My", "Laptop", "Is", "That"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Everyone is sleeping in the dorm room.",
            options = listOf("Sleeping", "Dorm", "Room", "Everyone"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "We were playing scrabble.",
            options = listOf("Playing", "Scrabble", "We", "Were"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Look at Ellen, she is a beautiful artist.",
            options = listOf("Beautiful", "She", "Ellen", "Artist"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "He is good at cricket.",
            options = listOf("At", "Good", "Cricket", "He"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "This house is mine.",
            options = listOf("This", "House", "Mine", "Is"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Would you like to have coffee?",
            options = listOf("Coffee", "Have", "You", "Would"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Did you hear that?",
            options = listOf("That", "Did", "You", "Hear"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "The monkey sat by the door.",
            options = listOf("Monkey", "By", "Door", "Sat"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Jack left in a hurry.",
            options = listOf("A", "Hurry", "Jack", "Left"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Michelle hurt her elbow while playing.",
            options = listOf("Hurt", "Elbow", "Michelle", "Hurt, playing"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Please open the door.",
            options = listOf("Open", "The", "Please", "Door"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "She bought a new car and started taking her driving lessons.",
            options = listOf("Started", "New", "Taking", "Bought, started, taking"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Joe accepted the job offer.",
            options = listOf("The", "Job", "Accepted", "Joe"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Kelly enjoys hip-hop music.",
            options = listOf("Kelly", "Hip-hop", "Music", "Enjoys"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "We went to the grocery store and bought so many items that carrying them home became difficult.",
            options = listOf("Went", "Bought", "Carrying", "All three"),
            answersIndex = "3",
            point = (1..3).random()
        ))
)

val englishQuizQuestions2 = AssignmentHoldersModel(
    Subjects.English,
    "An English quiz is a short test that assesses a student's " +
            "knowledge of English grammar, vocabulary, and usage. English" +
            " quizzes can be used to measure a student's progress in an English" +
            " course, to identify areas where they need additional support," +
            " or to prepare them for a larger exam",
    Grade.values().toList(),
    listOf(
        AssignmentHolder(
            question = "What is the past tense of the verb 'eat'?",
            options = listOf("Eaten", "Ate", "Eat", "Eating"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which of the following is a synonym for 'happy'?",
            options = listOf("Sad", "Angry", "Joyful", "Tired"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the plural form of 'child'?",
            options = listOf("Childrens", "Childs", "Childes", "Children"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which word is a conjunction?",
            options = listOf("Run", "And", "Blue", "Table"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the opposite of 'cold'?",
            options = listOf("Warm", "Hot", "Freezing", "Chilly"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the comparative form of 'good'?",
            options = listOf("Better", "Best", "Well", "Goodest"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which of the following is a pronoun?",
            options = listOf("Jump", "She", "Fast", "Dog"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the plural form of 'mouse'?",
            options = listOf("Mouses", "Mice", "Mousen", "Mousies"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which word is an adverb?",
            options = listOf("Apple", "Quickly", "Red", "Tall"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the present participle form of 'run'?",
            options = listOf("Running", "Ran", "Runs", "Runned"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which word is a preposition?",
            options = listOf("Jump", "She", "Under", "Fast"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the superlative form of 'far'?",
            options = listOf("Farrer", "Farther", "Furthest", "Farest"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which of the following is a conjunction?",
            options = listOf("Jump", "And", "Blue", "Table"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the plural form of 'ox'?",
            options = listOf("Oxen", "Oxes", "Oxies", "Oxy"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the past tense of the verb 'drink'?",
            options = listOf("Drinked", "Drank", "Drink", "Drunk"),
            answersIndex = "1",
            point = (1..3).random()
        ))
)

val scienceQuizQuestions2 = AssignmentHoldersModel(                                                                //////////////
    Subjects.ScienceTech,
    "Explore the latest scientific " +
            "discoveries and developments, and explain their significance " +
            "and implications for society.",
    Grade.values().toList(),
    listOf(
        AssignmentHolder(
            question = "What is the chemical symbol for gold?",
            options = listOf("Go", "Ag", "Au", "Ge"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which gas is most abundant in Earth's atmosphere?",
            options = listOf("Oxygen", "Carbon dioxide", "Nitrogen", "Helium"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the process by which plants make their own food using sunlight?",
            options = listOf("Photosynthesis", "Respiration", "Transpiration", "Germination"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the chemical formula for water?",
            options = listOf("H2O2", "CO2", "H2O", "O2"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which planet is known as the 'Red Planet'?",
            options = listOf("Earth", "Mars", "Venus", "Jupiter"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the smallest unit of matter?",
            options = listOf("Atom", "Molecule", "Cell", "Nucleus"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What gas do plants absorb from the atmosphere during photosynthesis?",
            options = listOf("Carbon dioxide", "Oxygen", "Nitrogen", "Hydrogen"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which of these is a primary color of light?",
            options = listOf("Red", "Yellow", "Green", "Blue"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the Earth's largest organ?",
            options = listOf("Brain", "Liver", "Heart", "Skin"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the chemical symbol for the element oxygen?",
            options = listOf("O", "O2", "O3", "Ox"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the process by which plants lose water vapor through their leaves?",
            options = listOf("Photosynthesis", "Respiration", "Transpiration", "Germination"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which gas do humans exhale when they breathe out?",
            options = listOf("Oxygen", "Carbon dioxide", "Nitrogen", "Helium"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the force that pulls objects toward the center of the Earth?",
            options = listOf("Friction", "Magnetism", "Gravity", "Tension"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the chemical formula for carbon dioxide?",
            options = listOf("H2O", "CO2", "O2", "CH4"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which planet is known as the 'Morning Star' or 'Evening Star'?",
            options = listOf("Mercury", "Mars", "Venus", "Jupiter"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the SI unit of force?",
            options = listOf("Newton", "Kilogram", "Meter", "Second"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the chemical symbol for helium?",
            options = listOf("H", "He", "Li", "Be"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the process by which an organism develops from a single cell into a multicellular organism?",
            options = listOf("Photosynthesis", "Respiration", "Transpiration", "Development"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the chemical formula for methane?",
            options = listOf("H2O", "CO2", "O2", "CH4"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which planet is known as the 'Giant of our Solar System'?",
            options = listOf("Earth", "Mars", "Venus", "Jupiter"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the smallest unit of life?",
            options = listOf("Atom", "Molecule", "Cell", "Nucleus"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What gas is produced during the process of respiration?",
            options = listOf("Carbon dioxide", "Oxygen", "Nitrogen", "Hydrogen"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which of these is NOT a primary color of pigment?",
            options = listOf("Red", "Yellow", "Green", "Blue"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which organ is responsible for pumping blood throughout the body?",
            options = listOf("Brain", "Liver", "Heart", "Skin"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the chemical symbol for the element nitrogen?",
            options = listOf("N", "Ni", "Ne", "No"),
            answersIndex = "0",
            point = (1..3).random()
        ))
)

val scienceQuizQuestions = AssignmentHoldersModel(
    Subjects.ScienceTech,
    "Explore the latest scientific " +
            "discoveries and developments, and explain their significance " +
            "and implications for society.",
    Grade.values().toList(),
    listOf(
        AssignmentHolder(
            question = "What is the chemical symbol for gold?",
            options = listOf("Go", "Ag", "Au", "Ge"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which gas is most abundant in Earth's atmosphere?",
            options = listOf("Oxygen", "Carbon dioxide", "Nitrogen", "Helium"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the process by which plants make their own food using sunlight?",
            options = listOf("Photosynthesis", "Respiration", "Transpiration", "Germination"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the chemical formula for water?",
            options = listOf("H2O2", "CO2", "H2O", "O2"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which planet is known as the 'Red Planet'?",
            options = listOf("Earth", "Mars", "Venus", "Jupiter"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the smallest unit of matter?",
            options = listOf("Atom", "Molecule", "Cell", "Nucleus"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What gas do plants absorb from the atmosphere during photosynthesis?",
            options = listOf("Carbon dioxide", "Oxygen", "Nitrogen", "Hydrogen"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which of these is a primary color of light?",
            options = listOf("Red", "Yellow", "Green", "Blue"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the Earth's largest organ?",
            options = listOf("Brain", "Liver", "Heart", "Skin"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the chemical symbol for the element oxygen?",
            options = listOf("O", "O2", "O3", "Ox"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the process by which plants lose water vapor through their leaves?",
            options = listOf("Photosynthesis", "Respiration", "Transpiration", "Germination"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which gas do humans exhale when they breathe out?",
            options = listOf("Oxygen", "Carbon dioxide", "Nitrogen", "Helium"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the force that pulls objects toward the center of the Earth?",
            options = listOf("Friction", "Magnetism", "Gravity", "Tension"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the chemical formula for carbon dioxide?",
            options = listOf("H2O", "CO2", "O2", "CH4"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which planet is known as the 'Morning Star' or 'Evening Star'?",
            options = listOf("Mercury", "Mars", "Venus", "Jupiter"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the SI unit of force?",
            options = listOf("Newton", "Kilogram", "Meter", "Second"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the chemical symbol for helium?",
            options = listOf("H", "He", "Li", "Be"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the process by which an organism develops from a single cell into a multicellular organism?",
            options = listOf("Photosynthesis", "Respiration", "Transpiration", "Development"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the chemical formula for methane?",
            options = listOf("H2O", "CO2", "O2", "CH4"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which planet is known as the 'Giant of our Solar System'?",
            options = listOf("Earth", "Mars", "Venus", "Jupiter"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the smallest unit of life?",
            options = listOf("Atom", "Molecule", "Cell", "Nucleus"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What gas is produced during the process of respiration?",
            options = listOf("Carbon dioxide", "Oxygen", "Nitrogen", "Hydrogen"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which of these is NOT a primary color of pigment?",
            options = listOf("Red", "Yellow", "Green", "Blue"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which organ is responsible for pumping blood throughout the body?",
            options = listOf("Brain", "Liver", "Heart", "Skin"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the chemical symbol for the element nitrogen?",
            options = listOf("N", "Ni", "Ne", "No"),
            answersIndex = "0",
            point = (1..3).random()
        ))
)

val currentAffairsQuizQuestions = AssignmentHoldersModel(
    Subjects.History,
    "Current affairs assignment: Demonstrate your knowledge" +
            " of recent events and developments around the world " +
            "by answering a series of questions on a variety of topics",
    Grade.values().toList(),
    mutableListOf(
        AssignmentHolder(
            question = "Who is the current President of the United States?",
            options = listOf("Barack Obama", "Joe Biden", "Donald Trump", "George W. Bush"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which country recently hosted the 2021 Summer Olympics?",
            options = listOf("China", "United States", "Japan", "Brazil"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the name of the COVID-19 vaccine developed by Pfizer and BioNTech?",
            options = listOf("Covaxin", "Moderna", "Sputnik V", "Comirnaty"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Who won the Nobel Peace Prize in 2020 for their efforts to combat hunger?",
            options = listOf("Greta Thunberg", "Malala Yousafzai", "World Food Programme", "Barack Obama"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which country recently became the first to land a rover on Mars in 2021 as part of the Tianwen-1 mission?",
            options = listOf("Russia", "United States", "China", "India"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What major event led to the impeachment of President Donald Trump in 2019?",
            options = listOf("COVID-19 pandemic", "Ukraine scandal", "Climate change summit", "Trade war with China"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which country recently withdrew from the European Union, a process commonly referred to as 'Brexit'?",
            options = listOf("France", "Germany", "United Kingdom", "Italy"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Who is the current Secretary-General of the United Nations?",
            options = listOf("Ban Ki-moon", "Kofi Annan", "António Guterres", "Javier Pérez de Cuéllar"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which country recently signed the Abraham Accords, normalizing relations with Israel in 2020?",
            options = listOf("Saudi Arabia", "Egypt", "United Arab Emirates", "Jordan"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Who became the first woman of South Asian descent to be elected Vice President of the United States in 2020?",
            options = listOf("Kamala Harris", "Nikki Haley", "Tulsi Gabbard", "Pramila Jayapal"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which country recently experienced a military coup in February 2021, leading to the detention of its political leaders?",
            options = listOf("Myanmar", "Cambodia", "Thailand", "Vietnam"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Who won the 2021 Nobel Prize in Literature for her exceptional storytelling?",
            options = listOf("Margaret Atwood", "J.K. Rowling", "Chimamanda Ngozi Adichie", "Louise Glück"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which global organization is responsible for coordinating the distribution of COVID-19 vaccines to low-income countries?",
            options = listOf("World Trade Organization", "World Health Organization", "International Monetary Fund", "Gavi, the Vaccine Alliance"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What historic agreement was reached between Israel and the United Arab Emirates in 2020, mediated by the United States?",
            options = listOf("Camp David Accords", "Oslo Accords", "Abraham Accords", "Geneva Accords"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which global event led to the cancellation of the 2020 Summer Olympics in Tokyo?",
            options = listOf("Economic recession", "Natural disaster", "Terrorist attack", "COVID-19 pandemic"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Who is the current Prime Minister of India as of 2021?",
            options = listOf("Narendra Modi", "Manmohan Singh", "Rahul Gandhi", "Amit Shah"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which major international climate agreement aims to combat climate change and limit global warming?",
            options = listOf("Kyoto Protocol", "Paris Agreement", "Montreal Protocol", "Copenhagen Accord"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which country recently became the first in the world to approve the Pfizer-BioNTech COVID-19 vaccine in December 2020?",
            options = listOf("United States", "United Kingdom", "Germany", "France"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Who is the founder and CEO of Tesla, Inc.?",
            options = listOf("Bill Gates", "Jeff Bezos", "Elon Musk", "Mark Zuckerberg"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which country recently became the first to successfully land a rover on the far side of the Moon in 2019?",
            options = listOf("Russia", "United States", "China", "India"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What significant trade agreement was signed between the United States, Mexico, and Canada, replacing NAFTA, in 2020?",
            options = listOf("Trans-Pacific Partnership", "United States-Mexico-Canada Agreement (USMCA)", "European Union-United States Trade Agreement", "Asia-Pacific Economic Cooperation"),
            answersIndex = "1",
            point = (1..3).random()
        ))
)

val currentAffairsQuizQuestions2 = AssignmentHoldersModel(                                                    /////////
    Subjects.History,
    "Current affairs assignment: Demonstrate your knowledge" +
            " of recent events and developments around the world " +
            "by answering a series of questions on a variety of topics",
    Grade.values().toList(),
    mutableListOf(
        AssignmentHolder(
            question = "Who is the current President of the United States?",
            options = listOf("Barack Obama", "Joe Biden", "Donald Trump", "George W. Bush"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which country recently hosted the 2021 Summer Olympics?",
            options = listOf("China", "United States", "Japan", "Brazil"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the name of the COVID-19 vaccine developed by Pfizer and BioNTech?",
            options = listOf("Covaxin", "Moderna", "Sputnik V", "Comirnaty"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Who won the Nobel Peace Prize in 2020 for their efforts to combat hunger?",
            options = listOf("Greta Thunberg", "Malala Yousafzai", "World Food Programme", "Barack Obama"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which country recently became the first to land a rover on Mars in 2021 as part of the Tianwen-1 mission?",
            options = listOf("Russia", "United States", "China", "India"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What major event led to the impeachment of President Donald Trump in 2019?",
            options = listOf("COVID-19 pandemic", "Ukraine scandal", "Climate change summit", "Trade war with China"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which country recently withdrew from the European Union, a process commonly referred to as 'Brexit'?",
            options = listOf("France", "Germany", "United Kingdom", "Italy"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Who is the current Secretary-General of the United Nations?",
            options = listOf("Ban Ki-moon", "Kofi Annan", "António Guterres", "Javier Pérez de Cuéllar"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which country recently signed the Abraham Accords, normalizing relations with Israel in 2020?",
            options = listOf("Saudi Arabia", "Egypt", "United Arab Emirates", "Jordan"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Who became the first woman of South Asian descent to be elected Vice President of the United States in 2020?",
            options = listOf("Kamala Harris", "Nikki Haley", "Tulsi Gabbard", "Pramila Jayapal"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which country recently experienced a military coup in February 2021, leading to the detention of its political leaders?",
            options = listOf("Myanmar", "Cambodia", "Thailand", "Vietnam"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Who won the 2021 Nobel Prize in Literature for her exceptional storytelling?",
            options = listOf("Margaret Atwood", "J.K. Rowling", "Chimamanda Ngozi Adichie", "Louise Glück"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which global organization is responsible for coordinating the distribution of COVID-19 vaccines to low-income countries?",
            options = listOf("World Trade Organization", "World Health Organization", "International Monetary Fund", "Gavi, the Vaccine Alliance"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What historic agreement was reached between Israel and the United Arab Emirates in 2020, mediated by the United States?",
            options = listOf("Camp David Accords", "Oslo Accords", "Abraham Accords", "Geneva Accords"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which global event led to the cancellation of the 2020 Summer Olympics in Tokyo?",
            options = listOf("Economic recession", "Natural disaster", "Terrorist attack", "COVID-19 pandemic"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Who is the current Prime Minister of India as of 2021?",
            options = listOf("Narendra Modi", "Manmohan Singh", "Rahul Gandhi", "Amit Shah"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which major international climate agreement aims to combat climate change and limit global warming?",
            options = listOf("Kyoto Protocol", "Paris Agreement", "Montreal Protocol", "Copenhagen Accord"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which country recently became the first in the world to approve the Pfizer-BioNTech COVID-19 vaccine in December 2020?",
            options = listOf("United States", "United Kingdom", "Germany", "France"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Who is the founder and CEO of Tesla, Inc.?",
            options = listOf("Bill Gates", "Jeff Bezos", "Elon Musk", "Mark Zuckerberg"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which country recently became the first to successfully land a rover on the far side of the Moon in 2019?",
            options = listOf("Russia", "United States", "China", "India"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What significant trade agreement was signed between the United States, Mexico, and Canada, replacing NAFTA, in 2020?",
            options = listOf("Trans-Pacific Partnership", "United States-Mexico-Canada Agreement (USMCA)", "European Union-United States Trade Agreement", "Asia-Pacific Economic Cooperation"),
            answersIndex = "1",
            point = (1..3).random()
        ))
)

val geographyQuizQuestions = AssignmentHoldersModel(
    Subjects.Geography,
    "Examine the impact of current events and developments on" +
            " the physical and human geography of the world.",
    Grade.values().toList(),
    listOf(
        AssignmentHolder(
            question = "Which river is the longest in the world?",
            options = listOf("Amazon River", "Nile River", "Yangtze River", "Mississippi River"),
            answersIndex = "1",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "Which continent is known as the 'Land Down Under'?",
            options = listOf("Africa", "South America", "Australia", "Asia"),
            answersIndex = "2",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "What is the largest desert in the world?",
            options = listOf("Sahara Desert", "Arctic Desert", "Gobi Desert", "Antarctica"),
            answersIndex = "0",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "Which country is the largest by land area?",
            options = listOf("Russia", "Canada", "United States", "China"),
            answersIndex = "0",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "Which mountain range stretches along the western coast of South America?",
            options = listOf("Andes Mountains", "Rocky Mountains", "Himalayas", "Alps"),
            answersIndex = "0",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "What is the world's smallest ocean?",
            options = listOf("Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Southern Ocean"),
            answersIndex = "3",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "Which country is known as the 'Land of the Rising Sun'?",
            options = listOf("China", "India", "Japan", "South Korea"),
            answersIndex = "2",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "What is the largest continent by land area?",
            options = listOf("North America", "Africa", "Asia", "Europe"),
            answersIndex = "2",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "Which river flows through the Grand Canyon in the United States?",
            options = listOf("Colorado River", "Mississippi River", "Amazon River", "Nile River"),
            answersIndex = "0",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "Which city is located at the confluence of the Danube and Sava rivers and is the capital of Serbia?",
            options = listOf("Zagreb", "Belgrade", "Bucharest", "Sarajevo"),
            answersIndex = "1",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "What is the highest mountain in Africa?",
            options = listOf("Mount Kilimanjaro", "Mount Kenya", "Atlas Mountain", "Ras Dashen"),
            answersIndex = "0",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "Which country is known for having the most islands in the world?",
            options = listOf("Indonesia", "Philippines", "Maldives", "Japan"),
            answersIndex = "0",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "Which desert is located in the southwestern United States and northern Mexico?",
            options = listOf("Gobi Desert", "Sahara Desert", "Atacama Desert", "Sonoran Desert"),
            answersIndex = "3",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "What is the capital city of Brazil?",
            options = listOf("Rio de Janeiro", "Sao Paulo", "Brasília", "Belo Horizonte"),
            answersIndex = "2",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "Which African country is known as the 'Pearl of Africa'?",
            options = listOf("Kenya", "Uganda", "Tanzania", "Rwanda"),
            answersIndex = "1",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "What is the longest river in Europe?",
            options = listOf("Seine River", "Rhine River", "Danube River", "Volga River"),
            answersIndex = "3",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "Which country is located on the Iberian Peninsula and shares a border with Spain?",
            options = listOf("Italy", "France", "Portugal", "Greece"),
            answersIndex = "2",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "Which ocean is the Great Barrier Reef located in?",
            options = listOf("Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"),
            answersIndex = "3",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "What is the capital city of South Korea?",
            options = listOf("Seoul", "Busan", "Incheon", "Daegu"),
            answersIndex = "0",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "Which country is the largest by population?",
            options = listOf("Russia", "India", "China", "United States"),
            answersIndex = "2",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "What is the highest mountain in North America?",
            options = listOf("Mount McKinley", "Mount St. Elias", "Mount Logan", "Pikes Peak"),
            answersIndex = "0",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "Which river flows through Cairo, the capital of Egypt?",
            options = listOf("Tigris River", "Nile River", "Euphrates River", "Jordan River"),
            answersIndex = "1",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "Which African country is known as the 'Rainbow Nation'?",
            options = listOf("Nigeria", "Kenya", "Ghana", "South Africa"),
            answersIndex = "3",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "What is the largest lake in Africa?",
            options = listOf("Lake Victoria", "Lake Tanganyika", "Lake Malawi", "Lake Chad"),
            answersIndex = "0",
            point = (1..3).random()

        ))
)

val geographyQuizQuestions2 = AssignmentHoldersModel(                                         /////
    Subjects.Geography,
    "Examine the impact of current events and developments on" +
            " the physical and human geography of the world.",
    Grade.values().toList(),
    listOf(
        AssignmentHolder(
            question = "Which river is the longest in the world?",
            options = listOf("Amazon River", "Nile River", "Yangtze River", "Mississippi River"),
            answersIndex = "1",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "Which continent is known as the 'Land Down Under'?",
            options = listOf("Africa", "South America", "Australia", "Asia"),
            answersIndex = "2",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "What is the largest desert in the world?",
            options = listOf("Sahara Desert", "Arctic Desert", "Gobi Desert", "Antarctica"),
            answersIndex = "0",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "Which country is the largest by land area?",
            options = listOf("Russia", "Canada", "United States", "China"),
            answersIndex = "0",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "Which mountain range stretches along the western coast of South America?",
            options = listOf("Andes Mountains", "Rocky Mountains", "Himalayas", "Alps"),
            answersIndex = "0",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "What is the world's smallest ocean?",
            options = listOf("Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Southern Ocean"),
            answersIndex = "3",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "Which country is known as the 'Land of the Rising Sun'?",
            options = listOf("China", "India", "Japan", "South Korea"),
            answersIndex = "2",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "What is the largest continent by land area?",
            options = listOf("North America", "Africa", "Asia", "Europe"),
            answersIndex = "2",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "Which river flows through the Grand Canyon in the United States?",
            options = listOf("Colorado River", "Mississippi River", "Amazon River", "Nile River"),
            answersIndex = "0",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "Which city is located at the confluence of the Danube and Sava rivers and is the capital of Serbia?",
            options = listOf("Zagreb", "Belgrade", "Bucharest", "Sarajevo"),
            answersIndex = "1",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "What is the highest mountain in Africa?",
            options = listOf("Mount Kilimanjaro", "Mount Kenya", "Atlas Mountain", "Ras Dashen"),
            answersIndex = "0",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "Which country is known for having the most islands in the world?",
            options = listOf("Indonesia", "Philippines", "Maldives", "Japan"),
            answersIndex = "0",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "Which desert is located in the southwestern United States and northern Mexico?",
            options = listOf("Gobi Desert", "Sahara Desert", "Atacama Desert", "Sonoran Desert"),
            answersIndex = "3",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "What is the capital city of Brazil?",
            options = listOf("Rio de Janeiro", "Sao Paulo", "Brasília", "Belo Horizonte"),
            answersIndex = "2",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "Which African country is known as the 'Pearl of Africa'?",
            options = listOf("Kenya", "Uganda", "Tanzania", "Rwanda"),
            answersIndex = "1",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "What is the longest river in Europe?",
            options = listOf("Seine River", "Rhine River", "Danube River", "Volga River"),
            answersIndex = "3",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "Which country is located on the Iberian Peninsula and shares a border with Spain?",
            options = listOf("Italy", "France", "Portugal", "Greece"),
            answersIndex = "2",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "Which ocean is the Great Barrier Reef located in?",
            options = listOf("Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"),
            answersIndex = "3",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "What is the capital city of South Korea?",
            options = listOf("Seoul", "Busan", "Incheon", "Daegu"),
            answersIndex = "0",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "Which country is the largest by population?",
            options = listOf("Russia", "India", "China", "United States"),
            answersIndex = "2",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "What is the highest mountain in North America?",
            options = listOf("Mount McKinley", "Mount St. Elias", "Mount Logan", "Pikes Peak"),
            answersIndex = "0",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "Which river flows through Cairo, the capital of Egypt?",
            options = listOf("Tigris River", "Nile River", "Euphrates River", "Jordan River"),
            answersIndex = "1",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "Which African country is known as the 'Rainbow Nation'?",
            options = listOf("Nigeria", "Kenya", "Ghana", "South Africa"),
            answersIndex = "3",
            point = (1..3).random()

        ),
        AssignmentHolder(
            question = "What is the largest lake in Africa?",
            options = listOf("Lake Victoria", "Lake Tanganyika", "Lake Malawi", "Lake Chad"),
            answersIndex = "0",
            point = (1..3).random()

        ))
)


val businessStudiesQuiz = AssignmentHoldersModel(
    Subjects.BusinessStudies,
    "Investigate the impact of current events and developments on" +
            "the global economy and business practices.",
    Grade.values().toList(),
    listOf(
        AssignmentHolder(
            question = "What is the primary goal of a business organization?",
            options = listOf("Profit maximization", "Customer satisfaction", "Employee happiness", "Social responsibility"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is a business plan?",
            options = listOf("A document outlining a company's goals and strategies", "A financial statement", "An employee training program", "A marketing campaign"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What type of business structure has limited liability for its owners?",
            options = listOf("Sole proprietorship", "Partnership", "Limited liability company (LLC)", "Corporation"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What does SWOT analysis stand for in business?",
            options = listOf("Strengths, Weaknesses, Opportunities, Threats", "Sales, Workforce, Operations, Technology", "Strategy, Wealth, Optimization, Time", "Service, Warranty, Order, Tax"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which economic concept refers to the total value of goods and services produced by a country in a specific period?",
            options = listOf("Profit", "Gross Domestic Product (GDP)", "Supply and demand", "Monopoly"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the process of turning raw materials into finished products?",
            options = listOf("Distribution", "Marketing", "Manufacturing", "Finance"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which financial statement provides an overview of a company's revenues and expenses for a specific period?",
            options = listOf("Balance sheet", "Income statement", "Cash flow statement", "Statement of retained earnings"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is a market segmentation strategy based on dividing a market into distinct groups with similar needs and behaviors?",
            options = listOf("Monopoly", "Oligopoly", "Market penetration", "Market segmentation"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the legal document that gives an individual or entity the exclusive right to make, use, and sell an invention?",
            options = listOf("Trademark", "Copyright", "Patent", "Trade secret"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which type of business organization is characterized by a single owner who assumes all the risks and responsibilities of the business?",
            options = listOf("Partnership", "Corporation", "Limited liability company (LLC)", "Sole proprietorship"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for a situation in which a business produces more goods than it can sell and has excess inventory?",
            options = listOf("Monopoly", "Oligopoly", "Overproduction", "Market segmentation"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the process of planning, organizing, and controlling an organization's resources to achieve specific goals?",
            options = listOf("Marketing", "Production", "Management", "Finance"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What type of business organization has shareholders and can issue stocks and bonds?",
            options = listOf("Sole proprietorship", "Partnership", "Limited liability company (LLC)", "Corporation"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the study of how people make choices to satisfy their needs and wants with limited resources?",
            options = listOf("Business ethics", "Microeconomics", "Macroeconomics", "Market research"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which economic system is characterized by private ownership of businesses and minimal government intervention?",
            options = listOf("Socialism", "Capitalism", "Communism", "Mixed economy"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for a situation in which a few large companies dominate an industry and have significant market power?",
            options = listOf("Monopoly", "Oligopoly", "Market penetration", "Market segmentation"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which department in a company is responsible for managing employees, including hiring, training, and performance evaluation?",
            options = listOf("Marketing", "Human resources", "Finance", "Operations"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for the process of bringing a new product or service to the market?",
            options = listOf("Distribution", "Production", "Marketing", "Innovation"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which financial statement shows a company's assets, liabilities, and shareholders' equity at a specific point in time?",
            options = listOf("Income statement", "Balance sheet", "Cash flow statement", "Statement of retained earnings"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for the funds that a business uses to finance its day-to-day operations and short-term liabilities?",
            options = listOf("Capital budget", "Operating budget", "Cash flow", "Working capital"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which type of market structure is characterized by a single seller with significant control over price and supply?",
            options = listOf("Monopoly", "Oligopoly", "Perfect competition", "Monopolistic competition"),
            answersIndex = "0",
            point = (1..3).random()
        ))
)

val businessStudiesQuiz2 = AssignmentHoldersModel(                                                            ////////
    Subjects.BusinessStudies,
    "Investigate the impact of current events and developments on" +
            "the global economy and business practices.",
    Grade.values().toList(),
    listOf(
        AssignmentHolder(
            question = "What is the primary goal of a business organization?",
            options = listOf("Profit maximization", "Customer satisfaction", "Employee happiness", "Social responsibility"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is a business plan?",
            options = listOf("A document outlining a company's goals and strategies", "A financial statement", "An employee training program", "A marketing campaign"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What type of business structure has limited liability for its owners?",
            options = listOf("Sole proprietorship", "Partnership", "Limited liability company (LLC)", "Corporation"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What does SWOT analysis stand for in business?",
            options = listOf("Strengths, Weaknesses, Opportunities, Threats", "Sales, Workforce, Operations, Technology", "Strategy, Wealth, Optimization, Time", "Service, Warranty, Order, Tax"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which economic concept refers to the total value of goods and services produced by a country in a specific period?",
            options = listOf("Profit", "Gross Domestic Product (GDP)", "Supply and demand", "Monopoly"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the process of turning raw materials into finished products?",
            options = listOf("Distribution", "Marketing", "Manufacturing", "Finance"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which financial statement provides an overview of a company's revenues and expenses for a specific period?",
            options = listOf("Balance sheet", "Income statement", "Cash flow statement", "Statement of retained earnings"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is a market segmentation strategy based on dividing a market into distinct groups with similar needs and behaviors?",
            options = listOf("Monopoly", "Oligopoly", "Market penetration", "Market segmentation"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the legal document that gives an individual or entity the exclusive right to make, use, and sell an invention?",
            options = listOf("Trademark", "Copyright", "Patent", "Trade secret"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which type of business organization is characterized by a single owner who assumes all the risks and responsibilities of the business?",
            options = listOf("Partnership", "Corporation", "Limited liability company (LLC)", "Sole proprietorship"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for a situation in which a business produces more goods than it can sell and has excess inventory?",
            options = listOf("Monopoly", "Oligopoly", "Overproduction", "Market segmentation"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the process of planning, organizing, and controlling an organization's resources to achieve specific goals?",
            options = listOf("Marketing", "Production", "Management", "Finance"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What type of business organization has shareholders and can issue stocks and bonds?",
            options = listOf("Sole proprietorship", "Partnership", "Limited liability company (LLC)", "Corporation"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the study of how people make choices to satisfy their needs and wants with limited resources?",
            options = listOf("Business ethics", "Microeconomics", "Macroeconomics", "Market research"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which economic system is characterized by private ownership of businesses and minimal government intervention?",
            options = listOf("Socialism", "Capitalism", "Communism", "Mixed economy"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for a situation in which a few large companies dominate an industry and have significant market power?",
            options = listOf("Monopoly", "Oligopoly", "Market penetration", "Market segmentation"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which department in a company is responsible for managing employees, including hiring, training, and performance evaluation?",
            options = listOf("Marketing", "Human resources", "Finance", "Operations"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for the process of bringing a new product or service to the market?",
            options = listOf("Distribution", "Production", "Marketing", "Innovation"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which financial statement shows a company's assets, liabilities, and shareholders' equity at a specific point in time?",
            options = listOf("Income statement", "Balance sheet", "Cash flow statement", "Statement of retained earnings"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for the funds that a business uses to finance its day-to-day operations and short-term liabilities?",
            options = listOf("Capital budget", "Operating budget", "Cash flow", "Working capital"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which type of market structure is characterized by a single seller with significant control over price and supply?",
            options = listOf("Monopoly", "Oligopoly", "Perfect competition", "Monopolistic competition"),
            answersIndex = "0",
            point = (1..3).random()
        ))
)

val civicEducationQuestions = AssignmentHoldersModel(
    Subjects.CivicEducation,
    "Assess the impact of current events and developments on civic" +
            "institutions and democratic processes.",
    Grade.values().toList(),
    listOf(
        AssignmentHolder(
            question = "What is the highest law of the land in the United States?",
            options = listOf("The Constitution", "The Bill of Rights", "The Declaration of Independence", "The Articles of Confederation"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "In a democracy, who holds the ultimate power?",
            options = listOf("The President", "The Congress", "The People", "The Judiciary"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the system of government in which citizens elect representatives to make decisions on their behalf?",
            options = listOf("Monarchy", "Oligarchy", "Democracy", "Dictatorship"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What are the first ten amendments to the United States Constitution called?",
            options = listOf("The Bill of Rights", "The Articles of Confederation", "The Preamble", "The Declaration of Independence"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for the division of powers and responsibilities between the national and state governments in the United States?",
            options = listOf("Federalism", "Unitarism", "Confederalism", "Autocracy"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the process of legally transferring citizenship from one country to another?",
            options = listOf("Naturalization", "Immigration", "Deportation", "Extradition"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for a system of government in which power is held by a single, self-appointed ruler?",
            options = listOf("Monarchy", "Oligarchy", "Democracy", "Dictatorship"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "In the United States, what are the three branches of government?",
            options = listOf("Executive, Legislative, Judicial", "President, Congress, Supreme Court", "State, Local, Federal", "Democracy, Republic, Monarchy"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for a government in which a single person or a small group holds all political power?",
            options = listOf("Democracy", "Oligarchy", "Republic", "Autocracy"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for a system of government in which citizens have the power to make decisions directly rather than through elected representatives?",
            options = listOf("Direct democracy", "Representative democracy", "Oligarchy", "Dictatorship"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the process of formally accusing a government official of wrongdoing and bringing them to trial?",
            options = listOf("Impeachment", "Veto", "Pardon", "Filibuster"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for the fundamental rights and freedoms to which all citizens are entitled?",
            options = listOf("Laws", "Regulations", "Constitution", "Human rights"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for the process of drawing electoral district boundaries to favor one political party over another?",
            options = listOf("Gerrymandering", "Electoral college", "Ballot initiative", "Voting rights act"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the principle that government is based on the will of the people and requires their consent to govern?",
            options = listOf("Rule of law", "Republicanism", "Popular sovereignty", "Separation of powers"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for a written agreement between two or more sovereign states or governments?",
            options = listOf("Treaty", "Declaration", "Constitution", "Bill of rights"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for a formal expression of disapproval by a legislative body of one of its members' actions?",
            options = listOf("Censure", "Recall", "Veto", "Referendum"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the principle that government is limited in what it may do, and that its powers are defined and restricted by law?",
            options = listOf("Limited government", "Federalism", "Separation of powers", "Checks and balances"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for a formal process by which a citizen can propose a new law or changes to an existing law?",
            options = listOf("Impeachment", "Veto", "Pardon", "Initiative"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for the practice of granting favors or jobs in exchange for political support?",
            options = listOf("Gerrymandering", "Patronage", "Campaign finance", "Lobbying"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for a formal change or addition to the Constitution?",
            options = listOf("Amendment", "Bill of rights", "Preamble", "Treaty"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for a system of government in which a hereditary monarch serves as the head of state within the parameters of a constitution?",
            options = listOf("Democracy", "Oligarchy", "Republic", "Constitutional monarchy"),
            answersIndex = "3",
            point = (1..3).random()
        ))
)

val civicEducationQuestions2 = AssignmentHoldersModel(                                                     /////
    Subjects.CivicEducation,
    "Assess the impact of current events and developments on civic" +
            "institutions and democratic processes.",
    Grade.values().toList(),
    listOf(
        AssignmentHolder(
            question = "What is the highest law of the land in the United States?",
            options = listOf("The Constitution", "The Bill of Rights", "The Declaration of Independence", "The Articles of Confederation"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "In a democracy, who holds the ultimate power?",
            options = listOf("The President", "The Congress", "The People", "The Judiciary"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the system of government in which citizens elect representatives to make decisions on their behalf?",
            options = listOf("Monarchy", "Oligarchy", "Democracy", "Dictatorship"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What are the first ten amendments to the United States Constitution called?",
            options = listOf("The Bill of Rights", "The Articles of Confederation", "The Preamble", "The Declaration of Independence"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for the division of powers and responsibilities between the national and state governments in the United States?",
            options = listOf("Federalism", "Unitarism", "Confederalism", "Autocracy"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the process of legally transferring citizenship from one country to another?",
            options = listOf("Naturalization", "Immigration", "Deportation", "Extradition"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for a system of government in which power is held by a single, self-appointed ruler?",
            options = listOf("Monarchy", "Oligarchy", "Democracy", "Dictatorship"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "In the United States, what are the three branches of government?",
            options = listOf("Executive, Legislative, Judicial", "President, Congress, Supreme Court", "State, Local, Federal", "Democracy, Republic, Monarchy"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for a government in which a single person or a small group holds all political power?",
            options = listOf("Democracy", "Oligarchy", "Republic", "Autocracy"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for a system of government in which citizens have the power to make decisions directly rather than through elected representatives?",
            options = listOf("Direct democracy", "Representative democracy", "Oligarchy", "Dictatorship"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the process of formally accusing a government official of wrongdoing and bringing them to trial?",
            options = listOf("Impeachment", "Veto", "Pardon", "Filibuster"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for the fundamental rights and freedoms to which all citizens are entitled?",
            options = listOf("Laws", "Regulations", "Constitution", "Human rights"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for the process of drawing electoral district boundaries to favor one political party over another?",
            options = listOf("Gerrymandering", "Electoral college", "Ballot initiative", "Voting rights act"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the principle that government is based on the will of the people and requires their consent to govern?",
            options = listOf("Rule of law", "Republicanism", "Popular sovereignty", "Separation of powers"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for a written agreement between two or more sovereign states or governments?",
            options = listOf("Treaty", "Declaration", "Constitution", "Bill of rights"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for a formal expression of disapproval by a legislative body of one of its members' actions?",
            options = listOf("Censure", "Recall", "Veto", "Referendum"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the principle that government is limited in what it may do, and that its powers are defined and restricted by law?",
            options = listOf("Limited government", "Federalism", "Separation of powers", "Checks and balances"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for a formal process by which a citizen can propose a new law or changes to an existing law?",
            options = listOf("Impeachment", "Veto", "Pardon", "Initiative"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for the practice of granting favors or jobs in exchange for political support?",
            options = listOf("Gerrymandering", "Patronage", "Campaign finance", "Lobbying"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for a formal change or addition to the Constitution?",
            options = listOf("Amendment", "Bill of rights", "Preamble", "Treaty"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for a system of government in which a hereditary monarch serves as the head of state within the parameters of a constitution?",
            options = listOf("Democracy", "Oligarchy", "Republic", "Constitutional monarchy"),
            answersIndex = "3",
            point = (1..3).random()
        ))
)

val biologyQuestions = AssignmentHoldersModel(
    Subjects.Biology,
    "This assignment could cover a wide range of topics, such as:\n" +
            "\n" +
            " The biological mechanisms of disease\n" +
            ", The impact of climate change on biodiversity\n" +
            ",    The ethical implications of new biological technologies",
    listOf(Grade.SS1, Grade.SS2, Grade.SS3),
    listOf(
        AssignmentHolder(
            question = "What is the process by which plants make their own food using sunlight?",
            options = listOf("Photosynthesis", "Respiration", "Fermentation", "Transpiration"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the basic unit of life?",
            options = listOf("Atom", "Cell", "Molecule", "Organism"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which gas do plants absorb from the atmosphere and release oxygen during photosynthesis?",
            options = listOf("Carbon dioxide (CO2)", "Oxygen (O2)", "Nitrogen (N2)", "Hydrogen (H2)"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the chemical substance found in the cell nucleus that carries genetic information?",
            options = listOf("DNA", "RNA", "Protein", "Carbohydrate"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the powerhouse of the cell that produces energy in the form of ATP?",
            options = listOf("Mitochondria", "Endoplasmic reticulum", "Golgi apparatus", "Nucleus"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which organelle is responsible for protein synthesis in a cell?",
            options = listOf("Mitochondria", "Ribosome", "Endoplasmic reticulum", "Lysosome"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the process by which cells divide and create two identical daughter cells?",
            options = listOf("Fertilization", "Mitosis", "Meiosis", "Binary fission"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which gas do animals breathe in and use in cellular respiration?",
            options = listOf("Oxygen (O2)", "Carbon dioxide (CO2)", "Nitrogen (N2)", "Hydrogen (H2)"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the fluid-filled sac that surrounds and protects the nucleus of a cell?",
            options = listOf("Cytoplasm", "Plasma membrane", "Vacuole", "Nuclear membrane"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which process allows organisms to exchange gases with their environment, such as taking in oxygen and releasing carbon dioxide?",
            options = listOf("Photosynthesis", "Respiration", "Fermentation", "Transpiration"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the green pigment in plant cells that is essential for photosynthesis?",
            options = listOf("Chlorophyll", "Melanin", "Hemoglobin", "Carotene"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which part of the plant is responsible for absorbing water and nutrients from the soil?",
            options = listOf("Leaves", "Stem", "Roots", "Flowers"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the genetic material of a virus?",
            options = listOf("DNA", "RNA", "Protein", "Carbohydrate"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the process by which DNA is copied to create an RNA molecule?",
            options = listOf("Replication", "Transcription", "Translation", "Mutation"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which part of a plant is responsible for the transport of water and nutrients between the roots and leaves?",
            options = listOf("Leaves", "Stem", "Roots", "Flowers"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for the process by which an organism gradually changes over time to better adapt to its environment?",
            options = listOf("Mutation", "Adaptation", "Evolution", "Selection"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which blood type is considered the universal recipient?",
            options = listOf("A", "B", "AB", "O"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the smallest unit of an element that retains the properties of that element?",
            options = listOf("Atom", "Molecule", "Cell", "Organism"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which type of biomolecule includes enzymes, antibodies, and hormones and plays various roles in the body?",
            options = listOf("Carbohydrates", "Lipids", "Proteins", "Nucleic acids"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for a group of organisms of the same species living in the same area?",
            options = listOf("Community", "Ecosystem", "Population", "Habitat"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which process involves the breaking down of complex organic molecules into simpler substances with the release of energy?",
            options = listOf("Photosynthesis", "Respiration", "Fermentation", "Transpiration"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the main function of red blood cells in the human body?",
            options = listOf("Carry oxygen", "Digest food", "Produce antibodies", "Filter waste"),
            answersIndex = "0",
            point = (1..3).random()
        ))
)

val basicTechnology = AssignmentHoldersModel(
    Subjects.BasicTech,
    "Analyze the impact of recent economic and technological development" +
            " on global business practices.",
    Grade.values().toList(),
    listOf(
        AssignmentHolder(
            question = "What is the process by which plants make their own food using sunlight?",
            options = listOf("Photosynthesis", "Respiration", "Fermentation", "Transpiration"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the basic unit of life?",
            options = listOf("Atom", "Cell", "Molecule", "Organism"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which gas do plants absorb from the atmosphere and release oxygen during photosynthesis?",
            options = listOf("Carbon dioxide (CO2)", "Oxygen (O2)", "Nitrogen (N2)", "Hydrogen (H2)"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the chemical substance found in the cell nucleus that carries genetic information?",
            options = listOf("DNA", "RNA", "Protein", "Carbohydrate"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the powerhouse of the cell that produces energy in the form of ATP?",
            options = listOf("Mitochondria", "Endoplasmic reticulum", "Golgi apparatus", "Nucleus"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which organelle is responsible for protein synthesis in a cell?",
            options = listOf("Mitochondria", "Ribosome", "Endoplasmic reticulum", "Lysosome"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the process by which cells divide and create two identical daughter cells?",
            options = listOf("Fertilization", "Mitosis", "Meiosis", "Binary fission"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which gas do animals breathe in and use in cellular respiration?",
            options = listOf("Oxygen (O2)", "Carbon dioxide (CO2)", "Nitrogen (N2)", "Hydrogen (H2)"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the fluid-filled sac that surrounds and protects the nucleus of a cell?",
            options = listOf("Cytoplasm", "Plasma membrane", "Vacuole", "Nuclear membrane"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which process allows organisms to exchange gases with their environment, such as taking in oxygen and releasing carbon dioxide?",
            options = listOf("Photosynthesis", "Respiration", "Fermentation", "Transpiration"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the green pigment in plant cells that is essential for photosynthesis?",
            options = listOf("Chlorophyll", "Melanin", "Hemoglobin", "Carotene"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which part of the plant is responsible for absorbing water and nutrients from the soil?",
            options = listOf("Leaves", "Stem", "Roots", "Flowers"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the genetic material of a virus?",
            options = listOf("DNA", "RNA", "Protein", "Carbohydrate"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the process by which DNA is copied to create an RNA molecule?",
            options = listOf("Replication", "Transcription", "Translation", "Mutation"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which part of a plant is responsible for the transport of water and nutrients between the roots and leaves?",
            options = listOf("Leaves", "Stem", "Roots", "Flowers"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for the process by which an organism gradually changes over time to better adapt to its environment?",
            options = listOf("Mutation", "Adaptation", "Evolution", "Selection"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which blood type is considered the universal recipient?",
            options = listOf("A", "B", "AB", "O"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the smallest unit of an element that retains the properties of that element?",
            options = listOf("Atom", "Molecule", "Cell", "Organism"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which type of biomolecule includes enzymes, antibodies, and hormones and plays various roles in the body?",
            options = listOf("Carbohydrates", "Lipids", "Proteins", "Nucleic acids"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for a group of organisms of the same species living in the same area?",
            options = listOf("Community", "Ecosystem", "Population", "Habitat"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which process involves the breaking down of complex organic molecules into simpler substances with the release of energy?",
            options = listOf("Photosynthesis", "Respiration", "Fermentation", "Transpiration"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the main function of red blood cells in the human body?",
            options = listOf("Carry oxygen", "Digest food", "Produce antibodies", "Filter waste"),
            answersIndex = "0",
            point = (1..3).random()
        ))
)

val arts = AssignmentHoldersModel(
    Subjects.Arts,
    "Analyze and interpret a current event or development through the lens of fine arts.",
    listOf(Grade.JS1, Grade.JS2, Grade.JS3),
    listOf(
        AssignmentHolder(
            question = "Who painted the 'Mona Lisa'?",
            options = listOf("Pablo Picasso", "Leonardo da Vinci", "Vincent van Gogh", "Michelangelo"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which art movement is known for its use of geometric shapes and primary colors?",
            options = listOf("Surrealism", "Impressionism", "Cubism", "Abstract Expressionism"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Who sculpted the statue 'David'?",
            options = listOf("Auguste Rodin", "Donatello", "Leonardo da Vinci", "Michelangelo"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which artist is famous for his paintings of sunflowers?",
            options = listOf("Salvador Dali", "Claude Monet", "Vincent van Gogh", "Pablo Picasso"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Who is known for his 'Campbell's Soup Cans' artwork?",
            options = listOf("Roy Lichtenstein", "Andy Warhol", "Jackson Pollock", "Mark Rothko"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for a work of art made by attaching various objects to a flat surface?",
            options = listOf("Collage", "Sculpture", "Mosaic", "Etching"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which art movement is characterized by dreamlike, surreal imagery?",
            options = listOf("Cubism", "Impressionism", "Surrealism", "Abstract Expressionism"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Who painted the 'Starry Night'?",
            options = listOf("Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Michelangelo"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which artist is known for his 'Drowning Girl' painting in the style of pop art?",
            options = listOf("Roy Lichtenstein", "Andy Warhol", "Jackson Pollock", "Mark Rothko"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for a technique of creating images by arranging small pieces of colored glass, stone, or other materials?",
            options = listOf("Collage", "Sculpture", "Mosaic", "Etching"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which artist is famous for his 'Persistence of Memory' painting with melting clocks?",
            options = listOf("Salvador Dali", "Claude Monet", "Vincent van Gogh", "Pablo Picasso"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for a style of art that emphasizes abstract or non-representational qualities?",
            options = listOf("Realism", "Abstract Art", "Impressionism", "Cubism"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Who painted the 'Last Supper'?",
            options = listOf("Auguste Rodin", "Donatello", "Leonardo da Vinci", "Michelangelo"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which artist is known for his water lily paintings?",
            options = listOf("Salvador Dali", "Claude Monet", "Vincent van Gogh", "Pablo Picasso"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for a technique of creating images by carving or etching into a surface?",
            options = listOf("Collage", "Sculpture", "Mosaic", "Etching"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which art movement is known for its focus on capturing the fleeting effects of light and color in the natural world?",
            options = listOf("Surrealism", "Impressionism", "Cubism", "Abstract Expressionism"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Who is famous for his 'Campbell's Soup Cans' artwork?",
            options = listOf("Roy Lichtenstein", "Andy Warhol", "Jackson Pollock", "Mark Rothko"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for a work of art made by attaching various objects to a flat surface?",
            options = listOf("Collage", "Sculpture", "Mosaic", "Etching"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which art movement is characterized by dreamlike, surreal imagery?",
            options = listOf("Cubism", "Impressionism", "Surrealism", "Abstract Expressionism"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Who painted the 'Starry Night'?",
            options = listOf("Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Michelangelo"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which artist is known for his 'Drowning Girl' painting in the style of pop art?",
            options = listOf("Roy Lichtenstein", "Andy Warhol", "Jackson Pollock", "Mark Rothko"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for a technique of creating images by arranging small pieces of colored glass, stone, or other materials?",
            options = listOf("Collage", "Sculpture", "Mosaic", "Etching"),
            answersIndex = "2",
            point = (1..3).random()
        ))
)

val arts2 = AssignmentHoldersModel(                                                           /////////
    Subjects.Arts,
    "Analyze and interpret a current event or development through the lens of fine arts.",
    listOf(Grade.JS1, Grade.JS2, Grade.JS3),
    listOf(
        AssignmentHolder(
            question = "Who painted the 'Mona Lisa'?",
            options = listOf("Pablo Picasso", "Leonardo da Vinci", "Vincent van Gogh", "Michelangelo"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which art movement is known for its use of geometric shapes and primary colors?",
            options = listOf("Surrealism", "Impressionism", "Cubism", "Abstract Expressionism"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Who sculpted the statue 'David'?",
            options = listOf("Auguste Rodin", "Donatello", "Leonardo da Vinci", "Michelangelo"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which artist is famous for his paintings of sunflowers?",
            options = listOf("Salvador Dali", "Claude Monet", "Vincent van Gogh", "Pablo Picasso"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Who is known for his 'Campbell's Soup Cans' artwork?",
            options = listOf("Roy Lichtenstein", "Andy Warhol", "Jackson Pollock", "Mark Rothko"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for a work of art made by attaching various objects to a flat surface?",
            options = listOf("Collage", "Sculpture", "Mosaic", "Etching"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which art movement is characterized by dreamlike, surreal imagery?",
            options = listOf("Cubism", "Impressionism", "Surrealism", "Abstract Expressionism"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Who painted the 'Starry Night'?",
            options = listOf("Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Michelangelo"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which artist is known for his 'Drowning Girl' painting in the style of pop art?",
            options = listOf("Roy Lichtenstein", "Andy Warhol", "Jackson Pollock", "Mark Rothko"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for a technique of creating images by arranging small pieces of colored glass, stone, or other materials?",
            options = listOf("Collage", "Sculpture", "Mosaic", "Etching"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which artist is famous for his 'Persistence of Memory' painting with melting clocks?",
            options = listOf("Salvador Dali", "Claude Monet", "Vincent van Gogh", "Pablo Picasso"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for a style of art that emphasizes abstract or non-representational qualities?",
            options = listOf("Realism", "Abstract Art", "Impressionism", "Cubism"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Who painted the 'Last Supper'?",
            options = listOf("Auguste Rodin", "Donatello", "Leonardo da Vinci", "Michelangelo"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which artist is known for his water lily paintings?",
            options = listOf("Salvador Dali", "Claude Monet", "Vincent van Gogh", "Pablo Picasso"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for a technique of creating images by carving or etching into a surface?",
            options = listOf("Collage", "Sculpture", "Mosaic", "Etching"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which art movement is known for its focus on capturing the fleeting effects of light and color in the natural world?",
            options = listOf("Surrealism", "Impressionism", "Cubism", "Abstract Expressionism"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Who is famous for his 'Campbell's Soup Cans' artwork?",
            options = listOf("Roy Lichtenstein", "Andy Warhol", "Jackson Pollock", "Mark Rothko"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for a work of art made by attaching various objects to a flat surface?",
            options = listOf("Collage", "Sculpture", "Mosaic", "Etching"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which art movement is characterized by dreamlike, surreal imagery?",
            options = listOf("Cubism", "Impressionism", "Surrealism", "Abstract Expressionism"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Who painted the 'Starry Night'?",
            options = listOf("Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Michelangelo"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which artist is known for his 'Drowning Girl' painting in the style of pop art?",
            options = listOf("Roy Lichtenstein", "Andy Warhol", "Jackson Pollock", "Mark Rothko"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for a technique of creating images by arranging small pieces of colored glass, stone, or other materials?",
            options = listOf("Collage", "Sculpture", "Mosaic", "Etching"),
            answersIndex = "2",
            point = (1..3).random()
        ))
)


val physics = AssignmentHoldersModel(
    Subjects.Physics,
    "Explain the physical principles underlying a current event or development",
    listOf(Grade.SS3, Grade.SS2, Grade.SS1),
    listOf(
        AssignmentHolder(
            question = "What is the SI unit of force?",
            options = listOf("Watt", "Joule", "Newton", "Coulomb"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the law that states that for every action, there is an equal and opposite reaction?",
            options = listOf("Newton's First Law", "Newton's Second Law", "Newton's Third Law", "Hooke's Law"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the acceleration due to gravity on the surface of the Earth?",
            options = listOf("9.8 m/s²", "10 m/s²", "8.2 m/s²", "12 m/s²"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which type of energy is stored in an object due to its position or height?",
            options = listOf("Kinetic energy", "Thermal energy", "Potential energy", "Electrical energy"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the law of conservation of energy?",
            options = listOf("Energy cannot be created or destroyed, only transferred or transformed", "Energy can be created and destroyed at will", "Energy can only be transformed into heat", "Energy is always lost as heat"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the SI unit of electrical resistance?",
            options = listOf("Volt", "Watt", "Ohm", "Ampere"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which scientist is known for his laws of planetary motion?",
            options = listOf("Isaac Newton", "Galileo Galilei", "Johannes Kepler", "Albert Einstein"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for the force that opposes the motion of objects moving through a fluid (e.g., air or water)?",
            options = listOf("Friction", "Gravity", "Tension", "Inertia"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the process by which a substance changes from a gas to a liquid?",
            options = listOf("Condensation", "Evaporation", "Sublimation", "Solidification"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the SI unit of electric charge?",
            options = listOf("Coulomb", "Ampere", "Volt", "Ohm"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the SI unit of frequency?",
            options = listOf("Hertz", "Watt", "Joule", "Newton"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for the resistance of a fluid to flow?",
            options = listOf("Inertia", "Viscosity", "Friction", "Pressure"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the law that states that an object at rest tends to stay at rest, and an object in motion tends to stay in motion with the same speed and in the same direction unless acted upon by an unbalanced external force?",
            options = listOf("Newton's First Law", "Newton's Second Law", "Newton's Third Law", "Hooke's Law"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the SI unit of power?",
            options = listOf("Watt", "Joule", "Newton", "Coulomb"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which law of thermodynamics states that energy cannot be created or destroyed in an isolated system?",
            options = listOf("First Law of Thermodynamics", "Second Law of Thermodynamics", "Third Law of Thermodynamics", "Zeroth Law of Thermodynamics"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for the energy of motion?",
            options = listOf("Kinetic energy", "Thermal energy", "Potential energy", "Electrical energy"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the SI unit of electric current?",
            options = listOf("Volt", "Watt", "Ohm", "Ampere"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which scientist formulated the laws of motion and universal gravitation?",
            options = listOf("Isaac Newton", "Galileo Galilei", "Johannes Kepler", "Albert Einstein"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for the force of attraction between two objects with mass?",
            options = listOf("Friction", "Gravity", "Tension", "Inertia"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the process by which a substance changes from a liquid to a gas?",
            options = listOf("Condensation", "Evaporation", "Sublimation", "Solidification"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the SI unit of electric voltage or potential difference?",
            options = listOf("Coulomb", "Ampere", "Volt", "Ohm"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the SI unit of energy?",
            options = listOf("Hertz", "Watt", "Joule", "Newton"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the term for the force exerted by a fluid on an object submerged in it?",
            options = listOf("Inertia", "Viscosity", "Friction", "Pressure"),
            answersIndex = "1",
            point = (1..3).random()
        ))
)

val chemistry = AssignmentHoldersModel(
    Subjects.Chemistry,
    "Investigate the chemical principles underlying a current event or development, " +
            "and discuss their implications for society.",
    listOf(Grade.SS3, Grade.SS2, Grade.SS1),
    listOf(
        AssignmentHolder(
            question = "What is the chemical symbol for the element gold?",
            options = listOf("Go", "Au", "Gl", "Ag"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which gas is the most abundant element in the Earth's atmosphere?",
            options = listOf("Carbon dioxide (CO2)", "Oxygen (O2)", "Nitrogen (N2)", "Hydrogen (H2)"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the chemical formula for water?",
            options = listOf("H2O2", "CO2", "H2O", "O2"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which gas is responsible for the sharp, pungent smell of rotten eggs?",
            options = listOf("Carbon monoxide (CO)", "Sulfur dioxide (SO2)", "Methane (CH4)", "Hydrogen sulfide (H2S)"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the atomic number of carbon?",
            options = listOf("6", "12", "14", "16"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which element is the main component of the Earth's core?",
            options = listOf("Iron (Fe)", "Oxygen (O)", "Carbon (C)", "Hydrogen (H)"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the chemical formula for methane, the primary component of natural gas?",
            options = listOf("CH4", "CO2", "H2O", "C6H12O6"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the chemical symbol for the element oxygen?",
            options = listOf("O2", "O", "O3", "CO2"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which gas do plants absorb from the atmosphere and release oxygen during photosynthesis?",
            options = listOf("Carbon dioxide (CO2)", "Oxygen (O2)", "Nitrogen (N2)", "Hydrogen (H2)"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the chemical formula for sulfuric acid?",
            options = listOf("H2SO3", "H2SO4", "HCl", "NaOH"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which element is commonly used in batteries, with the chemical symbol Pb?",
            options = listOf("Lead", "Lithium", "Lanthanum", "Beryllium"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the chemical formula for carbon dioxide?",
            options = listOf("CO", "CO2", "CH4", "C6H12O6"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which gas is known as laughing gas and is used as an anesthetic?",
            options = listOf("Carbon dioxide (CO2)", "Oxygen (O2)", "Nitrogen (N2)", "Nitrous oxide (N2O)"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the chemical symbol for sodium?",
            options = listOf("Na", "Ca", "K", "Fe"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which gas is responsible for the sensation of smell?",
            options = listOf("Carbon dioxide (CO2)", "Oxygen (O2)", "Nitrogen (N2)", "Olfactory gases"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the chemical formula for ammonia?",
            options = listOf("NH3", "NO2", "H2SO4", "CO2"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which element is commonly found in the Earth's crust and has the chemical symbol Si?",
            options = listOf("Sulfur (S)", "Sodium (Na)", "Silicon (Si)", "Silver (Ag)"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the chemical formula for hydrogen peroxide?",
            options = listOf("H2O", "H2SO4", "H2O2", "HCl"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which gas is commonly used in balloons to make them float?",
            options = listOf("Carbon dioxide (CO2)", "Oxygen (O2)", "Nitrogen (N2)", "Helium (He)"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the chemical symbol for potassium?",
            options = listOf("Na", "K", "P", "Fe"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = """Which element is known as the "building block of life" and is a key component of organic compounds?""",
            options = listOf("Oxygen (O)", "Carbon (C)", "Nitrogen (N)", "Hydrogen (H)"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the chemical formula for table salt (sodium chloride)?",
            options = listOf("NaOH", "Na2CO3", "H2SO4", "NaCl"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which element is a noble gas and is used in neon signs?",
            options = listOf("Argon (Ar)", "Krypton (Kr)", "Xenon (Xe)", "Radon (Rn)"),
            answersIndex = "1",
            point = (1..3).random()
        ))
)


val physics2 = AssignmentHoldersModel(
    Subjects.Physics,
    "A physics quiz is a short test that assesses a student's knowledge of physics concepts and" +
            " principles, such as mechanics, thermodynamics, electromagnetism, and optics.",
    listOf(Grade.SS3, Grade.SS2, Grade.SS1),
    listOf(
        AssignmentHolder(
            question = "What is the SI unit of force?",
            options = listOf("Joule (J)", "Watt (W)", "Newton (N)", "Volt (V)"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the acceleration due to gravity on Earth's surface?",
            options = listOf("9.81 m/s²", "2.54 cm/s²", "6.63 m/s²", "5.67 × 10^-11 m/s²"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which law of motion states that an object at rest tends to stay at rest, and an object in motion tends to stay in motion with the same speed and in the same direction unless acted upon by an unbalanced force?",
            options = listOf("Newton's First Law of Motion", "Newton's Second Law of Motion", "Newton's Third Law of Motion", "Law of Universal Gravitation"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the SI unit of electric current?",
            options = listOf("Ampere (A)", "Coulomb (C)", "Volt (V)", "Ohm (Ω)"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the speed of light in a vacuum?",
            options = listOf("299,792,458 meters per second (m/s)", "3,000,000 meters per second (m/s)", "186,282 miles per second (mi/s)", "100 kilometers per hour (km/h)"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the law that states that for every action, there is an equal and opposite reaction?",
            options = listOf("Newton's First Law of Motion", "Newton's Second Law of Motion", "Newton's Third Law of Motion", "Law of Universal Gravitation"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the SI unit of energy?",
            options = listOf("Joule (J)", "Watt (W)", "Newton (N)", "Coulomb (C)"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which planet is known as the Red Planet?",
            options = listOf("Venus", "Earth", "Mars", "Jupiter"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the law that states that the total electric charge in a closed system remains constant?",
            options = listOf("Law of Conservation of Momentum", "Law of Conservation of Energy", "Law of Conservation of Electric Charge", "Law of Inertia"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which instrument is used to measure atmospheric pressure?",
            options = listOf("Thermometer", "Barometer", "Hydrometer", "Altimeter"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the unit of electrical resistance?",
            options = listOf("Ampere (A)", "Coulomb (C)", "Volt (V)", "Ohm (Ω)"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which gas is the most abundant in Earth's atmosphere?",
            options = listOf("Carbon dioxide (CO2)", "Oxygen (O2)", "Nitrogen (N2)", "Hydrogen (H2)"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the SI unit of power?",
            options = listOf("Joule (J)", "Watt (W)", "Newton (N)", "Coulomb (C)"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the law that states that the gravitational force between two objects is directly proportional to the product of their masses and inversely proportional to the square of the distance between them?",
            options = listOf("Newton's First Law of Motion", "Newton's Second Law of Motion", "Newton's Third Law of Motion", "Law of Universal Gravitation"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the SI unit of electric charge?",
            options = listOf("Ampere (A)", "Coulomb (C)", "Volt (V)", "Ohm (Ω)"),
            answersIndex = "1",
            point = (1..3).random()
        )
    )
)


val chemistry2 = AssignmentHoldersModel(
    Subjects.Chemistry,
    "A chemistry quiz is a short test that assesses a student's knowledge of chemistry concepts and principles.",
    listOf(Grade.SS3, Grade.SS2, Grade.SS1),
    listOf(
        AssignmentHolder(
            question = "What is the chemical symbol for sodium?",
            options = listOf("So", "Na", "Sn", "Sa"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "How many electrons does a neutral hydrogen atom have?",
            options = listOf("1", "2", "0", "3"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the chemical formula for water?",
            options = listOf("H2O2", "CO2", "H2O", "O2"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which gas is known as laughing gas and is used as an anesthetic?",
            options = listOf("Carbon dioxide (CO2)", "Oxygen (O2)", "Nitrogen (N2)", "Nitrous oxide (N2O)"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the chemical symbol for potassium?",
            options = listOf("Ka", "Ko", "K", "Pt"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which element is commonly used in batteries, with the chemical symbol Pb?",
            options = listOf("Lead (Pb)", "Lithium (Li)", "Lanthanum (La)", "Beryllium (Be)"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the chemical formula for methane, the primary component of natural gas?",
            options = listOf("CH4", "CO2", "H2O", "C6H12O6"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the chemical formula for table salt (sodium chloride)?",
            options = listOf("NaOH", "Na2CO3", "H2SO4", "NaCl"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "What is the atomic number of carbon?",
            options = listOf("6", "12", "14", "16"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Which element is commonly found in the Earth's crust and has the chemical symbol Si?",
            options = listOf("Sulfur (S)", "Sodium (Na)", "Silicon (Si)", "Silver (Ag)"),
            answersIndex = "2",
            point = (1..3).random()
        )
    )
)


val french = AssignmentHoldersModel(
    Subjects.French,
    "Compare and contrast the French and English languages, and discuss the " +
            "challenges and opportunities that this presents for English students learning French.",
    listOf(Grade.JS1, Grade.JS2, Grade.JS3),
    listOf(
        AssignmentHolder(
            question = "Bonjour",
            options = listOf("Hello", "Goodbye", "Please", "Thank you"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Merci",
            options = listOf("Hello", "Goodbye", "Please", "Thank you"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Oui",
            options = listOf("Yes", "No", "Excuse me", "I'm sorry"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Non",
            options = listOf("Yes", "No", "Excuse me", "I'm sorry"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "S'il vous plaît",
            options = listOf("Hello", "Goodbye", "Please", "Thank you"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Excusez-moi",
            options = listOf("Yes", "No", "Excuse me", "I'm sorry"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Comment ça va?",
            options = listOf("Hello", "Goodbye", "How are you?", "What's your name?"),
            answersIndex = "2",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Je m'appelle [Your Name]",
            options = listOf("My name is [Your Name]", "How are you?", "What's your name?", "Goodbye"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Ça va bien, merci",
            options = listOf("I'm fine, thank you", "My name is [Your Name]", "How are you?", "Goodbye"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Enchanté(e)",
            options = listOf("Nice to meet you", "My name is [Your Name]", "How are you?", "Goodbye"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Parlez-vous anglais?",
            options = listOf("Do you speak French?", "Yes", "No", "I don't understand"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Je ne parle pas bien français",
            options = listOf("I don't speak French well", "Yes", "No", "I don't understand"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Je ne comprends pas",
            options = listOf("I don't speak French well", "Yes", "No", "I don't understand"),
            answersIndex = "3",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Où est la toilette?",
            options = listOf("Where is the bathroom?", "Yes", "No", "I don't understand"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Combien ça coûte?",
            options = listOf("How much does it cost?", "Yes", "No", "I don't understand"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "L'addition, s'il vous plaît",
            options = listOf("The check, please", "Yes", "No", "I don't understand"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Où est la gare?",
            options = listOf("Where is the train station?", "Yes", "No", "I don't understand"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Quelle heure est-il?",
            options = listOf("What time is it?", "Yes", "No", "I don't understand"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "J'ai besoin d'aide",
            options = listOf("I need help", "Yes", "No", "I don't understand"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Oui, s'il vous plaît",
            options = listOf("Yes, please", "No", "Excuse me", "I'm sorry"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Non, merci",
            options = listOf("Yes, please", "No, thank you", "Excuse me", "I'm sorry"),
            answersIndex = "1",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Pouvez-vous répéter, s'il vous plaît?",
            options = listOf("Can you repeat, please?", "Yes", "No", "I don't understand"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Comment dit-on [English word] en français?",
            options = listOf("How do you say [English word] in French?", "Yes", "No", "I don't understand"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Où est-ce que je peux trouver un restaurant?",
            options = listOf("Where can I find a restaurant?", "Yes", "No", "I don't understand"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "J'aime ça",
            options = listOf("I like it", "Yes", "No", "I don't understand"),
            answersIndex = "0",
            point = (1..3).random()
        ),
        AssignmentHolder(
            question = "Je n'aime pas ça",
            options = listOf("I don't like it", "Yes", "No", "I don't understand"),
            answersIndex = "0",
            point = (1..3).random()
        ))
)
