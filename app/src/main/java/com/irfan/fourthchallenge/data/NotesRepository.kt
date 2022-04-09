package com.irfan.fourthchallenge.data

import com.irfan.fourthchallenge.data.source.NotesLocalDataSource
import com.irfan.fourthchallenge.model.Note
import com.irfan.fourthchallenge.utils.AppExecutors
import com.irfan.fourthchallenge.utils.DataMapper

interface INotesRepository {
    fun getNotes(callback: (List<Note>) -> Unit)

    fun getNote(id: Int, callback: (Note) -> Unit)

    fun addNote(note: Note)

    fun deleteNote(note: Note)
}

class NotesRepository constructor(
    private val notesLocalDataSource: NotesLocalDataSource,
    private val appExecutors: AppExecutors,
) : INotesRepository {

    companion object {
        @Volatile
        private var instance: NotesRepository? = null

        fun getInstance(
            notesLocalData: NotesLocalDataSource,
            appExecutors: AppExecutors
        ): NotesRepository =
            instance ?: synchronized(this) {
                instance ?: NotesRepository(notesLocalData, appExecutors)
            }
    }

    override fun getNotes(
        callback: (List<Note>) -> Unit
    ) {
        appExecutors.diskIO().execute {
            val notes = notesLocalDataSource.getNotes()
            appExecutors.mainThread().execute {
                callback(DataMapper.mapEntitiesToDomain(notes))
            }
        }
    }

    override fun getNote(
        id: Int,
        callback: (Note) -> Unit
    ) {
        appExecutors.diskIO().execute {
            val note = notesLocalDataSource.getNote(id)
            appExecutors.mainThread().execute {
                callback(DataMapper.mapEntityToDomain(note))
            }
        }
    }

    override fun addNote(note: Note) {
        val noteEntity = DataMapper.mapDomainToEntity(note)
        if (note.id > 0) {
            appExecutors.diskIO().execute { notesLocalDataSource.updateNote(noteEntity) }
        } else {
            appExecutors.diskIO().execute { notesLocalDataSource.insertNote(noteEntity) }
        }
    }

    override fun deleteNote(
        note: Note
    ) {
        val noteEntity = DataMapper.mapDomainToEntity(note)
        appExecutors.diskIO().execute {
            notesLocalDataSource.deleteNote(noteEntity)
        }
    }
}

