package  com.example.day;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamUtils {

	/*
	 * ��Streamת����String
	 */
	public static String streamToString(InputStream is){
		byte[] b=new byte[100];
		
		int len = -1;
		
		StringBuffer buffer=new StringBuffer();
		try {
			while((len=is.read(b))!=-1){
			
				buffer.append(new String(b,0,len));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (is!=null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return buffer.toString();
	}
	
	/*
	 *��Stringת����String��ʹ��ByteArrayOutputStream��toString()����
	 */
	public static String streamToString2(InputStream is){
		//�����ڴ����������
		ByteArrayOutputStream  baos = new ByteArrayOutputStream();
		byte[] b=new byte[100];
		
		int len = -1;
		
		try {
			while((len=is.read(b))!=-1){
				baos.write(b, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (is!=null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return baos.toString();
	}
	/*
	 *��Stringת����byte[]��ʹ��ByteArrayOutputStream��toByteArray()����
	 */
	public static byte[] streamToByteArray(InputStream is){
		//�����ڴ����������
		ByteArrayOutputStream  baos = new ByteArrayOutputStream();
		byte[] b=new byte[1024];
		
		int len = -1;
		
		try {
			while((len=is.read(b))!=-1){
				baos.write(b, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
//			System.out.println("eeeeeeeeee"+);
		}finally{
			if (is!=null) {
				try {
					is.close();baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return baos.toByteArray();
	}
}
