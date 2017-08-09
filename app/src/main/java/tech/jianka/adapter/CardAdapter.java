package tech.jianka.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.text.ParseException;
import java.util.List;

import tech.jianka.activity.R;
import tech.jianka.data.Card;
import tech.jianka.data.Item;

import static tech.jianka.utils.ItemUtils.Obj2Bytes;
import static tech.jianka.utils.ItemUtils.longToString;
import static tech.jianka.utils.ItemUtils.saveFileToSDCard;

/**
 * Created by Richard on 2017/7/28.
 */

public class CardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ItemClickListener listener;
    private List<Card> cards;

    public CardAdapter(List<Card> cards, ItemClickListener listener) {
        this.listener = listener;
        this.cards = cards;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.card_item_big_rectangle, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (cards != null) {
                ((CardViewHolder) holder).mCardTitle.setText(cards.get(position).getCardTitle());
                ((CardViewHolder) holder).mCardContent.setText((String) cards.get(position).getCardContent());
                try {
                    String date = longToString(cards.get(position).getModifiedTime(), "HH:mm MM/dd");
                    ((CardViewHolder) holder).mCardDate.setText(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                ((CardViewHolder) holder).mCardContent.setText((String) cards.get(position).getCardContent());
            }
//        }
    }

    @Override
    public int getItemViewType(int position) {
        return cards.get(position).getItemType();
    }

    @Override
    public int getItemCount() {
        return cards == null ? 0 : cards.size();
    }

    public void addItem(Card card) {
        saveFileToSDCard(Obj2Bytes(card), card.getFilePath(), card.getCardTitle() + ".card");
        card.setFilePath(card.getFilePath()+card.getCardTitle() + ".card");
        cards.add(0,card);
        notifyDataSetChanged();
    }

    public boolean removeItem(int position) {
        Item toDeleteItem = cards.get(position);
        // TODO: 2017/8/6 完善能不能删除的逻辑
        String[] canNotDelete = {"收信箱", "任务"};
        String toCompare = toDeleteItem.getFileName();
        for (String name : canNotDelete) {
            if (name.equals(toCompare)) {
                return false;
            }
        }
        new File(cards.get(position).getFilePath()).delete();
        cards.remove(position);
        notifyDataSetChanged();
        return true;
    }

    public void shareItem(int clickedCardIndex, Activity context) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        intent.putExtra(Intent.EXTRA_TEXT, cards.get(clickedCardIndex).getCardTitle() +
                "\n" + cards.get(clickedCardIndex).getCardContent());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, context.getTitle()));
    }

    public Item getItem(int clickedItemIndex) {
        return cards.get(clickedItemIndex);
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
            mCardTitle = (TextView) itemView.findViewById(R.id.task_item_title);
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

    /**
     * 实现长按和点击的接口
     */
    public interface ItemClickListener {
        void onItemClick(int clickedCardIndex);

        void onItemLongClick(int clickedCardIndex);
    }

}
