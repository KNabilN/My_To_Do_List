package com.fitness.android.myapplication.UI;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fitness.android.myapplication.POJO.Tasks;
import com.fitness.android.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksHolder> {

    private Context context;
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String DES = "description";
    public static final String IMPORTANCE = "importance";

    public TasksAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public TasksHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TasksHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull TasksHolder holder, int position)
    {
        holder.setColor(arrayList.get(position).getImportance());
        holder.title.setText(arrayList.get(position).getTitle());
        holder.title.setTag(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    List<Tasks> arrayList;

    public class TasksHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title;
        private View colored;

        public TasksHolder(@NonNull View itemView) {
            super(itemView);
            //declare
            title = itemView.findViewById(R.id.task_title_item);
            colored = itemView.findViewById(R.id.view_color_item);
            //on click
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            //go to the task view
            Tasks tasks = (Tasks) title.getTag();
            Intent intent = new Intent(context.getApplicationContext(), TasksActivity.class);

            intent.putExtra(ID, tasks.getId());
            intent.putExtra(TITLE, tasks.getTitle());
            intent.putExtra(DES, tasks.getDes());
            intent.putExtra(IMPORTANCE, tasks.getImportance());

            context.startActivity(intent);
        }

        public void setColor(int color)
        {
            switch (color){
                case 1:
                    colored.setBackgroundColor(context.getResources().getColor(R.color.vit));
                    break;
                case 2:
                    colored.setBackgroundColor(context.getResources().getColor(R.color.mit));
                    break;
                case 3:
                    colored.setBackgroundColor(context.getResources().getColor(R.color.sit));
                    break;

            }
        }
    }

    public void setArrayList(List<Tasks> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    public List<Tasks> getTasks()
    {
        return arrayList;
    }
}
