1.���ɿ�

2.�����������
��:��ȡ����������ַ
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
            LogUtil.e("onResponse��complete");
            LogUtil.e("ʹ��okhttp��������ɹ�====" + response);
            //��������
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
            LogUtil.e("ʹ��okhttp��������ʧ��====" + e.getMessage());

        }


    }
