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
import com.app.server.repository.aaaboundedcontext.authentication.PasswordPolicyRepository;
import com.app.shared.aaaboundedcontext.authentication.PasswordPolicy;
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
public class PasswordPolicyTestCase extends EntityTestCriteria {

    @Autowired
    private PasswordPolicyRepository<PasswordPolicy> passwordpolicyRepository;

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

    private PasswordPolicy createPasswordPolicy(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        PasswordPolicy passwordpolicy = new PasswordPolicy();
        passwordpolicy.setPolicyName("ISO5xhnBbjEzkSBvkjDaRmi1vtLqXFsY1QyPwbRnJ4yblVzvp9");
        passwordpolicy.setMinCapitalLetters(6);
        passwordpolicy.setMinPwdLength(1);
        passwordpolicy.setAllowedSpecialLetters("9O46LwgbGXhX6AZ4lB60ZQhJLy6FlFqx2QrJcoWucbBWilecXx");
        passwordpolicy.setPolicyDescription("1YIz6Yl0Ubl5WJpz4VVc36qOiODctPnNcnn56ZHvv1fEz4dFsh");
        passwordpolicy.setMinNumericValues(2);
        passwordpolicy.setMinSpecialLetters(11);
        passwordpolicy.setMinSmallLetters(2);
        passwordpolicy.setMaxPwdLength(12);
        passwordpolicy.setEntityValidator(entityValidator);
        return passwordpolicy;
    }

    @Test
    public void test1Save() {
        try {
            PasswordPolicy passwordpolicy = createPasswordPolicy(true);
            passwordpolicy.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            passwordpolicy.isValid();
            passwordpolicyRepository.save(passwordpolicy);
            map.put("PasswordPolicyPrimaryKey", passwordpolicy._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("PasswordPolicyPrimaryKey"));
            PasswordPolicy passwordpolicy = passwordpolicyRepository.findById((java.lang.String) map.get("PasswordPolicyPrimaryKey"));
            passwordpolicy.setPolicyName("nomVZOJrPgibkHRsztbQnxWm9a8dnqSrLL26hjiHTez1ojOmQ6");
            passwordpolicy.setMinCapitalLetters(1);
            passwordpolicy.setMinPwdLength(3);
            passwordpolicy.setAllowedSpecialLetters("8IQCVT6yvqHk2RejLhYUokeLcJntZXfj6u44ygOYyHMprBULwN");
            passwordpolicy.setVersionId(1);
            passwordpolicy.setPolicyDescription("pTmawUthdTCZDbtYPxJPxTKzvc0VqVXcrrpUPFtYwzkJsVEy3M");
            passwordpolicy.setMinNumericValues(9);
            passwordpolicy.setMinSpecialLetters(6);
            passwordpolicy.setMinSmallLetters(2);
            passwordpolicy.setMaxPwdLength(31);
            passwordpolicy.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            passwordpolicyRepository.update(passwordpolicy);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("PasswordPolicyPrimaryKey"));
            passwordpolicyRepository.findById((java.lang.String) map.get("PasswordPolicyPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("PasswordPolicyPrimaryKey"));
            passwordpolicyRepository.delete((java.lang.String) map.get("PasswordPolicyPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validatePasswordPolicy(EntityTestCriteria contraints, PasswordPolicy passwordpolicy) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            passwordpolicy.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            passwordpolicy.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            passwordpolicy.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            passwordpolicyRepository.save(passwordpolicy);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "policyName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "policyName", "Lzy47cu2K913Fqa6MqQLYLxtRyHmJv2Ng5CYTbTD6MvQ1CrnuDlKiAQFYGXsqTVbPVw8a1A0dHlN0JX2OWgfr9rOIgaMSydLpQvJkhtux3ULG1fQuhJa53d8UTZWhk2JvuH8hYllP273kofgX0WUYwYqlBDlCjCQjlWMlL5QmiUEYhfoueJJwpNHf2EvmLJVITlSZxpX3dvJGcbB0ApAomQ99nRBiiLmflvnPfMOPGd6o8gwEAh9sVZJ4P74jyLDb"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "policyDescription", "yxQ34xSaiWn6wM32nlNsnE6z8BBzx5ktRK9UG1PzKIpRN7I5Qs5dNLovhHaPvydsBUInKYalLNllsCSAPSCpLpWgdsHdY4HLqAcG5A3lLZbMWXUGgsjYPtL3gR7zPlBYBTpSWB8UWYWgMeAaYjfuOjT5WK7lA4ruQ5aT7iNw8YOTtOFsUtvJg76zaot1V1JKKSmQuL6Saft4SzYTwLQlwJxgx0TivWji4LLddwRGZpo9XvRi9yZwy5bxVmcoz7qz6"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "maxPwdLength", 34));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "minPwdLength", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "minPwdLength", 20));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "minCapitalLetters", 19));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "minSmallLetters", 13));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "minNumericValues", 16));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "minSpecialLetters", 17));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "allowedSpecialLetters", "AkbSMxC6GQG5tx687i7EwgVHV35fYAxLdHLitQENE2DuZfkRvk83AbEk2VHp2Lud6XXPih4qvM4k59gMeNnTcDwiHg0CG4Oghp5kDUDZ9EzmLuZccskZyCjh1ljQE1H0kIc9J9lG66hZkGoe8ffSiF8Ib0k7wrwJnHBYRRDbSwkXcPaWFVdmocvoV03aac79UIo8YQwr6tYmlh09IaC5wCNtsxQQuehtiJsL6DCEF69Z6s7IEvWFSXljHKDVph5qb"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                PasswordPolicy passwordpolicy = createPasswordPolicy(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = passwordpolicy.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(passwordpolicy, null);
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 2:
                        passwordpolicy.setPolicyName(contraints.getNegativeValue().toString());
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 3:
                        passwordpolicy.setPolicyDescription(contraints.getNegativeValue().toString());
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 4:
                        passwordpolicy.setMaxPwdLength(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(passwordpolicy, null);
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 6:
                        passwordpolicy.setMinPwdLength(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 7:
                        passwordpolicy.setMinCapitalLetters(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 8:
                        passwordpolicy.setMinSmallLetters(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 9:
                        passwordpolicy.setMinNumericValues(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 10:
                        passwordpolicy.setMinSpecialLetters(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 11:
                        passwordpolicy.setAllowedSpecialLetters(contraints.getNegativeValue().toString());
                        validatePasswordPolicy(contraints, passwordpolicy);
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
