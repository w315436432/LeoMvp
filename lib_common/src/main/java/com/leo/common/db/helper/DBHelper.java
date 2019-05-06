package com.leo.common.db.helper;

import android.content.Context;

import com.leo.common.app.Constants;
import com.leo.common.db.DaoMaster;
import com.leo.common.db.DaoSession;
import com.leo.common.db.TestStudent;
import com.leo.common.db.TestStudentDao;
import com.socks.library.KLog;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 *
 * @author LeoWang
 *
 * @Package com.leo.common.db.helper
 *
 * @Description 数据库操作类
 *
 * @Date 2019/5/6 13:52
 *
 * @modify:
 */
public class DBHelper {
    private static DBHelper instance;
    /** DB相关*/
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    /** 测试表Dao*/
    private TestStudentDao testStudentDao;

    /** 测试表*/
    public static final int TEST_STUDENT_TAG = 0;

    public static DBHelper getInstance(Context context){
        if (instance == null){
            instance = new DBHelper();
        }
        DaoSession daoSession = getDaoSession(context);
        instance.testStudentDao = daoSession.getTestStudentDao();
        return instance;
    }

    /**
     * 获取DaoMaster
     * @param context context
     * @return DaoMaster
     */
    private static DaoMaster getDaoMaster(Context context){
        if (daoMaster == null){
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context, Constants.DB_NAME, null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    /**
     * 获取DaoSession
     * @param context context
     * @return DaoSession
     */
    private static DaoSession getDaoSession(Context context){
        if (daoSession == null){
            if (daoMaster == null){
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    /**
     * 增
     * @param testStudent 保存的对象
     */
    public void addToTestStudent(TestStudent testStudent){
        testStudentDao.insert(testStudent);
    }

    /**
     * 删
     * @param testStudent 删除的对象
     */
    public void deleteTestStudent(TestStudent testStudent){
        testStudentDao.delete(testStudent);
    }

    /**
     * 改
     * @param testStudent 先查后改
     */
    public void updateTestStudent(TestStudent testStudent){
        testStudentDao.update(testStudent);
    }

    /**
     * 通过条件查数据
     * @param name 表中姓名字段
     * @return 查询到的数据
     */
    public TestStudent queryTestStudent(String name){
        QueryBuilder<TestStudent> queryBuilder = testStudentDao.queryBuilder();
        try {
            queryBuilder.where(TestStudentDao.Properties.Name.eq(name)).build();
        } catch (Exception e){
            KLog.d("通过姓名查询学生失败");
            e.printStackTrace();
        }
        List<TestStudent> testStudents = queryBuilder.list();
        TestStudent student = null;
        if (testStudents != null && testStudents.size() > 0){
            student = testStudents.get(0);
        }
        return student;
    }

    /**
     * 取所有
     * @return 表中所有数据集合
     */
    public List<TestStudent> loadAllTestStudent(){
        return testStudentDao.loadAll();
    }

    public void clearTableByTag(int tableTag){
        switch (tableTag){
            case TEST_STUDENT_TAG:
                testStudentDao.deleteAll();
                break;
            default:
                // 随便写
                testStudentDao.deleteAll();
        }
    }

    /**
     * 清除数据库
     */
    public void clearDB(){
        testStudentDao.deleteAll();
    }
}
