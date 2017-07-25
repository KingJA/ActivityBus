package sample.kingja.activitybus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;
import java.util.Map;
import java.util.Set;

import kingja.activitybus.annotations.ActivityBus;
import kingja.activitybus.annotations.RequestParam;
import sample.kingja.componentbus.R;


/**
 * Description:TODO
 * Create Time:2017/7/19 13:06
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@ActivityBus(requestCode = 100)
public class SecondActivity extends AppCompatActivity {
    @RequestParam
    public int aInt;
    @RequestParam
    public boolean aBoolean;
    @RequestParam
    public byte aByte;
    @RequestParam
    public short aShort;
    @RequestParam
    public long aLong;
    @RequestParam
    public char aChar;
    @RequestParam
    public float aFloat;
    @RequestParam
    public double aDouble;
    @RequestParam
    public String aString;
    @RequestParam
    public Person aPerson;
    @RequestParam
    public List<Person> aPersons;
    @RequestParam
    public Set<Person> aSet;
    @RequestParam
    public Map<Integer, Person> aMap;
//    @RequestParam
//    public String[] aStringArray;
//    @RequestParam
//    public boolean[] aBooleanArray;
//    @RequestParam
//    public byte[] aByteArray;
//    @RequestParam
//    public char[] aCharArray;
//    @RequestParam
//    public short[] aShortArray;
//    @RequestParam
//    public int[] aIntArray;
//    @RequestParam
//    public long[] aLongArray;
//    @RequestParam
//    public float[] aFloatArray;
//    @RequestParam
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
