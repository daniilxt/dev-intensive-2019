package ru.skillbranch.devintensive.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_profile.*
import ru.skillbranch.devintensive.R


class ProfileActivity : AppCompatActivity() {
    //новая жизнь 2020 eee
    companion object {
        const val IS_EDIT_MODE = "IS_EDIT_MODE"
    }

    var isEditMode = false
    lateinit var viewFields: Map<String, TextView>

    /**
     * Вызывается при первом создании или перезапуске Activity.
     *
     * здесь задается внешний вид активности (UI) через метод setContentView().
     * инициализируются представления
     * представления связываются с необходимыми данными и реурсами
     * связываются данные со списками
     *
     * Этот метод также предоставляет Bundle, содержащий ранее
     * сохраненное состояние Activity, если оно было.
     *
     * Всегда сопровождается вызовом onStart().
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initViews(savedInstanceState)
    }
/*

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
    }
*/

    private fun initViews(savedInstanceState: Bundle?) {
        viewFields = mapOf(
            "nickname" to tv_nickname,
            "rank" to tv_rank,
            "firstName" to et_first_name,
            "lastName" to et_second_name,
            "about" to et_about,
            "repository" to et_repo,
            "rating" to tv_rating,
            "respect" to tv_respect
        )
        btn_edit.setOnClickListener {
            isEditMode = !isEditMode
            showCurrentMode(isEditMode)
        }
    }

    private fun showCurrentMode(isEdit: Boolean) {
        val info = viewFields.filter {
            setOf(
                "firstName",
                "lastName",
                "about",
                "repository"
            ).contains(it.key)
        }
        for ((_,v) in info) {
            v as EditText
            v.isFocusable = isEditMode
            v.isFocusableInTouchMode = isEdit
            v.isEnabled  = isEdit
            v.background.alpha = if (isEdit) 255 else 0
        }
    }

    /**
     * Если Activity возвращается в приоритетный режим после вызова onStop(),
     * то в этом случае вызывается метод onRestart().
     * Т.е. вызывается после того, как Activity была остановлена и снова была запущена пользователем.
     * Всегда сопровождается ввызовом метода onStart()/
     *
     * используется для специальных действий, которые должны выполняться только при повторном запуске Activity.
     */
    override fun onRestart() {
        super.onRestart()
        Log.d("M_MainActivity", "onRestart")
    }

    /**
     * При вызове onStart(0 окно еще не видно пользователю, но вскоре будет видно.
     * Вызывается непосредственно перед тем, как активность становится видимой пользователю.
     *
     * чтение из базы данных
     * запуск сложной анимации
     * запуск потоков, отслеживание показаний датчиков, запросов к GPS, таймеров, сервисов или других процессов,
     * которые нужны исключительно для обновления пользовательского интерфейса
     *
     * Затем следует onResume(), если Activity выходит на передний план.
     */
    override fun onStart() {
        super.onStart()
        Log.d("M_MainActivity", "onStart")
    }

    /**
     * Вызывается, когда Activity начнет взаимодействовать с пользователем.
     *
     * запуск воспроизведения анимации, аудио и видео
     * регистрация любых BroadcastReceiver или других процессов, которые мы освободили/приостановили в onPause()
     * выполнение любых других инициализзаций, которые должны происходить, когда Activity вновь активна (камера).
     *
     * Тут должен быть максимально легкий и быстрый код, чтобы приложение оставалось отзывчивым
     */
    override fun onResume() {
        super.onResume()
        Log.d("M_MainActivity", "onResume")
    }

    /**
     * Метод onPause() вызывается после сворачивания текущей активности или перехода к новой.
     * От onPause() можно перейти к вызову либо onResume(), либо onStop().
     *
     * остановка анимации, аудио и видео
     * сохранение состояния пользовательского ввода (легкие процессы)
     * сохранение в DB, если данные должны быть доступны в новой Activity
     * остановка сервисов, подписок, BroadcastReceiver
     *
     * Тут должен быть максимально легкий и быстрый код, чтобы приложение оставалось отзывчивым
     */
    override fun onPause() {
        super.onPause()
        Log.d("M_MainActivity", "onPause")
    }

    /**
     * Метод onStop() вызывается, когда Activity становится невидимым для пользователя.
     * Это может произойти при ее уничтожении, или если была запущена другая Activity (существующая или новая),
     * перекрывшая окно текущей Activity.
     *
     * запись в базу данных
     * приостановка сложной анимации
     * приостановка потоков, отслеживания показаний датчиков, запросов к GPS, таймеров, сервисов или других процессов,
     * которые нужны исключительно для обновления пользовательского интерфейса
     *
     * Не вызывается при вызове метода finish() у Activity
     */
    override fun onStop() {
        super.onStop()
        Log.d("M_MainActivity", "onStop")
    }

    /**
     * Метод вызывается по окончанию работы Activity, при вызове метода finish() или в случае,
     * когда система уничтожает этот экземпляр активности для освобождения ресурсов.
     */
    override fun onDestroy() {
        super.onDestroy()
        Log.d("M_MainActivity", "onDestroy")
    }

    /**
     * Этот метод сохраняет состояние представления в Bundle
     * Для API Level < 28 (Android P) этот метод будет выполняться до onStop(), и нет никаких гарантий относительно того,
     * произойдет ли это до или после onPause().
     * Для API Level >= 28 метод будет вызван после onStop().
     * Не будет вызван, если Activity будет явно закрыто пользователем при нажатии на системную клавишу back.
     */
}
