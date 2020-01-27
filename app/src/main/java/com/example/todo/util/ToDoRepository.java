package com.example.todo.util;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.todo.data.ToDoDao;
import com.example.todo.data.ToDoRoomDatabase;
import com.example.todo.model.ToDo;

import java.util.List;

public class ToDoRepository {
    private ToDoDao toDoDao;
    private LiveData<List<ToDo>> allToDos;

    public ToDoRepository(Application application) {
        //Get data from a remote API and then put it on a diff. list
        ToDoRoomDatabase db = ToDoRoomDatabase.getDatabase(application);

        toDoDao = db.toDoDao();
        allToDos = toDoDao.getAllToDos();
    }

    public LiveData<List<ToDo>> getAllToDos() {
        return allToDos;
    }

    public void insert(ToDo toDo){
        new insertAsyncTask(toDoDao).execute(toDo);
    }


    private class insertAsyncTask extends AsyncTask<ToDo, Void, Void> {
        private ToDoDao asyncTaskDao;
        public insertAsyncTask(ToDoDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(ToDo... params) {
            //[obj1, obj2,...]
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
