package valentinbourgeois.miniprojet.view;

import android.util.Log;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;


public class ImageScaleGestureListener implements ScaleGestureDetector.OnScaleGestureListener{
    private ImageView _image;
    private double _startX;
    private double _startY;
    private double _endX;
    private double _endY;

    public ImageScaleGestureListener(ImageView image) {
        _image = image;
    }
    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        _startX = detector.getCurrentSpanX();
        _startY = detector.getCurrentSpanY();
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {
        _endX = detector.getCurrentSpanX();
        _endY = detector.getCurrentSpanY();
        float dStart = (float) Math.sqrt(_startX * _startX + _startY * _startY);
        float dEnd = (float) Math.sqrt(_endX * _endX + _endY * _endY);

        float coef = 1;
        if(dStart != 0)
            coef = dEnd/dStart;

        Log.d("CALC",dStart+" "+dEnd+" "+coef);

        _image.setScaleX(_image.getScaleX()*coef);
        _image.setScaleY(_image.getScaleY()*coef);
    }
}
