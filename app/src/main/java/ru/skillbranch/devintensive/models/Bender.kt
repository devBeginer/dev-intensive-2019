package ru.skillbranch.devintensive.models

class Bender (var status:Status = Status.NORMAL, var question: Question = Question.NAME){

    fun askQuestion() : String = when(question){
        Question.NAME ->Question.NAME.question
        Question.PROFESSION ->Question.PROFESSION.question
        Question.MATERIAL ->Question.MATERIAL.question
        Question.BDAY ->Question.BDAY.question
        Question.SERIAL ->Question.SERIAL.question
        Question.IDLE ->Question.IDLE.question
    }

    fun showWarning() : String = when(question){
        Question.NAME -> "Имя должно начинаться с заглавной буквы\n${question.question}"
        Question.PROFESSION -> "Профессия должна начинаться со строчной буквы\n${question.question}"
        Question.MATERIAL -> "Материал не должен содержать цифр\n${question.question}"
        Question.BDAY -> "Год моего рождения должен содержать только цифры\n${question.question}"
        Question.SERIAL -> "Серийный номер содержит только цифры, и их 7\n${question.question}"
        Question.IDLE -> ""
    }

    fun listenAnswer(answer: String) : Pair<String, Triple<Int, Int, Int>>{
        /*return if(question.answer.contains(answer.toLowerCase())){
            if (question.isValidAnswer(answer)){
                question = question.nextQuestion()
                "Отлично - ты справился\n${question.question}" to status.color
            }else{
                showWarning() to status.color
            }
        }else if (question == Question.IDLE){
            "${question.question}" to status.color
        }else if (status != Status.CRITICAL){
            status = status.nextStatus()
            "Это не правильный ответ\n${question.question}" to status.color
        }else {
            status = Status.NORMAL
            question = Question.NAME
            "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
        }*/
        return if (question.isValidAnswer(answer)){
            if(question.answer.contains(answer.toLowerCase())){
                question = question.nextQuestion()
                "Отлично - ты справился\n${question.question}" to status.color
            }else if (question == Question.IDLE){
                "${question.question}" to status.color
            }else if (status != Status.CRITICAL){
                status = status.nextStatus()
                "Это не правильный ответ\n${question.question}" to status.color
            }else {
                status = Status.NORMAL
                question = Question.NAME
                "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
            }
        }else{
            showWarning() to status.color
        }
    }
    enum class Status(val color: Triple<Int, Int, Int>){
        NORMAL(Triple(255, 255, 255)) ,
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus():Status{
            return if (this.ordinal < values().lastIndex){
                values()[this.ordinal + 1]
            }else{
                values()[0]
            }
        }
    }


    enum class Question(val question: String, val answer: List<String>){
        NAME("Как меня зовут?", listOf("Бендер", "bender")) {
            override fun nextQuestion(): Question = PROFESSION
            override fun isValidAnswer(answer: String): Boolean{
                val pattern = Regex("^[A-Z].*")
                return pattern.matches(answer)
            }
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")){
            override fun nextQuestion(): Question = MATERIAL
            override fun isValidAnswer(answer: String): Boolean{
                val pattern = Regex("^[a-z].*")
                return pattern.matches(answer)
            }
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")){
            override fun nextQuestion(): Question = BDAY
            override fun isValidAnswer(answer: String): Boolean{
                val pattern = Regex("[^0-9]*")
                return pattern.matches(answer)
            }
        },
        BDAY("Когда меня создали?", listOf("2993")){
            override fun nextQuestion(): Question = SERIAL
            override fun isValidAnswer(answer: String): Boolean{
                val pattern = Regex("[0-9]*")
                return pattern.matches(answer)
            }
        },
        SERIAL("Мой серийный номер?", listOf("2716057")){
            override fun nextQuestion(): Question = IDLE
            override fun isValidAnswer(answer: String): Boolean{
                val pattern = Regex("[0-9]{7}")
                return pattern.matches(answer)
            }
        },
        IDLE("На этом все, вопросов больше нет", listOf()){
            override fun nextQuestion(): Question = IDLE
            override fun isValidAnswer(answer: String): Boolean {
                return true
            }
        };

        abstract fun nextQuestion() : Question

        abstract fun isValidAnswer(answer: String) : Boolean
    }
}