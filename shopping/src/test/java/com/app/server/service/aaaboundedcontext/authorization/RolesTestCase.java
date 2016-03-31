package com.app.server.service.aaaboundedcontext.authorization;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.aaaboundedcontext.authorization.RolesRepository;
import com.app.shared.aaaboundedcontext.authorization.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.framework.server.helper.RuntimeLogInfoHelper;
import com.athena.framework.server.helper.EntityValidatorHelper;
import com.athena.framework.server.test.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.Before;
import org.junit.After;
import com.athena.framework.shared.entity.web.entityInterface.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;
import com.app.shared.aaaboundedcontext.authorization.RoleMenuBridge;
import com.app.shared.aaaboundedcontext.authorization.AppMenus;
import com.app.server.repository.aaaboundedcontext.authorization.AppMenusRepository;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.biz.SpartanIncorrectDataException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class RolesTestCase extends EntityTestCriteria {

    @Autowired
    private RolesRepository<Roles> rolesRepository;

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private EntityValidatorHelper<Object> entityValidator;

    private RandomValueGenerator valueGenerator = new RandomValueGenerator();

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    private static List<EntityTestCriteria> entityContraint;

    @Autowired
    private ArtMethodCallStack methodCallStack;

    protected MockHttpSession session;

    protected MockHttpServletRequest request;

    protected MockHttpServletResponse response;

    protected void startSession() {
        session = new MockHttpSession();
    }

    protected void endSession() {
        session.clearAttributes();
        session.invalidate();
        session = null;
    }

    protected void startRequest() {
        request = new MockHttpServletRequest();
        request.setSession(session);
        org.springframework.web.context.request.RequestContextHolder.setRequestAttributes(new org.springframework.web.context.request.ServletRequestAttributes(request));
    }

    protected void endRequest() {
        ((org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()).requestCompleted();
        org.springframework.web.context.request.RequestContextHolder.resetRequestAttributes();
        request = null;
    }

    @Before
    public void before() {
        startSession();
        startRequest();
        setBeans();
    }

    @After
    public void after() {
        endSession();
        endRequest();
    }

    private void setBeans() {
        runtimeLogInfoHelper.createRuntimeLogUserInfo(1, "AAAAA", request.getRemoteHost());
        org.junit.Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        entityContraint = addingListOfFieldForNegativeTesting();
    }

    private Roles createRoles(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        Roles roles = new Roles();
        roles.setRoleIcon("CHW6KffwVRNa0MU7k0Usuw9k30T2uJBLa5Jw5w55D1wAYY4OIv");
        roles.setRoleDescription("oF7uRVEZgFsYHasEuRvhjVZMbHfCUbDRhP1P8e1NNZoXNtgMWP");
        roles.setRoleName("8Gg0rI3gSxz0CwnYHyEpgO2nhLz0sSQ0DEFcRyxefMhCQjnU8B");
        roles.setRoleHelp("VkzSQRiXHul7Co8reQOS7hXDDVTs8L7khosEhpTj1rD2NAiV5C");
        java.util.List<RoleMenuBridge> listOfRoleMenuBridge = new java.util.ArrayList<RoleMenuBridge>();
        RoleMenuBridge rolemenubridge = new RoleMenuBridge();
        AppMenus appmenus = new AppMenus();
        appmenus.setRefObjectId("xsVibVSz9K0XbJHhc6Xt3uQKj8FhFOJKiJO22SM7yPBj9YFl2b");
        appmenus.setAutoSave(true);
        appmenus.setMenuLabel("WU8Ksll5cmsWXHxnRUpyrBE8McYxH1YsoLw3NUe0FfP0BdKL4A");
        appmenus.setAppType(1);
        appmenus.setMenuIcon("WbrJ30Ul1ksz7sPTCqu8w4RPtSZiOJSoaeJy7n5Ojo9Mo5FO2B");
        appmenus.setMenuAction("pJQ0LNOgdbXM2YlvYK9c90bdGQviqdsJkrUPJTpecionYxmOYD");
        appmenus.setUiType("02r");
        appmenus.setMenuTreeId("Cath75tA9tLSK0G96ngfv5mcJytDXucWB4l93yEwuCqs2m8Bzj");
        appmenus.setAppId("mW87mvIUOylwDnVkLGEWvmZ0nUKXJFHeJ3awomxGORNe2wMWhy");
        appmenus.setMenuCommands("L7nLLecqGB2n2P1lJK1LEX10nF9HMOdeEl2uWhQrstYcWruWVH");
        appmenus.setMenuAccessRights(8);
        appmenus.setMenuHead(true);
        appmenus.setMenuDisplay(true);
        AppMenus AppMenusTest = new AppMenus();
        if (isSave) {
            AppMenusTest = appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        }
        rolemenubridge.setRoles(roles);
        rolemenubridge.setIsRead(true);
        rolemenubridge.setIsWrite(true);
        rolemenubridge.setIsExecute(true);
        rolemenubridge.setMenuId((java.lang.String) AppMenusTest._getPrimarykey());
        listOfRoleMenuBridge.add(rolemenubridge);
        roles.addAllRoleMenuBridge(listOfRoleMenuBridge);
        roles.setEntityValidator(entityValidator);
        return roles;
    }

    @Test
    public void test1Save() {
        try {
            Roles roles = createRoles(true);
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            roles.isValid();
            rolesRepository.save(roles);
            map.put("RolesPrimaryKey", roles._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            Roles roles = rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
            roles.setRoleIcon("0LRG4cCjzwqbVtksg3dq72hWxaD2rq5rbOM8sqUPDNOH13ZCEy");
            roles.setRoleDescription("PvpPYUGUeL3W1sDfa90egdUc2U1gnbXYjbSSxEAPZCu1x0cNA9");
            roles.setRoleName("zawrl8pvCL9RzOpMDcnFoPK63xghWUbnb1KtMHs70OFIIGZ5G9");
            roles.setRoleHelp("dNNnSNsOeXVFHOPKAJgWpfFWD2ozjsjBmyuZWYlJXYbyxHjHAR");
            roles.setVersionId(1);
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            rolesRepository.update(roles);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.delete((java.lang.String) map.get("RolesPrimaryKey")); /* Deleting refrenced data */
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateRoles(EntityTestCriteria contraints, Roles roles) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            roles.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            roles.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            roles.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            rolesRepository.save(roles);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "RoleName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "roleName", "WEvotpVLKEQSUHe4cVxBArqeHhv3tcp9FHDXmfEVpvU3FCjjTNXLQoRIF3IQ2WvFM1elNPIgpsDYjCT9YzPUHkCAYrdg1LV5AJD0QhNZwaiHxTDqMB8B5Kb3L7yyqLF9XksBdUcETO2xZ1jz2aA7qWXt9o9BOZtJ05c2Or3cyuYZUx9bbV2xyqdplnUajt4uuiPSTYyGDtC6N06mvTbScZMTbij7EvqWkP4ZX7iSI0DDSaSrr6QJCoQHi0OPrHhYp"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "RoleDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "roleDescription", "FCe00fbSJMxGrJq4IXjwfg5BpVN1rfUhTrxS0jMVknJmUVN1q69uIliMJLiL7X1TrQFTwfXQ8jlGYVFdgeyCBvnAG9qdc3jUzS4xW3EO4DfezOW6FLZKloYdeNrxUZqpj455OLHqcwui3spXONzutBxAPyea9eQVrw3bXPCGwSwcUvYVhiCRZviFyYRryqSI3bbbfuTlFoJBjVFRHsjd1lpq80KYFVjNkCytCQCTABspt8iGkJIYS2b4Vhtqy8LHJ"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "roleIcon", "vNhzZCiKVx8SMPWsLbRHpXvnTQ67HA1GXHBlSA33OJk08isY0nOaftk2ZwkSAlQxj"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "roleHelp", "frOPw3pvTOir735YaYNaOFyUVj7yLW1ZiEc09qPWYuerc8uVsJAsAFTBl7ghQODoiHsE9dkFLT08yczhy3oKyRsztDknygIHOtUDjhrezwBUrH6kLmMwCJgI60OCnuMyYQnphfG53HY9SABOuHi7DId2FzrN2OjQ33yp40P064PkTROVyAMVtXhGiEAYBumqtMWFkrfJdAGgXyBtio5I43F98267AlGCvfOzVKEkPVYlaFNVBddzKvyC0fz5Y17HZ"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Roles roles = createRoles(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = roles.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 2:
                        roles.setRoleName(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 4:
                        roles.setRoleDescription(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 5:
                        roles.setRoleIcon(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 6:
                        roles.setRoleHelp(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                }
            } catch (SpartanIncorrectDataException e) {
                e.printStackTrace();
            } catch (SpartanConstraintViolationException e) {
                e.printStackTrace();
            } catch (SpartanPersistenceException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            org.junit.Assert.fail();
        }
    }
}
