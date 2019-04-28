package com.wangzhixuan.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class reflectUtil{

	public static void main(String[] args) {
//		test1();
//		test2();
		/*DepartChild departchild = new DepartChild();
		departchild.setName("123");
		Object o = reflectget(departchild,"name");
		System.out.println(o);*/
	}

	/**
	 * @describe:第一个是类，第二个是字段名
	 * @author wangruwei
	 * @date 2017年8月29日下午2:24:10
	 * @param entity
	 * @param filename
	 * @return
	 */
	public static Object reflectget(Object entity,String filename) {
		try {
			
			Field field = entity.getClass().getDeclaredField(filename);
			PropertyDescriptor pd = new PropertyDescriptor(field.getName(),
					entity.getClass());
			Method getMethod = pd.getReadMethod();//获得get方法  
			Object o = getMethod.invoke(entity);//执行get方法返回一个Object  
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Object reflectset(Object entity,String filename,Object setValue) {
		try {
			
			Field field = entity.getClass().getDeclaredField(filename);
			PropertyDescriptor pd = new PropertyDescriptor(field.getName(),
					entity.getClass());
			Method getMethod = pd.getWriteMethod();//获得set方法  
			Object o = getMethod.invoke(entity,setValue);//执行set方法返回一个Object  
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static void test2() {
		/*
		try {
			DepartChild departchild = new DepartChild();
			departchild.setName("123");
			Method method = DepartChild.class.getMethod("getName");
			Field field = DepartChild.class.getDeclaredField("name");
			PropertyDescriptor pd = new PropertyDescriptor(field.getName(),  
					departchild.getClass());
			Method getMethod = pd.getReadMethod();//获得get方法  
            Object o = getMethod.invoke(departchild);//执行get方法返回一个Object  
            System.out.println(o);
            if(true)
            return;
            
            field.setAccessible(true);//设置是否允许访问，因为该变量是private的，所以要手动设置允许访问，如果msg是public的就不需要这行了。
            Object msg = field.get(method.invoke(departchild));
            System.out.println(msg);
//			Object o = departchild.getClass().newInstance();
			System.out.println(method.invoke(departchild));
			
			
			
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	private static void test1() {
		/*try {
			Class<?> c = Class.forName("com.sound.recycle.core.server.po.DepartChild");
			Field[] fs = c.getDeclaredFields(); 
			StringBuffer sb = new StringBuffer();  
            //通过追加的方法，将每个属性拼接到此字符串中  
            //最外边的public定义  
            sb.append(Modifier.toString(c.getModifiers()) + " class " + c.getSimpleName() +"{\n");  
            //里边的每一个属性  
            for(Field field:fs){  
                sb.append("\t");//空格  
                sb.append(Modifier.toString(field.getModifiers())+" ");//获得属性的修饰符，例如public，static等等  
                sb.append(field.getType().getSimpleName() + " ");//属性的类型的名字  
                sb.append(field.getName()+";\n");//属性的名字+回车  
                System.out.println(field.getName());
            }  
      
            sb.append("}");  
      
            System.out.println(sb);
            c.getDeclaredMethod("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  */
	}

}
