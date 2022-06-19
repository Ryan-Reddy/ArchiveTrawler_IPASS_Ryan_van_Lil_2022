package setup;

import archive.trawler.model.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@Slf4j
@WebListener
public class MyServletContextListenerDummyData implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        System.out.println("initializing application");
//        User u1 = new User("everette65@yahoo.com", "Coyote", "Casa", "user");
//        User u2 = new User("rbosco@gmail.com", "parker54larson.karen", "J9lYp.u=~aC6sc=qvU@", "user");
//        User u3 = new User("xgutmann@gmail.com", "isabelle.waters holly95", "Lc6&'?+Yp#bZ%e4HeF", "user");
//        User u4 = new User("marty07@yahoo.com", "chase61 scrist", "NHw!.@mSD?C9", "user");
//        User u5 = new User("abigail.vonrueden@parisian.info","eziemann", "22I^5ollR", "user");
//        User u6 = new User("alvena78@gmail.com", "blakin greenholt.kendrick", "oqKpf45$", "user");
//        User u7 = new User("murazik.brittany@gmail.com", "laury.robel",  "`!G@5f@,7=y", "user");
//        User u8 = new User("norberto.bergstrom@zboncak.net", "flatley.amelia", "aTZadda", "user");
//        User u9 = new User("ywitting@funk.com", "dmarquardt", "fcVi~fTl{i2kkgm=U<", "user");
//        User u10 = new User("hilario78@fahey.com", "thalia.wolf",  "o1TmGSHxoc>sl8E.q", "user"); TODO fix this, it causes the app not to work

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("terminating application");
    }

}
