package tech.jianka.adapter;

/**
 * Created by Richa on 2017/7/23.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import tech.jianka.activity.R;
import tech.jianka.data.Card;

public class CardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final CardItemClickListener mCardItemClickListener;
    private LayoutInflater inflater;
    private ArrayList<Card> cardArray;
    private Context context;

    public CardAdapter(Context context, ArrayList<Card> cardArray, CardItemClickListener listener) {
        this.context = context;
        this.cardArray = cardArray;
        this.mCardItemClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载Item View的时候根据不同TYPE加载不同的布局
        inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_item_one_column, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CardViewHolder) {
            ((CardViewHolder) holder).mTextView.setText(cardArray.get(position).getTitle());
        }
    }

    @Override
    public int getItemViewType(int position) {
        //Enum类提供了一个ordinal()方法，返回枚举类型的序数，
        return cardArray.get(position).getDataType().ordinal();
    }

    @Override
    public int getItemCount() {
        return cardArray == null ? 0 : cardArray.size();
    }

    //GENERAL 的ViewHolder
    public class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView mTextView;
        TextView mTextViewDate;

        public CardViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.card_item_title);
            mTextViewDate = (TextView) itemView.findViewById(R.id.card_item_date);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mCardItemClickListener.onCardItemClick(clickedPosition);
        }

        @Override
        public boolean onLongClick(View v) {
            int clickedPosition = getAdapterPosition();
            mCardItemClickListener.onCardItemLongClick(clickedPosition);
            return true;
        }
    }

    // TODO: 2017/7/26 不同item的不同ViewHolder
    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView mTitle;
        ImageView mImage;

        public ListViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.group_item_title);
            mImage = (ImageView) itemView.findViewById(R.id.group_item_image);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mCardItemClickListener.onCardItemClick(clickedPosition);
        }

        @Override
        public boolean onLongClick(View v) {
            int clickedPosition = getAdapterPosition();
            mCardItemClickListener.onCardItemLongClick(clickedPosition);
            return true;
        }
    }

    public interface CardItemClickListener {
        void onCardItemClick(int clickedCardIndex);

        void onCardItemLongClick(int clickedCardIndex);
    }
}