package com.example;

import com.example.entity.Environment;
import com.example.entity.Project;
import com.example.entity.User;
import com.example.model.Animal;
import com.example.model.Car;
import com.example.model.Person;
import com.example.model.TestClass1;
import com.example.service.impl.MailServiceImpl;
import com.example.service.impl.PersonServiceImpl;
import com.example.tracking.UsageTracked;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;

import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Application {

    public static void handleDb(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Date currentDate = new Date();
        User user1 = new User();
        user1.setFullname("Hibernate Example");
        user1.setUsername("gpcoder");
        user1.setSalary(BigDecimal.valueOf(2000.234000));
        user1.setPassword("123456"); // Should encode password
        user1.setCreatedAt(currentDate);
        user1.setModifiedAt(currentDate);
        user1.setTracking("ignored field");
        Long userId = (Long) session.save(user1);
        System.out.println("User id = " + userId);
        session.getTransaction().commit();
    }

    public static <T> List<T> findAll(Session session, Class<T> clazz) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> root = cq.from(clazz);
        CriteriaQuery<T> selectAll = cq.select(root);
        return session.createQuery(selectAll).list();
    }

    public static void handleDBFeatureFlag(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Project project = new Project();
        project.setCode("default");
        project.setName("default");
        Environment environment = new Environment();
        environment.setCode("dev");
        environment.setName("dev");
        Environment environment1 = new Environment();
        environment1.setName("stg");
        environment1.setCode("stg");
        Environment environment2 = new Environment();
        environment1.setName("default");
        environment1.setCode("default");
        project.setEnvironments(Set.of(environment, environment1, environment2));
        session.persist(project);
        session.getTransaction().commit();
    }

    public static void findDbFeatureFlag(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            Project project = session.find(Project.class, UUID.fromString("0fe5b82c-eda2-4bc7-a41a-b4f9c465ddec"));
            System.out.println(project.getEnvironments());
            System.out.println("----------------------------");
            System.out.println(project);
            session.close();
        }
    }

    public static void insertProjectFeatureFlag(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            Environment environment = session.load(Environment.class, UUID.fromString("41214623-9123-4b91-b419-b79b5b9ec1b5"));
            Environment environment1 = session.load(Environment.class, UUID.fromString("9b103214-4df0-451e-a95f-592cd3d0ef3f"));
            Environment environment2 = new Environment();
            environment2.setName("stg");
            environment2.setCode("stg");
            environment2 = (Environment) session.merge(environment2);
            session.beginTransaction();
            Project project = new Project();
            project.setName("bo");
            project.setCode("bo");
            project.setEnvironments(Set.of(environment, environment1, environment2));
            session.persist(project);
            session.getTransaction().commit();
        }
    }

    public static void deleteProjectFeatureFlag(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Project project = session.load(Project.class, UUID.fromString("0fe5b82c-eda2-4bc7-a41a-b4f9c465ddec"));
            session.delete(project);
            session.getTransaction().commit();
        }
    }

    private static void testL1Cache(SessionFactory sessionFactory) {
        try(Session session=sessionFactory.openSession()){
            session.beginTransaction();
            Environment environment= session.get(Environment.class,UUID.fromString("41214623-9123-4b91-b419-b79b5b9ec1b5"));
            System.out.println(environment.getName());
            environment.setCode("prod");
            environment.setName("prod");
//            session.saveOrUpdate(environment);
            Environment environment1=session.get(Environment.class, UUID.fromString("41214623-9123-4b91-b419-b79b5b9ec1b5"));
            System.out.println(environment1.getName());
            session.getTransaction().commit();
            System.out.println(environment.hashCode());
            System.out.println(environment1.hashCode());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        GenericApplicationContext applicationContext = new GenericApplicationContext();
//        applicationContext.registerBean("messageSource",
//                ResourceBundleMessageSource.class, () -> {
//                    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//                    messageSource.setBasename("translation.messages");
//                    return messageSource;
//                });
//        new XmlBeanDefinitionReader(applicationContext).loadBeanDefinitions("compose.xml");
        new AnnotationConfigApplicationContext(applicationContext.getDefaultListableBeanFactory())
                .scan("com.example.config",
                        MailServiceImpl.class.getPackage().getName(),
//                        "com.example.event",
                        "com.example.aop"
                );
        applicationContext.refresh();
        SessionFactory sessionFactory = applicationContext.getBean(SessionFactory.class);
        testL1Cache(sessionFactory);
//        handleDBFeatureFlag(sessionFactory);
//        findDbFeatureFlag(sessionFactory);
//        insertProjectFeatureFlag(sessionFactory);
//        deleteProjectFeatureFlag(sessionFactory);
//        handleDb(sessionFactory);
//        TimeUnit.SECONDS.sleep(20);
//        System.out.println("done ne");
//        Session session = sessionFactory.openSession();
//        System.out.println("xuong day khong");
//        session.close();
//        User user = session.get(User.class, 2L);
//        System.out.println("user ne " + user);
//        List<User> users = session.createQuery("FROM User", User.class).list();

//        List<User> users = findAll(session, User.class);
//        users.forEach(System.out::println);
//        TimeUnit.SECONDS.sleep(100);
//        User deleteUser= session.find(User.class, 4L);
//        deleteUser.setFullname("test jack");
//        System.out.println("delete user info "+deleteUser);
//        session.beginTransaction();
//        session.delete(deleteUser);
//        session.getTransaction().commit();
//        System.out.println("xuong day khong 2");
//        session.close();

//        session.close();
//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
//        applicationContext.scan("com.example");
//        System.out.println(applicationContext.getBean(TestClass1.class).getName());
//        System.out.println(applicationContext.getBean(TestClass1.class).getName());
//        System.out.println(applicationContext.getBean(TestClass1.class).getName());
//        System.out.println(applicationContext.getBean(TestClass1.class).getName());
//        System.out.println(applicationContext.getBean(TestClass1.class).getName());
//        System.out.println(applicationContext.getBean(TestClass1.class).getName());
//        MailServiceImpl mailService=applicationContext.getBean(MailServiceImpl.class);
//        mailService.sendMail("test@gmail.com", "test 1");
//        mailService.sendMail("john.doe@example.org", "test 2");
//        mailService.sendMail("known.spammer@example.org", "test 3");
//        System.out.println(mailService.getMail());
//        Person person=applicationContext.getBean(Person.class);
//        PersonServiceImpl personService=applicationContext.getBean(PersonServiceImpl.class);
//        personService.insertPerson(person);
//        System.out.println(applicationContext.getBean("mailServiceImpl",UsageTracked.class).getUseCount());
//        System.out.println(applicationContext.getBean(Animal.class).getName());;
//        System.out.println(applicationContext.getBean(Service.class).getName());
//        applicationContext.getBean(Service.class).printMap();
//        System.out.println(applicationContext.getBean("animal1",Animal.class));
//        Animal animal=applicationContext.getBean(Animal.class);
//        Animal animal1=applicationContext.getBean(Animal.class);
//
//        Animal animal2=applicationContext.getBean(Animal.class);
//
//        CustomFactoryBean customFactoryBean=(CustomFactoryBean) applicationContext.getBean("&animal");
//
//        System.out.println(customFactoryBean.getFactoryBeanName());
//
//        System.out.println(animal);
//        System.out.println(animal1);
//        System.out.println(animal2);
//
//        TranslateProperties translateProperties= applicationContext.getBean(TranslateProperties.class);
//        System.out.println(translateProperties);
//        LifeCycleClass lifeCycleClass=applicationContext.getBean(LifeCycleClass.class);
//        System.out.println(lifeCycleClass.getPhase());
//        System.out.println(lifeCycleClass.isRunning());
//        System.out.println(lifeCycleClass.isAutoStartup());
//        lifeCycleClass.stop();
//        lifeCycleClass.stop(applicationContext::close);
//        lifeCycleClass.onRefresh();
//        lifeCycleClass.onClose();


//        AppService appService = applicationContext.getBean(AppService.class);
//        Properties system = applicationContext.getBean("systemProperties", Properties.class);
//        system.setProperty("isDebugging", "true");
//        for (Map.Entry<Object, Object> entry : system.entrySet()) {
//            System.out.println(entry.getKey() + "=" + entry.getValue());
//        }
//        Environment env = applicationContext.getBean("environment", Environment.class);
//
//        Properties systemEnv = applicationContext.getBean("systemEnvironment", Properties.class);
//        for (Map.Entry<Object, Object> entry : systemEnv.entrySet()) {
//            System.out.println(entry.getKey() + "=" + entry.getValue());
//        }
//        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
//        resourceBundleMessageSource.setBasename("translation.messages");
//
//
//        Iterator<String> beanNamesIterator = applicationContext.getBeanFactory().getBeanNamesIterator();
//        while (beanNamesIterator.hasNext()) {
//            System.out.println(beanNamesIterator.next());
//        }
//
//        // if messageSource not init spring always inject bean DelegatingMessageSource with EMPTY MESSAGE for bean 'messageSource'
//        MessageSource messageSource = applicationContext.getBean("messageSource", MessageSource.class);
//        System.out.println(applicationContext.getMessage("test", new Object[]{"abc", "def"}, "not exists", Locale.US));
//        System.out.println(Arrays.toString(env.getActiveProfiles()));
//        System.out.println(Arrays.toString(env.getDefaultProfiles()));
//        System.out.println(applicationContext.containsBeanDefinition("appService"));
//        System.out.println(appService.getName());
//        System.out.println("Bean Definition");
//        System.out.println(applicationContext.containsBeanDefinition("messageSource"));
//        BeanDefinition messageSourceDefinition = applicationContext.getBeanFactory().getBeanDefinition("messageSource");
//        System.out.println("---Message Source------");
//        System.out.println(messageSourceDefinition.getBeanClassName());
//        System.out.println(Arrays.toString(messageSourceDefinition.getDependsOn()));
//        System.out.println(messageSourceDefinition);
//        System.out.println("-----------------------");
//        BeanDefinition appServiceDefinition = applicationContext.getBeanDefinition("appService");
//        System.out.println("---App Service------");
//        System.out.println(appServiceDefinition.getBeanClassName());
//        appServiceDefinition.getConstructorArgumentValues().getGenericArgumentValues().forEach((valueHolder) -> {
//            RuntimeBeanReference beanReference=(RuntimeBeanReference) valueHolder.getValue();
//            assert beanReference != null;
//            System.out.println(valueHolder.getSource() + ":" + valueHolder.getName() + "=" + beanReference.getBeanName());
//        });
//        System.out.println(appServiceDefinition);
//        System.out.println(appServiceDefinition.isSingleton());
//        System.out.println("-----------------------");
//        System.out.println(Objects.requireNonNull(applicationContext.getType("messageSource")).getName());
//        System.out.println(Objects.requireNonNull(applicationContext.getType("appService")).getName());
//        AppDao appDao=applicationContext.getBean(AppDao.class);
//        System.out.println(appDao.getName());
//
//        AppConfig appConfig=applicationContext.getBean(AppConfig.class);
//        Car car=applicationContext.getBean(Car.class);
//
//        applicationContext.getBean(AppConfig.class).getCar();
//        applicationContext.getBean(AppConfig.class).getCar();
        applicationContext.close();
    }
}
