package tech.jianka.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

import tech.jianka.activity.R;
import tech.jianka.data.GroupData;
import tech.jianka.data.Item;

import static tech.jianka.utils.CardUtil.getSpecifiedSDPath;

/**
 * Created by Richard on 2017/7/28.
 */

public class GroupAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ItemClickListener listener;
    private List<Item> items;

    public GroupAdapter(List<Item> items, ItemClickListener listener) {
        this.listener = listener;
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.group_item, parent, false);
        return new GroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof GroupViewHolder) {
            ((GroupViewHolder) holder).mTitle.setText(items.get(position).getFileName());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return Item.GROUP;
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public void addItem(Item item) {
        items.add(item);
        new File(getSpecifiedSDPath(item.getFilePath())).mkdirs();
        notifyDataSetChanged();
    }

    public int removeItem(int position) {
        Item toDeleteItem = items.get(position);
        String toCompare = toDeleteItem.getFileName();
        if (toCompare.equals("收信箱")) {
            return GroupData.INBOX;
        }
        File file = new File(items.get(position).getFilePath());
        if (file.list() != null && file.list().length > 0) {
            return GroupData.NOT_EMPTY;
        } else {
            file.delete();
            items.remove(position);
            notifyDataSetChanged();
            return GroupData.DELETE_DONE;
        }
    }

    public boolean removeItemAndChild(int position) {
        //先删除文件,再删除items中的数据
        boolean result =
        new File(items.get(position).getFilePath()).delete();
        items.remove(position);
        notifyDataSetChanged();
        return result;
    }

    public Item getItem(int clickedItemIndex) {
        return items.get(clickedItemIndex);
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
