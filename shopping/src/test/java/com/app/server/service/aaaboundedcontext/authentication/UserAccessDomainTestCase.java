package com.app.server.service.aaaboundedcontext.authentication;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.aaaboundedcontext.authentication.UserAccessDomainRepository;
import com.app.shared.aaaboundedcontext.authentication.UserAccessDomain;
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
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.biz.SpartanIncorrectDataException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class UserAccessDomainTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

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

    private UserAccessDomain createUserAccessDomain(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainDescription("vERF8pm1ZiTOFkpDoZY3iSFCW9fTg3sK3cdFptwxRHpiFwD2bq");
        useraccessdomain.setDomainName("ryXz4ErWN3YdyImCmtJfipWewn8IGpaxobhxluz4zZ3PGFeeXc");
        useraccessdomain.setDomainHelp("Bhh3qFzpIZKDFO8BL162Kd64q60w3V09UeVXzo8KN5lTonG2Oc");
        useraccessdomain.setDomainIcon("mTTvWeFHiTZKUyu6u6qbLG15GsfQkQfQDTmEp6X1A089oQ04MP");
        useraccessdomain.setEntityValidator(entityValidator);
        return useraccessdomain;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessDomain useraccessdomain = createUserAccessDomain(true);
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccessdomain.isValid();
            useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            UserAccessDomain useraccessdomain = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
            useraccessdomain.setUserAccessDomain(97679);
            useraccessdomain.setDomainDescription("fq5MuvvFhqFNmxQXRAHrRcYiA3tWreDOgjAz4W96tlzLeWJzJt");
            useraccessdomain.setDomainName("bFVsDu5cYe9Lcbo6AU7S8ASsmQU8Xt6DLfWW1Q2Fj5zqxF5BFr");
            useraccessdomain.setDomainHelp("3m8bg1k3iuT0iU04bw0G6KpFM2qYHUMk69l55JRybGeclHpYqM");
            useraccessdomain.setVersionId(1);
            useraccessdomain.setDomainIcon("bIJGTgyczM3en5ZmWR5MSK0lb2ua590JSRVRfyqDE2DzTqTkNU");
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccessdomainRepository.update(useraccessdomain);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessDomain(EntityTestCriteria contraints, UserAccessDomain useraccessdomain) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccessdomainRepository.save(useraccessdomain);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessDomain", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessDomain", 192986));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "domainName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "domainName", "VwpcRh0NqyEqGLTk1127RiexvaFXY05U4JkkdJAJ0dnQlxiBokYLLGx1csylLxE5z53FPp18ikkxJCNxqINpRWpqsflWiNmgcxv8ZsFUBzV5lBaopxG02XM1LkqbELqLnoaTe30u4i2fiAbsxvw7yyeuHqehIBYusT9GRjmYZh5MmvFKOI9qwjehLPgajUKAxTJmNvqvQsNj6DMkLWdXrzwimPH9NPMkrkJsmWy2HfY4m363A3iSH0dV5CGM8WCAV"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "domainDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "domainDescription", "eqIpZQ4sEZn1aJb3NxWznOX8MrzsKkqtDwwXpMiuDzhc3zKdeH6QqqzMV1c8VMtd56LITmJFcfb4QPL6SKNbmcMmB31GFFHrAI0d0T5ErYmsjfYS44ehqsn6Y4wQhDQfx8wPJpgBMcAahneEwmL2ThSMYHWJYQJKCwz88QV05w5yRLO0zMqqmzTkOLkZVP9cc9dpIQPqU9c5iJe6en1Wss3OiWd2znK5mPBDZBO5iqhLOoOFr6nPCp6fDxfGrMyUA"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainIcon", "KmyCJgncOBr6JTqmd5WiAyI1GNH6CERFXudnTMbF6FdlM2u4XujK8NK4Ehd72TQlMstuviMGYyotiDAYSk7Cv2qBqsFvw1DaNAVZDUbZhqymaqYvBCBoLewmJPtkVDEkbpmaImVI3jgTRJCg6aXSSAuIShEF6WNv9p3QFg6f9EFqGQ06o1mDQC8TsPwlFk0QZqfuilEwRIUHwMwIvCwbmkWwbRQuqE4op9dYhE9RuWGuy0S4NQSv1MGG1g24gwS9Q"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        UserAccessDomain useraccessdomainUnique = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessDomain useraccessdomain = createUserAccessDomain(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccessdomain.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 2:
                        useraccessdomain.setUserAccessDomain(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 4:
                        useraccessdomain.setDomainName(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 6:
                        useraccessdomain.setDomainDescription(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 7:
                        useraccessdomain.setDomainHelp(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 8:
                        useraccessdomain.setDomainIcon(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 9:
                        useraccessdomain.setUserAccessDomain(useraccessdomainUnique.getUserAccessDomain());
                        validateUserAccessDomain(contraints, useraccessdomain);
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
