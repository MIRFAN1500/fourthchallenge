package com.irfan.fourthchallenge.data.source.local.room

import androidx.room.*
import com.irfan.fourthchallenge.data.source.local.entity.NoteEntity

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getNotes(): List<NoteEntity>

    @Query("SELECT * FROM note WHERE id = :id")
    fun getNote(id: Int): NoteEntity

    @Insert
    fun insertNote(noteEntity: NoteEntity)

    @Update
    fun updateNote(noteEntity: NoteEntity)

    @Delete
    fun deleteNote(noteEntity: NoteEntity)
}
