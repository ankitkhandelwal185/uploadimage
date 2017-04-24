package iwayinfo.android.imageupload;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by IWAY on 21-04-2017.
 */

public class RetrieveActivity extends AppCompatActivity implements View.OnClickListener{

    EditText name;
    Button retrieve;
    ImageView image;
    TextView time_up, desc_, goback;
    private String Url= "http://iway.netai.net/getimage.php";
    private String imageUrl = "http://iway.netai.net/uploads/";
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);

        name = (EditText)findViewById(R.id.image_name);
        time_up = (TextView) findViewById(R.id.time_uploaded);
        desc_ = (TextView)findViewById(R.id.desc_uploaded);
        image = (ImageView)findViewById(R.id.imageview);
        retrieve = (Button)findViewById(R.id.retrieve_btn);
        goback = (TextView)findViewById(R.id.goback);
        retrieve.setOnClickListener(this);
        goback.setOnClickListener(this);

    }

    private void retrieve_image(){

        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        String url = Url+"?name="+name.getText().toString();
        //Loading Image from URL
        Picasso.with(this)
                .load(imageUrl+name.getText().toString()+".jpeg")
                .placeholder(R.drawable.ic_home_black_24dp)   // optional
                .error(R.drawable.ic_home_black_24dp)      // optional
                .resize(800,800)                        // optional
                .into(image);

        Log.e("retrieve","url"+url);

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("retrieve activity","volleyerror "+error);
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response){
        String name="";
        String desc="";
        String time = "";

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");
            JSONObject collegeData = result.getJSONObject(0);
            name = collegeData.getString("name");
            desc = collegeData.getString("desc");
            time = collegeData.getString("time");
            //Log.e("retrieve12",""+name+desc+time);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.e("retrieve",""+name+desc+time);
        time_up.setText("uploaded time is \n"+"\t\t\t\t"+time);
        time_up.setVisibility(View.VISIBLE);
        desc_.setText("Description \n"+"\t\t\t\t"+desc);
        desc_.setVisibility(View.VISIBLE);
    }





    @Override
    public void onClick(View view) {
        if(view==retrieve){
            retrieve_image();
        }
        if(view==goback){
            Intent intent = new Intent(RetrieveActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(RetrieveActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
