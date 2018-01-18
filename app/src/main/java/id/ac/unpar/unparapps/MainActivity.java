package id.ac.unpar.unparapps;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.NavigableMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private DrawerLayout sDrawerLayout;
    private ActionBarDrawerToggle sToogle;
   // private CardView news,portal,events,emergency;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sDrawerLayout=(DrawerLayout) findViewById(R.id.drawer);
        sToogle=new ActionBarDrawerToggle(this,sDrawerLayout,R.string.open,R.string.close);
        sDrawerLayout.addDrawerListener(sToogle);
        NavigationView navigationView=(NavigationView) findViewById(R.id.nav_view);
        sToogle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(navigationView);

//        Button ssoLogin = findViewById(R.id.button_goLogin);
//        ssoLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
//                startActivity(i);
//            }
//        });
    }

    public void selectItemDrawer(MenuItem item){
        android.support.v4.app.Fragment myFragment=null;
        Class fragmentClass;
        switch (item.getItemId()){
            case R.id.news :
                fragmentClass=News.class;
                break;

            case R.id.portal:
                fragmentClass=Portal.class;
                break;
            case R.id.events :
                fragmentClass=Events.class;
                break;
            case R.id.emergency:
                fragmentClass=Emergency.class;
                break;
            case R.id.toefl :
                fragmentClass=Toefl.class;
                break;
            case R.id.promo:
                fragmentClass=Promo.class;
                break;
            case R.id.directory :
                fragmentClass=Directory.class;
                break;
            case R.id.about:
                fragmentClass=About.class;
                break;
            default:
                fragmentClass=Home.class;

        }
        try{
            myFragment=(android.support.v4.app.Fragment) fragmentClass.newInstance();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content,myFragment).commit();
        item.setChecked(true);
        setTitle(item.getTitle());
        sDrawerLayout.closeDrawers();
    }

    private void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               selectItemDrawer(item);
               return true;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(sToogle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        android.support.v4.app.Fragment myFragment=null;
        Class fragmentClass;
        switch (v.getId()){
            case R.id.newsId :
                fragmentClass=News.class;
                break;

            case R.id.portalId:
                fragmentClass=Portal.class;
                break;
            case R.id.eventsId :
                fragmentClass=Events.class;
                break;
            case R.id.emergencyId:
                fragmentClass=Emergency.class;
                break;
            default:
                fragmentClass=MainActivity.class;
        }
        try{
            myFragment=(android.support.v4.app.Fragment) fragmentClass.newInstance();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        android.support.v4.app.FragmentManager fragmentManager= getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content,myFragment).commit();

    }
}
