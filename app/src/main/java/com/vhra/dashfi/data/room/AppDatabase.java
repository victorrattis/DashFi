package com.vhra.dashfi.data.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(version = 1, entities = { ValueEntity.class }, exportSchema = false)
@TypeConverters(LabelsConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ValueDao valueDao();
}
