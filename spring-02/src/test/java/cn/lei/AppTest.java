package cn.lei;

import static org.junit.Assert.assertTrue;

import cn.hutool.core.io.IoUtil;
import cn.lei.benas.PropertyValue;
import cn.lei.benas.PropertyValues;
import cn.lei.benas.factory.config.BeanDefinition;
import cn.lei.benas.factory.config.BeanReference;
import cn.lei.benas.factory.support.DefaultListableBeanFactory;
import cn.lei.benas.factory.xml.XmlBeanDefinitionReader;
import cn.lei.springframework.context.support.ClassPathXmlApplicationContext;
import cn.lei.springframework.core.io.DefaultResourceLoader;
import cn.lei.springframework.core.io.Resource;
import cn.lei.test.bean.UserDao;
import cn.lei.test.bean.UserService;
import cn.lei.test.common.MyBeanFactoryPostProcessor;
import cn.lei.test.common.MyBeanPostProcessor;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void test_BeanFactory(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("userDao",new BeanDefinition(UserDao.class));

        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId","10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));

        BeanDefinition beanDefinition = new BeanDefinition(UserService.class,propertyValues);
        beanFactory.registerBeanDefinition("userService",beanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }

    private DefaultResourceLoader resourceLoader;

    @Before
    public void init(){
        resourceLoader = new DefaultResourceLoader();
    }
    @Test
    public void test_classpath() throws IOException{
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_file() throws IOException{
        Resource resource = resourceLoader.getResource("src/test/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_url() throws IOException{
        Resource resource = resourceLoader.getResource("https://github.com/fuzhengwei/s" +
                "mall-spring/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_xml(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        UserService userService = (UserService) beanFactory.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }

    @Test
    public void test_BeanFactoryPostProcessorAndBeanPostProcessor(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        MyBeanFactoryPostProcessor beanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);
        UserService userService =beanFactory.getBean("userService", UserService.class);
        String res = userService.queryUserInfo();
        System.out.println(res);
    }

    @Test
    public void test_xml2(){

        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:springPostProcessor.xml");

        UserService userService =  applicationContext.getBean("userService", UserService.class);
        String res = userService.queryUserInfo();
        System.out.println(res);
    }

    @Test
    public void test_xml3(){
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        UserService userService = applicationContext.getBean("userService",UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果: " + result);
        System.out.println("ApplicationContextAware: "+ userService.getApplicationContext());
        System.out.println("BeanFactoryAware: "+ userService.getBeanFactory());
    }

    @Test
    public void test_prototype(){
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();
        UserService userService01 = applicationContext.getBean("userService",UserService.class);
        UserService userService02 = applicationContext.getBean("userService",UserService.class);

        System.out.println(userService01);
        System.out.println(userService02);
    }


}
