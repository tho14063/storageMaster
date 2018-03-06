package com.example.storagemaster.storagemaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;


/***************************************************
 * SLIDE BAR ACTIVITY created by James
 * creates a slider bar to change the number of items
 * in the inventory.
 **************************************************/
public class SlideBarActivity extends AppCompatActivity{

    private int seekValue;
    private SeekBar seek;
    private EditText numItems;

    private Button save;
    private Button more;
    private Button less;

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_bar);

        // JAMES - gets the size of the screen and then sets it to the desired size.
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (height * .4));

        //initialize the variables.
        this.position = Integer.parseInt(getIntent().getStringExtra("position"));
        seekValue = MainActivity.//user.inventory.get(0).items.get(position).getQuantity();
                itemList.get(position).getQuantity();
        seek = new SeekBar(this);
        seek = findViewById(R.id.seekBar);
        seek.setProgress(seekValue);



        numItems = new EditText(this);
        numItems = findViewById(R.id.numItems);

        numItems.setText("" + seekValue);

        more = findViewById(R.id.moreButton);
        less = findViewById(R.id.lessButton);
        save = findViewById(R.id.sliderSave);

        // set up the more button
        more.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                seekValue++;
                numItems.setText("" + seekValue);
                seek.post(new Runnable(){
                    @Override
                    public void run() {
                        seek.setProgress(seekValue);
                    }
                });
            }
        });

        // set up the less button
        less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seekValue--;
                numItems.setText("" + seekValue);
                seek.post(new Runnable(){
                    @Override
                    public void run() {
                        seek.setProgress(seekValue);
                    }
                });
            }
        });

        numItems.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                seekValue = Integer.parseInt(numItems.getText().toString());
                seek.post(new Runnable(){
                    @Override
                    public void run() {
                        seek.setProgress(seekValue);
                    }
                });
            }
        });

        //set up the seek bar. Needs to set the value in the text box equal to the value of the bar
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekValue = i;
                numItems.setText("" + seekValue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        save.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //set seek value and change the item's quantity equal to it
                seekValue = Integer.parseInt(numItems.getText().toString());
                MainActivity.//user.inventory.get(0).items.get(position).setQuantity(seekValue);
                        itemList.get(position).setQuantity(seekValue);

                MainActivity.adapter.notifyDataSetChanged();
                finish();
            }
        });
    }
}
