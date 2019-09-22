package com.vhra.dashfi.data.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface ValueDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsers(ValueEntity valueEntity);
}
