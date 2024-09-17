package com.myproject.notesappnvvm.Adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myproject.notesappnvvm.Activity.UpdateTaskActivity;
import com.myproject.notesappnvvm.Helper.HelperClass;
import com.myproject.notesappnvvm.MainActivity;
import com.myproject.notesappnvvm.Model.Beans.Task;
import com.myproject.notesappnvvm.R;
import com.myproject.notesappnvvm.ViewModel.TaskViewModel;

import java.util.Date;
import java.util.List;

public class TaskRecyclerViewAdapter extends RecyclerView.Adapter<TaskRecyclerViewAdapter.TaskViewHolder> {

    MainActivity mainActivity;
    List<Task> tasks;

    public TaskRecyclerViewAdapter(MainActivity mainActivity, List<Task> tasks) {
        this.mainActivity = mainActivity;
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mainActivity).inflate(R.layout.task_row_layout, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {

        Task task = tasks.get(position);

        String daysDifference = HelperClass.DaysDifference(new Date(), task.getDueDate());
        holder.txtTitle.setText(task.getTitle());
        holder.dueDate.setText(HelperClass.DateToString(task.getDueDate()));
        holder.daysLeft.setText(String.format("%s Days left", daysDifference));

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(mainActivity, UpdateTaskActivity.class);

            intent.putExtra("id", task.getId());
            intent.putExtra("title", task.getTitle());
            intent.putExtra("description", task.getDescription());
            intent.putExtra("dueDate", task.getDueDate());
            intent.putExtra("isCompleted", task.isCompleted());

            mainActivity.startActivity(intent);
        });


        holder.LLTaskRow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity, AlertDialog.THEME_HOLO_LIGHT)
                        .setTitle("Delete Task")
                        .setMessage("Are you sure want to delete")
                        .setIcon(R.drawable.baseline_delete_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                int index = holder.getAdapterPosition();
                                TaskViewModel taskViewModel = new TaskViewModel(mainActivity.getApplication());
                                taskViewModel.deleteTask(tasks.get(index).getId());
                                notifyItemRemoved(index);
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                builder.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, dueDate, daysLeft;
        View emptyLayout;
        LinearLayout LLTaskRow;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtRecyclerCardTitle);
            dueDate = itemView.findViewById(R.id.txtRecyclerCardDueDays);
            daysLeft = itemView.findViewById(R.id.txtRecyclerCardDaysLeft);
            emptyLayout = itemView.findViewById(R.id.empty_state_layout);
            LLTaskRow = itemView.findViewById(R.id.LLTaskRow);
        }
    }
}
