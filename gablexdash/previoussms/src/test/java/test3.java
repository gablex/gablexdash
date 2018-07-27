
import com.mspace1.model.Tdist;
import com.mspace1.model.Tuser;
import com.mspace1.model.group_contact;
import static com.mspace1.util.HibernateUtil.getSessionFactory;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;



public class test3 {
      public static void main(String[] args) { 
           String dest_addr = "";
        String message = "";
                String sentby = "";
        String source_addr = "";
        Session session5 = getSessionFactory().openSession();
            try {
                
//String sqll2=": select * from tDIST where (left(dest_addr,5) = left('\"+dest_addr+\"',5) or dest_addr = '%') and owner='admin' order by length(dest_addr) desc";
                String sql = "from Tdist  k where substring(k.destAddr,1,5) = substring('" + dest_addr + "',1,5) or k.destAddr='%' and k.owner='admin' order by length(k.destAddr) desc";
                session5.beginTransaction();

                List employee1 = session5.createQuery(sql).list();
                if (employee1 != null || (!sentby.equals(""))) {
                    if (source_addr.length() == 0) {
                        for (Iterator iterator = employee1.iterator(); iterator.hasNext();) {
                            Tdist employee = (Tdist) iterator.next();
                            source_addr = employee.getSourceAddr();
                            System.out.println(source_addr);
                        }
                    }
                    if (sentby.length() == 0) {
                        for (Iterator iterator = employee1.iterator(); iterator.hasNext();) {
                            Tdist employee = (Tdist) iterator.next();
                            source_addr = employee.getSourceAddr();
                            sentby = employee.getSentby();
                                      System.out.println(source_addr);
                                                System.out.println(sentby);
                        }

                    }

                    session5.getTransaction().commit();
                }
            } catch (HibernateException k) {
                session5.getTransaction().rollback();
            } finally {
                session5.close();
            }
      
      }
      
}
      
      

      
        