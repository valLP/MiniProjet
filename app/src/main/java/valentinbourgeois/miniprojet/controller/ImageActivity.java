package valentinbourgeois.miniprojet.controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import valentinbourgeois.miniprojet.R;
import valentinbourgeois.miniprojet.model.MyUtils;
import valentinbourgeois.miniprojet.view.ImageScaleGestureListener;

public class ImageActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{
    private ScaleGestureDetector _mScaleDetector;
    private GestureDetectorCompat _mDetector;
    private ImageView _image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        Intent intent = getIntent();
        String imageUri = intent.getStringExtra(CategoryActivity.IMAGE);
        _image = (ImageView) findViewById(R.id.image_full);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+ InitActivity.TMPDIR+imageUri;

        Bitmap bit = MyUtils.decodeSampledBitmapFromFile(path,500,500);
        _image.setImageBitmap(bit);

        _mScaleDetector = new ScaleGestureDetector(this, new ImageScaleGestureListener(_image));
        _mDetector = new GestureDetectorCompat(this,this);

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        _mScaleDetector.onTouchEvent(event);
        _mDetector.onTouchEvent(event);
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        float offsetX = _image.getX() - distanceX;
        float offsetY = _image.getY() - distanceY;

        _image.setX(offsetX);
        _image.setY(offsetY);
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }


    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
