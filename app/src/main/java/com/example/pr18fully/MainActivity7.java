package com.example.pr18fully;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.SimpleCursorTreeAdapter;
import android.os.Bundle;

public class MainActivity7 extends AppCompatActivity {

    ExpandableListView elvMain;
    DB3 db3;

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        // подключаемся к БД
        db3 = new DB3(this);
        db3.open();

        // готовим данные по группам для адаптера
        Cursor cursor = db3.getCompanyData();
        startManagingCursor(cursor);
        // сопоставление данных и View для групп
        String[] groupFrom = { DB3.COMPANY_COLUMN_NAME };
        int[] groupTo = { android.R.id.text1 };
        // сопоставление данных и View для элементов
        String[] childFrom = { DB3.PHONE_COLUMN_NAME };
        int[] childTo = { android.R.id.text1 };

        // создаем адаптер и настраиваем список
        SimpleCursorTreeAdapter sctAdapter = new MyAdapter(this, cursor,
                android.R.layout.simple_expandable_list_item_1, groupFrom,
                groupTo, android.R.layout.simple_list_item_1, childFrom,
                childTo);
        elvMain = (ExpandableListView) findViewById(R.id.elvMain);
        elvMain.setAdapter(sctAdapter);
    }

    protected void onDestroy() {
        super.onDestroy();
        db3.close();
    }

    class MyAdapter extends SimpleCursorTreeAdapter {

        public MyAdapter(Context context, Cursor cursor, int groupLayout,
                         String[] groupFrom, int[] groupTo, int childLayout,
                         String[] childFrom, int[] childTo) {
            super(context, cursor, groupLayout, groupFrom, groupTo,
                    childLayout, childFrom, childTo);
        }

        protected Cursor getChildrenCursor(Cursor groupCursor) {
            // получаем курсор по элементам для конкретной группы
            int idColumn = groupCursor.getColumnIndex(DB3.COMPANY_COLUMN_ID);
            return db3.getPhoneData(groupCursor.getInt(idColumn));
        }
    }
}