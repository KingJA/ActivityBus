
ActivityBus
---
ActivityBus provides a simple annotation-based API,generates a helper class to transfer various types of data between Activities and from Activity to Fragment,in order to keep your code clean and your job efficient.

English | [中文](https://github.com/KingJA/ActivityBus/blob/master/README_CN.md)
![](https://github.com/KingJA/ActivityBus/blob/master/res/activitybus.png)
## Supported Types
* Base type : `boolean`,`byte`,`char`,`short`,`int`,`long`,`float`,`double`,`String`
* Base array type : `boolean[]`,`byte[]`,`char[]`,`short[]`,`int[]`,`long[]`,`float[]`,`double[]`,`String[]`
* Container : `List`,`Set`,`Map`
* Serializable : entity implements the Serializable

## Download
```groovy
dependencies {
    compile 'com.kingja.activitybus:activitybus:1.2.5'
    annotationProcessor 'com.kingja.activitybus:activitybus-compiler:1.2.5'
}
```

Getting Started
---
### Usage in Activity
###### Step 1 (Annotate)

* Annotate fields with **@RequestParam** for AndroidBus to transfer them to target Activity.
* Annotate class of target Activity with **@ActivityBus** and set the requestCode if your want to call **startActivityForResult()**

```java
@ActivityBus(requestCode = 100)
public class TargetActivity extends AppCompatActivity {
    @RequestParam
    public int aInt;
    @RequestParam
    public String aString;
    @RequestParam
    public Person aPerson;
    @RequestParam
    public List<Person> aPersons;
    ...
}
```

###### Step 2 (transfer data)
Upon compilation, ActivityBus generates a class as TargetActivityBus([Activity Name] + Bus),call **goActivity()** to transfer the data in the 'FromActivity',here is MainActivity.

```java
public class MainActivity extends AppCompatActivity {
...
TargetActivityBus.goActivity(this,1,"Hello",new Person("Entity"),personList);
...
}
```

###### Step 3 (get data)
Call **register()** before you useing the annotated fields.

```java
@ActivityBus(requestCode = 100)
public class TargetActivity extends AppCompatActivity {
...
@Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TargetActivityBus.register(this);
    }
...
}

```

### Usage in Fragment
Usage in **Fragment** is more simple than in Activity,it only needs 2 steps.
###### Step 1 (Annotate)

```java
public class TargetFragment extends Fragment {
    @RequestParam
    public int aInt;
    @RequestParam
    public String aString;
    ...
}
```

###### Step 2 (transfer data)

```java
public class MainActivity extends AppCompatActivity {
...
TargetFragment targetFragment = TargetFragmentBus.newInstance(1,"Hello");
getSupportFragmentManager().beginTransaction().add(R.id.fl_fragment, targetFragment).commit();
...
}
```


Changelog
---

**Version 1.1.1 (2017-07-20)**
- Initial release .

Contact me
---
Any questions,Welcome to contact me.
* [Blog](http://www.jianshu.com/u/8a1a8ed656e8)
* Email : kingjavip@gmail.com
* QQ : 87049319

License
---

    Copyright 2017 KingJA

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
