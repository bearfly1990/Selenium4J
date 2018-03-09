package org.bearfly.selenium.tools;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

import org.bearfly.selenium.models.TestCase;

public class XMLUtils {

    public static String getValueByExpression(String filePath, String expression){
        String value = "";
        List<Element> elements = getElementsByXPath(filePath, expression);

        for (Element element : elements) {
            value = element.getValue().trim();
        }

        return value;
    }

    public static List<Element> getElementsByXPath(String filePath, String expression) {
        List<Element> elementList = new ArrayList<Element>();
        InputStream in = readXMLAsStream(filePath);
        SAXBuilder builder = new SAXBuilder();
        Document document;
        try {
            document = builder.build(in);
            XPathFactory xpath = XPathFactory.instance();
            XPathExpression<Element> expr = xpath.compile(expression, Filters.element());
            elementList = expr.evaluate(document);
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }
        return elementList;
    }

    @SuppressWarnings("unchecked")
    public static  <T> List<T> getListByXPath(String filePath, String expression, Class<T> clazz) {
        List<T> result = new ArrayList<T>();
        try {
            List<Element> elements = getElementsByXPath(filePath, expression);
            
            for(Element element : elements) {
                result.add((T) mapObject(element.getAttributes(), clazz));
            }
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static <T> Object mapObject(List<Attribute> attrs, Class<T> clazz) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Object obj = clazz.newInstance();
        Method[] methods = clazz.getDeclaredMethods();

        for(Method method : methods) {
            for(Attribute attr : attrs) {
                if(method.getName().toLowerCase().equals("set".concat(attr.getName()))) {
                    method.invoke(obj, attr.getValue());
                }
            }

        }
        return obj;
    }

    public static InputStream readXMLAsStream(String fileName) {
        InputStream in = XMLUtils.class.getResourceAsStream(fileName);
        return in;
    }

    public static void readXML() throws JDOMException, IOException {
        InputStream in = XMLUtils.class.getResourceAsStream("/query_sqlserver.xml");
        SAXBuilder builder = new SAXBuilder();
        Document document = builder.build(in);

        // create xpath factory
        XPathFactory xpath = XPathFactory.instance();

        XPathExpression<Element> expr = xpath.compile("//queries/query[@id='1']", Filters.element());
        List<Element> query = expr.evaluate(document);
        for (Element course : query) {
            System.out.println("   " + course.getValue().trim());
        }

        /*
         * System.out.println("\n2. select all attributes of element");
         * XPathExpression<Attribute> attrExpr = xpath.compile("//course/@id",
         * Filters.attribute()); List<Attribute> ids = attrExpr.evaluate(document); for
         * (Attribute id : ids){ System.out.println("   " + id.getValue()); }
         * 
         * System.out.println("\n3. select the second element"); expr =
         * xpath.compile("//course[2]/name", Filters.element()); Element name =
         * expr.evaluateFirst(document); System.out.println("   " + name.getValue());
         * 
         * System.out.println("\n4. select element by xpath with attribute"); expr =
         * xpath.compile("//course[@id='1']/name", Filters.element()); Element child =
         * expr.evaluateFirst(document); System.out.println("   " + child.getValue());
         */
    }
    public static void main(String[] args) {
        List<TestCase> testCases =  XMLUtils.getListByXPath("/TestCases.xml", "//testcase", TestCase.class);
        for(TestCase tc : testCases) {
            System.out.println(tc.getName()+" - "+ tc.getFile());
        }
    }
    
}
