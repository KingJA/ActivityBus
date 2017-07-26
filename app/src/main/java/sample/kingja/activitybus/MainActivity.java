package sample.kingja.activitybus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import sample.kingja.componentbus.R;

public class MainActivity extends AppCompatActivity {
    private List<Person> personList = new ArrayList<>();
    private Set<Person> personSet = new HashSet<>();
    private Map<Integer, Person> personMap = new HashMap<>();
    private String[] stringArray=new String[]{"Michael Jordan", "Kobe Bryant", "LeBron James"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < stringArray.length; i++) {
            personList.add(new Person(stringArray[i]));
            personSet.add(new Person(stringArray[i]));
            personMap.put(i, new Person(stringArray[i]));
        }
    }

    public void goTargetActivity(View view) {
        TargetActivityBus.goActivity(this, 1, true, (byte) 1, (short) 1, 1L, 'A', 1f, 1d, "Jordan", new Person
                ("Jordan"), personList, personSet, personMap,stringArray );
    }

    public void goTargetFragment(View view) {
        TargetFragment targetFragment = TargetFragmentBus.newInstance(1, true, (byte) 1, (short) 1, 1L, 'A', 1f, 1d,
                "Jordan", new Person("Jordan"), personList, personSet, personMap, stringArray);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_fragment, targetFragment).commit();
    }
}
