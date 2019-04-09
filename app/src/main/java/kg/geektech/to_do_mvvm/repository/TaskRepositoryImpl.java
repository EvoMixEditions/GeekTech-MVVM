package kg.geektech.to_do_mvvm.repository;

import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import kg.geektech.to_do_mvvm.AppDelegate;
import kg.geektech.to_do_mvvm.room.AppDatabase;
import kg.geektech.to_do_mvvm.room.Task;
import kg.geektech.to_do_mvvm.room.TaskDao;


public class TaskRepositoryImpl implements TaskRepository {

    private TaskDao taskDao;
    private LiveData<List<Task>> allTasks;

    public TaskRepositoryImpl() {
        AppDatabase db = AppDatabase.getDataBase(AppDelegate.getAppContext());
        taskDao = db.taskDao();
        allTasks = taskDao.getAllTasks();
    }

    @Override
    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    @Override
    public void insert(Task task) {
        new insertAsyncTask(taskDao).execute(task);
    }

    @Override
    public void deleteTask(Task task) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void update(Task task) {

    }

    
    private static class insertAsyncTask extends AsyncTask<Task, Void, Void> {

        private TaskDao taskDao;

        public insertAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.insert(tasks[0]);
            return null;
        }
    }
}
