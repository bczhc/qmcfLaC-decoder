package pers.zhc.tools.document;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.*;
import pers.zhc.tools.BaseActivity;
import pers.zhc.tools.R;
import pers.zhc.tools.filepicker.Picker;
import pers.zhc.tools.utils.Common;
import pers.zhc.tools.utils.DisplayUtil;
import pers.zhc.u.FileU;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Document extends BaseActivity {
    private ScrollView sv;
    private SQLiteDatabase db;
    private File dbFile = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.document_activity);
        Button insertBtn = findViewById(R.id.note_take);
        Button importBtn = findViewById(R.id.import_btn);
        Button exportBtn = findViewById(R.id.export_btn);
        insertBtn.setOnClickListener(v -> startActivityForResult(new Intent(this, NoteTakingActivity.class), 41));
        Button deleteBtn = findViewById(R.id.delete_btn);
        deleteBtn.setOnClickListener(v -> {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            EditText et = new EditText(this);
            adb.setTitle("请输入要删除的id（*表示全部）")
                    .setPositiveButton(R.string.ok, (dialog, which) -> {
                        String s = et.getText().toString();
                        if (s.matches(".*\\*.*")) {
                            try {
                                db.execSQL("DELETE FROM doc;");
                            } catch (Exception e) {
                                Common.showException(e, this);
                            }
                        } else {
                            try {
                                int i = Integer.parseInt(s);
                                db.execSQL("DELETE FROM doc WHERE id=" + i);
                            } catch (Exception e) {
                                Common.showException(e, this);
                            }
                        }
                        setSVViews();
                    })
                    .setNegativeButton(R.string.cancel, (dialog, which) -> {
                    })
                    .setView(et)
                    .show();
        });
        sv = findViewById(R.id.sv);
        importBtn.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(this, Picker.class);
            intent.putExtra("option", Picker.PICK_FILE);
            startActivityForResult(intent, 51);
        });
        exportBtn.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(this, Picker.class);
            intent.putExtra("option", Picker.PICK_FOLDER);
            startActivityForResult(intent, 61);
        });
        db = getDB(this);
        setSVViews();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 41:
                setSVViews();
                break;
            case 51:
                if (data != null) {
                    File file = new File(data.getStringExtra("result"));
                    try {
                        FileU.FileCopy(file, dbFile, true);
                    } catch (IOException e) {
                        e.printStackTrace();
                        Common.showException(e, this);
                        return;
                    }
                    if (file.exists()) Toast.makeText(this, R.string.importing_cuccess, Toast.LENGTH_SHORT).show();
                    else Toast.makeText(this, R.string.copying_failed, Toast.LENGTH_SHORT).show();
                    setSVViews();
                }
                break;
            case 61:
                if (data != null) {
                    String destFileDir = data.getStringExtra("result");
                    String dbPath = db.getPath();
                    File file = new File(dbPath);
                    String dbName = file.getName();
                    try {
                        File destFile = new File(destFileDir + File.separator + dbName);
                        FileU.FileCopy(file, destFile);
                        if (destFile.exists())
                            Toast.makeText(this, getString(R.string.exporting_success) + "\n" + destFile.getCanonicalPath(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        Common.showException(e, this);
                    }
                }
                break;
        }
    }

    private void setSVViews() {
        db = getDB(this);
        sv.removeAllViews();
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        sv.addView(linearLayout);
        Cursor cursor = db.rawQuery("SELECT * FROM doc", null);
        LinearLayout.LayoutParams ll_lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams smallLL_LP4 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 4F);
//        LinearLayout.LayoutParams smallLL_LP1 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1F);
        int margin = DisplayUtil.px2sp(this, 10);
        ExecutorService es = Executors.newCachedThreadPool();
        if (cursor.moveToFirst()) {
            es.execute(() -> {
                do {
                    LinearLayout ll = new LinearLayout(this);
                    ll_lp.setMargins(margin, margin, margin, margin);
                    ll.setLayoutParams(ll_lp);
                    for (int i = 0; i < 3; i++) {
                        LinearLayout smallLL = new LinearLayout(this);
                        String s = cursor.getString(i);
                        smallLL.setLayoutParams(smallLL_LP4);
                        TextView tv = new TextView(this);
                        tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        tv.setText(String.format(getString(R.string.tv), s));
                        runOnUiThread(() -> {
                            smallLL.addView(tv);
                            tv.setTextSize(15F);
                            ll.addView(smallLL);
                        });
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ll.setBackground(getDrawable(R.drawable.view_stroke));
                    }
                    runOnUiThread(() -> linearLayout.addView(ll));
                } while (cursor.moveToNext());
                cursor.close();
            });
        }
    }

    SQLiteDatabase getDB(Activity ctx) {
        /*DocDB db = new DocDB(ctx, "a", null, 1);
        return db.getWritableDatabase();*/
        SQLiteDatabase database = null;
        File dbPath = new File(getFilesDir().toString() + File.separator + "db");
        if (!dbPath.exists()) {
            System.out.println("dbPath.mkdirs() = " + dbPath.mkdirs());
        }
        try {
            dbFile = new File(dbPath.getPath() + File.separator + "doc.db");
            database = SQLiteDatabase.openOrCreateDatabase(dbFile, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS doc(\n" +
                    "    date varchar(50) not null,\n" +
                    "    title varchar(1048576) not null,\n" +
                    "    content varchar(10485760) not null\n" +
                    ");");
        } catch (Exception e) {
            e.printStackTrace();
            Common.showException(e, ctx);
        }
        return database;
    }
}