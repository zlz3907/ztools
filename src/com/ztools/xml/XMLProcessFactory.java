package com.ztools.xml;

public class XMLProcessFactory {

    /**
     * &#49;&#54;&#36716;&#30721;
     */
    public static String HEX_CODE = "com.ztools.xml.IXMLProcessHexCodeImpl";
    public static String Default_CODE = "XMLBean";

    public static IXMLProcess getXmlProcess(String className) {

        if (!"".equals(className) || "XMLBean".equals(className)) {
            // if("XMLBean".equals(className)){
            return new DefaultXmlCharsetProcess();
        } else {
            try {
                return (IXMLProcess) Class.forName(className).newInstance();
            } catch (InstantiationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return new DefaultXmlCharsetProcess();

    }
}
