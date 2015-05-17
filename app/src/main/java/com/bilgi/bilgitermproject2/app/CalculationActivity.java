package com.bilgi.bilgitermproject2.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.bilgi.bilgitermproject2.app.math.Complex;

import java.util.ArrayList;


public class CalculationActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CalculationResultView(this));

        ArrayList<Complex> complexCoordinates = (ArrayList<Complex>) getIntent().getSerializableExtra(GetCoordinatesActivity.EXTRA_COMPLEX_COORDINATES);



        Toast.makeText(getApplicationContext(), "coor:"+complexCoordinates.get(0).getImag(),
                Toast.LENGTH_LONG).show();
    }

    private static class CalculationResultView extends View {
        private Paint   mPaint = new Paint();
        private float[] mPts;

        private static final float SIZE = 300;
        private static final int SEGS = 32;
        private static final int X = 0;
        private static final int Y = 1;

        private void buildPoints() {
            final int ptCount = (SEGS + 1) * 2;
            mPts = new float[ptCount * 2];

            float value = 0;
            final float delta = SIZE / SEGS;
            for (int i = 0; i <= SEGS; i++) {
                mPts[i*4 + X] = SIZE - value;
                mPts[i*4 + Y] = 0;
                mPts[i*4 + X + 2] = 0;
                mPts[i*4 + Y + 2] = value;
                value += delta;
            }
        }

        public CalculationResultView(Context context) {
            super(context);

            buildPoints();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Paint paint = mPaint;

            canvas.translate(10, 10);

            canvas.drawColor(Color.WHITE);

            paint.setColor(Color.RED);
            paint.setStrokeWidth(0);
            canvas.drawLines(mPts, paint);

            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(3);
            canvas.drawPoints(mPts, paint);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calculation, menu);
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
}
