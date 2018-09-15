#xUtils网络请求步骤

https://github.com/wyouflf/xUtils

https://github.com/wyouflf/xUtils3

1.代码示例

private void initNewsData() {

        topUrl = Constants.BASE_URL + "top" + Constants.KEY;

        RequestParams params = new RequestParams(topUrl);
        params.setConnectTimeout(5000);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("onSuccess==" + result);
                //把数据缓存到本地
                CacheUtils.putString(context, topUrl, result);
                processData(result);
                //发送消息：
                handler2.sendEmptyMessage(MESSAGE_RESULT_OK);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("onError==" + ex.getMessage());
                //联网失败--> 发送消息
                handler2.sendEmptyMessage(MESSAGE_RESULT_NO);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.e("onCancelled==" + cex.getMessage());
            }

            @Override
            public void onFinished() {
                LogUtil.e("onFinished==");
            }
        });

    }

2.MyApplication中初始化

  //初始化xUtils  
  x.Ext.init(this);  
  x.Ext.setDebug(true);

3.