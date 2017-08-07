package tech.jianka.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import tech.jianka.activity.R;
import tech.jianka.data.Item;

import static tech.jianka.utils.CardUtil.getSpecifiedSDPath;
import static tech.jianka.utils.CardUtil.longToString;
import static tech.jianka.utils.SDCardHelper.Obj2Bytes;
import static tech.jianka.utils.SDCardHelper.saveFileToSDCard;

/**
 * Created by Richard on 2017/7/28.
 */

public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ItemClickListener listener;
    private List<Item> items;
    Context context;

    public ItemAdapter(List<Item> items, ItemClickListener listener) {
        this.listener = listener;
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case Item.GROUP:
                view = inflater.inflate(R.layout.group_item, parent, false);
                return new GroupViewHolder(view);
            case Item.CARD:
                view = inflater.inflate(R.layout.card_item_big_rectangle, parent, false);
                return new CardViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof GroupViewHolder) {
            ((GroupViewHolder) holder).mTitle.setText(items.get(position).getFileName());
        } else if (holder instanceof CardViewHolder) {
            if (items != null) {
                ((CardViewHolder) holder).mCardTitle.setText(items.get(position).getCardTitle());
                ((CardViewHolder) holder).mCardContent.setText((String) items.get(position).getCardContent());
                try {
                    String date = longToString(items.get(position).getModifiedTime(), "HH:mm MM/dd");
                    ((CardViewHolder) holder).mCardDate.setText(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                ((CardViewHolder) holder).mCardContent.setText((String) items.get(position).getCardContent());
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getItemType();
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public void addItem(Item item) {
        items.add(item);
        try {
            if (item.getItemType() == Item.GROUP) {
                new File(getSpecifiedSDPath(item.getFilePath())).createNewFile();
            } else {
                saveFileToSDCard(Obj2Bytes(item), item.getFilePath(), item.getCardTitle() + ".card");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        notifyDataSetChanged();
    }

    public boolean removeItem(int position) {
        Item toDeleteItem = items.get(position);
        // TODO: 2017/8/6 完善能不能删除的逻辑
        String[] canNotDelete = {context.getResources().getString(R.string.card_group_inbox),
                context.getResources().getString(R.string.task), context.getResources().getString(R.string.task_important_emergent)};
        String toCompare = toDeleteItem.getFileName();
        for (String name : canNotDelete) {
            if (name.equals(toCompare)) {
                return false;
            }
        }
        new File(items.get(position).getFilePath()).delete();
        items.remove(position);
        notifyDataSetChanged();
        return true;
    }

    public void shareItem(int clickedCardIndex, Activity context) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, context.getResources().getString(R.string.card_share));
        intent.putExtra(Intent.EXTRA_TEXT, items.get(clickedCardIndex).getCardTitle() +
                "\n" + items.get(clickedCardIndex).getCardContent());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, context.getTitle()));
    }

    public Item getItem(int clickedItemIndex) {
        return items.get(clickedItemIndex);
    }

    public class CardViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {
        TextView mCardTitle;
        TextView mCardDate;
        TextView mCardGroup;
        TextView mCardContent;
        ImageView mCardImage;

        public CardViewHolder(View itemView) {
            super(itemView);
            mCardTitle = (TextView) itemView.findViewById(R.id.card_item_title);
            mCardDate = (TextView) itemView.findViewById(R.id.card_item_date);
            mCardGroup = (TextView) itemView.findViewById(R.id.card_item_group);
            mCardImage = (ImageView) itemView.findViewById(R.id.card_item_image);
            mCardContent = (TextView) itemView.findViewById(R.id.card_item_content);
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
