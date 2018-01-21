package id.ac.unpar.unparapps;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

import id.ac.unpar.unparapps.Adapter.PromoAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Promo.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Promo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Promo extends Fragment {
    String url = "http://pm.unpar.ac.id/wp-json/wp/v2/posts?fields=id,title,featured_media&per_page=25&categories=288";
    List<Object> list;
    Gson gson;
    Map<String,Object> mapPost;
    Map<String,Object> mapTitle;
    String postTitle[];
    double postId[];
    double mediaId[];
    PromoAdapter adapter;
    RecyclerView rv;
    ProgressDialog progressDialog;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Promo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Promo.
     */
    // TODO: Rename and change types and number of parameters
    public static Promo newInstance(String param1, String param2) {
        Promo fragment = new Promo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.activity_promo, container, false);

        rv = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view);
        rv.setHasFixedSize(true);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                gson = new Gson();
                list = (List) gson.fromJson(s, List.class);
                postTitle = new String[list.size()];
                postId=new double[list.size()];
                mediaId=new double[list.size()];
                for(int i=0;i<list.size();++i){

                    mapPost = (Map<String,Object>)list.get(i);
                    mapTitle = (Map<String, Object>) mapPost.get("title");

                    postTitle[i] = (String) mapTitle.get("rendered");
                    postId[i]= (Double) mapPost.get("id");
                    mediaId[i]= (Double) mapPost.get("featured_media");


                    progressDialog.dismiss();
                }
                adapter = new PromoAdapter(postTitle,postId,mediaId);
                rv.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity(), "Some error occurred", Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(getActivity());
        rQueue.add(request);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
 
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
