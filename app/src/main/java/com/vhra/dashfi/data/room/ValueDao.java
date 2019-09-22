package com.vhra.dashfi.data.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ValueDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsers(ValueEntity valueEntity);

    @Query("SELECT * FROM value")
    List<ValueEntity> getAllValues();
}
