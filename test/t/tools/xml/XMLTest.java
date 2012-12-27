package t.tools.xml;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import com.ztools.xml.XMLReader;
import com.ztools.xml.XMLWriter;

public final class XMLTest {

  public static Object creatTestObject() {
    Person p = new Person("Name" + 3, 3 + "", "f");

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("aKey", "zlz");
    Map<String, String> subMap = new HashMap<String, String>();
    subMap.put("sub1", "v1");
    subMap.put("sub2", "v2");
    map.put("bKey", subMap);

    Object[] arr = {1, "2", 1, map};
    p.setArr(arr);

    List<String> c = new ArrayList<String>();
    c.add("&#1232;&amp;and;");
    c.add("xxx");
    p.setChildren(c);
    List<Integer> t = new ArrayList<Integer>();
    t.add(1);
    t.add(333);
    List<Integer> tt = new ArrayList<Integer>();
    tt.add(11);
    tt.add(333);
    p.setThr(tt);

    List<Object> objList = new ArrayList<Object>();
    objList.add(1); // int
    objList.add(1123132123123L); // long
    objList.add(1.00001D); // double
    objList.add(9.99f); // float
    objList.add((short) 5); // short
    objList.add((byte) 120); // byte
    objList.add(true);

    objList.add('c'); // char
    objList.add("hello<>&';:\"<![CDATA[abc]]> xml");
    objList.add(new Person("aaa", "12", "f"));
    objList.add(p);
    p.setObjlist(objList);

    List<House> houses = new ArrayList<House>();
    House h = new House("hourse1", "beijing", 100);
    List<Room> rooms = new ArrayList<Room>();
    rooms.add(new Room(1));
    rooms.add(new Room(2));
    rooms.add(new Room(3));
    h.setRooms(rooms);
    houses.add(h);
    houses.add(new House("hourse2", "wuhan", 200));
    houses.add(new House("hourse3", "wuhan", 120));

    p.setHouses(houses);
    // List<Person> list = new ArrayList<Person>();
    // list.add(p);
    return p;
  }

  @Test
  public void testObjectToXmlString() {

    Object objA = creatTestObject();
    String xml1 = XMLWriter.objectToXmlString(objA);
    Assert.assertNotNull(xml1);
    System.out.println(xml1);
    // System.out.println(xml1.replaceAll(" hashcode=\"-?\\d+\"", ""));
    // System.out.println("--------------------");
    Object objB = XMLReader.xmlStringToObject(xml1);
    String xml2 = XMLWriter.objectToXmlString(objB);
    // System.out.println(xml2 + "\n-----------------");
    // System.out.println(xml2.replaceAll(" hashcode=\"-?\\d+\"", ""));
    Assert.assertEquals(xml1.replaceAll(" hashcode=\"-?\\d+\"", ""),
                        xml2.replaceAll(" hashcode=\"-?\\d+\"", ""));

  }

  @Test
  public void testNullValueInMap() {
    Map<String, String> map = new HashMap<String, String>();
    map.put("keyone", "hello");
    map.put("keytwo", null);
    map.put(null, "three");

    String xmlStr = XMLWriter.objectToXmlString(map);
    System.out.println(xmlStr);
    Object obj = XMLReader.xmlStringToObject(xmlStr);
    String xmlStr2 = XMLWriter.objectToXmlString(obj);
    System.out.println();
    System.out.println(xmlStr2);
    Assert.assertEquals(obj, map);
  }

  @Test
  public void testMapInMap() {
    Map<String, String> map = new HashMap<String, String>();
    map.put("keyone", "hello");
    map.put("keytwo", null);
    map.put(null, "three");

    Map<String, Object> pMap = new HashMap<String, Object>();
    pMap.put("subMap", map);
    pMap.put("one", 1);
    pMap.put("integer", new Integer(129));
    pMap.put("string", "string");

    Object testObj = pMap;
    String xmlStr = XMLWriter.objectToXmlString(testObj);
    System.out.println(xmlStr);
    Object obj = XMLReader.xmlStringToObject(xmlStr);
    String xmlStr2 = XMLWriter.objectToXmlString(obj);
    System.out.println();
    System.out.println(xmlStr2);
    Assert.assertEquals(obj, testObj);
  }
}
