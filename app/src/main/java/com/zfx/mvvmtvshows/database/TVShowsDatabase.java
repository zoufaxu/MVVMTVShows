package com.zfx.mvvmtvshows.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.zfx.mvvmtvshows.dao.TVShowDao;
import com.zfx.mvvmtvshows.models.TVShow;

//@Database是Room库中的一个注解，用于指定数据库的一些基本信息和配置。在这个注解中，entities参数指定了数据库中的实体类，
// 它指定了TVShow.class作为数据库的实体类。version参数指定了数据库的版本号，
// 如果您修改了数据库的结构或模式，则需要增加版本号以使应用程序能够正确升级数据库。
// exportSchema参数指定了是否要导出数据库的模式文件，通常在开发中启用此选项以便进行调试和分析。
@Database(entities = TVShow.class , version = 1 , exportSchema = false)
public abstract class TVShowsDatabase extends RoomDatabase {

    private static TVShowsDatabase tvShowsDatabase;

    public static synchronized TVShowsDatabase getTvShowsDatabase(Context context){
        if (tvShowsDatabase == null){
            tvShowsDatabase = Room.databaseBuilder(
                    context,
                    TVShowsDatabase.class,
                    "tv_shows_db"
            ).build();
        }
        return tvShowsDatabase;
    }

    public abstract TVShowDao tvShowDao();







}
