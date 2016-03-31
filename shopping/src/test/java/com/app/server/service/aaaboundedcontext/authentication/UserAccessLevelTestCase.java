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
import com.app.server.repository.aaaboundedcontext.authentication.UserAccessLevelRepository;
import com.app.shared.aaaboundedcontext.authentication.UserAccessLevel;
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
public class UserAccessLevelTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

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

    private UserAccessLevel createUserAccessLevel(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelDescription("tnUJ94ZqiwixYJGG6VoRGk32LAdrtyO3oaE6PMvQvgsZXX0tSi");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelName("Il1BDNmkbKmbPCczgYQw0JTI21D1dNQsqpjfaoTYpmNpnYgMIw");
        useraccesslevel.setLevelIcon("bsvkosRxnEQd5lHOjMWqKal9WiEQ3vlvEr4rG5SWSDDrz8DEbZ");
        useraccesslevel.setLevelHelp("om9baSXGruNawy3Xz3apyYLuPXmGLoAK7IcfSE2AEse3cF0Twm");
        useraccesslevel.setEntityValidator(entityValidator);
        return useraccesslevel;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessLevel useraccesslevel = createUserAccessLevel(true);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccesslevel.isValid();
            useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            UserAccessLevel useraccesslevel = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
            useraccesslevel.setVersionId(1);
            useraccesslevel.setLevelDescription("cvAdxYHcUp2ldc4SeZHLbRBj6wN4WgBq8F2TuLfnPyJ3XYYYuN");
            useraccesslevel.setUserAccessLevel(33002);
            useraccesslevel.setLevelName("iw42E6Rm2XkbwAUaFxuOz2oiJeiFrXeCOtFInDgrOk41OwXwhd");
            useraccesslevel.setLevelIcon("Cjgj3XvzGZ0YkedLESXS8geNhrxHDrcnHV0bYGa9NhrbCTEVP3");
            useraccesslevel.setLevelHelp("2E3fpbqObaYl85sQBCAB8OEYIUWfm56JAIRTEcdIxvLmNeYclg");
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccesslevelRepository.update(useraccesslevel);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessLevel(EntityTestCriteria contraints, UserAccessLevel useraccesslevel) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccesslevelRepository.save(useraccesslevel);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessLevel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessLevel", 169908));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "levelName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "levelName", "XOCqlrT6zy2PPG9WKmaJRiuG46Kh50iehR3KMJteTWpW8FDWccLVfc27p71tnmQFnIbDeHWa6L8z0nZPyLmaA12N7FJqlGW5nKbmAZ64pfPGRxb7Se0ZSNO5o0JifXeUhrwFuj5Oa6TM7H2aGm5SkzCJ0IHgjoqZSe9UvTpzKqHgti93BUmDLXcX4n8V5TGuM0zUybfAhWQ8QjNv1BXuJaxpWIMKZi8fXbZCkA8njCdoU1daw54T1GZ08Nh5j6FPJ"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "levelDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "levelDescription", "K4PkrmC4zlRUQD6dAIgUsWfmFFW7TlRBXCtCxTI0hqe5iKdKQcoCtyYbW4a6r8zOGWepCNGONt9y88s5oqKqOFJt7RWr5wXD9tQvPxtHdHTIFdKFXKPhMipSYeMhdNCs7M6hC3a9sIZ5Ku5exWTZKL3H8M6smrSzmyccuqOMZKPX497yzFWnd4B2MMFTyEUsbveQByHi2W1XuDbYpySOMrPsI90tM3GIuu2OMNkTTyYNZpqYTvGfaS8pUkzVIf04W"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelIcon", "qS2EdUrwEiuj4KAd9QAgmWORfzpEHce8AxWq9JwRlw99XFaPSuJcnzEpZJkCtgldM6BTePnkdbuY1raWAAFJirKzzZyicVsK28aog2kS7qnVZMy7zgXnjvc4eHOQtrOOhUuB26VNL0LwEPARtXzIFkhR0gF5vjXx2iexoqAXt7ERAAemoMea58i0hz6zq5ZmJjtgk18eqDcs3NERbPSMLm5WtEDPv17C1oX5nCOkxrEq44aNfvQyVlR2kC1l4c89Y"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        UserAccessLevel useraccesslevelUnique = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessLevel useraccesslevel = createUserAccessLevel(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccesslevel.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 2:
                        useraccesslevel.setUserAccessLevel(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 4:
                        useraccesslevel.setLevelName(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 6:
                        useraccesslevel.setLevelDescription(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 7:
                        useraccesslevel.setLevelHelp(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 8:
                        useraccesslevel.setLevelIcon(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 9:
                        useraccesslevel.setUserAccessLevel(useraccesslevelUnique.getUserAccessLevel());
                        validateUserAccessLevel(contraints, useraccesslevel);
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
