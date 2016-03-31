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
import com.app.server.repository.organizationboundedcontext.location.CityRepository;
import com.app.shared.organizationboundedcontext.location.City;
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
import com.app.shared.organizationboundedcontext.location.State;
import com.app.server.repository.organizationboundedcontext.location.StateRepository;
import com.app.shared.organizationboundedcontext.location.Country;
import com.app.server.repository.organizationboundedcontext.location.CountryRepository;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.biz.SpartanIncorrectDataException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CityTestCase extends EntityTestCriteria {

    @Autowired
    private CityRepository<City> cityRepository;

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

    private City createCity(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        State state = new State();
        state.setStateFlag("EbytLSu6Koy10CrJCZPzAmg9heDn0mVdWEEX2lQXwGgSqbkjkd");
        state.setStateDescription("gsXPhxA2tKSkvWstllLxotgMVJC69xTxxlcG7VJcGG5lsq8b6N");
        state.setStateCodeChar3("gvthA1mEGZmSAJhAD2w4185lpiNGr0A8");
        state.setStateCode(2);
        state.setStateCapitalLongitude(1);
        state.setStateCapital("bbnaiAIv4TJjevdftklQUp0q6NKTeAgxB3xshZWqbCcErSS2c1");
        state.setStateCapitalLatitude(5);
        Country country = new Country();
        country.setCapital("MhEvRU1fAFA10RXHO0ItHa0IdlrFX7gu");
        country.setCountryCode2("I2Y");
        country.setIsoNumeric(488);
        country.setCurrencyCode("LSs");
        country.setCountryCode1("nRg");
        country.setCapitalLatitude(7);
        country.setCountryName("Q6I4sjFbY5lS3lRbKZQuHBeETHsIGoOC2xHC4h8EbEBPIQegmE");
        country.setCurrencySymbol("h7Yq0HGRtgzf1C7jbgmUQTfAC9uU3qBe");
        country.setCapitalLongitude(1);
        country.setCountryFlag("oLSykchNji1yAcU1frGcIpijXhh0yJCSmqkQ0b2PUXsazwJ6Ib");
        country.setCurrencyName("HIPezR7bEXKGRghLBdnOu6b2rvFaAihtqc94q4J6Eysmi2u9j8");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateFlag("XZwm9SEajcncoVgWjgquNq4gnc9wQQeJkqPHtbpl4rxVVWz6fD");
        state.setStateDescription("HIfXpcSehRj6CM2Fkn9mJlNZTgBhkzzmuXt1i8DfgOIIcgjVcT");
        state.setStateCodeChar3("0x1gPGXypCTZr095pqm88SkMclMJv6oz");
        state.setStateCode(1);
        state.setStateCapitalLongitude(4);
        state.setStateCapital("Jr9YHYFbToXfzbuibQexSrNrnT7acdks04DWlfGQIYrHlQbuAG");
        state.setStateCapitalLatitude(4);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar2("Ps3ATPhwsFmMeSxRhA5wr9JIU9ENKIhu");
        state.setStateName("aP7HCzVP8hTmwmPH7DN9qIIPnRT9fUPmOZBxUx0Rp85kcyHZER");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCityLongitude(9);
        city.setCityCodeChar2("1Zugnoy1WOqS8H6EgDSGnLlX5p6rTrlb");
        city.setCityCode(3);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("8d39QXndJygCi5lwdaMByHH9o1x3jOuOW6prhBRtgk6DDFqqpu");
        city.setCityDescription("AxSw8LQj2HWPsPgqnHAMlJNUsl1J9pqxJ0nj3aGf5iV9paSdJn");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        city.setCityName("cHUuWVlmQpKXKvYBHvSarDLnFXPEE16AsiRzLvWl4ehCHQpg6p");
        city.setCityLatitude(4);
        city.setEntityValidator(entityValidator);
        return city;
    }

    @Test
    public void test1Save() {
        try {
            City city = createCity(true);
            city.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            city.isValid();
            cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            City city = cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
            city.setCityLongitude(5);
            city.setCityCodeChar2("kYBLnILkCZq8rzmKU4DIS79oYIqTNfVw");
            city.setVersionId(1);
            city.setCityCode(3);
            city.setCityFlag("qwctOExdflPkn0X0zlEmJbsGJ378wfS958Q07y1zClRfpDsD7H");
            city.setCityDescription("DviV5Bz3AkuBhVbdv0ZgmZ1qKsVlfvUGyo01Em6hhjcCYhomQS");
            city.setCityName("InrE4A5L2R5Agp1sMGGialB8du1Bc7fM1PhO1T49lJbd5Ek5XL");
            city.setCityLatitude(9);
            city.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            cityRepository.update(city);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<City> listofstateId = cityRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<City> listofcountryId = cityRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCity(EntityTestCriteria contraints, City city) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            city.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            city.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            city.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            cityRepository.save(city);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "cityName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "cityName", "5w0hUn1BK8dQskvRboA4jzhs57hPtc38zH5GAzrSNqxAkSTGxiLDVvf527GPPDXXGcNn6paGACIpVF04KFjOAwU2142jwpigHMHegLf5pMQSO1sxWHu8M9alqqQPFyp3No3Rj6p6kV2DzRUScHYoQEwL8twvWFPqJpYWTN36HCqCqgf8zuMmWVBLugo8nwSUrIa6i6v6UKC6Ha4L9X3isy5RBQXqpP94Ky0mkXWdWm3GEtw2mTpHa7YSbsWKIcncd"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "cityCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "cityCodeChar2", "1hQX71C4vBUEtz0OzdlLqNWQchmanZMSs"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "cityCode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "cityCode", 4));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "cityDescription", "DiiRbgKYkKiFE3NKfAj3un9flKri4qaF43onJwFsU7CwQHWJENaBe7i1mJQP0qIpsyHntA1ivNvpEryvgnlXE4Rlc4XamYUUH1hXmk1vD5G3takf2kwplk4fwhsFjFFFK"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "cityFlag", "82SDW408Nc2viPcjeF4It3EkYw6qAKL3Rca1jpmfpJKzGjeXuHkxNw1awHFc5NZMRYs3OzeeQZNxoAqASMrv4VhUBjYi4ZUr7uc8ICQB6lgHHKU19ASxdSB8rTSiiRhPj"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "cityLatitude", 15));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "cityLongitude", 15));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                City city = createCity(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = city.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 2:
                        city.setCityName(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 4:
                        city.setCityCodeChar2(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 6:
                        city.setCityCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 7:
                        city.setCityDescription(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 8:
                        city.setCityFlag(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 9:
                        city.setCityLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 10:
                        city.setCityLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
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
