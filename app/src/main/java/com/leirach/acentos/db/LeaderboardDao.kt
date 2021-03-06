package com.leirach.acentos.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface LeaderboardDao {
    @Query("SELECT * FROM leaderboard ORDER BY score DESC")
    fun getAll(): LiveData<List<LeaderboardItem>>

    @Query("SELECT COUNT(id) FROM leaderboard")
    fun getCount(): LiveData<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(score: LeaderboardItem)

    //@Query("DELETE FROM leaderboard ORDER BY score LIMIT 1")
    @Query("DELETE FROM leaderboard WHERE id IN (SELECT id FROM leaderboard WHERE juego = :juegoId ORDER BY score LIMIT 1)")
    suspend fun delete(juegoId: Int)

    @Query("DELETE FROM leaderboard")
    suspend fun nukeTable()
}