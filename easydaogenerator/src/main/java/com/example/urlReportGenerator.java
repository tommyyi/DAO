package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class urlReportGenerator
{
    public static void main(String[] args) throws Exception
    {
        Schema schema = new Schema(1, "com.ty.statisticsimp.dao");
        Entity note = schema.addEntity("UrlEntity");
        note.addIdProperty();
        note.addStringProperty("url").notNull();

        new DaoGenerator().generateAll(schema, "D:\\CODE\\MusicAPP\\StatisticsImp\\app\\src\\main\\java");
    }
}