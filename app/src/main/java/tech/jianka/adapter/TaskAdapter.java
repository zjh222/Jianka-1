package tech.jianka.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.text.ParseException;
import java.util.List;

import tech.jianka.activity.R;
import tech.jianka.data.DataType;
import tech.jianka.data.Item;
import tech.jianka.data.Task;

import static tech.jianka.utils.ItemUtils.Obj2Bytes;
import static tech.jianka.utils.ItemUtils.longToString;
import static tech.jianka.utils.ItemUtils.saveFileToSDCard;

/**
 * Created by Richard on 2017/7/28.
 */

public class TaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ItemClickListener listener;
    private List<Task> tasks;

    public TaskAdapter(List<Task> tasks, ItemClickListener listener) {
        this.listener = listener;
        this.tasks = tasks;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == DataType.GROUP) {
            view = inflater.inflate(R.layout.task_group_item, parent, false);
            return new GroupViewHolder(view);

        } else {
            view = inflater.inflate(R.layout.task_item, parent, false);
            return new TaskViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof GroupViewHolder) {
            ((GroupViewHolder) holder).mTitle.setText(tasks.get(position).getFileName());
            switch (tasks.get(position).getTaskType()) {
                case DataType.TASK_IMPORTANT_EMERGENT:
                    ((GroupViewHolder) holder).mImage.setImageResource(R.drawable.background_important_emergent);
                    break;
                case DataType.TASK_IMPORTANT_NOT_EMERGENT:
                    ((GroupViewHolder) holder).mImage.setImageResource(R.drawable.background_important_not_emergent);
                    break;
                case DataType.TASK_UNIMPORTANT_EMERGENT:
                    ((GroupViewHolder) holder).mImage.setImageResource(R.drawable.background_unimportant_emergent);
                    break;
                case DataType.TASK_UNIMPORTANT_NOT_EMERGENT:
                    ((GroupViewHolder) holder).mImage.setImageResource(R.drawable.background_umimportant_not_emergent);
                    break;
            }

        } else if (holder instanceof TaskViewHolder) {
            StaggeredGridLayoutManager.LayoutParams layoutParams =
                    new StaggeredGridLayoutManager.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setFullSpan(true);
            layoutParams.setMargins(10,10,10,10);
            holder.itemView.setLayoutParams(layoutParams);
            if (tasks != null) {
                try {
                    String date = longToString(tasks.get(position).getModifiedTime(), "HH:mm MM/dd");
                    ((TaskViewHolder) holder).mTaskDate.setText(date);
                    ((TaskViewHolder) holder).mCardImage.setImageResource(R.drawable.task_image_important_not_emergent);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return tasks.get(position).getItemType();
    }

    @Override
    public int getItemCount() {
        return tasks == null ? 0 : tasks.size();
    }

    public void addItem(Task task) {
        tasks.add(task);
        saveFileToSDCard(Obj2Bytes(task), task.getFilePath(), task.getTaskTitle() + ".card");
        notifyDataSetChanged();
    }

    public boolean removeItem(int position) {
        Item toDeleteItem = tasks.get(position);
        String[] canNotDelete = {"很重要-很紧急", "很重要-不紧急", "不重要-很紧急", "不重要-不紧急"};
        String toCompare = toDeleteItem.getFileName();
        for (String name : canNotDelete) {
            if (name.equals(toCompare)) {
                return false;
            }
        }
        new File(tasks.get(position).getFilePath()).delete();
        tasks.remove(position);
        notifyDataSetChanged();
        return true;
    }

    public void shareItem(int clickedCardIndex, Activity context) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        intent.putExtra(Intent.EXTRA_TEXT, tasks.get(clickedCardIndex).getTaskTitle() +
                "\n" + tasks.get(clickedCardIndex).getTaskContent());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, context.getTitle()));
    }

    public Item getItem(int clickedItemIndex) {
        return tasks.get(clickedItemIndex);
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {
        TextView mTaskTitle;
        TextView mTaskDate;
        TextView mCardGroup;
        TextView mCardContent;
        ImageView mCardImage;

        public TaskViewHolder(View itemView) {
            super(itemView);
            mTaskTitle = (TextView) itemView.findViewById(R.id.task_item_title);
            mTaskDate = (TextView) itemView.findViewById(R.id.task_item_date);
            mCardImage = (ImageView) itemView.findViewById(R.id.task_item_image);
            mCardContent = (TextView) itemView.findViewById(R.id.task_item_content);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            listener.onItemClick(clickedPosition);
        }

        @Override
        public boolean onLongClick(View v) {
            int clickedPosition = getAdapterPosition();
            listener.onItemLongClick(clickedPosition);
            return true;
        }

    }

    public class GroupViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView mTitle;
        ImageView mImage;

        public GroupViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.group_item_title);
            mImage = (ImageView) itemView.findViewById(R.id.group_item_image);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            listener.onItemClick(clickedPosition);
        }

        @Override
        public boolean onLongClick(View v) {
            int clickedPosition = getAdapterPosition();
            listener.onItemLongClick(clickedPosition);
            return true;
        }
    }

    /**
     * 实现长按和点击的接口
     */
    public interface ItemClickListener {
        void onItemClick(int clickedCardIndex);

        void onItemLongClick(int clickedCardIndex);
    }
}
