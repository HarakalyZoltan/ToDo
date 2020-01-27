package com.example.todo.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;
import com.example.todo.model.ToDo;

import java.util.List;

import static android.view.LayoutInflater.from;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ToDoViewHolder> {

    private final LayoutInflater toDoInflater;
    private List<ToDo> toDoList; //cached copy of todo items


    public ToDoListAdapter(Context context) {
        toDoInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = toDoInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ToDoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoViewHolder holder, int position) {
        if (toDoList != null) {
            ToDo current = toDoList.get(position);
            holder.toDoTextView.setText(current.getToDo());
        }else {
            holder.toDoTextView.setText(R.string.no_todo);
        }
    }
    public void setToDo(List<ToDo> toDos) {
        toDoList = toDos;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (toDoList != null)
            return toDoList.size();
        else return 0;
    }

    public class ToDoViewHolder extends RecyclerView.ViewHolder{
        public final TextView toDoTextView;
        public ToDoViewHolder(@NonNull View itemView) {
            super(itemView);
            toDoTextView = itemView.findViewById(R.id.textView);
        }
    }
}
