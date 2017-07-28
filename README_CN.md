
ActivityBus
---
ActivtyBus基于编译时注解，自动生成帮助类，实现Activity和Activityt之间，Activity和Fragment之间的一键数值传值,避免了重写模板代码，提高开发效率。

中文 | [English](https://github.com/KingJA/ActivityBus/blob/master/README.md)
![](https://github.com/KingJA/ActivityBus/blob/master/res/activitybus.png)
## 支持的传值类型
* Base type : `boolean`,`byte`,`char`,`short`,`int`,`long`,`float`,`double`,`String`
* Base array type : `boolean[]`,`byte[]`,`char[]`,`short[]`,`int[]`,`long[]`,`float[]`,`double[]`,`String[]`
* Container : `List`,`Set`,`Map`
* Serializable : 实现 Serializable 接口的所有实体

## 下载
```groovy
dependencies {
    compile 'com.kingja.activitybus:activitybus:1.2.5'
    annotationProcessor 'com.kingja.activitybus:activitybus-compiler:1.2.5'
}
```

使用
---
### 在Activity间传值
###### 第一步：添加注解

@RequestParam：在目标Activity的需要传值的成员变量上添加该注解。
@ActivityBus：如果你需要调用startActivityForResult()，在目标Activity 上添加该注解，并设置requestCode。

```java
@ActivityBus(requestCode = 100)
public class TargetActivity extends AppCompatActivity {
    @RequestParam
    public int age;
    @RequestParam
    public String name;
    @RequestParam
    public Person person;
    ...
}
```

###### 第二步：传递数据
编译以后，ActivityBus会自动生成代理类，如TargetActivityBus，名称格式为【目标Actiity】+Bus，就是说，把你的Activty变成了一辆公交车，可以带客了，乘客就是你要传输的数据。之后调用代理类的goActivity()传入所需的数据即可。

```java
public class MainActivity extends AppCompatActivity {
    ...
    TargetActivityBus.goActivity(this,1,"Hello",new Person("Entity"));
    ...
}
```

###### 第三步：在目标Activity中进行注册
在目标Activity中调用register()，相当于告诉公交车，我要到这来。乘客到这站就可以下车了。接下来你就可以对乘客do anything了。
```java
@ActivityBus(requestCode = 100)
public class TargetActivity extends AppCompatActivity {
    @RequestParam
    public int age;
    @RequestParam
    public String name;
    @RequestParam
    public Person person;
...
@Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ...
        TargetActivityBus.register(this);
        tv_name.setText("My name is"+name);
    }
...
}

```

### Activty和Fragment间传值
在Fragment中使用更简单，只需要两步。
###### 第一步：添加注解

```java
public class TargetFragment extends Fragment {
    @RequestParam
    public int aInt;
    @RequestParam
    public String aString;
    ...
}
```

###### 第二步：传递数据
调用Fragment代理类的newInstance()方法进行传值。
```java
public class MainActivity extends AppCompatActivity {
    ...
    TargetFragment targetFragment = TargetFragmentBus.newInstance(1,"Hello");
    getSupportFragmentManager().beginTransaction().add(R.id.fl_fragment, targetFragment).commit();
    ...
}
```


更新日志
---

**Version 1.1.1 (2017-07-20)**
- Initial release .

联系我
---
Any questions,Welcome to contact me.
* [Blog](http://www.jianshu.com/u/8a1a8ed656e8)
* Email : kingjavip@gmail.com
* QQ : 87049319

开源协议
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
