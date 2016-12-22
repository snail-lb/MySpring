package com.cn.ioc.xml;

import com.cn.ioc.beans.AbstractBeanDefinitionReader;
import com.cn.ioc.beans.BeanDefinition;
import com.cn.ioc.beans.PropertyValue;
import com.cn.ioc.io.ResourceLoader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * 解析spring配置文件
 * @author lvbiao
 *
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

	public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
		super(resourceLoader);
	}

	public void loadBeanDefinitions(String location) throws Exception {
		//加载spring配置文件，并获取其输入流
		InputStream inputStream = getResourceLoader().getResource(location).getInputStream();
		doLoadBeanDefinitions(inputStream);
	}

	protected void doLoadBeanDefinitions(InputStream inputStream) throws Exception {
		//DocumentBuilderFactory:定义工厂 API，使应用程序能够从 XML 文档获取生成 DOM 对象树的解析器。
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		//DocumentBuilder:XML 文档获取 DOM 文档实例,使用此类，应用程序员可以从 XML 获取一个 Document。
		DocumentBuilder docBuilder = factory.newDocumentBuilder();
		Document doc = docBuilder.parse(inputStream);
		// 解析文档
		registerBeanDefinitions(doc);
		//关闭输入流
		inputStream.close();
	}

	public void registerBeanDefinitions(Document doc) {
		Element root = doc.getDocumentElement();

		parseBeanDefinitions(root);
	}

	protected void parseBeanDefinitions(Element root) {
		NodeList nl = root.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node node = nl.item(i);
			if (node instanceof Element) {
				Element ele = (Element) node;
				processBeanDefinition(ele);
			}
		}
	}

	protected void processBeanDefinition(Element ele) {
		String name = ele.getAttribute("name");
		String className = ele.getAttribute("class");
		//查找到一个bean节点，便新建一个BeanDefinition
        BeanDefinition beanDefinition = new BeanDefinition();
        //设置bean属性
        processProperty(ele,beanDefinition);
        //设置bean名称
        beanDefinition.setBeanClassName(className);
        //将获取到的bean放入到父类中存储bean的Map中去
		getRegistry().put(name, beanDefinition);
	}

	//设置bean属性
    private void processProperty(Element ele,BeanDefinition beanDefinition) {
        NodeList propertyNode = ele.getElementsByTagName("property");
        for (int i = 0; i < propertyNode.getLength(); i++) {
            Node node = propertyNode.item(i);
            if (node instanceof Element) {
                Element propertyEle = (Element) node;
                String name = propertyEle.getAttribute("name");
                String value = propertyEle.getAttribute("value");
                //设置bean属性
                beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name,value));
            }
        }
    }
}
