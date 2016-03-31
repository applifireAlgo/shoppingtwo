package com.app.server.service.organizationboundedcontext.contacts;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organizationboundedcontext.contacts.CoreContactsRepository;
import com.app.shared.organizationboundedcontext.contacts.CoreContacts;
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
import com.app.shared.organizationboundedcontext.location.Timezone;
import com.app.server.repository.organizationboundedcontext.location.TimezoneRepository;
import com.app.shared.organizationboundedcontext.location.Language;
import com.app.server.repository.organizationboundedcontext.location.LanguageRepository;
import com.app.shared.organizationboundedcontext.contacts.Title;
import com.app.server.repository.organizationboundedcontext.contacts.TitleRepository;
import com.app.shared.organizationboundedcontext.contacts.Gender;
import com.app.server.repository.organizationboundedcontext.contacts.GenderRepository;
import com.app.shared.organizationboundedcontext.location.Address;
import com.app.server.repository.organizationboundedcontext.location.AddressRepository;
import com.app.shared.organizationboundedcontext.location.AddressType;
import com.app.server.repository.organizationboundedcontext.location.AddressTypeRepository;
import com.app.shared.organizationboundedcontext.location.City;
import com.app.server.repository.organizationboundedcontext.location.CityRepository;
import com.app.shared.organizationboundedcontext.location.State;
import com.app.server.repository.organizationboundedcontext.location.StateRepository;
import com.app.shared.organizationboundedcontext.location.Country;
import com.app.server.repository.organizationboundedcontext.location.CountryRepository;
import com.app.shared.organizationboundedcontext.contacts.CommunicationData;
import com.app.shared.organizationboundedcontext.contacts.CommunicationType;
import com.app.server.repository.organizationboundedcontext.contacts.CommunicationTypeRepository;
import com.app.shared.organizationboundedcontext.contacts.CommunicationGroup;
import com.app.server.repository.organizationboundedcontext.contacts.CommunicationGroupRepository;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.biz.SpartanIncorrectDataException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CoreContactsTestCase extends EntityTestCriteria {

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    private CoreContacts createCoreContacts(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        Timezone timezone = new Timezone();
        timezone.setUtcdifference(4);
        timezone.setCountry("dNG3gTELswzui3ijjPxwJW43bqsGkdUoPltgsY5Iwnj3mlo2UY");
        timezone.setGmtLabel("J87uTd9u787IMpyO3HURvxQ8zNSCj3eXXaqU2yEBlg6IbjsT7n");
        timezone.setCities("0FSdl9cb0GsgxaxS52Y9NukB49AyjtLudEEhmR0EwxkhPBeZkc");
        timezone.setTimeZoneLabel("xI15ZB1FEcu6FuW06dkvMMnkVqQlTdkhKtOu7QTKs3ZwdtrmFz");
        Language language = new Language();
        language.setAlpha4parentid(2);
        language.setLanguage("aA5feVfgCJAEPehbYiGON8jL9DSzDaibF67veQp0PlQmDXYVkM");
        language.setAlpha2("5x");
        language.setAlpha4("U0bH");
        language.setLanguageIcon("rspdkXWIJhJ30jRgmSPD2DOvsncizkCdZeoCPd8t44ek5XXsnN");
        language.setLanguageType("1Q4yodI2vh6EQgcabLAQM7s0HdmoUkmA");
        language.setLanguageDescription("AGEoGhuYhtwHznj6VXa26G1DZUadHMyW9DaMSakQJRNZPLKYln");
        language.setAlpha3("PWr");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("ZUh9t4YKaGiaNkKqdXictrqun9T24ocnf00wEq9UVOz9Ww4AQN");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("TiOFky9Nygoxk7k181CyTm20xqlomtufTUULD1r46Zf2AUgnlD");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        corecontacts.setMiddleName("3UYuUWofRXhdALrFS2GHW9RIftn1sfkysocJxgfx6rNFCALQCZ");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setLastName("438DQyjYljX5MgsF0c3VJYMMc28Xaq8n59KZAvhFRhf5QpWllj");
        corecontacts.setAge(86);
        corecontacts.setFirstName("GDgCyeM7NTQkGOjvaA23W8uhj3nAeqSVp4JAoS1HNo9MWe1OlF");
        corecontacts.setPhoneNumber("9tkorPQZg0G331R3IpMP");
        corecontacts.setNativeFirstName("OOH9QYK0vEBgQPus6I66QmNrO3zrgqnegVWIY7lfNC1xg5sIJy");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setEmailId("zKdbYNVDAbehdgnHIBuLri7aZNnmyl26L2RHyR0xrnw5fIQLgw");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1459432489195l));
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1459432489195l));
        corecontacts.setNativeLastName("xhYbOUEcrBSrQjfUHqdeEuuIN1y1y09o4BEgRiKnqxzOIEPOrY");
        corecontacts.setNativeTitle("mpgzH1oumLxiOksdogDZfnwJdmCYRFzpIp8UG4bYegGZ9aB5VJ");
        corecontacts.setNativeMiddleName("DgYzkj4KNvnArhnHJAvES3AyxuAyfZyneix99mODIWxCdqYvkR");
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddress2("pylNDgY22lUAILTbcLUI3vLb8cq3CN2qEBcTYMLEtwFCRmUoNY");
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("UURyc2L6e2nxlnNHgRvObzB51S1HHeFhyDsPoa3QOzJ8kHGTIW");
        addresstype.setAddressTypeIcon("0pEZMp9X9ALf1GjEzxp97NFh65wyqnjRZ5JCn8iSvKJmPs02a5");
        addresstype.setAddressType("syP1f6tglsEbQBU7Q1jHmXowLWbeli78jKIrfdWUfXdlpkCl9M");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityLongitude(10);
        city.setCityCodeChar2("z4KamPrV0wpYocASLkKrWV3M5rtaCQuP");
        city.setCityCode(1);
        State state = new State();
        state.setStateFlag("xWLonKS9bo74lyY6qQflao4myBxZ9efNMFTgmDOP8GyDdDIsWD");
        state.setStateDescription("ovzyeFIfU8of2Uy6XqFwbzHRPolajWTt1icfo01Wm9N6Gr0xSO");
        state.setStateCodeChar3("tdWku6REStBpTKnQp6WNmDFWSZlbagEe");
        state.setStateCode(1);
        state.setStateCapitalLongitude(8);
        state.setStateCapital("hq2OIh2AayGLjQn4za3YXkzURxF7kh2uzMjl7DBSaWovb7kNRq");
        state.setStateCapitalLatitude(10);
        Country country = new Country();
        country.setCapital("DwdXkZCS2YlEuOEBcdPhvjgbplhsY5iU");
        country.setCountryCode2("wak");
        country.setIsoNumeric(986);
        country.setCurrencyCode("LH0");
        country.setCountryCode1("J0K");
        country.setCapitalLatitude(7);
        country.setCountryName("iKvWntN9D2BoIZT2ExT9HKj5KXIb7x9BALd0211cd8IMouM4l3");
        country.setCurrencySymbol("oKUiQfdheEM1bkkDOfXhIxSpdPHmT6Z8");
        country.setCapitalLongitude(3);
        country.setCountryFlag("LJyfFumxgST4H0lcOEgwhafxf2VnlQWi8kDlWQc5dtC2sRS7cQ");
        country.setCurrencyName("thcUMo17O4M23coAD8IyYOJ45E5GKD6F3qfhoxiu6wMFSiklnO");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateFlag("POoQuUs742y4X1g3WIFdM7DhMlnSOrVVqgDytonA4z63oodlLE");
        state.setStateDescription("pNg60LojUie58TIO7Gg9SHsXX0idO9Ux7oG7oBOkSTlEph1Wm1");
        state.setStateCodeChar3("YllfAU25ZcwsZLfqkstAEbmwCYQNxTaF");
        state.setStateCode(2);
        state.setStateCapitalLongitude(6);
        state.setStateCapital("uW8DEJfBG6mTJrzxhoxE8C6QoHeGIfk2PsDcHYQYtUFnEqyx0I");
        state.setStateCapitalLatitude(4);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar2("aCl9C4oO8wYC7jCiod9I7Ilo6kyMk35R");
        state.setStateName("sGOyEZJkXNlwxoSsAlEpo5LeZY3ROX6UC68z8jgo1M8qKvp9o4");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityLongitude(10);
        city.setCityCodeChar2("bDeSD7YfSccpbjvYTIlm8ycMkhlhHCbQ");
        city.setCityCode(2);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("BGNDMpYXaRbPc9ngypNQvkNflajBdw1q7tsm2L3gUzzPfTM29d");
        city.setCityDescription("hcC3tLO2LRFznIdJF5lS48sICQ4tTA6jQo5vUma0oBDFihSkiK");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityName("DwuvrGNGxkBhvIrSUxJyo6Q3a9iufijR5WiEoFPfX7XQhw32Hi");
        city.setCityLatitude(4);
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setAddress2("vbBxRFeAVzWCyM2kynIlMvbPlsmc9BIyOiTd8RPsD69PEicSly");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress3("PRRQaJDCrmPpxj0oiiKqGu5QviKvIhjBhORUOtOrZ1rb8OKmS4");
        address.setAddress1("g7Kbha2eJU8TkLWUxvGK6arRoRiu6d0sncSmmK48qt3Hd6P8a9");
        address.setZipcode("NOWUM9");
        address.setLatitude("jlEPskqUqLWXvolgwQSP0Jp0lKWhotq5z2quan0BSAF8cxt859");
        address.setAddressLabel("2v4t2EYntgU");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLongitude("X5BpJOMMQlnWDieZ3yGlaXPnDSvcBko2um5bRQcX0U4BnCAOD8");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeDescription("yBMNT3kl9rgA5ww2abqCaTRPa5IYWRBwgSQju4cScg7Vs0osP8");
        communicationtype.setCommTypeName("rMcRn2lVW1Uhr57nUId2cIEUQTuMSGx6oNYVhr5TBeSlANVj3a");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("GNa5qV9pgtLCvww9vx7PYkBxDTAQXQzaPrv6pE4aV1kDL0zXQK");
        communicationgroup.setCommGroupName("bxTMKZlyJuGFHEnsLsVhRe5CFxy8Nt6enaGDXEsPgLYlEl4ODD");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        communicationtype.setCommTypeDescription("sHHMxjM3YHxE2AZJ5RvUYGXqs3eaqtXncF4fFdH4Y4URrDwgMd");
        communicationtype.setCommTypeName("kJHgTyp6WYlmvTwAmYcBcMclVq3FMwK4MRLUheNxMnjafNjYEg");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommData("");
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey());
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        corecontacts.setEntityValidator(entityValidator);
        return corecontacts;
    }

    @Test
    public void test1Save() {
        try {
            CoreContacts corecontacts = createCoreContacts(true);
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            corecontacts.isValid();
            corecontactsRepository.save(corecontacts);
            map.put("CoreContactsPrimaryKey", corecontacts._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private TitleRepository<Title> titleRepository;

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private CommunicationTypeRepository<CommunicationType> communicationtypeRepository;

    @Autowired
    private CommunicationGroupRepository<CommunicationGroup> communicationgroupRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setMiddleName("4wQqEFs9WSLGXIRhUH5gZjp0iN2G8kAZ52jUJXgjg7UXltcx53");
            corecontacts.setLastName("p50OB9BQUzKUgPyLhvtTSpxLfj80YgZrS0gXAS0AiWvbWjt7aA");
            corecontacts.setAge(121);
            corecontacts.setFirstName("8Xm2IHf4lAJ8H2douCgcdar23va5SOw9XhpIb376xm1mTUSqRu");
            corecontacts.setPhoneNumber("1wRmA03z5QMGknhckUCU");
            corecontacts.setNativeFirstName("2Qy9CQfDeobM36pMAndGRWejNw8WbHzKOjrhNgp55smJu0HE2R");
            corecontacts.setEmailId("oPVTfjLy1TzJtxmJgzuldexL5avhZe7qxDEH5WiDREIirRX8k3");
            corecontacts.setDateofbirth(new java.sql.Timestamp(1459432490689l));
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1459432490729l));
            corecontacts.setVersionId(1);
            corecontacts.setNativeLastName("z0q7esYSuO7WZr1fAyPeULCDL6qUTnIe0fJI2rN0vZXkPbEAti");
            corecontacts.setNativeTitle("K5s6DP8gcSrfzg7Kj687spIMEIKs1XY8qwxRR6dynBpfCaxcfj");
            corecontacts.setNativeMiddleName("c6QwzcHoA47lH2JDfnViZbH5J3rtmf0DtkycVbCMGgAZcCyVCg");
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            corecontactsRepository.update(corecontacts);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBynativeLanguageCode() {
        try {
            java.util.List<CoreContacts> listofnativeLanguageCode = corecontactsRepository.findByNativeLanguageCode((java.lang.String) map.get("LanguagePrimaryKey"));
            if (listofnativeLanguageCode.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBytitleId() {
        try {
            java.util.List<CoreContacts> listoftitleId = corecontactsRepository.findByTitleId((java.lang.String) map.get("TitlePrimaryKey"));
            if (listoftitleId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBygenderId() {
        try {
            java.util.List<CoreContacts> listofgenderId = corecontactsRepository.findByGenderId((java.lang.String) map.get("GenderPrimaryKey"));
            if (listofgenderId.size() == 0) {
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
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.delete((java.lang.String) map.get("CoreContactsPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCoreContacts(EntityTestCriteria contraints, CoreContacts corecontacts) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            corecontactsRepository.save(corecontacts);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "firstName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "sOek32QH4iXhPtMpbsyHKcuXXy3MXOJgQmWcNT1MSTVLZNadvic7ooCj9GMfKrcazU9aT52jAW1KiGsc7hhd5n933K34DWHrwd5JYG0EbwPgABS72xfRH9OMFIq3ai4Q0I8j6Z21wQELUsB1yzur8ztI3NwXciWf01cenCLID7gOIacRGvYBWfCryDRIlAbsjwUZkYynBcKTWK9lvsqfei5iuLn0pSgizIo3pbIktx315Oz5oVumyKTmvG38XiWCW"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "Xu02eVwzm9kH4OVxpiQYsFxVJKdcwyuJRFwWPyUdSnYWMraG8ZPLhbGxV07guWNYNmrd2boENcKjwKed4nwv7PUs9A0imlxHD1IrB2Cq8kT6BZRT2wFsyUxPAqDt0LlMMdocotYF1djT6RLAlK6xwWLMHJiEg04Iimh1XoonPisL7OFQtnvgfttkBVb17NnWWnJKOSUoAo76ttrMw7MrXkSeF7FCrPdDCuNhxV5yR5y2PqRZVOTTI5sYAufBBm2p1"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "6zDOVA91ygmHKIvJ9cGNJmLd6Bjz5iz0wjeUkh3DdpGi2h3zcvI7itQPNDOmhRKGwE6bpCYaRlCMyJ6VNGZSLBhYOTyIb8WqMzcsSSmlmF2VJBh8WvudjmXHm6QZmz67mmgDwmrJzjd8wCRSvivaEBdmPFm2rLrKO3QHkXMlny3NORYkBw4u5T2laqmTsLlqZz9Wv5VacLOjgLxEYl4v42qnIXCrcFViPjDRNI2JSGbyKGepeD9Er05SJp3y6rcjc"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "3IRHDgfqRKU3jOqDjjzEEPWMVW4DM9sFF627Jiu1QrR74qEGW5Cd7rWJLUL47wJmW"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "Yl18guLcpZ6omWxKBVbyThBrLEDGVd4i4XeKDqBqMII81P9hbq01z8TNodR8Qke9cKF6YAWMmxdaI85AqtbCGKAQE5hEO94CvgWxSdUGm80ZgatpS8bO10g7BaaIvYCs7Ua8COWUAAX0JsfMXrheBJK49ef0JuvtnDY90v59ypzxSrm6GiycRJyBdpeRbGd9VzLsMaZWRwrSoEuGbBCBVbSrVcSIoR9UbgGErSOL5DA2FFruEj8EfhOdXVq1zmQbN"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "0Wi21KhcPdhoDJgUk9SwEOTLrzlZklpvcf6YJHCAOOcCkYtTDzkTorpVpsngchcsG8wKbmuDHO0JfkJT4dvfyNhqXU1YrMu8rKkMcTYtyCvMISXGSNgXiLuKBhL2glYQh8S9W4CqZXKI6k7ofAgdxCZptAMFQG1kTjcw89PX69rLKKtqiwhMnFwSPeP6y1viJ5VXAo7V9P38fKQv0Q3tdsFtrhwMuuAtDf23RmUHuH16nGXDFfAIwE6NDXz3pz8rt"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "Wn1Kdll75n4CduudFDS5LeQfBFoddgI77wdAJDXGbHOpPpGOLz0nvMUvBxWLYzwd9nHfqknltS7jOxLMyzGTaqAWEglViDMZEc6YMwxXMP0wYOK7ZSzQL1EZLiXsl0gPbD4AMYeRue7I3NS1hwKjqDkyJTzcKvIBgFrC4QCzwIZyIogoibR74wKyRQL3TcLfVvPIC4v6hGyfb74C5vJYLNq4RwbQJt3XAx8AoRM6QgXIAjW2HdPZ7cLM06noZc8RJ"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 138));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "3v5aWldbANlLSSLPYDepeC95mpkaftQBqwEGtL8LDFxGx5xYn46nvX58Ctr9AQe27jO5QkplaWw4L07j4NByn0YAjLWONZA3XZOxoaNShYL2y4fVBSSpdnaLZTICejXfk3sI2WKvCJoY2B0KbeZDeotdVOTDpiNGurmg0AyBOOw9lLufFAQxYCfJAARR4OPHWcZDsAzAoP42ys2TQzOpdDIs4f3UrJmvtBYYe0ZjriTtfj6k0d7IuHwMcoyNZ6w3p"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "xIlVGQvJDkFlu2Pd6ZGpD"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                CoreContacts corecontacts = createCoreContacts(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = corecontacts.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 2:
                        corecontacts.setFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 3:
                        corecontacts.setMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 5:
                        corecontacts.setLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 6:
                        corecontacts.setNativeTitle(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 7:
                        corecontacts.setNativeFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 8:
                        corecontacts.setNativeMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 9:
                        corecontacts.setNativeLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 10:
                        corecontacts.setAge(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 11:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 12:
                        corecontacts.setEmailId(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 13:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 14:
                        corecontacts.setPhoneNumber(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
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
