package sample.kingja.activitybus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Map;
import java.util.Set;

import kingja.activitybus.annotations.RequestParam;
import sample.kingja.componentbus.R;

/**
 * Description：TODO
 * Create Time：2017/7/2522:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class TargetFragment extends Fragment {
    @RequestParam
    protected int aInt;
    @RequestParam
    boolean aBoolean;
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
    @RequestParam
    public String[] aStringArray;
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
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_target, null);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((TextView) rootView.findViewById(R.id.tv_booean)).setText("Boolean : " + aBoolean);
        ((TextView) rootView.findViewById(R.id.tv_char)).setText("Char : " + aChar);
        ((TextView) rootView.findViewById(R.id.tv_byte)).setText("Byte : " + aByte);
        ((TextView) rootView.findViewById(R.id.tv_short)).setText("Short : " + aShort);
        ((TextView) rootView.findViewById(R.id.tv_int)).setText("Int : " + aInt);
        ((TextView) rootView.findViewById(R.id.tv_long)).setText("Long : " + aLong);
        ((TextView) rootView.findViewById(R.id.tv_float)).setText("Float : " + aFloat);
        ((TextView) rootView.findViewById(R.id.tv_double)).setText("Double : " + aDouble);
        ((TextView) rootView.findViewById(R.id.tv_string)).setText("String : " + aString);
        ((TextView) rootView.findViewById(R.id.tv_object)).setText("Entity : " + aPerson.toString());
        ((TextView) rootView.findViewById(R.id.tv_list)).setText("List : " + aPersons.toString());
        ((TextView) rootView.findViewById(R.id.tv_set)).setText("Set : " + aSet.toString());
        ((TextView) rootView.findViewById(R.id.tv_map)).setText("Map : " + aMap.toString());
        ((TextView) rootView.findViewById(R.id.tv_stringArray)).setText("StringArray : " + printArray(aStringArray));
    }

    private String printArray(String[] stringArray) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < stringArray.length; i++) {
            sb.append(stringArray[i]);
            if (i != stringArray.length - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
