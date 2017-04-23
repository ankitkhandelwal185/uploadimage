package iwayinfo.android.imageupload;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
    TextView time_up;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);

        name = (EditText)findViewById(R.id.image_name);
        time_up = (TextView) findViewById(R.id.time_uploaded);
        image = (ImageView)findViewById(R.id.imageview);
        retrieve = (Button)findViewById(R.id.retrieve_btn);


        retrieve.setOnClickListener(this);


    }

    private void retrieve_image(){}

    @Override
    public void onClick(View view) {
        if(view==retrieve){
            retrieve_image();
        }
    }
}
