package com.bilgi.bilgitermproject2.app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.bilgi.bilgitermproject2.app.math.Complex;

import java.util.ArrayList;
import java.util.List;


public class GetCoordinatesActivity extends ActionBarActivity {

    public final static String EXTRA_COMPLEX_COORDINATES = "com.bilgi.bilgitermproject2.app.EXTRA_COMPLEX_COORDINATES";

    private ArrayList<Complex> complexCoordinates = new ArrayList<Complex>();
    private DrawingView drawingView;
    private FrameLayout frameLayout;
    private Button buttonCalculate;
    private int numberOfCoordinates = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_coordinates);
        drawingView = new DrawingView(this);

        frameLayout=(FrameLayout)findViewById(R.id.frameLayout);
        frameLayout.addView(drawingView);
        buttonCalculate =(Button)findViewById(R.id.buttonCalculate);
        buttonCalculate.setVisibility(View.INVISIBLE);
        Intent mIntent = getIntent();
        setNumberOfCoordinates(mIntent.getIntExtra(MainActivity.EXTRA_NUMBER_OF_COORDINATES, 0));

    }



    public void sendCoordinates(View view) {
        Intent intent = new Intent(this, CalculationActivity.class);
        intent.putExtra(EXTRA_COMPLEX_COORDINATES, complexCoordinates);
        startActivity(intent);
    }

    class DrawingView extends SurfaceView {

        private final SurfaceHolder surfaceHolder;
        private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        int numberOfTouches = 0;

        public DrawingView(Context context) {
            super(context);
            surfaceHolder = getHolder();
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.FILL);
        }


        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                if(numberOfTouches == numberOfCoordinates){
                    buttonCalculate.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), "Girdiginiz koordinat sayisi:" + numberOfTouches +". Lutfen Hesapla tusuna basiniz!",
                            Toast.LENGTH_LONG).show();
                    return false;
                }
                if (surfaceHolder.getSurface().isValid()) {
                    Canvas canvas = surfaceHolder.lockCanvas();
                    canvas.drawColor(Color.BLACK);
                    canvas.drawCircle(event.getX(), event.getY(), 5, paint);
                    canvas.drawText("  x:" + event.getX() + " y:" + event.getY(), event.getX(), event.getY(), paint);
                    surfaceHolder.unlockCanvasAndPost(canvas);
                    Complex complex = new Complex((double)event.getX(),(double)event.getY());
                    complexCoordinates.add(complex);
                    numberOfTouches ++;

                }
            }
            return false;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_get_coordinates, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void setNumberOfCoordinates(int numberOfCoordinates) {
        this.numberOfCoordinates = numberOfCoordinates;
    }
}
