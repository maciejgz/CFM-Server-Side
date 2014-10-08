package pl.mg.cfm.dao;

import javax.ejb.Stateless;

import pl.mg.cfm.commons.dao.CFMDao;


@Stateless
public class CFMDaoFactory {

    public static String DAO_JPA = "DAO_JPA";

    public static CFMDao getCFMDao(String type) {
        if (type.equals(DAO_JPA)) {
            return new CFMDaoHibernate();
        } else {
            return null;
        }
    }

}
