package t.tools.xml;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.ztools.xml.XMLReader;
import com.ztools.xml.XMLWriter;

public class XMLTest {

    public static Object creatTestObject() {
        Person p = new Person("Name" + 3, 3 + "", "f");

        Object[] arr = { 1, "2", 1 };
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
         System.out.println(xml1.replaceAll(" hashcode=\"-?\\d+\"", ""));
        // System.out.println("--------------------");
        Object objB = XMLReader.xmlStringToObject(xml1);
        String xml2 = XMLWriter.objectToXmlString(objB);
        // System.out.println(xml2.replaceAll(" hashcode=\"-?\\d+\"", ""));
        Assert.assertEquals(xml1.replaceAll(" hashcode=\"-?\\d+\"", ""),
                xml2.replaceAll(" hashcode=\"-?\\d+\"", ""));
    }
}
