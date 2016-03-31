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
import com.app.server.repository.organizationboundedcontext.location.AddressRepository;
import com.app.shared.organizationboundedcontext.location.Address;
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
import com.app.shared.organizationboundedcontext.location.AddressType;
import com.app.server.repository.organizationboundedcontext.location.AddressTypeRepository;
import com.app.shared.organizationboundedcontext.location.City;
import com.app.server.repository.organizationboundedcontext.location.CityRepository;
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
public class AddressTestCase extends EntityTestCriteria {

    @Autowired
    private AddressRepository<Address> addressRepository;

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

    private Address createAddress(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("36IlonBrxlZE5pmCb7M6KiEXxupmHGTIM58dUyRfFBIwb2Jkgf");
        addresstype.setAddressTypeIcon("23ZLpjx6fXUQ75Dt1w6i6Hsz65SYYjsQFaHz3oT6w2Mqr0oCwc");
        addresstype.setAddressType("LdH6uXGeOwB21RGCbpKcZD9S83ZruxeLz828gAI0IL2B4uA0z2");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityLongitude(10);
        city.setCityCodeChar2("rHkli2ojqacmhod9R5YIDB30EK2825sf");
        city.setCityCode(2);
        State state = new State();
        state.setStateFlag("GAjcMRuJtnFFnDO4vanFhjBgVfE8ESJfKPoV0z0CZd4Ea7JfUT");
        state.setStateDescription("rINqA6Q4a233GFEuZnZTnVUDcP9jwxX300x0XfMsYdv1hdWGH8");
        state.setStateCodeChar3("MAhNWGA2aKcpvDDZyzuXLRhgG6jeO9CO");
        state.setStateCode(2);
        state.setStateCapitalLongitude(2);
        state.setStateCapital("gajzsGcTQ0ao4pIVqZr7y78uOcSmy9GjmLAWmPKUpaZ1kgfgUU");
        state.setStateCapitalLatitude(7);
        Country country = new Country();
        country.setCapital("FdTchjlVrABoDa9V20jtMiZckJpsqu2q");
        country.setCountryCode2("sJy");
        country.setIsoNumeric(740);
        country.setCurrencyCode("vBb");
        country.setCountryCode1("P56");
        country.setCapitalLatitude(6);
        country.setCountryName("HjYOUh1RSt3TTg9mhHRfPTvV1xa47hghnI4rlylw6unWnndJyg");
        country.setCurrencySymbol("T3TxyVafQkBKTKHGRON8Io0VwFVsNUOT");
        country.setCapitalLongitude(2);
        country.setCountryFlag("bOZNWQz8xxCbwEbAO4xGgMGxHwnzP41DhzHlXv5rerDEhocFTU");
        country.setCurrencyName("Pa2ls4hpANFD3AZiFf0vaxmGSJevle52tYYvjr0PnADqqpKpeq");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateFlag("GRPA2lTSxw0pfJlkBjPVrTw5kRveA2sUyEUldTwlG4ulnUQw5H");
        state.setStateDescription("MH9A0hTbdlbSIy9SoehZHuuKkMX9NXkbcMcdOh4Gpm3CbLH08f");
        state.setStateCodeChar3("IyM5GJlyVwBiLjTgWKzvQxBwnbtNdAiC");
        state.setStateCode(2);
        state.setStateCapitalLongitude(3);
        state.setStateCapital("5k1oRjzTMkLzXOFsOVTdsBxrLJNCMBlVkxMW6q2QsTVPyMwmWx");
        state.setStateCapitalLatitude(10);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar2("27KeStzSnyMEcKznQDEtzaNU3IWPPPEx");
        state.setStateName("lMLI5glP6welJlTDcE5BcLbIpmqrRogfDSY1wjij3tkuNBDUuP");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityLongitude(8);
        city.setCityCodeChar2("jRT4S6rmKwvWKWPHQCCtFXPEV62FD3BC");
        city.setCityCode(2);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("qJ6ByMlLzzbwNLS6AJKm8sIiEqbWU4aVVWBX1KGGXGuHXgCvYp");
        city.setCityDescription("o7FSzJkBNqmTW5eDSe9g3EEdr7GjPcAqx4D25g1hKHh2wzFYlG");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityName("xheh2sB82QDYezChzaS54Z5xI0ScLMYCniYK10nKmPKF4YGTg2");
        city.setCityLatitude(2);
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        Address address = new Address();
        address.setAddress2("e4SJAowam6hXkObNQSyQodruQ3ekmAX4LXPILtjT7KR6GjS6TG");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress3("7PxDlZ17q7fCSO8stIVZ5QZZICK2bszNwvKnlJYP4eJQr0AX8t");
        address.setAddress1("tQ4Y38OogkT03Peao12fKc6T3xTyxBp51u4IEfRWdr5EIgH5Gp");
        address.setZipcode("nfEpzr");
        address.setLatitude("siBN0aft6Aa8tCJ5Z3Bicj3pefhpuaXG3earSzMHrzoC0l387a");
        address.setAddressLabel("q2kQRbhvHgw");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey());
        address.setLongitude("NXzfn85XH2emvFP5jxg8inmunxBaZCurXM7IRloh7LpUXd5gaJ");
        address.setEntityValidator(entityValidator);
        return address;
    }

    @Test
    public void test1Save() {
        try {
            Address address = createAddress(true);
            address.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            address.isValid();
            addressRepository.save(address);
            map.put("AddressPrimaryKey", address._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            Address address = addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
            address.setAddress2("96qm4g5mc7sX9CWI6Qm2t3B7QaRhgDWq8mgU4vwOJs0pD7U2j0");
            address.setVersionId(1);
            address.setAddress3("h7jPLqCS6fERf8qpKdcDu936zNM4V5ElMsed3QUAXIebpMFZXM");
            address.setAddress1("dGUNfGOzWA0JFJj21iOtX9ov44zIiAU8nYspVaaEsbTCwWbZ8z");
            address.setZipcode("Yd8KGb");
            address.setLatitude("pdWgjlzgqbgZwJdGSjsUZNLiVC18wpypOMjeefNTEhE8JaSgaz");
            address.setAddressLabel("GXd9ObZjsYM");
            address.setLongitude("CLqJ7XWx2RURhiGzN6M4sjr65cZGPHru6NrcdFqMO86cH9h1y2");
            address.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            addressRepository.update(address);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByaddressTypeId() {
        try {
            java.util.List<Address> listofaddressTypeId = addressRepository.findByAddressTypeId((java.lang.String) map.get("AddressTypePrimaryKey"));
            if (listofaddressTypeId.size() == 0) {
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
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycityId() {
        try {
            java.util.List<Address> listofcityId = addressRepository.findByCityId((java.lang.String) map.get("CityPrimaryKey"));
            if (listofcityId.size() == 0) {
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
            java.util.List<Address> listofcountryId = addressRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
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
    public void test3findBystateId() {
        try {
            java.util.List<Address> listofstateId = addressRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
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
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.delete((java.lang.String) map.get("AddressPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAddress(EntityTestCriteria contraints, Address address) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            address.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            address.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            address.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            addressRepository.save(address);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 1, "addressLabel", "BiYvWdL0kHqi"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "address1", "SDevkk8WgEYjbv90jfUWohLteXhLT1ENVOJYWXgR5BVIe0ifnsq9CPHGr"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "address2", "p2j7QOd9D7Wdpwg1uZWTNmif9qIC01pu87FdQnIxdeEaEo9F0lT3wfrup"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "address3", "ltFSOenQc6SCPM50R3jWI3hCAGmEAirT4zWJoQly1hB2a4UezJN7G47XY"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "zipcode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "zipcode", "YJ5SKei"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "latitude", "Tjc6RFKvK6fA7m1KDdk4kigtDDsbLSDFC4kDYo7uZdn6o7sjRqdRmiAhqkq0NmsMm"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "longitude", "s3WE52YohgsUlD95uiqhgKYgwhYpS7aivqRVED78mcwnqaCjOQnRiHRh6uGK24Yo2"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Address address = createAddress(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = address.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        address.setAddressLabel(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 2:
                        address.setAddress1(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 3:
                        address.setAddress2(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 4:
                        address.setAddress3(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(address, null);
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 6:
                        address.setZipcode(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 7:
                        address.setLatitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 8:
                        address.setLongitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
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
