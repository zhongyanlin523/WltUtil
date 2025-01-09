package com.kenning.kcutil

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.kenning.base.BaseActivity
import com.kenning.kcutil.databinding.ActivityMainBinding
import com.kenning.kcutil.utils.date.DateExtendUtil
import com.kenning.kcutil.utils.date.Date_Format
import com.kenning.kcutil.utils.date.formatBy
import com.kenning.kcutil.utils.datepicker.IPickerListener
import com.kenning.kcutil.utils.language.LanguageUtil
import java.util.Date


class MainActivity : BaseActivity(), IPickerListener {

    //    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val da = DateExtendUtil.getBalanceDateByDay(
            Date().formatBy(Date_Format.YMD),
            Date_Format.YMD,
            5
        )
        Log.e("kenning", da)
        loadRootFragment(binding.fcvMain.id, FirstFragment())

        binding.fab.setOnClickListener { view ->
        }
        binding.tagswitch.setOnClickListener {
            val language = arrayOf("中文 Chinese", "英文 English",

                LanguageUtil.getLocalizedTextFromChinese(this,"切换"),
                LanguageUtil.getLngString(this, R.string.切换))
            AlertDialog.Builder(this).setSingleChoiceItems(language, -1) { dialog, which ->
                when (which) {
                    0 -> {
                        changeLanguage("ch")
                    }

                    1 -> {
                        changeLanguage("en-rUS")
                    }
                }
            }.show()
        }

        //隐私政策
        binding.llPrivacyPolicy.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("url", LanguageUtil.getLngString(this, R.string.wlttyprivacyhtml))
            bundle.putString("name", R.string.隐私政策)
            startActivity(Intent(this, PrivacyPolicyActivity::class.java).putExtras(bundle))
        }
    }

    /**
     * 如果是7.0以下，我们需要调用changeAppLanguage方法，
     * 如果是7.0及以上系统，直接把我们想要切换的语言类型保存在SharedPreferences中即可
     * 然后重新启动MainActivity
     * @param language
     */
    private fun changeLanguage(language: String) {
        LanguageUtil.changeAppLanguage(this, language)
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    override fun closeAct() {
        TODO("Not yet implemented")
    }

    override fun getBeforeData() {
        TODO("Not yet implemented")
    }

    override fun defaultData() {
        TODO("Not yet implemented")
    }

    override fun dealData() {
        TODO("Not yet implemented")
    }

    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun bindClickEvent() {
        TODO("Not yet implemented")
    }

    override fun reLoadApp() {
        TODO("Not yet implemented")
    }

    override fun onDismissPicker() {

    }

    override fun onDateChange(requestcode: Int, start: String, end: String): Boolean {
        binding.tag11.text = start
        return true
    }

}