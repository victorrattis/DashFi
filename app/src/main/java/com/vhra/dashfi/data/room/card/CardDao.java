package com.vhra.dashfi.data.room.card;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCard(CardEntity entity);

    @Query("SELECT * FROM card")
    List<CardEntity> getCards();
}
