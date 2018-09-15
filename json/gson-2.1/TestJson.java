package com.example.gson;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import android.graphics.Paint.Join;
import android.test.AndroidTestCase;
//JSON 
public class TestJson extends AndroidTestCase{
	//json---java对象：
	public void test1() throws JSONException{
		String json="{\"id\":2, \"name\":\"大虾\", \"price\":12.3, \"imagePath\":\"http://192.168.10.165:8080/L05_Server/images/f1.jpg\"}";
		//创建JSONObject对象
		JSONObject obj=new JSONObject(json);
		
		//获取obj对象的值
		int id = obj.getInt("id");
		String name = obj.getString("name");
		double price = obj.getDouble("price");
		String path = obj.getString("imagePath");
		//封装JAVA对象
		Food f=new Food(id,name,price,path);
		//显示
		System.out.println(f);
	}
	//json----java集合
	public void testList() throws JSONException{
		String json="[{\"id\":2, \"name\":\"大虾\", \"price\":12.3, \"imagePath\":\"http://192.168.10.165:8080/L05_Server/images/f1.jpg\"},{\"id\":5, \"name\":\"大虾2\", \"price\":500.3, \"imagePath\":\"http://192.168.10.165:8080/L05_Server/images/f2.jpg\"}]";
		//创建JSONArray对象
		JSONArray array=new JSONArray(json);
		List<Food> list=new ArrayList<Food>();
	for(int i=0;i<array.length();i++){
		//获取JSONObject对象
		JSONObject jo=array.getJSONObject(i);
		//获取jo对象的值
		int id = jo.getInt("id");
		String name = jo.getString("name");
		double price = jo.getDouble("price");
		String imagePath = jo.getString("imagePath");
		//封装JAVA对象
		Food f=new Food(id,name,price,imagePath);
		list.add(f);
		
	}
	//显示
	System.out.println(list);
	}
	//JAVA---JSON对象
	public void testJava1() throws JSONException{
		//创建JAVA对象
		Food f=new Food(8,"牛肉",88.88,"http://192.168.10.7.:8080/f5.jpg");
		//创建JSONObject对象
		JSONObject js=new JSONObject();
		//设置JSONObject数据
		js.put("id", f.getId());
		js.put("name", f.getName());
		js.put("price", f.getPrice());
		js.put("imagePath", f.getImagePath());
		//转换为JSON字符串
		System.out.println(js.toString());
	}
	//JAVA---JSON集合
	public void testJavaArray(){
		//创建JAVA对象
		Food f1=new Food(8,"牛肉",88.88,"http://192.168.10.7.:8080/f5.jpg");
		Food f2=new Food(8,"羊肉",188.88,"http://192.168.10.7.:8080/f6.jpg");
		//List集合存入
		List list=new ArrayList();
		list.add(f1);
		list.add(f2);
		//创建JSONArray对象
		JSONArray array=new JSONArray();
		
		//转换为JSON字符串
		for(int i=0;i<list.size();i++){
			array.put(list.get(i));
		}
		//显示
		System.out.println(array.toString());
	}


	
	//使用Gson方式  1 将json串转换成对象
	
	public void testJsonToObj2(){
		String json="{\"id\":2, \"name\":\"大虾\", \"price\":12.3, \"imagePath\":\"http://192.168.10.165:8080/L05_Server/images/f1.jpg\"}";
		
		//1.创建Gson
		Gson gson =new Gson();
		
		
		//2.通过formJson方法获取对象
		Food food = gson.fromJson(json, Food.class);
		
		System.out.println(food);
		
	}
	//使用Gson方式  2 将json串转换成集合
	public void testJsonToList2(){
		
		String json="[{\"id\":2, \"name\":\"大虾\", \"price\":12.3, \"imagePath\":\"http://192.168.10.165:8080/L05_Server/images/f1.jpg\"},{\"id\":3, \"name\":\"螃蟹\", \"price\":1299.3, \"imagePath\":\"http://192.168.10.165:8080/L05_Server/images/f2.jpg\"}]";
		
		
		//1.创建Gson
		Gson gson =new Gson();
		
		
		//2.通过formJson方法获取对象
		List<Food> list = gson.fromJson(json, new TypeToken<List<Food>>(){}.getType());
		
		System.out.println(list);
		
	}

	//用ＧＳＯＮ－－－－－JAVA对象----JSON串
	public void testGson1(){
		//创建ＧＳＯＮ对象
		Gson g=new Gson();
		//转为JSON字符串
		String json = g.toJson(new Food(8,"牛肉",88.88,"http://192.168.10.7.:8080/f5.jpg"));
		
		System.out.println(json);
	}
	//用ＧＳＯＮ－－－－－JAVA集合----JSON集合
	public void testGson2(){
		//创建ＧＳＯＮ对象
		Gson g=new Gson();
		//转为JSON字符串
		
		Food f1=new Food(8,"牛肉",88.88,"http://192.168.10.7.:8080/f5.jpg");
		Food f2=new Food(8,"羊肉",188.88,"http://192.168.10.7.:8080/f6.jpg");
		//List集合存入
		List list=new ArrayList();
		list.add(f1);
		list.add(f2);
		
		System.out.println(list);
	}
}
class Food{
	int id;
	String name;
	Double price;
	String imagePath;
	
	public Food(int id, String name, Double price, String imagePath) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.imagePath = imagePath;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", price=" + price
				+ ", imagePath=" + imagePath;
	}
	
}