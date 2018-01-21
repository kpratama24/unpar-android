package id.ac.unpar.unparapps.Posts;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import id.ac.unpar.unparapps.R;

/**
 * Created by Carissa on 1/21/2018.
 */

public class PromoPost extends Activity {
    TextView title;
    TextView content;
    ImageView img;
    ProgressDialog progressDialog;
    Gson gson;
    Map<String, Object> mapPost;
    Map<String, Object> mapTitle;
    Map<String, Object> mapContent;
    Map<String, Object> mapMedia;
    String urlImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.promo_content);

        final String id = getIntent().getExtras().getString("id");
        final String media = getIntent().getExtras().getString("media");
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarId);


        title = (TextView) findViewById(R.id.title);
        content = (TextView)findViewById(R.id.content);
     //   img= (ImageView) findViewById(R.id.imageContent);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        String url = "http://pm.unpar.ac.id/wp-json/wp/v2/posts/"+id+"?fields=id,title,date,content,featured_media&categories=288";
        urlImage="http://pm.unpar.ac.id/wp-json/wp/v2/media/"+media+"?fields=guid";

        // String url = "http://www.thejavaprogrammer.com/wp-json/wp/v2/posts/"+id+"?fields=title,content";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarId);
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
                StringRequest request = new StringRequest(Request.Method.GET, urlImage, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        mapMedia=(Map<String, Object>) mapPost.get("guid");
               //         new GetXMLTask(img).execute(mapMedia.get("rendered").toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                        Toast.makeText(PromoPost.this, id, Toast.LENGTH_LONG).show();
                    }
                });
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                progressDialog.dismiss();
                Toast.makeText(PromoPost.this, id, Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(PromoPost.this);
        rQueue.add(request);
    }
    private class GetXMLTask extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public GetXMLTask(ImageView imageView){
            this.imageView = imageView;
        }

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        protected void onPostExecute(Bitmap result){
            img.setImageBitmap(result);
        }
    }
}
