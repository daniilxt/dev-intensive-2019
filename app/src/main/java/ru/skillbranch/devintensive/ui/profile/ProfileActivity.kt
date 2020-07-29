package ru.skillbranch.devintensive.ui.profile

import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_profile.*
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.models.Profile
import ru.skillbranch.devintensive.viewmodels.ProfileViewModel


class ProfileActivity : AppCompatActivity() {
    //новая жизнь 2020 eee
    companion object {
        const val IS_EDIT_MODE = "IS_EDIT_MODE"
    }

    private lateinit var viewModel: ProfileViewModel
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
        initViewModel()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(IS_EDIT_MODE, isEditMode)
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        viewModel.getProfileData().observe(this, Observer { updateUI(it) })
        viewModel.getTheme().observe(this, Observer { updateTheme(it) })
    }

    private fun updateTheme(mode: Int) {
        delegate.localNightMode = mode
    }

    private fun updateUI(profile: Profile) {
        profile.toMap().also {
            for ((k, v) in viewFields) {
                v.text = it[k].toString()
            }
        }
    }

    private fun initViews(savedInstanceState: Bundle?) {
        viewFields = mapOf(
            "nickName" to tv_nick_name,
            "rank" to tv_rank,
            "firstName" to et_first_name,
            "lastName" to et_last_name,
            "about" to et_about,
            "repository" to et_repository,
            "rating" to tv_rating,
            "respect" to tv_respect
        )
        isEditMode = savedInstanceState?.getBoolean(IS_EDIT_MODE, false) ?: false
        showCurrentMode(isEditMode)

        btn_edit.setOnClickListener {
            if (isEditMode) saveProfileInfo()
            isEditMode = !isEditMode
            showCurrentMode(isEditMode)
        }
        btn_switch_theme.setOnClickListener {
            viewModel.switchTheme()
        }
    }

    private fun saveProfileInfo() {
        Profile(
            firstName = et_first_name.text.toString(),
            lastName = et_last_name.text.toString(),
            about = et_about.text.toString(),
            repository = et_repository.text.toString()
        ).apply {
            viewModel.saveProfileData(this)
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
        for ((_, v) in info) {
            v as EditText
            v.isFocusable = isEditMode
            v.isFocusableInTouchMode = isEdit
            v.isEnabled = isEdit
            v.background.alpha = if (isEdit) 255 else 0
        }
        ic_eye.visibility = if (isEdit) View.GONE else View.VISIBLE
        wr_about.isCounterEnabled = isEdit

        // можно поменять цвет наложением
        with(btn_edit) {
            val filter: ColorFilter? = if (isEdit) {
                PorterDuffColorFilter(
                    resources.getColor(R.color.color_accent, theme),
                    PorterDuff.Mode.SRC_IN
                )
            } else {
                null
            }

            val icon = if (isEdit) {
                resources.getDrawable(R.drawable.ic_baseline_save_24, theme)
            } else {
                resources.getDrawable(R.drawable.ic_baseline_edit_24, theme)
            }
            background.colorFilter = filter
            setImageDrawable(icon)
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
