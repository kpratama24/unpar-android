package id.ac.unpar.unparapps.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.ac.unpar.unparapps.R;

/**
 * Created by Carissa on 1/17/2018.
 */

public class PromoAdapter extends RecyclerView.Adapter<PromoAdapter.MyViewHolder> {
    private String[] mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public CardView mCardView;
        public TextView mTextView;

        public MyViewHolder(View v){
            super(v);

            mCardView = (CardView) v.findViewById(R.id.card_view);
            mTextView = (TextView) v.findViewById(R.id.tv_text);

        }

    }

    public PromoAdapter(String[] myDataset){
        mDataset = myDataset;
    }

    @Override
    public PromoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.promo_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        holder.mTextView.setText(mDataset[position]);
    }

    @Override
    public int getItemCount() { return mDataset.length; }
}
