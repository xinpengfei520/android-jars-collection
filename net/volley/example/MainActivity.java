package com.example.day;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	//设置路径
		public static final String path="http://192.168.10.7:8080/Servlet/ZhuCe";
	private EditText et_username;
	private EditText et_pwd;
	private String username;
	private String pwd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_username = (EditText) findViewById(R.id.et_username);
		et_pwd = (EditText)findViewById(R.id.et_pwd);
	}
	//方式二：使用HttpClient方式——实现登录（get请求）
	public void testClientGet(View view){
		//获取输入内容
				username = et_username.getText().toString();
				pwd = et_pwd.getText().toString();
		new Thread(){
			public void run(){
				//1.创建请求对象
				try {
					HttpGet hg=new HttpGet(path+"?username="+URLEncoder.encode(username,"utf-8")+"&pwd="+URLEncoder.encode(pwd,"utf-8"));
					//2.创建客户端对象
					HttpClient hc=new DefaultHttpClient();
					//3.执行请求对象
					HttpResponse hr = hc.execute(hg);
					//4.根据相应对象调用方法获取响应
					final int code = hr.getStatusLine().getStatusCode();
					//5.处理响应
					if(code==200){//响应成功
						//读取
						InputStream content = hr.getEntity().getContent();
						final String st = StreamUtils.streamToString2(content);
						runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								Toast.makeText( MainActivity .this, "请求成功!"+st, 0).show();
							}
						});
					}else{//响应失败
						runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								Toast.makeText( MainActivity .this, "请求失败!"+code, 0).show();
							}
						});
					}
				} catch (final Exception e) {
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							Toast.makeText( MainActivity .this, "网页链接错误！原因："+e.getMessage(), 0).show();
						}
					});
					e.printStackTrace();
				}
				
			}
		}.start();
	}
	//方式二：使用HttpClient方式——实现登录（post请求）
public void testClientPost(View view){
	//获取输入内容
	username = et_username.getText().toString();
	pwd = et_pwd.getText().toString();
	new Thread(){
		public void run(){
			//1.创建请求对象
			try {
				HttpPost hp=new HttpPost(path);
				List< NameValuePair> parameters=new ArrayList<NameValuePair>();
				parameters.add(new BasicNameValuePair("username=",URLEncoder.encode(username,"utf-8")));
				parameters.add(new BasicNameValuePair("pwd=",URLEncoder.encode(pwd,"utf-8")));
				HttpEntity entity = new UrlEncodedFormEntity(parameters);
				hp.setEntity(entity);
				
				//2.创建客户端对象
				HttpClient hc=new DefaultHttpClient();
				//3.执行请求对象
				HttpResponse hr = hc.execute(hp);
				//4.根据相应对象调用方法获取响应
				final int code = hr.getStatusLine().getStatusCode();
				//5.处理响应
				if(code==200){//响应成功
					//读取
					InputStream content = hr.getEntity().getContent();
					final String st = StreamUtils.streamToString2(content);
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							Toast.makeText( MainActivity .this, "请求成功!"+st, 0).show();
						}
					});
				}else{//响应失败
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							Toast.makeText( MainActivity .this, "请求失败!"+code, 0).show();
						}
					});
				}
			} catch (final Exception e) {
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						Toast.makeText( MainActivity .this, "网页链接错误！原因："+e.getMessage(), 0).show();
					}
				});
				e.printStackTrace();
			}
			
		}
	}.start();
		
	}
//方式一：测试HttpUrlConnection——get请求
	public void testConnectionGet(View view){
		//获取输入内容
		username = et_username.getText().toString();
		pwd = et_pwd.getText().toString();
		new Thread(){
			public void run(){
				
					
				
					try {
						//1.创建URL对象
						URL	url = new URL(path+"?username="+URLEncoder.encode(username,"utf-8")+"&pwd="+URLEncoder.encode(pwd,"utf-8"));
					
					//2.打开连接
					HttpURLConnection uc = (HttpURLConnection) url.openConnection();
					//3.设置请求参数
					uc.setRequestMethod("GET");//请求方法
					uc.setConnectTimeout(5000);//连接时间
					uc.setReadTimeout(5000);//响应时间
					//4.获取响应
					final int code = uc.getResponseCode();
					if(code==200){
						//5.处理响应
						//读取流
						InputStream stream = uc.getInputStream();
						//读取
						final String st = StreamUtils.streamToString2(stream);
						//更新ui
						runOnUiThread( new Runnable() {
							
							public void run() {
								Toast.makeText(MainActivity.this, "请求成功!"+st, 0).show();
							}
						});
					}else{
						runOnUiThread( new Runnable() {
							
							public void run() {
								Toast.makeText(MainActivity.this, "请求失败!错误码："+code, 0).show();
							}
						});
					}
					
					} catch (final Exception e) {
						runOnUiThread( new Runnable() {
							
							public void run() {
								Toast.makeText(MainActivity.this, "网络连接错误，错误原因："+e.getMessage(), 0).show();
							}
						});
						e.printStackTrace();
					}
			}
		}.start();
	}
	//方式一：测试HttpUrlConnection——poast请求
	public void testConnectionPost(View view){
		//获取输入内容
		username = et_username.getText().toString();
		pwd = et_pwd.getText().toString();
		new Thread(){
			private String content;

			public void run(){
				
				try {
					//1.创建URL对象
					
					URL url = new URL(path);
					//2.打开连接
					HttpURLConnection uc = (HttpURLConnection) url.openConnection();
					//3.设置参数
					//请求方法
					uc.setRequestMethod("POST");
					//连接时间
					uc.setConnectTimeout(2000);
					//响应时间
					uc.setReadTimeout(2000);
					//4.获取请求数据
					OutputStream os = uc.getOutputStream();
					os.write(("username="+URLEncoder.encode(username,"utf-8")+"&pwd="+URLEncoder.encode(pwd,"utf-8")).getBytes());
					
					//5.获取响应
					final int code = uc.getResponseCode();
					//6.处理响应
					if(code==200){//响应成功
						//读取
						InputStream stream = uc.getInputStream();
						content = StreamUtils.streamToString2(stream);
						//更新ui
						runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								Toast.makeText(MainActivity.this,"请求成功!"+ content, 0).show();
							}
						});
						
					}else{//响应失败
						//更新ui
						runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								Toast.makeText(MainActivity.this,"请求失败!请求码："+ code, 0).show();
							}
						});
					}
				} catch (final Exception e) {
					//更新ui
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							Toast.makeText(MainActivity.this,"网络连接错误！错误码："+ e.getMessage(), 0).show();
						}
					});
					e.printStackTrace();
				}
				
			}
		}.start();
		
	}
}
