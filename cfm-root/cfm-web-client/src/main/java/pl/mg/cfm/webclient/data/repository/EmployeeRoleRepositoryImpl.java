package pl.mg.cfm.webclient.data.repository;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.mg.cfm.webclient.data.entity.EmployeeRole;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by m on 2015-04-28.
 */
@Repository
public class EmployeeRoleRepositoryImpl implements EmployeeRoleRepository {

    @PersistenceContext(name = "cfm-localhost")
    private EntityManager entityManager;


    Logger logger = Logger.getLogger(this.getClass());

    /**
     * Searching employee role using JPA Criteria API.
     *
     * @param roleName name of the role
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public EmployeeRole getRole(String roleName) {
        EmployeeRole resultRole = null;
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<EmployeeRole> criteriaQuery = cb.createQuery(EmployeeRole.class);

        Root<EmployeeRole> root = criteriaQuery.from(EmployeeRole.class);
        criteriaQuery.select(root)
                .where(cb.equal(root.get("name"), roleName));
        TypedQuery<EmployeeRole> query = entityManager.createQuery(criteriaQuery);

        try {
            resultRole = query.getSingleResult();
        } catch (NoResultException e) {
            logger.debug("employeeRole not found");
            logger.error(e.getMessage(), e);
        } catch (NonUniqueResultException e) {
            logger.debug("employeeRole not found");
            logger.error(e.getMessage(), e);
        } catch (Exception e) {
            logger.debug("connection error");
            logger.error(e.getMessage(), e);
        }

        return resultRole;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<EmployeeRole> getAllRoles() {
        return entityManager.createNamedQuery("employeeRole.getAll", EmployeeRole.class).getResultList();
    }


}
