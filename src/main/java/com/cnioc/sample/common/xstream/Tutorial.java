package com.cnioc.sample.common.xstream;
import com.thoughtworks.xstream.XStream;
/**
 * 
 * <p>Title:Tutorial</p>
 * <p>Desc:</p>
 * @author fangj
 * @date 2017年12月18日 下午5:14:29
 */
public class Tutorial {
	public static void main(String[] args) {
		Person fangj = new Person();
		//System.out.println(buildXml(fangj));
		Person tmp =buildObject(Person.class,buildXml(fangj));
		System.out.println(tmp);
		RendezvousMessage msg = new RendezvousMessage(15);
		System.out.println(buildXml(msg));
	}
	
	public static void withAnnotations(){
		XStream xstream = new XStream();
		xstream.processAnnotations(Person.class);
		Person fangj = new Person();
		//fangj.setName("fangj");
		System.out.println(xstream.toXML(fangj));
	}
	
	public static void withOutAnnotations(){
		XStream xstream = new XStream();
		RendezvousMessage msg = new RendezvousMessage(15);
		System.out.println(xstream.toXML(msg));
	}
	
    public static <T>  String buildXml(T object){
    	XStream xstream = new XStream();
    	xstream.processAnnotations(object.getClass());
    	return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+xstream.toXML(object);
    }
    
    @SuppressWarnings("unchecked")
	public static <T> T buildObject(Class<T> classz,String xml){
    	XStream xstream = new XStream();
    	xstream.processAnnotations(classz);
    	return (T)xstream.fromXML(xml);
    }
}
