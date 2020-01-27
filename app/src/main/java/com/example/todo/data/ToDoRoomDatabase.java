package com.example.todo.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.todo.model.ToDo;

@Database(entities = {ToDo.class}, version = 1)
public abstract class ToDoRoomDatabase extends RoomDatabase {

    public abstract ToDoDao toDoDao();
    public static volatile ToDoRoomDatabase INSTANCE;

    public static ToDoRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ToDoRoomDatabase.class) {
                if (INSTANCE == null) {
                    //create our db
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ToDoRoomDatabase.class, "todo_database")
                            .addCallback(roomDatabseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomDatabseCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final ToDoDao toDoDao;

        public PopulateDbAsync(ToDoRoomDatabase db) {
            toDoDao = db.toDoDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
//            toDoDao.deleteAll(); //removes all items from our table
//            //for testing
//            ToDo toDo = new ToDo("Do stuff");
//            toDoDao.insert(toDo);
//            toDoDao.insert(toDo);


            return null;
        }
    }
}
