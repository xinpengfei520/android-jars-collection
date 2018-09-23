1.集成库

2.联网请求代码
例:获取新闻中心网址
        mNewsCenterUrl = Constans.NEWS_CENTER_URL;
 OkHttpUtils
                .get()
                .url(mNewsCenterUrl)
                .id(100)
                .build()
                .execute(new MyStringCallback());

public class MyStringCallback extends StringCallback {


        @Override
        public void onResponse(String response, int id) {
            LogUtil.e("onResponse：complete");
            LogUtil.e("使用okhttp联网请求成功====" + response);
            //缓存数据
//            CacheUtils.putString(context, Constans.NEWS_CENTER_URL, response);
            processedData(response);


            switch (id) {
                case 100:
                    Toast.makeText(context, "http", Toast.LENGTH_SHORT).show();
                    break;
                case 101:
                    Toast.makeText(context, "https", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            e.printStackTrace();
            LogUtil.e("使用okhttp联网请求失败====" + e.getMessage());

        }


    }
