package id.ac.unpar.unparapps.Adapter;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.ac.unpar.unparapps.Posts.NewsPosts;
import id.ac.unpar.unparapps.R;

/**
 * Created by Carissa on 1/17/2018.
 */

public class PromoAdapter extends RecyclerView.Adapter<PromoAdapter.MyViewHolder> {
    private String[] mDataset;
    private double[] dataId;
    private double[] mediaId;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public CardView mCardView;
        private String mItem;
        public TextView mTextView;

        public MyViewHolder(View v){
            super(v);
            v.setOnClickListener(this);
            mCardView = (CardView) v.findViewById(R.id.card_view);
            mTextView = (TextView) v.findViewById(R.id.tv_text);

        }
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(v.getContext(),NewsPosts.class);
            intent.putExtra("id",((int)dataId[getPosition()])+"");
            intent.putExtra("media",(int)mediaId[getPosition()]);
            v.getContext().startActivity(intent);

            Log.d("test", "onClick " + mDataset[getPosition()]+ " " + (int)dataId[getPosition()]);
        }
    }

    public PromoAdapter(String[] myDataset,double[] dataId,double[] mediaId){
        mDataset = myDataset;
        this.dataId = dataId;
        this.mediaId = mediaId;
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
