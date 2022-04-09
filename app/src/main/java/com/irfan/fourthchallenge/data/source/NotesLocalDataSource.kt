package com.irfan.fourthchallenge.data.source

import com.irfan.fourthchallenge.data.source.local.entity.NoteEntity
import com.irfan.fourthchallenge.data.source.local.room.NoteDao

class NotesLocalDataSource constructor(private val noteDao: NoteDao) {
    companion object {
        private var instance: NotesLocalDataSource? = null

        fun getInstance(noteDao: NoteDao): NotesLocalDataSource =
            instance ?: synchronized(this) {
                instance ?: NotesLocalDataSource(noteDao)
            }
    }

    fun getNotes(): List<NoteEntity> = noteDao.getNotes()

    fun getNote(id: Int): NoteEntity = noteDao.getNote(id)

    fun insertNote(noteEntity: NoteEntity) = noteDao.insertNote(noteEntity)

    fun updateNote(noteEntity: NoteEntity) = noteDao.updateNote(noteEntity)

    fun deleteNote(noteEntity: NoteEntity) = noteDao.deleteNote(noteEntity)
}