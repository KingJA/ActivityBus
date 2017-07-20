package sample.kingja.activitybus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import kingja.activitybus.annotations.Passenger;
import sample.kingja.componentbus.R;


/**
 * Description:TODO
 * Create Time:2017/7/19 13:06
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

public class ThirdActivity extends AppCompatActivity {
    @Passenger
    public int status;
    @Passenger
    public String message;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ThirdActivityBus.register(this);
    }

}
