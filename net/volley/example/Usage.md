
# Usage:

### 1.郭霖的CSDN:

	http://blog.csdn.net/guolin_blog/article/details/17482165

### 2.StringRequest和JsonRequest的用法都是非常类似的，基本就是以下三步

	1. 创建一个RequestQueue对象。

	2. 创建一个Request对象。

	3. 将Request对象添加到RequestQueue里面。

### 3.获取一个RequestQueue对象

	RequestQueue mQueue = Volley.newRequestQueue(context); 

### 4.HTTP请求&请求JSON&请求图片

   4.1 StringRequest的用法

	StringRequest stringRequest = new StringRequest("http://www.baidu.com",  
                            new Response.Listener<String>() {  
                                @Override  
                                public void onResponse(String response) {  
                                    Log.d("TAG", response);  
                                }  
                            }, new Response.ErrorListener() {  
                                @Override  
                                public void onErrorResponse(VolleyError error) {  
                                    Log.e("TAG", error.getMessage(), error);  
                                }  
                            });  

   4.2 StringRequest的POST请求：我们只需要在StringRequest的匿名类中重写getParams()方法，在这里设置POST参数就可以了

	    StringRequest stringRequest = new StringRequest(Method.POST, url,  listener, errorListener) {  
          @Override  
          protected Map<String, String> getParams() throws AuthFailureError {  
            Map<String, String> map = new HashMap<String, String>();  
            map.put("params1", "value1");  
            map.put("params2", "value2");  
            return map;  
          }  
        };

   4.3 JsonRequest的用法

	    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://m.weather.com.cn/data/101010100.html", null,  
            new Response.Listener<JSONObject>() {  
                @Override  
                public void onResponse(JSONObject response) {  
                    Log.d("TAG", response.toString());  
                }  
            }, new Response.ErrorListener() {  
                @Override  
                public void onErrorResponse(VolleyError error) {  
                    Log.e("TAG", error.getMessage(), error);  
                }  
            });  

   4.4  ImageRequest的用法

	    ImageRequest imageRequest = new ImageRequest(  
            "http://developer.android.com/images/home/aw_dac.png",  
            new Response.Listener<Bitmap>() {  
                @Override  
                public void onResponse(Bitmap response) {  
                    imageView.setImageBitmap(response);  
                }  
            }, 0, 0, Config.RGB_565, new Response.ErrorListener() {  
                @Override  
                public void onErrorResponse(VolleyError error) {  
                    imageView.setImageResource(R.drawable.default_image);  
                }  
            });  
  
### 5.Android网络通信Volley框架源码浅析（一）

[https://blog.csdn.net/yuanzeyao/article/details/25837897](https://blog.csdn.net/yuanzeyao/article/details/25837897)
