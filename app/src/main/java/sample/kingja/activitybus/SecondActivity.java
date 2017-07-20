package sample.kingja.activitybus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;
import java.util.Map;
import java.util.Set;

import kingja.activitybus.annotations.ActivityBus;
import kingja.activitybus.annotations.Passenger;
import sample.kingja.componentbus.R;


/**
 * Description:TODO
 * Create Time:2017/7/19 13:06
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@ActivityBus(requestCode = 100)
public class SecondActivity extends AppCompatActivity {
    @Passenger
    public int aInt;
    @Passenger
    public boolean aBoolean;
    @Passenger
    public byte aByte;
    @Passenger
    public short aShort;
    @Passenger
    public long aLong;
    @Passenger
    public char aChar;
    @Passenger
    public float aFloat;
    @Passenger
    public double aDouble;
    @Passenger
    public String aString;
    @Passenger
    public Person aPerson;
    @Passenger
    public List<Person> aPersons;
    @Passenger
    public Set<Person> aSet;
    @Passenger
    public Map<Integer, Person> aMap;
//    @Passenger
//    public String[] aStringArray;
//    @Passenger
//    public boolean[] aBooleanArray;
//    @Passenger
//    public byte[] aByteArray;
//    @Passenger
//    public char[] aCharArray;
//    @Passenger
//    public short[] aShortArray;
//    @Passenger
//    public int[] aIntArray;
//    @Passenger
//    public long[] aLongArray;
//    @Passenger
//    public float[] aFloatArray;
//    @Passenger
//    public double[] aDoubleArray;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        SecondActivityBus.register(this);
        ((TextView) findViewById(R.id.tv_booean)).setText("Boolean : " + aBoolean);
        ((TextView) findViewById(R.id.tv_char)).setText("Char : " + aChar);
        ((TextView) findViewById(R.id.tv_byte)).setText("Byte : " + aByte);
        ((TextView) findViewById(R.id.tv_short)).setText("Short : " + aShort);
        ((TextView) findViewById(R.id.tv_int)).setText("Int : " + aInt);
        ((TextView) findViewById(R.id.tv_long)).setText("Long : " + aLong);
        ((TextView) findViewById(R.id.tv_float)).setText("Float : " + aFloat);
        ((TextView) findViewById(R.id.tv_double)).setText("Double : " + aDouble);
        ((TextView) findViewById(R.id.tv_string)).setText("String : " + aString);
        ((TextView) findViewById(R.id.tv_object)).setText("Entity : " + aPerson.toString());
        ((TextView) findViewById(R.id.tv_list)).setText("List : " + aPersons.toString());
        ((TextView) findViewById(R.id.tv_set)).setText("Set : " + aSet.toString());
        ((TextView) findViewById(R.id.tv_map)).setText("Map : " + aMap.toString());
    }

}
