package com.example.service.impl;

import com.example.dao.AppDao;
import com.example.dao.impl.AppDaoImpl;
import com.example.service.AppService;

public class AppServiceImpl implements AppService {

    private final AppDao appDao;

    private AppServiceImpl(AppDao appDao) {
        this.appDao = appDao;
    }

    @Override
    public String getName() {
        return "appServiceImpl "+appDao.getName();
    }

//    public void setAppDao(AppDao appDao) {
//        this.appDao=appDao;
//    }

    public static AppService createInstance(AppDao appDao) {
        return new AppServiceImpl(appDao);
    }
}
