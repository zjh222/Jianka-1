package tech.jianka.adapter;

/**
 * Created by Richa on 2017/7/23.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import tech.jianka.activity.R;
import tech.jianka.data.Card;
import tech.jianka.data.CardType;

public class CardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static CardClickListener mCardClickListener;
    private LayoutInflater inflater;
    private ArrayList<Card> cardArray;

    public CardAdapter(ArrayList<Card> cardArray,CardClickListener listener) {
        this.cardArray = cardArray;
        this.mCardClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载Item View的时候根据不同TYPE加载不同的布局
        inflater = LayoutInflater.from(parent.getContext());
        boolean attachToParentImmediately = false;
        View view = inflater.inflate(R.layout.card_item, parent, attachToParentImmediately);
        if (viewType == CardType.GENERAL.ordinal()) {
            return new GeneralCardViewHolder(view);
        }
        // TODO: 2017/7/23 修正这个判断流程
        else {
            return new Item2ViewHolder(inflater.inflate(R.layout.card_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof GeneralCardViewHolder) {
            ((GeneralCardViewHolder) holder).mTextView.setText(cardArray.get(position).getTitle());
            ((GeneralCardViewHolder) holder).mTextViewDate.setText(cardArray.get(position).getDate().toString());
        } else if (holder instanceof Item2ViewHolder) {
            ((Item2ViewHolder) holder).mTextView.setText(cardArray.get(position).getTitle());
        }
    }

    @Override
    public int getItemViewType(int position) {
        //Enum类提供了一个ordinal()方法，返回枚举类型的序数，
        return cardArray.get(position).getCardType().ordinal();
    }

    @Override
    public int getItemCount() {
        return cardArray == null ? 0 : cardArray.size();
    }

    //GENERAL 的ViewHolder
    public static class GeneralCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView mTextView;
        TextView mTextViewDate;

        public GeneralCardViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_card_item_title);
            mTextViewDate = (TextView) itemView.findViewById(R.id.tv_card_item_time);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mCardClickListener.onCardClick(clickedPosition);
        }
    }

    //INBOX 的ViewHolder
    public static class Item2ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public Item2ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_card_item_title);
        }
    }

    public interface CardClickListener {
        void onCardClick(int clickedCardIndex);
    }

}