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
import com.app.server.repository.aaaboundedcontext.authentication.PasswordAlgoRepository;
import com.app.shared.aaaboundedcontext.authentication.PasswordAlgo;
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
public class PasswordAlgoTestCase extends EntityTestCriteria {

    @Autowired
    private PasswordAlgoRepository<PasswordAlgo> passwordalgoRepository;

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

    private PasswordAlgo createPasswordAlgo(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        PasswordAlgo passwordalgo = new PasswordAlgo();
        passwordalgo.setAlgoName("xo556VuvCjrG4G7trln0jJFwvJCtYa1Gv4wAtU6iDpmmIISSsT");
        passwordalgo.setAlgoHelp("8yX7yI2MUBrKHG7RqAK6I0tervxM2N0VddEVu8WDk1ltWJP2n3");
        passwordalgo.setAlgorithm(3);
        passwordalgo.setAlgoDescription("tfUQ2ZgJ7Xx8bQjbDzwPAvw3nvFCOttNZnvImrGob9dMESho12");
        passwordalgo.setAlgoIcon("NWKY0w2wi2U0lq6AlFKMQ4cMP0L5CCbrp2BseQEtZCnzX2bKWb");
        passwordalgo.setEntityValidator(entityValidator);
        return passwordalgo;
    }

    @Test
    public void test1Save() {
        try {
            PasswordAlgo passwordalgo = createPasswordAlgo(true);
            passwordalgo.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            passwordalgo.isValid();
            passwordalgoRepository.save(passwordalgo);
            map.put("PasswordAlgoPrimaryKey", passwordalgo._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("PasswordAlgoPrimaryKey"));
            PasswordAlgo passwordalgo = passwordalgoRepository.findById((java.lang.String) map.get("PasswordAlgoPrimaryKey"));
            passwordalgo.setAlgoName("G1L0wxhj0cD3yMtcOlO9Q1FpLZsrMBllci1FYX19wyrsq6WJNA");
            passwordalgo.setAlgoHelp("dxHoNOifUr7IhTADUUM9kTzzCBGJYkMs1TtCCwLFbVX0KCsb4A");
            passwordalgo.setAlgorithm(11);
            passwordalgo.setAlgoDescription("lJZgID6bDzgheLtxnsrFCvABwFr3TIOfNZNTOB5oSsdqDkp5tl");
            passwordalgo.setVersionId(1);
            passwordalgo.setAlgoIcon("ccxOgMLF9JvnM7OIRd39QvGYnmlBVXobDfmF8I2vR8Qa8Y3xbt");
            passwordalgo.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            passwordalgoRepository.update(passwordalgo);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("PasswordAlgoPrimaryKey"));
            passwordalgoRepository.findById((java.lang.String) map.get("PasswordAlgoPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("PasswordAlgoPrimaryKey"));
            passwordalgoRepository.delete((java.lang.String) map.get("PasswordAlgoPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validatePasswordAlgo(EntityTestCriteria contraints, PasswordAlgo passwordalgo) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            passwordalgo.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            passwordalgo.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            passwordalgo.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            passwordalgoRepository.save(passwordalgo);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "algorithm", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "algorithm", 18));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "algoName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "algoName", "RHikOn7p6qMNKtz3FsJ02Ceq2XlbwmjSpKAQiQIlmEmuzY7RJx6WEtYcvVruJwWH1qIuXuUvRlFasOW7GYcqYa5l4TCeD2GRZh6qCC47u4bYd9FeK4pulBeBzhCvdrDblJsKaOvQPnV4T4Ayt4N92JnKYp8SX4TWB4R2ik7FS83HcGEj7MZWSRaxnOVttSznHWxOIHXZdoZNqlA2xIpXKlBnAq3QNcsJKkCejxUXCkQuuIgUnTQ8x8lzUkuDWxjCY"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "algoDescription", "aNWWPwqqI6EiMrKTP4WsABvTSK67AQTXiWcGwappEJZ1NWLvcPk56oOnwln4IespxJ0SW3JLtUrvJXg9jCMtFiKsBJizcU2iTZ9SsUxgZzIvzlAr9HflNIzKqZLhs6FRksoLUNS8m0gqyjoKWGCj4vKpHmqYnhYYoOz2mlPsv4X8OBvAmzlwDfFhmeT7Em0HHWBE5xtid9yC0g5vA0XVHtsDIJE5RVkYNOge4JdYmTC7DNt9iIyrKYWLbdOq6DNxz"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "algoIcon", "0z0gMtuq234lCjkZGd1ZWuBZGzNOL62fWOVeZAGAZwkwSwzBIwaMIB0rhOVoNOoVV"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "algoHelp", "emP8U5gIkxVKQ4o8cj8Xm0Cvvp7wEbBHpSu8Ly6RTKDEEnEoBisjSNeSJcpVhtm4BMSCOR21gNAxwQvqNOm14NNiKjCjggrXK3XGvnKiTYkKy3oXAOC6IiupJLTWOw02M8mtufG4Qa45FeAf6Px4XmZpliXp9MwxL1uoWXGQHwFHVIqYvemUNBAHql4DAC99XBlm02GaKWKTUDQ5ntDZPEK9AdEwRcjKA3DhT7M4PPnmn1xIgjfBbt0H3EK1jwBU8"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                PasswordAlgo passwordalgo = createPasswordAlgo(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = passwordalgo.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(passwordalgo, null);
                        validatePasswordAlgo(contraints, passwordalgo);
                        failureCount++;
                        break;
                    case 2:
                        passwordalgo.setAlgorithm(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordAlgo(contraints, passwordalgo);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(passwordalgo, null);
                        validatePasswordAlgo(contraints, passwordalgo);
                        failureCount++;
                        break;
                    case 4:
                        passwordalgo.setAlgoName(contraints.getNegativeValue().toString());
                        validatePasswordAlgo(contraints, passwordalgo);
                        failureCount++;
                        break;
                    case 5:
                        passwordalgo.setAlgoDescription(contraints.getNegativeValue().toString());
                        validatePasswordAlgo(contraints, passwordalgo);
                        failureCount++;
                        break;
                    case 6:
                        passwordalgo.setAlgoIcon(contraints.getNegativeValue().toString());
                        validatePasswordAlgo(contraints, passwordalgo);
                        failureCount++;
                        break;
                    case 7:
                        passwordalgo.setAlgoHelp(contraints.getNegativeValue().toString());
                        validatePasswordAlgo(contraints, passwordalgo);
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
