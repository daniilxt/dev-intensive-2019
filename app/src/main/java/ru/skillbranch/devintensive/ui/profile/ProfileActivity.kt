package ru.skillbranch.devintensive.ui.profile

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.models.Bender


class ProfileActivity : AppCompatActivity(){
    //новая жизнь 2020 eee

    private lateinit var benderImage: ImageView
    private lateinit var textTxt: TextView
    private lateinit var messageEt: EditText
    private lateinit var sendBtn: ImageView
    private lateinit var benderObj: Bender
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

//        val editSearch: EditText = findViewById(R.id.et_message)
//        editSearch.setOnEditorActionListener(this)
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
/*    override fun onSaveInstanceState(outState: Bundle?) {
        if (outState != null) {
            super.onSaveInstanceState(outState)
            outState.putString("STATUS", benderObj.status.name)
            outState.putString("QUESTION", benderObj.question.name)
        }
        Log.d("M_MainActivity", "onSaveInstanceState ${benderObj.status.name} ${benderObj.question.name}")
    }*/
}
