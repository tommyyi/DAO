package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class Easydaogenerator
{
    public static void main(String[] args) throws Exception
    {
        // �����������ģ��㴴����һ���������ʵ�壨Entity����ģʽ��Schema������
        // ���������ֱ�������ݿ�汾�����Զ����ɴ���İ�·����
        Schema schema = new Schema(1, "me.itangqi.greendao");
        //      ��Ȼ�������Ը�⣬��Ҳ���Էֱ�ָ�����ɵ� Bean �� DAO �����ڵ�Ŀ¼��ֻҪ������ʾ��
        //      Schema schema = new Schema(1, "me.itangqi.bean");
        //      schema.setDefaultJavaPackageDao("me.itangqi.dao");

        // ģʽ��Schema��ͬʱҲӵ������Ĭ�ϵ� flags���ֱ�������ʾ entity �Ƿ��� activie �Լ��Ƿ�ʹ�� keep sections��
        // schema2.enableActiveEntitiesByDefault();
        // schema2.enableKeepSectionsByDefault();

        // һ����ӵ����һ�� Schema �����������ʹ�������ʵ�壨Entities���ˡ�
        // һ��ʵ�壨�ࣩ�͹��������ݿ��е�һ�ű��˴�����Ϊ��Note������������
        Entity note = schema.addEntity("Note");
        // ��Ҳ�������¸�������
        // note.setTableName("NODE");

        // greenDAO ���Զ�����ʵ���������ֵ���������ֶΣ�������Ĭ��ֵ
        // ���������������ñ��е��ֶΣ�
        note.addIdProperty();
        note.addStringProperty("text").notNull();
        // ���� Java ��ʹ���շ���������ͬ��Ĭ�����ݿ��е�������ʹ�ô�д���»������ָ�ʵġ�
        // For example, a property called ��creationDate�� will become a database column ��CREATION_DATE��.
        note.addStringProperty("comment");
        note.addDateProperty("date");

        // ������ǽ�ʹ�� DAOGenerator ��� generateAll() �����Զ����ɴ��룬�˴�����Ҫ�����Լ�������������Ŀ¼����֮ǰ������ java-gen)��
        // ��ʵ�����Ŀ¼��·�������� build.gradle �����ã�����Ȥ�����ѿ�����������������Ͳ�����⡣
        new DaoGenerator().generateAll(schema, "C:/Android/code/DAO/app/src/main/java-gen");
    }
}