package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MusicDldaogenerator
{
    public static void main(String[] args) throws Exception
    {
        // �����������ģ��㴴����һ���������ʵ�壨Entity����ģʽ��Schema������
        // ���������ֱ�������ݿ�汾�����Զ����ɴ���İ�·����
        Schema schema = new Schema(1, "com.yueyinyue.pleaseyou.dao");
        //      ��Ȼ�������Ը�⣬��Ҳ���Էֱ�ָ�����ɵ� Bean �� DAO �����ڵ�Ŀ¼��ֻҪ������ʾ��
        //      Schema schema = new Schema(1, "me.itangqi.bean");
        //      schema.setDefaultJavaPackageDao("me.itangqi.dao");

        // ģʽ��Schema��ͬʱҲӵ������Ĭ�ϵ� flags���ֱ�������ʾ entity �Ƿ��� activie �Լ��Ƿ�ʹ�� keep sections��
        // schema2.enableActiveEntitiesByDefault();
        // schema2.enableKeepSectionsByDefault();

        // һ����ӵ����һ�� Schema �����������ʹ�������ʵ�壨Entities���ˡ�
        // һ��ʵ�壨�ࣩ�͹��������ݿ��е�һ�ű��˴�����Ϊ��Note������������
        Entity note = schema.addEntity("MusicDlRecord");
        // ��Ҳ�������¸�������
        // note.setTableName("NODE");

        // greenDAO ���Զ�����ʵ���������ֵ���������ֶΣ�������Ĭ��ֵ
        // ���������������ñ��е��ֶΣ�
        note.addIdProperty();
        // ���� Java ��ʹ���շ���������ͬ��Ĭ�����ݿ��е�������ʹ�ô�д���»������ָ�ʵġ�
        // For example, a property called ��creationDate�� will become a database column ��CREATION_DATE��.
        note.addStringProperty("musicId").notNull();

        note.addStringProperty("ringListenUrl").notNull();
        note.addStringProperty("picUrl").notNull();
        note.addStringProperty("songName").notNull();
        note.addStringProperty("singer").notNull();

        note.addStringProperty("absoluteDir").notNull();
        note.addStringProperty("fullSongFileName").notNull();
        note.addIntProperty("fullSongDlPercentage").notNull();
        note.addStringProperty("vibrateRingFileName").notNull();
        note.addIntProperty("vibrateRingDlPercentage").notNull();

        // ������ǽ�ʹ�� DAOGenerator ��� generateAll() �����Զ����ɴ��룬�˴�����Ҫ�����Լ�������������Ŀ¼����֮ǰ������ java-gen)��
        // ��ʵ�����Ŀ¼��·�������� build.gradle �����ã�����Ȥ�����ѿ�����������������Ͳ�����⡣
        //D:\CODE\MusicAPP\Yueyinyue\app\src\main\java\com\yueyinyue\pleaseyou\dao
        new DaoGenerator().generateAll(schema, "D:/CODE/MusicAPP/Yueyinyue/app/src/main/java");
    }
}