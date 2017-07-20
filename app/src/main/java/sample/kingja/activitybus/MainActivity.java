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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < 2; i++) {
            personList.add(new Person("List" + i));
            personSet.add(new Person("Set" + i));
            personMap.put(i, new Person("Map" + i));
        }
    }

    public void goSecondActivity(View view) {
        SecondActivityBus.goActivity(this, 1, true, (byte) 1, (short) 1, 1L, 'A', 1f, 1d, "Hello", new Person("Entity"),
                personList, personSet, personMap);
    }
}
