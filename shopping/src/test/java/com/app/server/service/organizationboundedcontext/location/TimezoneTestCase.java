package com.app.server.service.organizationboundedcontext.location;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organizationboundedcontext.location.TimezoneRepository;
import com.app.shared.organizationboundedcontext.location.Timezone;
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
public class TimezoneTestCase extends EntityTestCriteria {

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

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

    private Timezone createTimezone(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        Timezone timezone = new Timezone();
        timezone.setUtcdifference(1);
        timezone.setCountry("jypCqdkUgKXElUQ9FxtvU6Pn4d0rxfeQkM1ZZDvVo0qy4YwomX");
        timezone.setGmtLabel("yi8SjBrkhS9Bpa5rQUv7URKSu9vMOxJqndlSxemZFmfDr9DWtK");
        timezone.setCities("qf0WeczgYJYwvY32twnUoRmg1vYajMbSw5IUeMR0yCnstU8a5y");
        timezone.setTimeZoneLabel("CQK0caf3dtkwneCBkQvIY9BHrfpjneNJsAdbvgH8cbPqF1Ifa3");
        timezone.setEntityValidator(entityValidator);
        return timezone;
    }

    @Test
    public void test1Save() {
        try {
            Timezone timezone = createTimezone(true);
            timezone.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            timezone.isValid();
            timezoneRepository.save(timezone);
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("TimezonePrimaryKey"));
            Timezone timezone = timezoneRepository.findById((java.lang.String) map.get("TimezonePrimaryKey"));
            timezone.setUtcdifference(4);
            timezone.setCountry("J2QA1UApOTt9dT9Ytvr5193EKSaFA3a9bHxjZq4BzfGSHq88DA");
            timezone.setVersionId(1);
            timezone.setGmtLabel("Y4JWSiOJdZmHlP8BgV5DlL8XZQdLFbXVa6exmtAwEPcX8Fu7X8");
            timezone.setCities("9x2EigP8CNKDZBhau97KhPAqBImEi4aFbPdOzHCjyIu3ZgGuVm");
            timezone.setTimeZoneLabel("PxGJoS7I5OVoiAnqlBUgTAzi5o7cg2AyDH67jVShlEkmUZVMol");
            timezone.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            timezoneRepository.update(timezone);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("TimezonePrimaryKey"));
            timezoneRepository.findById((java.lang.String) map.get("TimezonePrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("TimezonePrimaryKey"));
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateTimezone(EntityTestCriteria contraints, Timezone timezone) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            timezone.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            timezone.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            timezone.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            timezoneRepository.save(timezone);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "utcdifference", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "utcdifference", 19));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "gmtLabel", "VF3ezsbqeqF9gJF8INvmYGcOIGSl5NUIcRUe4mUwE9o85PJEaD0PvupE8r5uGq2SyDD0ewzXo3HEsYwCVWB2yps6Vo28QLsRBrJhERvJzSRtlUGM8VEqhJJIrtkCRZQRCkqBmMNY0D9UGpjzFw3Ss7nsh4zJl2I2zlfpWR5eJdIDtr4JVr86fXCAce4TEwdhwq35iEYbbobxJ2tENpyoWj04TEOqHIhw8jZsWWVS6v0W6QqOclEFOEf2yGjlcB2SH"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "timeZoneLabel", "9uf55egSVbLAOCCD8h5XPGy4HMkRVP3cF0njsxpvWnx7u12v03GL48apja96YUeX9nH0QLq0NX158tnRFjrsHqrHfenL7Pn38g6KmPgfsicfpwIzeCSU1Cxkg6EnkxbxhcvDZLfc93bBempYCMezGPDnAm1I9e1xCMH8a76cCo3xlX1bHml0jMT8LvYMraFQjt3rgDfOn0bNqfhP1Ze5S5Yi2BFVBu75B3akqpzqsi6FnFtrUErQPMC5ugbdHC5TX"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "country", "hFDzB6wGWCfbovxENhxJWzzTkIHVYR7nn68fZ1bmOwh7VreT81kdEtrs8q9yYtahkWmTS4biRYej2fPmXQiVfXn2T8BOrlPGVnDXKfA6BJU9xwJEXZzlUKUpW3B3VKaz8abbccrgwDF0cPZ7Cj5IOKSdXKjuKRCz2JoAGlu4E9CZPufRIN6GQjSbF0rMhySqsZuLQO0effdSp8GW8OeMeI1iRn0fSAyGhU3euzVRpHSvim7MrlmasM3MdGcrCeNY9"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "cities", "szVeS0vcKXySeFfuGcQZrzaajvzfeBhnWVtvwUZMWkG613A9UvzxdW6zDpQnnyWOH1IWBhgjnjCi3Lad5cRB8FxstQOQ3yPWABIp8AYH0W0bIi9l201TFja8cnn0GlosMk4sXIGY4jYRVdbdJxy1KPvq6uAmsQkBCMf3TQdLabLmOvoaPyi6f30p0x10JvqV5pP6opl6LmTFW3mZWvpI366ntKCvCpGTJrUUtGuPpmSsRWRBc5X3oPemg7e5nLWkm"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Timezone timezone = createTimezone(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = timezone.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(timezone, null);
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 2:
                        timezone.setUtcdifference(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 3:
                        timezone.setGmtLabel(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 4:
                        timezone.setTimeZoneLabel(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 5:
                        timezone.setCountry(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 6:
                        timezone.setCities(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
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
