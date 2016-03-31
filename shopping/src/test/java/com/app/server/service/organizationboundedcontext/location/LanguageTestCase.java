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
import com.app.server.repository.organizationboundedcontext.location.LanguageRepository;
import com.app.shared.organizationboundedcontext.location.Language;
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
public class LanguageTestCase extends EntityTestCriteria {

    @Autowired
    private LanguageRepository<Language> languageRepository;

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

    private Language createLanguage(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        Language language = new Language();
        language.setAlpha4parentid(3);
        language.setLanguage("l0bK52WyW3t6pVqO5g4SzEydPhVyMJ7OqHG0oF2Uu2Im0F6Sc0");
        language.setAlpha2("EP");
        language.setAlpha4("KJon");
        language.setLanguageIcon("zRov24VovxEw5rlv5ezG9mWUFtTzug8Muxs4Zmrl5CHi6qFcs9");
        language.setLanguageType("LfyEgOqkBHnzeYZpXvcQqjRbELD5QDB6");
        language.setLanguageDescription("TwuogE9rXYWlqlUCQl1Ee4FpOZ9CfzNmYo8rH4pTVrEThooTuw");
        language.setAlpha3("C6V");
        language.setEntityValidator(entityValidator);
        return language;
    }

    @Test
    public void test1Save() {
        try {
            Language language = createLanguage(true);
            language.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            language.isValid();
            languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("LanguagePrimaryKey"));
            Language language = languageRepository.findById((java.lang.String) map.get("LanguagePrimaryKey"));
            language.setAlpha4parentid(9);
            language.setLanguage("Iy8NraMoiXAsnfolOFH7ZpxJbqZ6b2C9957wu3ALw8SoTlILUf");
            language.setAlpha2("rI");
            language.setAlpha4("gGtW");
            language.setLanguageIcon("PK56wX8H6t16fdEWQJKN54eBPgaH6hoxEb79ifARCF6Rza4Yab");
            language.setLanguageType("9D0h6LljO9Wet2nK9wUS6VjG5gvUkg3L");
            language.setLanguageDescription("cPtpGsPZqJKGQlWMH5v6FU2o2QMGXNcVzQKri2TRJl0LD9W2iN");
            language.setVersionId(1);
            language.setAlpha3("2Bv");
            language.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            languageRepository.update(language);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("LanguagePrimaryKey"));
            languageRepository.findById((java.lang.String) map.get("LanguagePrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("LanguagePrimaryKey"));
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateLanguage(EntityTestCriteria contraints, Language language) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            language.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            language.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            language.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            languageRepository.save(language);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "language", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "language", "U4Et5PodjlKiWYcgxguPcrcAcOcZhMx8VNVy0JckQ7zm6NAopy4SJzgOwWPvEqvp8csHovme4DXKHNmsvAADwZLGA8OKa9FG9ZsC6g0tevfEZ3Q1JDndTWbqy8GEFUdp4YrA6LI7ZtXjvVcTpJ40rRlMSoe4kZwCGVqQZxFr0tysc7Z4If8zMtX8meXVMNHzfUoYGmRnKfDXdIf2oCX3UxBZRgGmM6kOXhYqzPwJUe9o4jMqzaOs76LOh8Zh5puWk"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "languageType", "wduTsKs5hF0TXYDslt2TRPFAK9Y78pzej"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "languageDescription", "Je1qheAStCopzxFLioGHJDxJBaXoVh2kDmdl2TIGSCUHTTwYB7IrcueVdaQWJ8lt1d2Gnd7WhRr26VOHyhjgeY0vOmAxd3PsDRRYkypEti1kvz9RKLdoLMbh6E8lVFq5X7qq1CdNJ91mAyxaJNymL8z4t5DUb6mQR82rO73ddKEbVfKjYIAcq1mgsYVDnrnzYIeQr2kNKyFUwY1VcN7TllaBOs7hj2ux0KS4LqeNGKxH3ZoU3qW3aeSMFn8cRPKOe"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "languageIcon", "TbsmEv0Bs4LHXRtMoqxBLCfS3Mc2rpzD7e1KnCMPhsl6UTGEXZn2Bpv2RYgqOLxVw5wrMEfOVKo2Capon4Fte5aUyLp1X0iNNdujpchXocQVougTdpesuKG7KhM3xcxaj"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "alpha2", "XJh"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "alpha3", "RLMu"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "alpha4", "6y3Xb"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "alpha4parentid", 18));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Language language = createLanguage(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = language.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(language, null);
                        validateLanguage(contraints, language);
                        failureCount++;
                        break;
                    case 2:
                        language.setLanguage(contraints.getNegativeValue().toString());
                        validateLanguage(contraints, language);
                        failureCount++;
                        break;
                    case 3:
                        language.setLanguageType(contraints.getNegativeValue().toString());
                        validateLanguage(contraints, language);
                        failureCount++;
                        break;
                    case 4:
                        language.setLanguageDescription(contraints.getNegativeValue().toString());
                        validateLanguage(contraints, language);
                        failureCount++;
                        break;
                    case 5:
                        language.setLanguageIcon(contraints.getNegativeValue().toString());
                        validateLanguage(contraints, language);
                        failureCount++;
                        break;
                    case 6:
                        language.setAlpha2(contraints.getNegativeValue().toString());
                        validateLanguage(contraints, language);
                        failureCount++;
                        break;
                    case 7:
                        language.setAlpha3(contraints.getNegativeValue().toString());
                        validateLanguage(contraints, language);
                        failureCount++;
                        break;
                    case 8:
                        language.setAlpha4(contraints.getNegativeValue().toString());
                        validateLanguage(contraints, language);
                        failureCount++;
                        break;
                    case 9:
                        language.setAlpha4parentid(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateLanguage(contraints, language);
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
