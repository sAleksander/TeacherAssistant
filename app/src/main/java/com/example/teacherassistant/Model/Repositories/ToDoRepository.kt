package com.example.teacherassistant.Model.Repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.teacherassistant.Model.DAOs.ToDoDAO
import com.example.teacherassistant.Model.Student
import com.example.teacherassistant.Model.ToDo

class ToDoRepository(private  val toDoDAO: ToDoDAO) {
    val allToDos: LiveData<List<ToDo>> = toDoDAO.getAllToDos()

    @Suppress("RedundantSuspendModifier")

    @WorkerThread
    suspend fun insert(toDo: ToDo){
        toDoDAO.insertToDo(toDo)
    }

    @WorkerThread
    suspend fun update(toDo: ToDo){
        toDoDAO.updateToDo(toDo)
    }

    @WorkerThread
    suspend fun delete(toDo: ToDo){
        toDoDAO.deleteToDo(toDo)
    }
}