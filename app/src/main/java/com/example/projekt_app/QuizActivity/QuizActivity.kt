package com.example.projekt_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class QuizActivity : AppCompatActivity() {

    private lateinit var questions: List<Question>
    private var currentQuestionIndex = 0
    private var correctAnswers = 0
    private var totalQuestions = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        totalQuestions = intent.getIntExtra("QUESTION_COUNT", 5)
        questions = generateQuestions(totalQuestions)

        displayQuestion()

        findViewById<Button>(R.id.button_answer_1).setOnClickListener { checkAnswer(0) }
        findViewById<Button>(R.id.button_answer_2).setOnClickListener { checkAnswer(1) }
        findViewById<Button>(R.id.button_answer_3).setOnClickListener { checkAnswer(2) }
        findViewById<Button>(R.id.button_answer_4).setOnClickListener { checkAnswer(3) }
    }

    private fun displayQuestion() {
        if (currentQuestionIndex < questions.size) {
            val question = questions[currentQuestionIndex]
            findViewById<TextView>(R.id.text_question).text = question.text
            setButtonDefault(findViewById(R.id.button_answer_1), question.answers[0])
            setButtonDefault(findViewById(R.id.button_answer_2), question.answers[1])
            setButtonDefault(findViewById(R.id.button_answer_3), question.answers[2])
            setButtonDefault(findViewById(R.id.button_answer_4), question.answers[3])
        } else {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("CORRECT_ANSWERS", correctAnswers)
            intent.putExtra("TOTAL_QUESTIONS", totalQuestions)
            startActivity(intent)
            finish()
        }
    }
    private fun setButtonDefault(button: Button, text: String) {
        button.text = text
        button.setBackgroundColor(ContextCompat.getColor(this, R.color.button_default))
        button.isEnabled = true
    }

    private fun checkAnswer(answerIndex: Int) {
        val buttons = listOf(
            findViewById<Button>(R.id.button_answer_1),
            findViewById<Button>(R.id.button_answer_2),
            findViewById<Button>(R.id.button_answer_3),
            findViewById<Button>(R.id.button_answer_4)
        )
        val correctAnswerIndex = questions[currentQuestionIndex].correctAnswerIndex

        if (correctAnswerIndex == answerIndex) {
            correctAnswers++
            buttons[answerIndex].setBackgroundColor(ContextCompat.getColor(this, R.color.GREEN))
        } else {
            buttons[answerIndex].setBackgroundColor(ContextCompat.getColor(this, R.color.RED))
        }

        buttons.forEachIndexed { index, button ->
            button.isEnabled = false
            if (index == correctAnswerIndex) {
                button.setBackgroundColor(ContextCompat.getColor(this, R.color.GREEN))
            }
        }

        currentQuestionIndex++
        buttons[0].postDelayed({ displayQuestion() }, 1000)  // Używamy tylko jednego przycisku do opóźnienia
    }

    private fun generateQuestions(count: Int): List<Question> {
        val sampleQuestions = listOf(
            Question("Najlepsza Uczelnia w Rzeszowie", listOf("WSIZ", "URZ", "PRZ", "WSPIA"), 0),
            Question("Ile to 1+1", listOf("1", "2", "3", "4"), 1),
            Question("Stolica Polski to?", listOf("Kraków", "Warszawa", "Gdańsk", "Poznań"), 1),
            Question("Najwyższy szczyt w Polsce to?", listOf("Giewont", "Rysy", "Babia Góra", "Kasprowy Wierch"), 1),
            Question("Który polski noblista napisał 'Quo Vadis'?", listOf("Henryk Sienkiewicz", "Władysław Reymont", "Czesław Miłosz", "Wisława Szymborska"), 0),
            Question("W którym roku Polska odzyskała niepodległość?", listOf("1914", "1918", "1920", "1939"), 1),
            Question("Najdłuższa rzeka w Polsce to?", listOf("Odra", "Wisła", "Warta", "Bug"), 1),
            Question("Które miasto nazywane jest 'Polskim Hollywood'?", listOf("Łódź", "Warszawa", "Kraków", "Wrocław"), 0),
            Question("Największe jezioro w Polsce to?", listOf("Jezioro Śniardwy", "Jezioro Mamry", "Jezioro Hańcza", "Jezioro Wigry"), 0),
            Question("Które miasto jest znane z koziołków na ratuszu?", listOf("Gdańsk", "Wrocław", "Poznań", "Kraków"), 2),
            Question("Pierwszy król Polski to?", listOf("Mieszko I", "Bolesław Chrobry", "Kazimierz Wielki", "Władysław Jagiełło"), 1),
            Question("Które miasto było stolicą Polski przed Warszawą?", listOf("Kraków", "Poznań", "Gniezno", "Wrocław"), 0),
            Question("Jakie miasto jest nazywane 'Polską Wenecją'?", listOf("Bydgoszcz", "Wrocław", "Gdańsk", "Szczecin"), 0),
            Question("Który polski kompozytor jest znany z utworów fortepianowych?", listOf("Fryderyk Chopin", "Ignacy Jan Paderewski", "Karol Szymanowski", "Witold Lutosławski"), 0),
            Question("Które miasto jest nazywane 'Miastem Królów'?", listOf("Warszawa", "Kraków", "Gniezno", "Wrocław"), 1),
            Question("Najdłuższy most w Polsce to?", listOf("Most Solidarności", "Most Północny", "Most Siekierkowski", "Most Gdański"), 0),
            Question("Polska waluta to?", listOf("Euro", "Złoty", "Forint", "Kuna"), 1),
            Question("Który polski święty jest patronem Europy?", listOf("Św. Wojciech", "Św. Jan Paweł II", "Św. Stanisław", "Św. Faustyna"), 1),
            Question("Który polski pisarz otrzymał Nagrodę Nobla w 2018 roku?", listOf("Olga Tokarczuk", "Wisława Szymborska", "Henryk Sienkiewicz", "Czesław Miłosz"), 0),
            Question("Najstarsze miasto w Polsce to?", listOf("Gniezno", "Kalisz", "Kraków", "Poznań"), 1),
            Question("Które województwo ma największą powierzchnię?", listOf("Mazowieckie", "Wielkopolskie", "Lubelskie", "Dolnośląskie"), 0),
            Question("Gdzie znajduje się słynny pomnik Chrystusa Króla?", listOf("Warszawa", "Poznań", "Świebodzin", "Częstochowa"), 2),
            Question("Największa wyspa w Polsce to?", listOf("Wolin", "Uznam", "Hel", "Sobieszewska"), 0),
            Question("Które miasto jest znane z Festiwalu Filmowego w Polsce?", listOf("Warszawa", "Gdynia", "Kraków", "Łódź"), 1),
            Question("W którym roku Polska weszła do Unii Europejskiej?", listOf("2000", "2002", "2004", "2006"), 2),
            Question("Największy zamek w Polsce to?", listOf("Zamek Królewski na Wawelu", "Zamek Królewski w Warszawie", "Zamek Książ", "Zamek w Malborku"), 3),
            Question("Które polskie miasto jest znane z pierników?", listOf("Toruń", "Gdańsk", "Wrocław", "Lublin"), 0),
            Question("Który polski reżyser zdobył Oscara za film 'Pianista'?", listOf("Andrzej Wajda", "Krzysztof Kieślowski", "Roman Polański", "Agnieszka Holland"), 2),
            Question("Największy las w Polsce to?", listOf("Bory Tucholskie", "Puszcza Białowieska", "Puszcza Kampinoska", "Bieszczady"), 1)
        )
        return sampleQuestions.shuffled().take(count)
    }
}
data class Question(val text: String, val answers: List<String>, val correctAnswerIndex: Int)
