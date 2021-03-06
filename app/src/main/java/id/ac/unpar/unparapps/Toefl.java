package id.ac.unpar.unparapps;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Toefl.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Toefl#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Toefl extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    WebView ssoWebView;
    String summary=null;
    Button firstButton;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Toefl() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Toefl.
     */
    // TODO: Rename and change types and number of parameters
    public static Toefl newInstance(String param1, String param2) {
        Toefl fragment = new Toefl();
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


        View v=inflater.inflate(R.layout.activity_toefl, container, false);
        ssoWebView = (WebView) v.findViewById(R.id.webview_ssoLogin);
        final ProgressBar loading = v.findViewById(R.id.bar_ssoProcessLogin);
        ssoWebView.loadUrl("http://cdc.unpar.ac.id/old/jadwal-tes-toefl/");

        // Enable Javascript
        WebSettings webSettings = ssoWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);



        // Force links and redirects to open in the WebView instead of in a browser
        ssoWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                loading.setVisibility(View.GONE);
               // ssoWebView.loadUrl("javascript:window.HTMLOUT.processHTML('<html>'+document.getElementByClass('content').innerHTML+'</html>');");

              //  String summary = "<html><body>You scored <b>192</b> points.</body></html>";
             //  ssoWebView.setWebChromeClient(new WebChromeClient());
               // ssoWebView.loadData(summary, "text/html", null);
                ssoWebView.evaluateJavascript(
                        "(function() { return ('<html>'+document.getElementsByTagName('section')[0].innerHTML+'</html>'); })();",
                        new ValueCallback<String>() {
                            @Override
                            public void onReceiveValue(String html) {
                                 summary = "<html><body>"+html+"</body></html>";

                                Log.d("HTML", html);
                                // code here
                            }
                        });

            //    ssoWebView.loadData(summary,"text/html", null);

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                loading.setVisibility(View.VISIBLE);
            }
        });

        firstButton = (Button) v.findViewById(R.id.cekNilai);
        String value = firstButton.getText().toString();
        if(value.equalsIgnoreCase("Cek Nilai")){
            firstButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // display a message by using a Toast
                  ssoWebView.loadUrl("http://cdc.unpar.ac.id/old/cek-nilai-toefl/");
                    //   Toast.makeText(getActivity(), "First Fragment", Toast.LENGTH_LONG).show();
                    ssoWebView.setWebViewClient(new WebViewClient(){
                        @Override
                        public void onPageFinished(WebView view, String url) {
                            loading.setVisibility(View.GONE);
                            firstButton.setVisibility(View.GONE);
//                            String summary = "<html><body>You scored <b>192</b> points.</body></html>";
//                            ssoWebView.loadData(summary, "text/html", null);
//                            ssoWebView.loadUrl("javascript:(function() { document.getElementById('email_field').value = '" + email + "'; })()");
                          //  firstButton.setText("Cek Jadwal");
                        }

                        @Override
                        public void onPageStarted(WebView view, String url, Bitmap favicon) {
                            loading.setVisibility(View.VISIBLE);
                         //   firstButton.setText("Cek Jadwal");
                        }
                    });
                }
            });

        }

        // perform setOnClickListener on first Button

        return v;
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
