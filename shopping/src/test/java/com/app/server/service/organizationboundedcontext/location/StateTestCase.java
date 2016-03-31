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
import com.app.server.repository.organizationboundedcontext.location.StateRepository;
import com.app.shared.organizationboundedcontext.location.State;
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
public class StateTestCase extends EntityTestCriteria {

    @Autowired
    private StateRepository<State> stateRepository;

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

    private State createState(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        Country country = new Country();
        country.setCapital("PRLSo2XTKV7X2QIzsWIamQsR9yYkmUAj");
        country.setCountryCode2("DGm");
        country.setIsoNumeric(815);
        country.setCurrencyCode("YZe");
        country.setCountryCode1("huZ");
        country.setCapitalLatitude(1);
        country.setCountryName("kA5RJkD6QI1TlMdYlk7SoTDVr2hFc4jPawCURjRf36thBbjUsq");
        country.setCurrencySymbol("cOw8o2ENLIkHRVF56wX6kVDRAEuGEd4W");
        country.setCapitalLongitude(5);
        country.setCountryFlag("XJWQ7MTH2CnRNZVj0xiKYQQ9uaeGPdE1g7Myl8zXHRWWgW8RdL");
        country.setCurrencyName("0LHWVVKbzZs0QYJ7MBUxvzsM7Ch8HC7NAACTF2emd0GgKmPe4Y");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateFlag("5HUhmf1JQU1dA3ntEUToQYYihox7vn6ZZar4KiLWjFUGaK5G2P");
        state.setStateDescription("3OlRzjXl5xAI170NxPsiANB3fJd3cNkO2uvBDtczyU2jh1v7UT");
        state.setStateCodeChar3("aK2cMcIMefmStL0popgnZ0vpeQyyUwD6");
        state.setStateCode(1);
        state.setStateCapitalLongitude(11);
        state.setStateCapital("8RoYLGMes3WUMaCPUKGPKeaMf2lM7opIjHUejDSaNAUZ6DO15z");
        state.setStateCapitalLatitude(2);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        state.setStateCodeChar2("dfdDSgjOtzfIBz4KO6udLvmuFxjXunG1");
        state.setStateName("m3jwanCTFoaDATQwaDkgIjCbehxRVNf1u876w5dkyRwBKUuUJo");
        state.setEntityValidator(entityValidator);
        return state;
    }

    @Test
    public void test1Save() {
        try {
            State state = createState(true);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            state.isValid();
            stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            State state = stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
            state.setStateFlag("QDDF5YhoX6eqqth48tQbNwnJkmgZaAv5moCiueTL5vatLtE9JM");
            state.setStateDescription("oD6rPr3TS42HmMr2FDNzkglYnTlL2lwvnofst75quPfxTdjLCS");
            state.setStateCodeChar3("TkmLg7olFgKS5cSxYz1Hizg2Q04cWVXe");
            state.setStateCode(1);
            state.setStateCapitalLongitude(9);
            state.setStateCapital("KqOtRn8o0Uljt1qqFHIaQZmOzuZ87XIMXHDUoOFU3XEEk7IMRD");
            state.setStateCapitalLatitude(4);
            state.setVersionId(1);
            state.setStateCodeChar2("QEYusiU2DX97VNtKGUJKRgtZORAmwtpP");
            state.setStateName("8JprGuNL8PinEkqyQl7K2brGumUAajvkSvNiCGomhdEG6rRQKx");
            state.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            stateRepository.update(state);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<State> listofcountryId = stateRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
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
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateState(EntityTestCriteria contraints, State state) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            state.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            state.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            state.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            stateRepository.save(state);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "stateName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "stateName", "2mqKtEfM7cweWbbcsvq3dVGZ0QkPaGbgHm0OHZk5bkXyp5xODfe9VZzaI7gdIhhK0bbgvnCOOsNSkIxWbNIXUbHabwnSvOc0x9ROzWh8LbjIcppqgG4aN9s5lzbjSHVGLmwNt6qW0fjPlDSgQRkkhKeNCy1Ts3CaquMO6FTzSEi40W7WpD9M2vaxpntGVvUsFCOjOnlt4LYuuxvueJvJ5NhzUfqbQLBa3OgsAy2VpUr0WjjZqVxqNEJUcmANrgS9E"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "stateCode", 3));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "stateCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "stateCodeChar2", "u9OlCLhOp5UXiHXjxZkQWyCgc7KobRjop"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "stateCodeChar3", "t9LEExCDSLST3ILaaICj17mWnaKO3G3rv"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "stateDescription", "QLykveqke8FQMM6UB4eUy0woEEwl6CbnQgOtKd27oux8ZrCrNBTfD4CM2diuggljkoHEmQOVOOJRnBOHl5KVwVnJ3Xfv0HjjQrG0PZsO8IFcZM2lw4eAHK5JMYRvpRBFdA8On1gkNrxr1YtoqXGN29gIvVDJ0Wlpxr9BSfydS3adjIxh893EHkDrR7goZX6Deb2npy55wlQSnXkds4ToaBms8zEVUNiZANCOsgt9aQjIzh84ctdxADXGm1r3aqQ7k"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "stateFlag", "lnniBYl2w804GEm4fG2TZWnidoH78QjKyg0SVLSbgYm8dyH2oAyr74CpKNnPqwCUIajjA42QAuSDQusnpgwf4a3hj6oyxvInkppWfL4qWzffvbuzuRfOEJLhU5G4tXcqZ"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "stateCapital", "QCdeZ4EhVKG95DpjGK27rdIHAyTUO0FHotXugAwi8NfxA2soTo429Z9daIicQ1sajgo7qkh8VIctaX5xxo6DyfZHzLn8ApBhyqw5atF1bovv8sLe4HKxoplttCoj05vR6"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "stateCapitalLatitude", 22));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "stateCapitalLongitude", 12));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                State state = createState(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = state.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 2:
                        state.setStateName(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 3:
                        state.setStateCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 5:
                        state.setStateCodeChar2(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 6:
                        state.setStateCodeChar3(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 7:
                        state.setStateDescription(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 8:
                        state.setStateFlag(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 9:
                        state.setStateCapital(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 10:
                        state.setStateCapitalLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 11:
                        state.setStateCapitalLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
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
