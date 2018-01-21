package id.ac.unpar.unparapps.Posts;


import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

public class NewsPosts extends Activity {
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
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarId);
               // toolbar.setBackground(new ColorDrawable(Color.parseColor("#80000000")));
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });

                gson = new Gson();
                mapPost = (Map<String, Object>) gson.fromJson(s, Map.class);
                mapTitle = (Map<String, Object>) mapPost.get("title");

                mapContent = (Map<String, Object>) mapPost.get("content");
                CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
                collapsingToolbar.setTitle(mapTitle.get("rendered").toString());
                String summary = "<head><body>"+mapContent.get("rendered").toString()+"</body>";
                title.setText(mapTitle.get("rendered").toString());
                content.setText(Html.fromHtml(summary));
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
