package tech.jianka.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import tech.jianka.activity.R;
import tech.jianka.data.CardGroup;

/**
 * Created by Richard on 2017/7/28.
 */

public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int CARD_GROUP = 1;
    public static final int CARD = 2;
    public static final int CARD_AND_GROUP = 3;
    public static final int TASK_GROUP = 4;
    public static final int TASK_AND_GROUP = 5;
    public static final int TASK_IMPORTANT_EMERGENT = 6;
    public static final int TASK_IMPORTANT_NOT_EMERGENT = 7;
    public static final int TASK_UNIMPORTANT_EMERGENT = 8;
    public static final int TASK_UNIMPORTANT_NOT_EMERGENT = 9;

    private CardGroup cards;
    private ItemClickListener listener;
    private ArrayList<CardGroup> groups;
    private int adapterType = 0;

    public ItemAdapter(int adapterType, ItemClickListener listener) {
        this.listener = listener;
        this.adapterType = adapterType;
    }

    public ItemAdapter(ArrayList<CardGroup> groups, int adapterType, ItemClickListener listener) {
        this.listener = listener;
        this.groups = groups;
        this.adapterType = adapterType;

    }

    public ItemAdapter(CardGroup cards, int adapterType, ItemClickListener listener) {
        this.cards = cards;
        this.listener = listener;
        this.adapterType = adapterType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case CARD_GROUP:
                view = inflater.inflate(R.layout.group_item, parent, false);
                return new GroupViewHolder(view);
            case CARD:
                view = inflater.inflate(R.layout.card_item_one_column, parent, false);
                return new CardViewHolder(view);
            case TASK_GROUP:
                view = inflater.inflate(R.layout.group_item, parent, false);
                return new GroupViewHolder(view);
            case TASK_IMPORTANT_EMERGENT:
                break;
            case TASK_IMPORTANT_NOT_EMERGENT:
                break;
            case TASK_UNIMPORTANT_EMERGENT:
                break;
            case TASK_UNIMPORTANT_NOT_EMERGENT:
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof GroupViewHolder) {
            String title = groups.get(position).getGroupTitle();
            ((GroupViewHolder) holder).mTitle.setText(title);
//            ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
//            params.height = params.width*2/3;
//            holder.itemView.setLayoutParams(params);
        } else if (holder instanceof CardViewHolder) {
            if (cards != null) {
                ((CardViewHolder) holder).mCardTitle.setText(cards.get(position).getTitle());
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (adapterType == CARD_AND_GROUP) {
            // TODO: 2017/7/28 return type use position
            return adapterType;
        }
        else if (adapterType == TASK_AND_GROUP) {
            // TODO: 2017/7/28 return type use position
            return adapterType;
        }else return adapterType;
    }

    @Override
    public int getItemCount() {
        if (groups != null) {
            return groups.size();
        } else return cards.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView mCardTitle;
        TextView mCardDate;
        TextView mCardGroup;
        ImageView mCardImage;

        public CardViewHolder(View itemView) {
            super(itemView);
            mCardTitle = (TextView) itemView.findViewById(R.id.card_item_title);
            mCardDate = (TextView) itemView.findViewById(R.id.card_item_date);
            mCardGroup = (TextView) itemView.findViewById(R.id.card_item_group);
            mCardImage = (ImageView) itemView.findViewById(R.id.card_item_image);
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
//            ViewGroup.LayoutParams params = itemView.getLayoutParams();
//            params.height = params.width * 2 / 3;
//            itemView.setLayoutParams(params);
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
