package tech.jianka.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import tech.jianka.activity.R;
import tech.jianka.data.CardGroup;
import tech.jianka.data.DataType;

/**
 * Created by Richa on 2017/7/27.
 */

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupViewHolder> {

    private final GroupItemClickListener mGroupItemClickListener;
    private LayoutInflater inflater;
    private ArrayList<CardGroup> groups;

    public GroupAdapter(ArrayList<CardGroup> groups, GroupItemClickListener listener) {
        this.mGroupItemClickListener = listener;
        this.groups = groups;
    }

    @Override
    public GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载Item View的时候根据不同TYPE加载不同的布局
        inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.group_item, parent, false);
        return new GroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GroupViewHolder holder, int position) {
        if (holder instanceof GroupViewHolder) {
            holder.itemTitle.setText(groups.get(position).getGroupTitle());
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    @Override
    public int getItemViewType(int position) {
        //Enum类提供了一个ordinal()方法，返回枚举类型的序数，
        return DataType.GROUP_FRAGMENT.ordinal();
    }

    //Group对象 的ViewHolder
    class GroupViewHolder extends ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView itemTitle;
        TextView childItemCount;

        public GroupViewHolder(View itemView) {
            super(itemView);
            itemTitle = (TextView) itemView.findViewById(R.id.group_item_title);
            childItemCount = (TextView) itemView.findViewById(R.id.group_item_number);

            //对卡组信息进行设置
            
            itemView.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mGroupItemClickListener.onGroupItemClick(clickedPosition);
        }

        @Override
        public boolean onLongClick(View v) {
            int clickedPosition = getAdapterPosition();
            mGroupItemClickListener.onGroupItemLongClick(clickedPosition);
            return true;
        }
    }

    // TODO: 2017/7/26 不同item的不同ViewHolder

    public interface GroupItemClickListener {
        void onGroupItemClick(int clickedGroupIndex);
        void onGroupItemLongClick(int clickedGroupIndex);
    }
}
