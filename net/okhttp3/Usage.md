
# OKHttp的使用

## 1.OKHttp简介

### 1.1_简介 

OKHttp是一款高效的HTTP客户端，支持连接同一地址的链接共享同一个socket，通过连接池来减小响应延迟，还有透明的GZIP压缩，请求缓存等优势，其核心主要有路由、连接协议、拦截器、代理、安全性认证、连接池以及网络适配，拦截器主要是指添加，移除或者转换请求或者回应的头部信息

这个库也是square开源的一个网络请求库(okhttp内部依赖okio)。现在已被Google使用在Android源码上了，可见其强大。

关于网络请求库，现在应该还有很多人在使用android-async-http。他内部使用的是HttpClient，但是Google在6.0版本里面删除了HttpClient相关API，可见这个库现在有点过时了。

### 1.2_下载地址

```http://square.github.io/okhttp/```

### 1.3_OKHttp主要功能

1、联网请求文本数据
2、大文件下载
3、大文件上传
4、请求图片
2_原生OKHttp的Get和Post请求小案例
参照网址：

```http://square.github.io/okhttp/```

1.1_小案例的布局和代码
请求网络测试地址：

http://api.m.mtime.cn/PageSubArea/TrailerList.api

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:orientation="vertical"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.atguigu.okhttpsample.MainActivity">

    <Button
        android:id="@+id/btn_get"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="get请求" />
    <Button
        android:id="@+id/btn_post"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="post请求" />

    <TextView
        android:id="@+id/tv_result"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="显示请求数据" />
</LinearLayout>
```

1.2_点击事件

```
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    btn_get = (Button) findViewById(R.id.btn_get);
    btn_post = (Button) findViewById(R.id.btn_post);
    tv_result = (TextView) findViewById(R.id.tv_result);
    //设置点击事件
    btn_get.setOnClickListener(this);
    btn_post.setOnClickListener(this);
}

@Override
public void onClick(View v) {
    switch (v.getId()){
        case R.id.btn_get:
            getDataFromByGet();
            break;
        case R.id.btn_post:
            getDataFromByPost();
            break;
    }
}
```

1.3_OKHttp的get请求

```
private void getDataFromByGet() {

    new Thread(){
        @Override
        public void run() {
            super.run();
            try {
                String resutl = get("http://api.m.mtime.cn/PageSubArea/TrailerList.api");
                Message msg = Message.obtain();
                msg.what = GET;
                msg.obj = resutl;
                handler.sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }.start();
}
```

/**
 * get请求
 * @param url
 * @return
 * @throws IOException
 */

private  String get(String url) throws IOException {
    Request request = new Request.Builder()
            .url(url)
            .build();
    Response response = client.newCall(request).execute();
    return response.body().string();
}

1.4_OKHttp的post请求

```
private void getDataFromByPost() {
    new Thread(){
        @Override
        public void run() {
            super.run();

            String resutl = null;
            try {
                resutl = post("http://api.m.mtime.cn/PageSubArea/TrailerList.api","");
                Message msg = Message.obtain();
                msg.what = POST;
                msg.obj = resutl;
                handler.sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }.start();
}
```

/**
 * post请求
 * @param url
 * @param json
 * @return
 * @throws IOException
 */
private  String post(String url, String json) throws IOException {
    RequestBody body = RequestBody.create(JSON, json);
    Request request = new Request.Builder()
            .url(url)
            .post(body)
            .build();
    Response response = client.newCall(request).execute();
    return response.body().string();
}


1.5_在Handler中显示数据
/**
 * get请求
 */
private static final int GET = 1;
/**
 * post请求
 */
private static final int POST = 2;
private Button btn_get,btn_post;
private TextView tv_result;
private OkHttpClient client = new OkHttpClient();
private Handler handler = new Handler(){
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what){
            case GET://get请求
                tv_result.setText(msg.obj.toString());
                break;
            case POST://post请求
                tv_result.setText(msg.obj.toString());
                break;
        }
    }
};

3_第三方封装好的OKHttp库-okhttp-utils
1_okhttp-utils简介

1_下载并且运行案例
https://github.com/hongyangAndroid/okhttp-utils

2_解决报错
在sample-okhttp中的build.gradle文件中
如下配置

allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}


2_把okhttp-utils集成到案例中
1_关联库
 
compile project(':okhttputils')


2_解决报错
 因为okhttputils库里面本身就有okhttp库和okio库，需要注释掉

dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    testCompile 'junit:junit:4.12'
    compile 'com.android.support:appcompat-v7:23.3.0'
//    compile files('libs/okhttp-3.4.1.jar')
//    compile files('libs/okio-1.9.0.jar')
    compile project(':okhttputils')
}



3_使用okhttp-utils请求文本
get请求
public void getDataByOkhttputils()
{
    String url = "http://www.zhiyun-tech.com/App/Rider-M/changelog-zh.txt";
    url="http://api.m.mtime.cn/PageSubArea/TrailerList.api";
    OkHttpUtils
            .get()
            .url(url)
            .id(100)
            .build()
            .execute(new MyStringCallback());
}



Post请求
public void getDataByOkhttputils()
{
    String url = "http://www.zhiyun-tech.com/App/Rider-M/changelog-zh.txt";
    url="http://api.m.mtime.cn/PageSubArea/TrailerList.api";
    OkHttpUtils
            .post()
            .url(url)
            .id(100)
            .build()
            .execute(new MyStringCallback());
}


4_使用okhttp-utils文件下载
1_布局文件
<Button
    android:id="@+id/btn_downloadFile"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="下载文件" />



<ProgressBar
    style="?android:progressBarStyleHorizontal"
    android:layout_gravity="bottom"
    android:id="@+id/mProgressBar"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />



2_下载文件代码

/**
 * 下载文件
 */
public void downloadFile()
{
    String url = "http://vfx.mtime.cn/Video/2016/07/24/mp4/160724154733643806.mp4";
    OkHttpUtils//
            .get()//
            .url(url)//
            .build()//
            .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "160724154733643806.mp4")//
            {

                @Override
                public void onBefore(Request request, int id) {
                }

                @Override
                public void inProgress(float progress, long total, int id) {
                    mProgressBar.setProgress((int) (100 * progress));
                    Log.e(TAG, "inProgress :" + (int) (100 * progress));
                }

                @Override
                public void onError(Call call, Exception e, int id) {
                    Log.e(TAG, "onError :" + e.getMessage());
                }

                @Override
                public void onResponse(File file, int id) {
                    Log.e(TAG, "onResponse :" + file.getAbsolutePath());
                }
            });
}


3_记得加权限

<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>


5_文件上传
1_支持文件上传服务器的搭建


2_文件上传

/**
 * 文件上传
 */
public void multiFileUpload()
{
    String mBaseUrl = "http://192.168.10.165:8080/FileUpload/FileUploadServlet";
    File file = new File(Environment.getExternalStorageDirectory(), "1.jpg");
    File file2 = new File(Environment.getExternalStorageDirectory(), "1.txt");
    if (!file.exists())
    {
        Toast.makeText(MainActivity.this, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
        return;
    }
    Map<String, String> params = new HashMap<>();
    params.put("username", "杨光福");
    params.put("password", "123");

    String url = mBaseUrl;
    OkHttpUtils.post()//
            .addFile("mFile", "messenger_01.png", file)//
            .addFile("mFile", "test1.txt", file2)//
            .url(url)
            .params(params)//
            .build()//
            .execute(new MyStringCallback());
}

6.使用okhttp-utils请求图片
1_请求单张图片
public void getImage(View view) {
    mTv.setText("");
    String url = "http://images.csdn.net/20150817/1.jpg";
    OkHttpUtils
            .get()//
            .url(url)//
            .tag(this)//
            .build()//
            .connTimeOut(20000)
            .readTimeOut(20000)
            .writeTimeOut(20000)
            .execute(new BitmapCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    mTv.setText("onError:" + e.getMessage());
                }

                @Override
                public void onResponse(Bitmap bitmap, int id) {
                    Log.e("TAG", "onResponse：complete");
                    mImageView.setImageBitmap(bitmap);
                }
            });
}



2_在列表中OKHttpActivity请求图片-布局文件
布局文件

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ListView
        android:id="@+id/listview"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>

</RelativeLayout>


3_请求数据

/**
 * 请求数据，如果有缓存先加载缓存数据
 */
private void getData() {
    //获取缓存数据
    String saveJson = CacheUtils.getString(OKHttpActivity.this, url);
    if (!TextUtils.isEmpty(saveJson)) {
        processData(saveJson);
    }
    //请求网络
    OkHttpUtils
            .get()
            .url(url)
            .id(108)
            .build()
            .execute(new MyStringCallback());
}


4.缓存数据

public class MyStringCallback extends StringCallback {
    @Override
    public void onBefore(Request request, int id) {
        setTitle("loading...");
    }

    @Override
    public void onAfter(int id) {
        setTitle("Sample-okHttp");
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        e.printStackTrace();
        Log.e("TAG", "onError:" + e.getMessage());
    }

    @Override
    public void onResponse(String response, int id) {
        Log.e("TAG", "onResponse：complete");

        switch (id) {
            case 100:
                Toast.makeText(OKHttpActivity.this, "http", Toast.LENGTH_SHORT).show();
                break;
            case 101:
                Toast.makeText(OKHttpActivity.this, "https", Toast.LENGTH_SHORT).show();
                break;
            case 108://列表中显示数据
                if (response != null) {
                    //缓存数据
                    CacheUtils.putString(OKHttpActivity.this, url, response);
                    //解析和显示数据
                    processData(response);
                }

                break;
        }
    }

    @Override
    public void inProgress(float progress, long total, int id) {
        Log.e("OkHttpActivity", "inProgress:" + progress);
    }
}



5_使用Android 自带API解析json数据
实例Bean类

public class DataBean {

    /**
     * id : 61651
     * movieName : 《奇异博士》中文版预告片
     * coverImg : http://img31.mtime.cn/mg/2016/07/25/143053.69987441.jpg
     * movieId : 108737
     * url : http://vfx.mtime.cn/Video/2016/07/24/mp4/160724154733643806_480.mp4
     * hightUrl : http://vfx.mtime.cn/Video/2016/07/24/mp4/160724154733643806.mp4
     * videoTitle : 奇异博士 中文版预告片
     * videoLength : 151
     * rating : -1
     * type : ["动作","冒险","奇幻","科幻"]
     * summary : 卷福"施展魔法 展开宏大幻境
     */

    private List<TrailersEntity> trailers;

    public void setTrailers(List<TrailersEntity> trailers) {
        this.trailers = trailers;
    }

    public List<TrailersEntity> getTrailers() {
        return trailers;
    }

    public static class TrailersEntity {
        private int id;
        private String movieName;
        private String coverImg;
        private int movieId;
        private String url;
        private String hightUrl;
        private String videoTitle;
        private int videoLength;
        private int rating;
        private String summary;
        private List<String> type;

        public void setId(int id) {
            this.id = id;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public void setCoverImg(String coverImg) {
            this.coverImg = coverImg;
        }

        public void setMovieId(int movieId) {
            this.movieId = movieId;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setHightUrl(String hightUrl) {
            this.hightUrl = hightUrl;
        }

        public void setVideoTitle(String videoTitle) {
            this.videoTitle = videoTitle;
        }

        public void setVideoLength(int videoLength) {
            this.videoLength = videoLength;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public void setType(List<String> type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public String getMovieName() {
            return movieName;
        }

        public String getCoverImg() {
            return coverImg;
        }

        public int getMovieId() {
            return movieId;
        }

        public String getUrl() {
            return url;
        }

        public String getHightUrl() {
            return hightUrl;
        }

        public String getVideoTitle() {
            return videoTitle;
        }

        public int getVideoLength() {
            return videoLength;
        }

        public int getRating() {
            return rating;
        }

        public String getSummary() {
            return summary;
        }

        public List<String> getType() {
            return type;
        }
    }
}

解析json数据

/**
 * 解析json数据
 *
 * @param response
 * @return
 */
private DataBean parsedJson(String response) {
    DataBean dataBean = new DataBean();
    try {
        JSONObject jsonObject = new JSONObject(response);
        JSONArray jsonArray = jsonObject.optJSONArray("trailers");
        if (jsonArray != null && jsonArray.length() > 0) {
            List<DataBean.TrailersEntity> trailers = new ArrayList<>();
            dataBean.setTrailers(trailers);
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObjectItem = (JSONObject) jsonArray.get(i);

                if (jsonObjectItem != null) {

                    DataBean.TrailersEntity mediaItem = new DataBean.TrailersEntity();

                    String movieName = jsonObjectItem.optString("movieName");//name
                    mediaItem.setMovieName(movieName);

                    String videoTitle = jsonObjectItem.optString("videoTitle");//desc
                    mediaItem.setVideoTitle(videoTitle);

                    String imageUrl = jsonObjectItem.optString("coverImg");//imageUrl
                    mediaItem.setCoverImg(imageUrl);

                    String hightUrl = jsonObjectItem.optString("hightUrl");//data
                    mediaItem.setHightUrl(hightUrl);

                    //把数据添加到集合
                    trailers.add(mediaItem);
                }
            }
        }

    } catch (JSONException e) {
        e.printStackTrace();
    }
    return dataBean;
}


6_设置适配器

设置适配器
/**
 * json数据解析和设置适配器
 *
 * @param saveJson
 */
private void processData(String saveJson) {
    DataBean dataBean = parsedJson(saveJson);
    List<DataBean.TrailersEntity> datas = dataBean.getTrailers();
    MyAdapter myAdapter = new MyAdapter(OKHttpActivity.this, datas);
    listview.setAdapter(myAdapter);
}



适配器代码

public class MyAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<DataBean.TrailersEntity> mDatas;

    public MyAdapter(Context context, List<DataBean.TrailersEntity> datas){
        this.mContext = context;
        this.mDatas = datas;
    }
    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHoder viewHoder;
        if(convertView ==null){
            convertView = View.inflate(mContext, R.layout.item,null);
            viewHoder = new ViewHoder();
            viewHoder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
            viewHoder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHoder.tv_desc = (TextView) convertView.findViewById(R.id.tv_desc);

            convertView.setTag(viewHoder);
        }else{
            viewHoder = (ViewHoder) convertView.getTag();
        }

        //根据position得到列表中对应位置的数据
        DataBean.TrailersEntity trailersEntity = mDatas.get(position);
        viewHoder.tv_name.setText(trailersEntity.getMovieName());
        viewHoder.tv_desc.setText(trailersEntity.getVideoTitle());
        //1.使用OKHttp请求图片
        OkHttpUtils
                .get()//
                .url(trailersEntity.getCoverImg())//
                .tag(this)//
                .build()//
                .connTimeOut(20000)
                .readTimeOut(20000)
                .writeTimeOut(20000)
                .execute(new BitmapCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id)
                    {
                        Log.e("TAG", "onError:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(Bitmap bitmap, int id)
                    {
                        Log.e("TAG", "onResponse：complete");
                        viewHoder.iv_icon.setImageBitmap(bitmap);
                    }
                });

        return convertView;
    }

    static class ViewHoder{
        ImageView iv_icon;
        TextView tv_name;
        TextView tv_desc;
    }
}


适配器布局

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="100dp"
    android:gravity="center_vertical">

    <RelativeLayout
        android:id="@+id/rl_image"
        android:layout_width="120dp"
        android:layout_height="80dp"
        android:layout_centerVertical="true">

        <ImageView
            android:id="@+id/iv_icon"
            android:layout_width="120dp"
            android:layout_height="80dp"
            android:layout_marginLeft="8dp"
            android:scaleType="fitXY"
            android:src="@drawable/video_default" />

        <ImageView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_alignParentBottom="true"
            android:layout_alignParentRight="true"
            android:layout_marginBottom="8dp"
            android:layout_marginRight="8dp"
            android:src="@drawable/center_collect_play" />
    </RelativeLayout>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_centerVertical="true"
        android:layout_marginLeft="8dp"
        android:layout_toRightOf="@id/rl_image"
        android:orientation="vertical">

        <TextView
            android:id="@+id/tv_name"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:singleLine="true"
            android:text="视频的名称"
            android:textColor="#000000"
            android:textSize="18sp" />

        <TextView
            android:id="@+id/tv_desc"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="8dp"
            android:singleLine="true"
            android:text="视频的描述"
            android:textColor="#55000000"
            android:textSize="18sp" />

    </LinearLayout>

</RelativeLayout>


4_其他库OkHttpUtils

封装了okhttp的网络框架，支持大文件上传下载，上传进度回调，下载进度回调，表单上传（多文件和多参数一起上传），链式调用，可以自定义返 回对象，支持Https和自签名证书，支持cookie自动管理，支持四种缓存模式缓存网络数据，支持301、302重定向，扩展了统一的上传管理和下载 管理功能

1_下载地址
https://github.com/jeasonlzy0216/OkHttpUtils
