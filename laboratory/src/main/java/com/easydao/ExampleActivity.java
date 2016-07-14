package com.easydao;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;
import de.greenrobot.dao.query.WhereCondition;
import me.itangqi.greendao.DaoMaster;
import me.itangqi.greendao.DaoSession;
import me.itangqi.greendao.Note;
import me.itangqi.greendao.NoteDao;

public class ExampleActivity extends Activity
{
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private Cursor cursor;
    public static final String TAG = "DaoExample";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_example);

        // 官方推荐将获取 DaoMaster 对象的方法放到 Application 层，这样将避免多次创建生成 Session 对象
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        db = helper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        // 获取 NoteDao 对象
        //daoSession.getNoteDao();

        addNote();
        search_update_delete();

        String textColumn = NoteDao.Properties.Text.columnName;
        String orderBy = textColumn + " COLLATE LOCALIZED ASC";
        cursor = db.query(daoSession.getNoteDao().getTablename(), daoSession.getNoteDao().getAllColumns(), null, null, null, null, orderBy);
    }

    private void search_update_delete() {
        // Query 类代表了一个可以被重复执行的查询
        WhereCondition text=NoteDao.Properties.Text.eq("we are beginning");
        WhereCondition text1=NoteDao.Properties.Text.eq("we are beginning");
        WhereCondition text2=NoteDao.Properties.Text.eq("we are beginning");
        QueryBuilder queryBuilder = daoSession.getNoteDao().queryBuilder();
        Query query = queryBuilder
                .where(queryBuilder.or(text1, text2))
                .orderAsc(NoteDao.Properties.Date)
                .build();

        //查询结果以 List 返回
        List notes = query.list();

        // 在 QueryBuilder 类中内置两个 Flag 用于方便输出执行的 SQL 语句与传递参数的值
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;

        Note note=(Note)notes.get(0);
        note.setText("sweetie baby");
        daoSession.getNoteDao().update(note);

        /*for(int index=0;index<notes.size();index++)
            daoSession.getNoteDao().delete((Note)notes.get(index));*/
    }


    private void addNote()
    {
        String noteText = "we are beginning";

        final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
        String comment = "Added on " + df.format(new Date());

        // 插入操作，简单到只要你创建一个 Java 对象
        Note note = new Note(null, noteText, comment, new Date());
        daoSession.getNoteDao().insert(note);
        Log.d(TAG, "Inserted new note, ID: " + note.getId());
        //cursor.requery();
    }
}
