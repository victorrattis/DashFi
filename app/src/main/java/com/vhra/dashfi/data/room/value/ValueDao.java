package com.vhra.dashfi.data.room.value;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ValueDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertValue(ValueEntity... valueEntity);

    @Query("SELECT * FROM value")
    List<ValueEntity> getAllValues();

    @Query("SELECT * FROM value")
    LiveData<List<ValueEntity>> getLiveAllValues();
}
