package com.app.server.service.aaaboundedcontext.authorization;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.aaaboundedcontext.authorization.AppMenusRepository;
import com.app.shared.aaaboundedcontext.authorization.AppMenus;
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
public class AppMenusTestCase extends EntityTestCriteria {

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

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

    private AppMenus createAppMenus(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        AppMenus appmenus = new AppMenus();
        appmenus.setRefObjectId("XY6tzCvptRoL5I5GtM3UADr3TO6lvKMYcjUuN7CubPGn1W8zWP");
        appmenus.setAutoSave(true);
        appmenus.setMenuLabel("FNs8Fq5fa8ljXxrtRF34Nw1JBdcG7lYPA7n1i8Ymz7lde9CGa7");
        appmenus.setAppType(2);
        appmenus.setMenuIcon("nPAOEZVmeB3uIwBuEx72E5oxbhnAKy0NYQDU1nDrYs6Gxc99Ja");
        appmenus.setMenuAction("jy2mpmp5uNRJHLsP5bDuLXvU2f65F0R6KwdnjSxrGcBEiXBOMf");
        appmenus.setUiType("vyl");
        appmenus.setMenuTreeId("z6aA5WVEoKRab8mvuNa7EJGYKfLYZeKYQ0wik3f4uyUXzeJ5pT");
        appmenus.setAppId("3zB6bFWq8tmSJJiJ70AaPuFaSxvCHkWgKfdWTqrWa6au5w4YDp");
        appmenus.setMenuCommands("bksAsGDFXhMlIQt51N0cgeNthx9Ei1SVbCDHmX1IrBhxu5C3Ft");
        appmenus.setMenuAccessRights(3);
        appmenus.setMenuHead(true);
        appmenus.setMenuDisplay(true);
        appmenus.setEntityValidator(entityValidator);
        return appmenus;
    }

    @Test
    public void test1Save() {
        try {
            AppMenus appmenus = createAppMenus(true);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            appmenus.isValid();
            appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            AppMenus appmenus = appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
            appmenus.setRefObjectId("uS9UNgZt3ArIvWQTSYgsuS7JlVbFGJWhzejeVe47nvLQ2Pq7iP");
            appmenus.setMenuLabel("PybZjgGNGEa4S0ePQjmRapLah98a6pwy8wHX3mUZqTIYRwc4GG");
            appmenus.setAppType(1);
            appmenus.setMenuIcon("guzbdXBUADim43qEUa5oOaSCO5eTBT0d7ba47WnMx8fbCGCf6t");
            appmenus.setMenuAction("QoDhJdR09OZLqTHQyHeYMrsgjzThMBeEcp49JaFVftcE6ePjNG");
            appmenus.setUiType("fSt");
            appmenus.setMenuTreeId("5EYllTo5OF3mRlJleu4QxgZPoQu8Dvzd3f15TDd949LKMgxd34");
            appmenus.setAppId("kdb7vYjEE4scwf1hVQmOOjb2oyH2CsQL781WNU468Lesd2lutL");
            appmenus.setVersionId(1);
            appmenus.setMenuCommands("tiR54WF9g2kEov9jGos8tmyUiePPVOKGDCMURq0P7Go8VapU3D");
            appmenus.setMenuAccessRights(5);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            appmenusRepository.update(appmenus);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAppMenus(EntityTestCriteria contraints, AppMenus appmenus) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            appmenusRepository.save(appmenus);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "menuTreeId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "eCgK5FC6ZYX5GbvIjIOVRPTS9PpnLl1jmn55IuyDJrh3feZVq7E1uuTArYBzPM9ltB8LoraA2HUjk7qY3MW2P2JYYDbBrLcAnPCgL6Fdba49MIH1KsWzlvbis8teeuoRInbX1nExMhdDxn9aAejVnbIffv7FsruC5SXFGizMR67mf57kf33UZsq0M6hCJPFDik2GLnMuN80yQJR8NquKtsl70MvvCLiLdYAoCgKaujR7K9eGrgINxXzKow0VzTezG"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "mDyfDLxseW2V2lzIjUS7UbKqvehoilnEJ0x50k8RUtwV7BIbP0Mk4vcR6r1qIqXgcNnYz6DT0ceZivnoNsZAtML8kf3BO0WfSkQzsLG7E7TZefcojG5A8nzkLu0tKAzXOn2In2Pb0YLezC0IMiDwg1a9stkFtxwPJEoMpKOH6EW6PHAMQNdu1AfVDCNPBcQ2yk5qS2zoydu3OL2zKxL2qjto1FqJsbcDK1j1HXDb5xdNsfGIMZwwRQA5m0GggDsks"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "psJVeEMauAQXmrkxavSg5lkJjZWRTJB1I6wSNp40gvzryM3AJuwASYrXeVpJcPsAGuNDjrsw0O1WsazsD9LdDhHTOdMYdG17pZYBAeXnyegutl3kUDgQr8vkzp3g4aXnkDHcELdnIO6buTrzDULZWHp97kOpQhZoNR9KBqNjWSTDspZWQvwsxGAnza7bQ3ZzIBl38LY66mn8jInCv9uFdoTPmHPS7jeeLlcHgmQ7rj3csDoED0YRJgN0MYw5eY6AY"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "gfhuL2qVFyoEa31D0wuIttqiWgaxEqAQQPkhqxMl0MywX5oZ9N1SbtekBYgJx5p9c"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "BQqib58RexVPmrCQcQqcROUfccNAy6tqqg4aNyfxVaUBhbSxKhvXA1xvM8akKsSQy9xno8CoMLlXB8MYnKmZOqbVR7ZEXumpNfyxk2WMv7TRQnSBjwQ4l3frTKJgT5cA334IuF1qu5BOz2hi8VbenTuCKl9UTCWTURevhtYYZd4plE4VVEI2jwydRINhUuacNFz62NQXRj7e5ALNsYuV0krCgZ9SBemWcW7Gb1lsotOgJ9IKT3GvvKYZ9aRxFQmf7"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "m3A9"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "4u8crU2dlCsr9rp6bHXiJUNiLZwLSWc5HGe2rewDYkpsgPSuE668yeQRdb1qg3d91PxCbX2yzGkMvTE0eHQHREqTtWipuTpWC54ycOL4bTG6nMLimiMpIvTkxYAIS3OzLyijdnUPOMLqDtIHpNvtwORjCVpXGfRCh4exG7lyEfqkupmGih5NSCi92LSwYqhMV6wQQBfioNJRS4fnI0y1a2sqLScJRpdA5EtZO2lmH8MtJJKjbknU3CDfgNbXZQ5UN"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 12));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 4));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "qdgYXJ6YJmoRTfxY7hkVT9PhQNVnx9x65uXXELtVBGe1xuNt78jcCt6AVMpzrd4RpNOPEOOVrYONO36d9yQljHpHx1NXah5Nhbo0wLr2vIsC1cRSFWrqBCHgsLn45JfRaIfGBQfnGZoI1t0tH2qgQvySlCv8VW3BE4xx2MTKSpJs6kSIT6WZL0XBIMw0UkD39ixB00egDaPyL1glbfXdcNLlFfmBl4O8Y0tavkEgflkSz6ZbGpF2Rj4LUewDK9lz0"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 18, "autoSave", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 19, "autoSave", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                AppMenus appmenus = createAppMenus(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = appmenus.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 2:
                        appmenus.setMenuTreeId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 3:
                        appmenus.setMenuIcon(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 4:
                        appmenus.setMenuAction(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 5:
                        appmenus.setMenuCommands(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 7:
                        break;
                    case 8:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 9:
                        break;
                    case 10:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 11:
                        appmenus.setMenuLabel(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 12:
                        appmenus.setUiType(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 13:
                        appmenus.setRefObjectId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 14:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 15:
                        appmenus.setMenuAccessRights(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 16:
                        appmenus.setAppType(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 17:
                        appmenus.setAppId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 18:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 19:
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
