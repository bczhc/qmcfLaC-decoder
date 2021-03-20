package pers.zhc.tools.diary

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import pers.zhc.tools.BaseActivity
import pers.zhc.tools.R
import pers.zhc.tools.utils.sqlite.SQLite3

class DiaryAttachmentActivity : BaseActivity() {
    private lateinit var diaryDatabase: SQLite3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.diary_attachment_activity)

        diaryDatabase = DiaryMainActivity.getDiaryDatabase(this)

        checkAttachmentInfoRecord()
    }

    private fun checkAttachmentInfoRecord() {
        val fileStoragePath = DiaryAttachmentSettingsActivity.getFileStoragePath(diaryDatabase)
        if (fileStoragePath == null) {
            // record "info_json" doesn't exists, then start to set it
            startActivity(Intent(this, DiaryAttachmentSettingsActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.diary_attachment_actionbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add -> {
                startActivity(Intent(this, DiaryAttachmentAddingActivity::class.java))
            }
            R.id.file_library -> {
                startActivity(Intent(this, FileLibraryActivity::class.java))
            }
            R.id.setting_btn -> {
                startActivity(Intent(this, DiaryAttachmentSettingsActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}