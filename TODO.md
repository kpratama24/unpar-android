# UNPAR APPS
## To do list :

`ActivityHelper.java`
----------
Kelas ini digunakan untuk menyimpan semua method yang bersifat *reusable*
```java
//contoh implementasi kelas

public static void doAToast(Context c, String message){
    Toast.makeText(c, message, Toast.LENGTH_SHORT).show();
}

public static void addFragmentToActivity(FragmentManager fm, Fragment fragment,){
    //TODO do a code
}
```

`Mengambil data dari backend`
---------
### Dirapatkan

`Reusable Code`
---------

### Dirapatkan

`Parsing data login dari SSO`
---------
### Dirapatkan

`Android 8 Support`
---------
- `build.gradle` sudah diupdate untuk men-*support* ***Android*** 8. 
- Penyesuaian pembuatan kelas perlu dirapatkan.

`Log.d instead of Toast`
---------
Lebih baik jika ingin melakukan *debugging* pada logcat, menggunakan ***Utility Log*** milik ***Android***. Hal ini akan menghindari *user* mengetahui isi dari kode milik kita.

#### Contoh penggunaaan :

`AnActivity.java`
```java

public class Activity extends AppCompatActivity{

    @Override
     public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Log.d("onCreateDebug","your message here");
    }
}
```

