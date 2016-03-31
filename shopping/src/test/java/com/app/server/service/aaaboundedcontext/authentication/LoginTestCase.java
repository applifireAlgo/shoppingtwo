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
import com.app.server.repository.aaaboundedcontext.authentication.LoginRepository;
import com.app.shared.aaaboundedcontext.authentication.Login;
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
import com.app.shared.aaaboundedcontext.authentication.User;
import com.app.server.repository.aaaboundedcontext.authentication.UserRepository;
import com.app.shared.aaaboundedcontext.authentication.UserAccessDomain;
import com.app.server.repository.aaaboundedcontext.authentication.UserAccessDomainRepository;
import com.app.shared.aaaboundedcontext.authentication.UserAccessLevel;
import com.app.server.repository.aaaboundedcontext.authentication.UserAccessLevelRepository;
import com.app.shared.aaaboundedcontext.authentication.PassRecovery;
import com.app.shared.aaaboundedcontext.authentication.Question;
import com.app.server.repository.aaaboundedcontext.authentication.QuestionRepository;
import com.app.shared.aaaboundedcontext.authentication.UserData;
import com.app.shared.organizationboundedcontext.contacts.CoreContacts;
import com.app.server.repository.organizationboundedcontext.contacts.CoreContactsRepository;
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
public class LoginTestCase extends EntityTestCriteria {

    @Autowired
    private LoginRepository<Login> loginRepository;

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

    private Login createLogin(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        User user = new User();
        user.setUserAccessCode(8665);
        user.setSessionTimeout(1338);
        user.setMultiFactorAuthEnabled(1);
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainDescription("2fc2hpI9DE4lh8pB75BaD81WaQrQTaGESkbR9n2EZjDtwXRsib");
        useraccessdomain.setDomainName("pXJNPSOlr3ekOgkv5GoDCdOYUXRY5hsLlUjlHcEg0UbKWdlcjj");
        useraccessdomain.setDomainHelp("9H5Efu5wHbUapp3sZoUF1BLPM0NUooNV5VVSocwnCMscAafbho");
        useraccessdomain.setDomainIcon("f9ggRqhiORI5vchlzamxZdm9PQsprvFzCjZ8gucWOPl2wz3hMW");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelDescription("1XP5iUWwE9AnQO571Txdge2YSFk0XL7lsDESmfIsaXgt3oBgjb");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelName("oJtE6umjBlNrhts36lMi3N4Sb1orMRF4DWnwdHtY7NyFceXQyD");
        useraccesslevel.setLevelIcon("Z6pYmFHa2bGmCsZAhYeRLHCpFlr7D6SJZqrTTdctIBjEdyWlLR");
        useraccesslevel.setLevelHelp("xsvSg3VnfXMWiHmsTXBPg5PombMK0tqdTOL0SRgNlLOtW0N1PV");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        user.setUserAccessCode(42510);
        user.setSessionTimeout(1629);
        user.setMultiFactorAuthEnabled(1);
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1459432499059l));
        user.setGenTempOneTimePassword(1);
        user.setAllowMultipleLogin(1);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1459432499059l));
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setChangePasswordNextLogin(1);
        user.setPasswordAlgo("aRsVF0Z14KrAcJk9JW6Y632rni4WMdy7NiQpE2jLYR3q3fgJLV");
        user.setIsLocked(1);
        user.setIsDeleted(1);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        Question question = new Question();
        question.setQuestionDetails("0YTBj7IwEl");
        question.setQuestion("H6S3Da4dKdRWq9CV1smV1cmLJ6sNFii6AZ8itOHjo7QPJHczss");
        question.setLevelid(8);
        question.setQuestionIcon("RhzockrIEnMZC6Si8Fo0qzVPAOgb0PdFriPQtYEQihMIblneTI");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey()); /* ******Adding refrenced table data */
        passrecovery.setUser(user);
        passrecovery.setAnswer("snlkslMV5uWdYRtTvBfB3uD8EUoX37m6ZdPujISSt28rmaQwXG");
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setOneTimePassword("aRiZmwQCaFNZdVaGIZN8MttY3ORa19g0");
        userdata.setLast5Passwords("ixRJJ6BnRBtaX2ZV0EdcUysfpezTph12RbIyZfKvGzzMvmO8Se");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1459432499662l));
        userdata.setPassword("NqmqiMhVbHvDymfZSknvC9lYC3YnXbuJjK1PvyydDMZTaV1eiB");
        userdata.setOneTimePassword("Y5bFG5Athx55bN3AFKdy0MVqMUFd9pTd");
        userdata.setLast5Passwords("7J22lgriJVddYRLAT857jOHh9BYn7IBEJNWeMTU3Zm6hIdMUQF");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1459432499718l));
        userdata.setPassword("T2gAA1byOx3p8IWHraXJjE30OXyXbMbhTmJ6Uph9wChPcJddu9");
        userdata.setUser(user);
        userdata.setOneTimePasswordExpiry(4);
        user.setUserData(userdata);
        CoreContacts corecontacts = new CoreContacts();
        Timezone timezone = new Timezone();
        timezone.setUtcdifference(4);
        timezone.setCountry("8AR98vq7KIYVrXSWagKzjUHYJliQwAht1jaSKNJ7UNMeKWjLKS");
        timezone.setGmtLabel("502xpx36o1zFhjCfbiI7gp92Shx5m6EBZj0M3GovXH5gh9ZQ2K");
        timezone.setCities("JkXKhztbofGcvdUbb3018B1LDNdnoj24RGJNEtb4s0TkdayBgF");
        timezone.setTimeZoneLabel("iDzgGTgRcGa5LuustXt3yDBwKsZVj5nUIOGFcaInX6VsVUx19L");
        Language language = new Language();
        language.setAlpha4parentid(9);
        language.setLanguage("SSpv3Fb2HE6pdNEIrEr5EiqZCRtDLiCnklWLbOSV7VcbaE9WdM");
        language.setAlpha2("Sa");
        language.setAlpha4("XOw9");
        language.setLanguageIcon("Xj7EeUwdntgNKkyxlEKFUWbuehcVsZ3Hg3ZHHBEXP8HDQEoKO6");
        language.setLanguageType("lwkfYSSuupFed6pNgp4GPRA6oOhN2fMm");
        language.setLanguageDescription("pXHIaylF5KH7w4mLAENSx67vgJVMVKhwejnxDZMc5evdcLZWLW");
        language.setAlpha3("sI6");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("7h3pI29XvbB0x1qbX0Y3dobKlT9Tmgi3UAER6DFTNimIZObbJf");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("m1opMZ85gtHBDbA99WizspQR19jsjI0ti4oVxELeO6KWkbLqVc");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        corecontacts.setMiddleName("MbdKXQugJCBH0MVVzAYywj4sTIhh0tgAHt0fXGo3ZCzHqtbWgT");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setLastName("oC5060ij7OV4M5hBi7DbkbGV6fNCFknyhT69de01lBYzGKgwLa");
        corecontacts.setAge(53);
        corecontacts.setFirstName("FurGxi3YslDBKeqpWxbb7EPXZEwekDSmkTY3h3V2UMY1TEkObp");
        corecontacts.setPhoneNumber("r3cElwjhtUimAXZPZgBQ");
        corecontacts.setNativeFirstName("2s7zT7VadWUoeSiNrMCvARqHvBo8sM6nM6afKeay7bSXc07un9");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setEmailId("teqSxMlaE7QHk7TKrBVG4erPeJFGHiOgzCn6SFqNR9FEzlWeF0");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1459432500518l));
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1459432500518l));
        corecontacts.setNativeLastName("Np2uAA3ZJ0eoK9qjuHWVPHJvyNxkz3dthdTTb7Hm3J2Hx7r1Rt");
        corecontacts.setNativeTitle("tyxFUYvOEQgaD99Ja1K3LXjuvCYZrMqvHrew7AtdSHcMH8X5bz");
        corecontacts.setNativeMiddleName("L5Af7HzlNy5DCd4NFXCcDFDv0Bcj7VkXU2dudMwnrNhO0RCk0U");
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddress2("Y2hNzsg4haDIrrfqfy0VPLGCuvGa7ffhFrjIFbUkINZMob7m1G");
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("UA6MzVWYEIiLEiPgnK8E65Nusbaoy6vh64yoOpTzcA6H0ofEMm");
        addresstype.setAddressTypeIcon("Xrq01aAJohohMmaF5fOW8OjYFEUWhYpaSva6Aaf14Gp3EKK7ZS");
        addresstype.setAddressType("g8kQo5k21IjmVZfz5pjq1DLNvefv91F6UUb3R87i32BF9UJuIb");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityLongitude(2);
        city.setCityCodeChar2("jL5bX3gJBQ8NM7JmjlxYTTb5BDrJnn2h");
        city.setCityCode(2);
        State state = new State();
        state.setStateFlag("Mn69pAG5xkQHImFmLGbwhBien59sEAtv7NWEe9MAUuL4BbXHFo");
        state.setStateDescription("p6qYsLsi0ENjjHFpKzaGa98VUm2IEkWFmYUTi7bZRFZdg9WkKu");
        state.setStateCodeChar3("qkLjjT7G08BGQ79J68HAsgGv4W5iNquu");
        state.setStateCode(1);
        state.setStateCapitalLongitude(8);
        state.setStateCapital("8sEWgPUQsnlkWoBZy0kV0kXf0OWApZyirccCelp6vy9MfU6TlK");
        state.setStateCapitalLatitude(4);
        Country country = new Country();
        country.setCapital("31EdXkou838F6FcFsQUVm17ibE9pPQn4");
        country.setCountryCode2("c18");
        country.setIsoNumeric(537);
        country.setCurrencyCode("1e4");
        country.setCountryCode1("gRx");
        country.setCapitalLatitude(11);
        country.setCountryName("ljTAF7TqBjIgkX6xrmBgjPE0LKZ2h7U2sTUzkJEBGfYULtvvYu");
        country.setCurrencySymbol("m3PuwNOkXmWJbZ18I1r28zS7nIAsUtow");
        country.setCapitalLongitude(4);
        country.setCountryFlag("GH0AxZoXiDgPnGFTapk0xuS8OjEk2g0ujvkVeYfesFFRiPAWDi");
        country.setCurrencyName("gRn3mZDrdGM4jq9do7LSO8d77XLW4bp0DbRb404v3XXQbbaNUe");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateFlag("cOIFPf84Md1zqixDZ9vfIbkegZ7PQGKqwIWr3S2IzrXH4G6OMz");
        state.setStateDescription("D0IjFWN6S8jTbR5RrtVllOEKtBUz95cntOWHYbpxI8dTrcWOpD");
        state.setStateCodeChar3("KeOSBITLwLJD47hNkFvX7x4C1ra5GicO");
        state.setStateCode(1);
        state.setStateCapitalLongitude(9);
        state.setStateCapital("64BFn7ZmMjLrmVuIvZruzQ3eOPO5RpbwuX3aRKCkXaTTu9AVHD");
        state.setStateCapitalLatitude(9);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar2("f2ktjxoTR95DJ5lK04Mxp745Vj44tdUu");
        state.setStateName("wwQfavdUZTMhL8ZKycgthdNnh5gGOY5hc3dxPqm81ctqwZTPTz");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityLongitude(9);
        city.setCityCodeChar2("DX1wO3I2fzn95U3psmGLvXjrwmqlqbyL");
        city.setCityCode(1);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("9GmRli6jIh9HbpjwDQGQrmCZoDsLdbht2Id5LA67cU2kUCZH3D");
        city.setCityDescription("3ILogjyBLot87Cr7unDhOz6pX3px3F0nOJ4mBAE18Kk6GTjCDL");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityName("SuMBdYDSYwZ6iY3oM7vU1fTgWbchWXzlfW8L0O8Ir6clAAn4dX");
        city.setCityLatitude(3);
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setAddress2("rriufeLKuoP08xkrzmdWfj9nI7XZBh8k2kOh7ctth8m81HRdc9");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress3("raQ987rsxeJ0YhaRor0hgAa7VJRSamodPXnK7JI7ryt4E3ZXCG");
        address.setAddress1("C5VqgKDZn5op9b9oWnsYLlnHLWb10itra1lAQB9sv4bk4FqXgF");
        address.setZipcode("zTelwb");
        address.setLatitude("cntETz79Ymb55fDe0VbiKNL0uwHcaT8IEaSi3GTCH12SHFW7Qh");
        address.setAddressLabel("wVrICEhpUMx");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLongitude("6Fr96IZBWSmp1S5Q3b7SGxGcYnmEfPJBBUQeWwilhRk6KMKGSK");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeDescription("vrYiwJ8CalZRLNYlUGF6DvopuMsJ8UT8r16TCkh2E55ndDzNQm");
        communicationtype.setCommTypeName("4aknlmFOI5h3GzSDa0px7A45817Zev4ZTZb9UTQjq3LyJo5cah");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("mAJZLP5Vkge8USw9kRfi7ospnAEXx2emnpyz0Mx5W6eH6InImv");
        communicationgroup.setCommGroupName("DkoAtSPVex54MRU4xJoPjMYbS4nm4U38vD95Glap24J29uMekU");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        communicationtype.setCommTypeDescription("IgMEa5gCsnThzVXWumxX54ei7fpT04nfwiWkE6SI90ZCKZCXFA");
        communicationtype.setCommTypeName("89BtDoemBQLeScqkEgpJHV7sDAhw7pWb7OqNVJmZtm1LcYLC5J");
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
        Login login = new Login();
        user.setUserId(null);
        login.setUser(user);
        login.setServerAuthText("DWkeQ8YGqSU6FPm6");
        login.setFailedLoginAttempts(9);
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        login.setLoginId("jHsTw7N2co9GabEwyJGPIpV33BjWkH96BQ8eq5jG8xmW9myBQn");
        login.setServerAuthImage("7VihdGCbgv3kfRKWr2FEAkBUpeYGH7KF");
        login.setIsAuthenticated(true);
        login.setEntityValidator(entityValidator);
        return login;
    }

    @Test
    public void test1Save() {
        try {
            Login login = createLogin(true);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            login.isValid();
            loginRepository.save(login);
            map.put("LoginPrimaryKey", login._getPrimarykey());
            map.put("UserPrimaryKey", login.getUser()._getPrimarykey());
            map.put("CoreContactsPrimaryKey", login.getCoreContacts()._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setServerAuthText("qxhw8z1BPs3rUyov");
            login.setVersionId(1);
            login.setFailedLoginAttempts(10);
            login.setLoginId("SS0cPEedXjZ2Emk0RUnRnNo5csaTNTFXTx5WnrlIMVoAorQgtB");
            login.setServerAuthImage("jkIz59mnzJc7arfCJlfKcFFfd517H9gN");
            login.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            loginRepository.update(login);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testNQFindMappedUser() {
        try {
            loginRepository.FindMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNQFindUnMappedUser() {
        try {
            loginRepository.FindUnMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.delete((java.lang.String) map.get("LoginPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateLogin(EntityTestCriteria contraints, Login login) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            login.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            login.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            login.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            loginRepository.save(login);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "loginId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "TskRVeDa95OozRuV7dkPCaZKt2dqeA2TtM0iCm2CGwHMiKAxtRcjXe8OMoZcwKjzoaDQo1ZwNfGbGE73FerSWFPqZHnEimXMhZlt8d0LAA0asTdIwYQ7eUTu7mp0DHO4xuIIFOFA3ISd6YORjYhabf6G0Am5XuwZUIhHTX8aAQZfgjCqxzfCb2qwzLwK045fA9ppcXjRl"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "Dm9YE9EobqwmK9cqm06KQpOWanl4Vyqg2"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "IMDDieaZVmHPT3Djg"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 12));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "isAuthenticated", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Login login = createLogin(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = login.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(login, null);
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 2:
                        login.setLoginId(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 3:
                        login.setServerAuthImage(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 4:
                        login.setServerAuthText(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 5:
                        login.setFailedLoginAttempts(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 6:
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
