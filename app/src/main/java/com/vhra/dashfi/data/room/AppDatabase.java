package com.vhra.dashfi.data.room;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.vhra.dashfi.data.room.card.CardDao;
import com.vhra.dashfi.data.room.card.CardEntity;
import com.vhra.dashfi.data.room.value.LabelsConverter;
import com.vhra.dashfi.data.room.value.ValueDao;
import com.vhra.dashfi.data.room.value.ValueEntity;

import java.util.Arrays;

@Database(version = 2, entities = { ValueEntity.class, CardEntity.class })
@TypeConverters(LabelsConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    private static final String TAG = "AppDatabase";
    private static final String DATABASE_NAME = "dashfi-db";

    private static volatile AppDatabase sInstance = null;

    // TODO: Fix migration

    public static synchronized AppDatabase getInstance(final Context context) {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            Log.d(TAG, "Database on create!");
                            ValueDao valueDao = getInstance(context).valueDao();
                            CardDao cardDao = getInstance(context).cardDao();
                            new Thread(() -> {
                                valueDao.insertValue(new ValueEntity(0, "item 1", 13.50, Arrays.asList("gastos", "agosto")));
                                valueDao.insertValue(new ValueEntity(0, "item 2", 15.00, Arrays.asList("gastos", "agosto")));
                                valueDao.insertValue(new ValueEntity(0, "item 3", 50.00, Arrays.asList("gastos", "agosto")));
                                valueDao.insertValue(new ValueEntity(0, "item 4", 35.00, Arrays.asList("gastos", "agosto")));
                                valueDao.insertValue(new ValueEntity(0, "item 5", 12.00, Arrays.asList("gastos", "agosto")));
                                valueDao.insertValue(new ValueEntity(0, "item 6", 50.50, Arrays.asList("gastos", "agosto")));
                                valueDao.insertValue(new ValueEntity(0, "Salário de Agosto de 2019", 1500, Arrays.asList("receita", "agosto", "2019")));

                                cardDao.insertCard(new CardEntity(0, "Receita de Agosto/2019", "label:agosto,receita,2019", 0));
                                cardDao.insertCard(new CardEntity(0, "Gastos de Agosto/2019", "label:gastos", 0));
                                Log.d(TAG, "Database on create: completed!");
                            }).start();
                        }
                    })
                    .build();
            Log.d(TAG, "initialize Room database!");
        }
        return sInstance;
    }

    public abstract ValueDao valueDao();
    public abstract CardDao cardDao();
}
