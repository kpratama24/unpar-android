package id.ac.unpar.unparapps.Adapter;

import android.content.Intent;
import android.net.Uri;
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

public class EmergencyAdapter extends RecyclerView.Adapter<EmergencyAdapter.EmergencyViewHolder> {
    private String[] mDataset;

    public class EmergencyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public CardView mCardView;
        public TextView mTextView;

        public EmergencyViewHolder(View v){
            super(v);
            v.setOnClickListener(this);
            mCardView = (CardView) v.findViewById(R.id.card_view);
            mTextView = (TextView) v.findViewById(R.id.tv_text);

        }

        @Override
        public void onClick(View v) {

            // nomor dari http://www.infobdg.com/v2/info-kota/nomor-telpon-penting/
            String phoneNumber="0";
            if(getPosition()==0){
                 phoneNumber=" 022-118";

            }
            else if(getPosition()==1){
                //harusnya ada pilihan lg rs apa

                phoneNumber="022-2034386-9"; //RS advent
            }
            else if(getPosition()==2){
                //harusnya ada pilihan lg
                phoneNumber="022-4203500"; //polrestabes bandung
            }
            else if(getPosition()==3){
                //harusnya ada pilihan lg(?)
                phoneNumber="022-113";
            }


            v.getContext().startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
            Log.d("test", "onClick " + mDataset[getPosition()] +" "+phoneNumber);

        }
    }

    public EmergencyAdapter(String[] myDataset){
        mDataset = myDataset;
    }

    @Override
    public EmergencyAdapter.EmergencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.emergency_item, parent, false);
        EmergencyViewHolder vh = new EmergencyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(EmergencyViewHolder holder, int position){
        holder.mTextView.setText(mDataset[position]);
    }

    @Override
    public int getItemCount() { return mDataset.length; }
}
