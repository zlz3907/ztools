package com.ztools.xml;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

@Deprecated
public class XMLHandler extends AbsHandler {

    /**
	 * 
	 */
    private static final long serialVersionUID = -1878740402690543538L;
    private XMLBean xmlBean;
    private String rootName;

    private String tempHashcode = null;
    private List<Object> tempItemObject = new ArrayList<Object>();
    private String tempItemName;
    private Object currObject;
    // private int oldDepth;
    private int depth;
    private boolean isBaseType = false;

    // private boolean isStartElement = true;
    private boolean isEndElement = false;
    private IXMLProcess iXmlProcess = null;
    private Map<String, Object> objMap = new HashMap<String, Object>();

    public XMLHandler(XMLBean xmlBean) {
        if (null != xmlBean) {
            this.xmlBean = xmlBean;
        } else {
            throw new NullPointerException("xmlBean is NULL.");
        }
    }

    @Override
    public void startDocument() throws SAXException {
        // this.rootName = this.xmlBean.getRootName();
        // System.out.println("startDocument...");
        // //TODO
        // iXmlProcess = XMLProcessFactory.getXmlProcess(rootName);
    }

    private Field[] getFields(Class<?> c) {
        Field[] ret = null;
        if (null != c) {
            Field[] temp;
            do {
                Field[] cfs = c.getDeclaredFields();
                if (null == ret) {
                    ret = cfs;
                } else {
                    temp = new Field[ret.length + cfs.length];
                    System.arraycopy(ret, 0, temp, 0, ret.length);
                    System.arraycopy(cfs, 0, temp, ret.length, cfs.length);
                    ret = temp;
                }
                c = c.getSuperclass();
            } while (null != c);
        }
        return ret;
    }

    private Object encapsulateObject(Attributes attributes, Class<?> c) {
        if (null != c && null != attributes) {
            Field[] fs = getFields(c);
            if (null == fs) {
                return null;
            }
            try {
                Object obj = c.newInstance();
                for (int i = 0; i < fs.length; i++) {
                    String value = attributes.getValue(fs[i].getName());
                    if (null != value && !"".equals(value)) {
                        String methodName = getMethodName(fs[i].getName());
                        if ("Boolean".equalsIgnoreCase(fs[i].getType()
                                .getSimpleName())) {
                            if (0 <= fs[i].getName().toLowerCase()
                                    .indexOf("is")) {
                                methodName = getMethodName(fs[i].getName()
                                        .substring(2));
                            }
                        }
                        Method m = c.getMethod(methodName, fs[i].getType());
                        Object p = null;
                        Class<?> type = fs[i].getType();
                        p = parseValue(value, type);
                        if (null != p) {
                            m.invoke(obj, p);
                        }

                    }

                }
                return obj;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private boolean isRoot = true;

    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
        String className = attributes.getValue("class");
        if (null == className) {
            // set default class name
            className = this.xmlBean.getBeanClass().getName();
        }
        // System.out.println("qName: " + qName + " depth: " + depth);
        if (isRoot) {
            rootName = qName;
            isRoot = false;
            iXmlProcess = XMLProcessFactory.getXmlProcess(rootName);
        }
        // is root
        if (this.rootName.equals(qName)) {

            if (null != className && !"".equals(className)) {
                try {
                    this.xmlBean.setBeanClass(Class.forName(className));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            this.tempItemObject.add(new ArrayList<Object>());
            this.tempItemName = qName;
            this.depth++;
            return;
        }

        // change depth
        // if (!qName.equals(this.tempItemName) && isStartElement) {
        // this.oldDepth++;
        // }
        this.tempItemName = qName;
        // System.out.println("qName: " + qName + " depth: " + depth);
        tempHashcode = attributes.getValue("hashcode");
        try {
            Class<?> c = Class.forName(className);
            currObject = parseValue("0", c);

            if (null == currObject) {
                isBaseType = false;
                currObject = encapsulateObject(attributes, c);
                String hashcode = tempHashcode;
                if (null != hashcode && !"".equals(hashcode)) {
                    Object obj = objMap.get(hashcode);
                    if (null != obj) {
                        currObject = obj;
                    } else {
                        objMap.put(hashcode, currObject);
                    }
                }
            } else {
                if ("0".equals(tempHashcode)) {
                    currObject = parseValue(null, c);
                } else if (null != tempHashcode && !"".equals(tempHashcode)){
                    Object obj = objMap.get(tempHashcode);
                    if (null != obj)
                        currObject = obj;
                }
                isBaseType = true;
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (depth < this.tempItemObject.size()) {
            this.tempItemObject.set(this.depth, currObject);
        } else {
            this.tempItemObject.add(this.depth, currObject);
        }
        // isStartElement = true;
        isEndElement = false;
        this.depth++;
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        if (0 < length && isBaseType) {
            String s = new String(ch).substring(start, start + length);
            // System.out
            // .println("base type : " + s + " " + currObject.getClass());
            // if (!isObjectRef)

            currObject = parseValue(s, currObject.getClass());
            if (null != tempHashcode && !"".equals(tempHashcode)) {
                objMap.put(tempHashcode, currObject);
            }
            // isObjectRef = false;
            tempItemObject.set(this.depth - 1, currObject);
        }
    }

    private Object parseValue(String value, Class<?> type) {
        Object p = null;
        try {
            value = iXmlProcess.read(value);
            if (type.equals(String.class)) {
                p = null != value ? value : "";
            } else if (type.equals(Double.class)
                    || "double".equals(type.getSimpleName())) {
                p = null != value ? Double.parseDouble(value) : 0.00d;
            } else if (type.equals(Float.class)
                    || "float".equals(type.getSimpleName())) {
                p = null != value ? Float.parseFloat(value) : 0.0f;
            } else if (type.equals(Long.class)
                    || "long".equals(type.getSimpleName())) {
                p = null != value ? Long.parseLong(value) : 0l;
            } else if (type.equals(Integer.class)
                    || "int".equals(type.getSimpleName())) {
                p = null != value ? Integer.parseInt(value) : 0;
            } else if (type.equals(Short.class)
                    || "short".equals(type.getSimpleName())) {
                p = null != value ? Short.parseShort(value) : 0;
            } else if (type.equals(Boolean.class)
                    || "boolean".equals(type.getSimpleName())) {
                p = null != value ? Boolean.parseBoolean(value) : false;
            } else if (type.equals(Date.class)) {
                p = DateFormat.getDateInstance(0, Locale.getDefault()).parse(
                        value);
            } else if (type.equals(Character.class)
                    || "char".equals(type.getSimpleName())) {
                if (null != value && value.length() > 0) {
                    p = value.charAt(0);
                } else {
                    p = new Character('0');
                }
            } else if (type.equals(Byte.class)
                    || "byte".equals(type.getSimpleName())) {
                p = null != value ? Byte.parseByte(value) : 0;
            }
        } catch (ParseException e) {
            p = new Date();
        } catch (NumberFormatException e) {
            p = 0;
        }
        return p;
    }

    private String getMethodName(String fieldName) {
        String methodName = fieldName;
        methodName = "set".concat(methodName.substring(0, 1).toUpperCase())
                .concat(methodName.substring(1));
        return methodName;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        this.depth--;
        isBaseType = false;
        if (!qName.equals(tempItemName) && isEndElement) {
            // this.depth--;
            tempItemName = qName;
        }
        if (this.tempItemObject.size() > depth && depth > 0) {
            Object obj = this.tempItemObject.get(depth - 1);
            Object curr = this.tempItemObject.get(depth);
            if (obj instanceof Collection<?>) {
                Collection<Object> c = (Collection<Object>) obj;
                c.add(curr);
                this.tempItemObject.set(depth - 1, c);
            } else if (null != obj) {
                // String methodName = getMethodName(qName);

                Method m = null;
                try {
                    Method[] ms = obj.getClass().getMethods();
                    for (int i = 0; i < ms.length; i++) {
                        if (0 == ms[i].getName().toLowerCase()
                                .indexOf("set" + qName.toLowerCase())) {
                            m = ms[i];
                            break;
                        }
                    }
                    // m = obj.getClass().getMethod(methodName,
                    // curr.getClass());
                    if (null != m) {
                        m.invoke(obj, new Object[] { curr });
                        this.tempItemObject.set(depth - 1, obj);
                    }
                } catch (SecurityException e) {
                    e.printStackTrace();
                    // } catch (NoSuchMethodException e) {
                    // e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    System.out.println(m);
                    System.out.println(curr);
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }
        // System.err.println(qName + " depth: " + depth + " obj: " +
        // this.tempItemObject);
        isEndElement = true;
        // isStartElement = false;
    }

    @Override
    public void endDocument() throws SAXException {
        Object obj = (this.tempItemObject.get(depth));
        if (obj instanceof List<?>) {
            this.xmlBean.setItemList((List<?>) obj);
        }
    }

    @Override
    public Object getXmlObject() {
        return this.tempItemObject.get(depth);
    }

}
