package com.example.cilo.opentour;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import static android.graphics.BitmapFactory.decodeFile;

/**
 * Created by cilo on 5/26/17.
 */

public class ActivityPostPics extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    Button startTourBtn,aboutBtn,addPhotoBtn,postPicBtn;
    Intent intent;

    public static final int REQUEST_CAMERA = 1;
    public static final int SELECT_FILE = 2;

    Common common;
    int count;

    ImageView imageSelected;
    Bitmap currentImg;
    EditText commentEt;
    TextView feedbackMsgTv;

    Bitmap bitmap;
    Drawable drawable;

    HashMap<String,String> serverRequestDataHashmap,serverResponseDataHashmap;
    ArrayList<HashMap<String,String>> dataFromServerArraylist;
    String url;
    HandleJsonDataFromServer handleJsonDataFromServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_pics);
        overridePendingTransition(0,0);

        common = new Common(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");

        aboutBtn = (Button) toolbar.findViewById(R.id.about_btn);
        startTourBtn = (Button) toolbar.findViewById(R.id.start_tour_btn);
        setSupportActionBar(toolbar);

        imageSelected = (ImageView) findViewById(R.id.img_selected);
        commentEt = (EditText) findViewById(R.id.comment);
        addPhotoBtn = (Button) findViewById(R.id.add_photo);
        postPicBtn = (Button) findViewById(R.id.post_pic);
        feedbackMsgTv = (TextView) findViewById(R.id.feedback_msg);

        startTourBtn.setText("Back home");
        startTourBtn.setBackgroundColor(Color.parseColor("#54A846"));

        startTourBtn.setOnClickListener(this);
        aboutBtn.setOnClickListener(this);
        addPhotoBtn.setOnClickListener(this);
        postPicBtn.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        boolean state = false;
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                //startActivity(new Intent(this, Create.class));
                imageSelected.setImageBitmap(thumbnail);
                setCurrentImg(thumbnail);
                state = true;
            }else if (requestCode == SELECT_FILE) {
                String selectedImageUri = common.getAbsolutePath(data.getData());
                // startActivity(new Intent(this, Create.class));
                imageSelected.setImageBitmap(decodeFile(selectedImageUri));
                setCurrentImg(decodeFile(selectedImageUri));
                state = true;
            }
        }

        if(state == true){

            drawable = imageSelected.getDrawable();
            bitmap = ((BitmapDrawable) drawable).getBitmap();

            if(bitmap == null){
                addPhotoBtn.setText("Add Photo");
            }else{
                addPhotoBtn.setText("Change Photo");

                commentEt.setVisibility(View.VISIBLE);
                postPicBtn.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_tour_btn:
                intent = new Intent(this,ActivityHome.class);
                startActivity(intent);
                break;
            case R.id.about_btn:
                intent = new Intent(this,OpenTourIntro.class);
                startActivity(intent);
                break;
            case R.id.add_photo:
                selectImage();
                break;
            case R.id.post_pic:
                String comment = commentEt.getText().toString();
                bitmap = getCurrentImg();
                if(bitmap == null) {

                }else{
                    postToursMoments(1,1,bitmap,comment);
                }
                break;
        }
    }

    public void postToursMoments(int userID, int tourID, Bitmap pic, final String comment){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        pic.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT);

        url = "/post_tours_moments.php";
        serverRequestDataHashmap = new HashMap<String,String>();
        serverRequestDataHashmap.put("user_id",""+userID);
        serverRequestDataHashmap.put("tour_id",""+tourID);
        serverRequestDataHashmap.put("pic",encodedImage);
        serverRequestDataHashmap.put("comment",comment);

        new ServerRequest(serverRequestDataHashmap, url, new UrlCallBack() {
            @Override
            public void done(String response) {

                if(response == null){

                }else{
                    try {
                        handleJsonDataFromServer = new HandleJsonDataFromServer(response,"tours");
                        if(handleJsonDataFromServer == null){

                        }else{
                            boolean state = handleJsonDataFromServer.postToursMoments();
                            if(state == true){
                                Toast.makeText(getApplicationContext(),"Your post was succesfully added!",Toast.LENGTH_LONG).show();

                                count = 0;
                                final Handler handler = new Handler();
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        while(count < 6){
                                            count++;
                                            handler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    if(count < 5){
                                                        feedbackMsgTv.setText("Your post was succesfully added!");
                                                        feedbackMsgTv.setTextColor(Color.parseColor("#54A846"));
                                                    }else{
                                                        feedbackMsgTv.setText("");
                                                        addPhotoBtn.setText("Post Another Photo");
                                                        imageSelected.setImageDrawable(null);
                                                        commentEt.setText("");
                                                        commentEt.setVisibility(View.GONE);
                                                        postPicBtn.setVisibility(View.GONE);
                                                    }
                                                }
                                            });

                                            try {
                                                Thread.sleep(1000);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                }).start();

                                Log.d("cilo5","post successful!");
                            }else{
                                Log.d("cilo5","post not successful!");
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).execute();
    }

    public Bitmap getCurrentImg() {
        return currentImg;
    }

    public void setCurrentImg(Bitmap currentImg) {
        this.currentImg = currentImg;
    }

    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Gallery", "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
}
