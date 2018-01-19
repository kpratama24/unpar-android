package id.ac.unpar.unparapps.Posts;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.Map;

import id.ac.unpar.unparapps.R;

/**
 * Created by Carissa on 1/19/2018.
 */

public class NewsPosts extends AppCompatActivity {
    TextView title;
    TextView content;
    ProgressDialog progressDialog;
    Gson gson;
    Map<String, Object> mapPost;
    Map<String, Object> mapTitle;
    Map<String, Object> mapContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_content);

        final String id = getIntent().getExtras().getString("id");
        final String t = getIntent().getExtras().getString("title");
        final String c = getIntent().getExtras().getString("content");

        title = (TextView) findViewById(R.id.title);
        content = (TextView)findViewById(R.id.content);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        String url = "http://pm.unpar.ac.id/wp-json/wp/v2/posts/"+id+"?fields=id,title,date,content";
       // String url = "http://www.thejavaprogrammer.com/wp-json/wp/v2/posts/"+id+"?fields=title,content";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                gson = new Gson();
                mapPost = (Map<String, Object>) gson.fromJson(s, Map.class);
                mapTitle = (Map<String, Object>) mapPost.get("title");
                mapContent = (Map<String, Object>) mapPost.get("content");
                String summary = "<html><head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"></head><body>"+mapContent.get("rendered").toString()+"</body></html>";
                title.setText(mapTitle.get("rendered").toString());
                content.setText(mapContent.get("rendered").toString());
            //    content.loadData(summary,"text/html","UTF-8");

                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                progressDialog.dismiss();
                Toast.makeText(NewsPosts.this, id, Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(NewsPosts.this);
        rQueue.add(request);
    }
}
