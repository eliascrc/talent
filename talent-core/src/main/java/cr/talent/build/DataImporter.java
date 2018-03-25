package cr.talent.build;

import cr.talent.core.TestBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DataImporter {

    protected final ClassPathXmlApplicationContext context;

    public DataImporter(){
        this.context = new ClassPathXmlApplicationContext("talent-core.spring.xml");
    }

    public void importData(){
        TestBean testBean = this.context.getBean(TestBean.class);
        System.out.println(testBean.getMessage());
    }

    public static void main(String[] args) {
        DataImporter dataImporter = new DataImporter();
        dataImporter.importData();
    }
}
