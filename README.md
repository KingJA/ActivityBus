# ActivityBus
A smart library for transfering data between Activities. CLICK THE ***STAR***  if it's useful for you.

## Preview


## Supported Types
* Base type : 'boolean','byte','char','short','int','long','float','double'
* Base array type : 'boolean[]'','byte[]'','char[]'','short[]'','int[]'','long[]'','float[]'','double[]''
* Container : 'List','Set','Map'
* Serializable : entity implements the Serializable

## Gradle
```groovy
dependencies {
    compile 'com.kingja.activitybus:activitybus:1.1.1'
    annotationProcessor 'com.kingja.activitybus:activitybus-compiler:1.1.1'
}
```

## Usage
### step 1

declare **@Passenger** on the variables that your want to transfer,declare **@ActivityBus** on the class and set the
requestCode if your want to call **startActivityForResult()**

```java
@ActivityBus(requestCode = 100)
public class SecondActivity extends AppCompatActivity {
    @Passenger
    public int aInt;
    @Passenger
    public String aString;
    @Passenger
    public Person aPerson;
    @Passenger
    public List<Person> aPersons;
}
```
### step 2
After compiling the project, it will create a generate java File named like SecondActivityBus.java(ToActivity name+Bus),then call **register()** before you useing the data

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
### step 3
call **goActivity()** to transfer the variables in the 'FromActivity',here is MainActivity.

```java
public class MainActivity extends AppCompatActivity {
...
SecondActivityBus.goActivity(this,1,"Hello",new Person("Entity"),personList);
...
}
```

## Changelog

**v1.1.1**
- Initial release .

## Contact me
Any questions,Welcome to contact me.
* [Blog](http://www.jianshu.com/u/8a1a8ed656e8)
* email:kingjavip@gmail.com
* QQ:87049319

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
