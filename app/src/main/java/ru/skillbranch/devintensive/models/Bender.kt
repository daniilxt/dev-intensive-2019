package ru.skillbranch.devintensive.models

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {

  fun askQuestion(): String = when (question) {
    Question.NAME -> Question.NAME.question
    Question.PROFESSION -> Question.PROFESSION.question
    Question.MATERIAL -> Question.MATERIAL.question
    Question.BDAY -> Question.BDAY.question
    Question.SERIAL -> Question.SERIAL.question
    Question.IDLE -> Question.IDLE.question
  }

  fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {
    return if (question.answer.contains(answer)) {
      question = question.nextQuestion()
      if (question == Question.IDLE) {
        "Отлично - ты справился\nНа этом все, вопросов больше нет" to status.color

      } else {
        "Отлично Это правильный ответ!. \n${question.question}" to status.color
      }
    } else {
      status = status.nextStatus()
      if (status == Status.CRITICAL) {
        status = Status.NORMAL
        "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
      } else {
        "Это не правильный ответ! \n${question.question}" to status.color
      }

    }
  }

  enum class Status(val color: Triple<Int, Int, Int>) {
    NORMAL(Triple(255, 255, 255)),
    WARNING(Triple(255, 120, 0)),
    DANGER(Triple(255, 60, 60)),
    CRITICAL(Triple(255, 255, 0));

    fun nextStatus(): Status {
      return if (this.ordinal < values().lastIndex) {
        values()[this.ordinal + 1]
      } else {
        values()[0]
      }
    }
  }

  enum class Question(val question: String, val answer: List<String>) {
    NAME("Как меня зовут?", listOf("bender", "бендер")) {
      override fun nextQuestion(): Question = PROFESSION
    },
    PROFESSION("Как у меня профессия?", listOf("bender", "сгибальщик")) {
      override fun nextQuestion(): Question = MATERIAL
    },
    MATERIAL("Из чего я сделан?", listOf("дерево", "металл", " metal", "iron", "wood")) {
      override fun nextQuestion(): Question = BDAY
    },
    BDAY("Когда меня создали?", listOf("2993")) {
      override fun nextQuestion(): Question = SERIAL
    },
    SERIAL("Какой мой серийный номер?", listOf("2716057")) {
      override fun nextQuestion(): Question = IDLE
    },
    IDLE("На этом всё, вопросов больше нет?", listOf()) {
      override fun nextQuestion(): Question = NAME
    };

    abstract fun nextQuestion(): Question

  }
}