package id.ac.unpar.unparapps.Pager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.ac.unpar.unparapps.Adapter.DirectoryAdapter;
import id.ac.unpar.unparapps.Adapter.EmergencyAdapter;
import id.ac.unpar.unparapps.R;

/**
 * Created by Carissa on 1/20/2018.
 */

public class EmergencyPager extends Fragment {

    private static final String ARG_POSITION = "position";

  //  @BindView(R.id.textView)
    TextView textView;

    private int position;

    public static EmergencyPager newInstance(int position) {
        EmergencyPager f = new EmergencyPager();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_directory_page,container,false);
                RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view);
        rv.setHasFixedSize(true);
        EmergencyAdapter adapter = new EmergencyAdapter(new String[]{"x","z", "y", "m"});
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
      //  textView= rootView.findViewById(R.id.tv_text);
        ViewCompat.setElevation(rootView, 50);
       // textView.setText("CARD " + position);
        return rootView;
    }
}