# ActivityBus
A smart library for transfering data between Activities. CLICK THE ***STAR***  if it's useful for you.

## Diagrammatize

ActivityBus provides a simple annotation-based API,generates a helper class to transfer various types of data,in order to keep your code clean and your job efficient.

## Supported Types
* Base type : `boolean`,`byte`,`char`,`short`,`int`,`long`,`float`,`double`,`String`
* Base array type : `boolean[]`,`byte[]`,`char[]`,`short[]`,`int[]`,`long[]`,`float[]`,`double[]`,`String[]`
* Container : `List`,`Set`,`Map`
* Serializable : entity implements the Serializable

## Download
```groovy
dependencies {
    compile 'com.kingja.activitybus:activitybus:1.1.1'
    annotationProcessor 'com.kingja.activitybus:activitybus-compiler:1.1.1'
}
```

## Getting Started
### Activity step 1

* Annotate fields with **@RequestParam** for AndroidBus to transfer them to target Activity.
* Annotate class of target Activity with **@ActivityBus** and set the requestCode if your want to call **startActivityForResult()**

```java
@ActivityBus(requestCode = 100)
public class SecondActivity extends AppCompatActivity {
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
### Activity step 2
Upon compilation, ActivityBus generates a class as SecondActivityBus([Activity Name] + Bus),then call **register()** before you useing the variables

```java
@ActivityBus(requestCode = 100)
public class SecondActivity extends AppCompatActivity {
...
@Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        SecondActivityBus.register(this);
    }
...
}

```
### Activity step 3
call **goActivity()** to transfer the variables in the 'FromActivity',here is MainActivity.

```java
public class MainActivity extends AppCompatActivity {
...
SecondActivityBus.goActivity(this,1,"Hello",new Person("Entity"),personList);
...
}
```


### Fragment step 1

```java
public class TargetFragment extends Fragment {
    @RequestParam
    public int aInt;
    @RequestParam
    public String aString;
    ...
}
```

### Fragment step 2

```java
public class MainActivity extends AppCompatActivity {
...
TargetFragment targetFragment = TargetFragmentBus.newInstance(1,"Hello");
getSupportFragmentManager().beginTransaction().add(R.id.fl_fragment, targetFragment).commit();
...
}
```


## Changelog

**Version 1.1.1 (2017-07-20)**
- Initial release .

## Contact me
Any questions,Welcome to contact me.
* [Blog](http://www.jianshu.com/u/8a1a8ed656e8)
* Email : kingjavip@gmail.com
* QQ : 87049319

## License

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
