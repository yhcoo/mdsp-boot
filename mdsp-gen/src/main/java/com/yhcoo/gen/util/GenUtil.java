package com.yhcoo.gen.util;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.yhcoo.gen.model.config.ColumnInfoConfig;
import com.yhcoo.gen.model.config.TableInfoConfig;
import com.yhcoo.gen.model.dto.BuildConfigDTO;
import com.yhcoo.gen.model.entity.ColumnInfo;
import com.yhcoo.gen.model.entity.TableInfo;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.BeanUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class GenUtil {

    private static Boolean hasBigDecimal = false;

    public static List<String> getTemplates() {
        List<String> templates = new ArrayList<String>();
        {
            templates.add("templates/Entity.java.vm");
            templates.add("templates/Mapper.xml.vm");
            templates.add("templates/Mapper.java.vm");
            templates.add("templates/service.java.vm");
            templates.add("templates/serviceImpl.java.vm");
            templates.add("templates/query.java.vm");
            templates.add("templates/controller.java.vm");
            templates.add("templates/index.js.vm");
            templates.add("templates/index.vue.vm");
            templates.add("templates/menu.sql.vm");
        }
        return templates;
    }

    /**
     * 生成代码
     * @param tableInfo 
     * @param columns
     * @param zip
     */
    public static void generatorCode(BuildConfigDTO buildConfigDTO, TableInfo tableInfo, List<ColumnInfo> columns,
                                     ZipOutputStream zip) {
        hasBigDecimal = false;
        // 配置信息
        Configuration config = getConfig();
        // 表信息
        TableInfoConfig tableConfig = new TableInfoConfig();

        //  配置 包名等配置
        buildTableConfig(buildConfigDTO, tableConfig);

        BeanUtils.copyProperties(tableInfo, tableConfig);

        //  构建表基本信息
        buildTableInfo(config, tableConfig,columns);

        // 生成代码
        gen(buildConfigDTO, tableConfig, zip);



    }

    /**
     * 列名转换成Java属性名
     */
    public static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[] { '_' }).replace("_", "");
    }

    /**
     * 表名转换成Java类名
     */
    public static String tableToJava(String tableName, String tablePrefix) {
        if (StringUtils.isNotBlank(tablePrefix)) {
            tableName = tableName.replace(tablePrefix, "");
        }
        return columnToJava(tableName);
    }

    /**
     * 获取配置信息
     */
    public static Configuration getConfig() {
        try {
            return new PropertiesConfiguration("generator.properties");
        } catch (ConfigurationException e) {
            throw new RuntimeException("获取配置文件失败，", e);
        }
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String template,BuildConfigDTO buildConfigDTO,TableInfoConfig tableInfoConfig) {

        String sep = File.separator;

        String sqlPath = StringUtils.isNotBlank(buildConfigDTO.getSubProjectName())
            ? buildConfigDTO.getSubProjectName() + sep + "docs" +  sep + "sql" + sep
            : "tmp" + sep + "docs" +  sep + "sql"  + sep;

        String mainPath = StringUtils.isNotBlank(buildConfigDTO.getSubProjectName())
            ? buildConfigDTO.getSubProjectName() + sep + "src" +  sep + "main" + sep
            : "tmp" + sep + "src" +  sep + "main"  + sep;

        String javaPath = mainPath + "java" + sep;
        String resourcesPath = mainPath + "resources";

        String frontPath = StringUtils.isNotBlank(buildConfigDTO.getVueProjecePath()) ?
            buildConfigDTO.getVueProjecePath() + sep + "src" +  sep :
            getRootPath() +  sep + "mdsp-console" + sep + "src" +  sep;


        String className = tableInfoConfig.getClassName();
        if (template.contains("templates/index.js.vm")) {
            return frontPath + "api" + sep + buildConfigDTO.getModuleName() + sep + toLowerCaseFirstOne(className) + ".js";
        }

        if (template.contains("templates/index.vue.vm")) {
            return frontPath + "views" + sep + buildConfigDTO.getModuleName() + sep + toLowerCaseFirstOne(className) + sep + "index.vue";
        }

        if (template.contains("templates/Entity.java.vm")) {
            return javaPath + tableInfoConfig.getEntityPackageName().replace(".", sep) + sep + className + ".java";
        }
        if (template.contains("templates/query.java.vm")) {
            String path = javaPath + tableInfoConfig.getQueryPackageName().replace(".", sep);
            return path + sep + className + "Query.java";
        }
        if (template.contains("templates/Mapper.java.vm")) {
            String path = javaPath + tableInfoConfig.getMapperPackageName().replace(".", sep);
            return path + sep + className + "Mapper.java";
        }
        if (template.contains("templates/service.java.vm")) {
            String path = javaPath + tableInfoConfig.getServicePackageName().replace(".", sep);
            return path + sep + className + "Service.java";
        }
        if (template.contains("templates/serviceImpl.java.vm")) {
            String path = javaPath + tableInfoConfig.getServicePackageName().replace(".", sep);
            return path + sep + "impl" + sep + className + "ServiceImpl.java";
        }
        if (template.contains("templates/controller.java.vm")) {
            String path = javaPath + tableInfoConfig.getControllerPackageName().replace(".", sep);
            return path + sep + className + "Controller.java";
        }
        if (template.contains("templates/Mapper.xml.vm")) {
            return resourcesPath + sep+  "mapper" + sep+ className + "Mapper.xml";
        }
        if (template.contains("templates/menu.sql.vm")) {
            return sqlPath + className + "menu.sql";
        }
        return null;
    }

    /**
     * 构建表配置信息  包名等
     * @param buildConfigDTO
     * @param tableInfoConfig
     */
    public static void buildTableConfig(BuildConfigDTO buildConfigDTO,TableInfoConfig tableInfoConfig) {
        BeanUtils.copyProperties(buildConfigDTO, tableInfoConfig);
    }

    /**
     * 构建表基本数据信息
     * @param config
     * @param tableConfig
     * @param columns
     */
    public static void  buildTableInfo(Configuration config,TableInfoConfig tableConfig,List<ColumnInfo> columns) {
        // 表名转换成Java类名
        String className = tableToJava(tableConfig.getTableName(), config.getString("tablePrefix"));
        tableConfig.setClassName(className);
        tableConfig.setLowerClassName(StringUtils.uncapitalize(className));

        // 列信息
        List<ColumnInfoConfig> columnInfoConfigs = new ArrayList<>();
        for (ColumnInfo column : columns) {


            ColumnInfoConfig columnEntity = new ColumnInfoConfig();
            BeanUtils.copyProperties(column, columnEntity);

            // 列名转换成Java属性名
            String upAttrName = columnToJava(column.getColumnName());
            columnEntity.setUpAttrName(upAttrName);
            String temp = columnEntity.getUpAttrName();
            temp = (new StringBuilder()).append(Character.toLowerCase(temp.charAt(0)))
                    .append(temp.substring(1)).toString();
            columnEntity.setAttrName(temp);
            // 列的数据类型，转换成Java类型
            String attrType = config.getString(columnEntity.getDataType(), "unknowType");
            columnEntity.setAttrType(attrType);
            if (!hasBigDecimal && StringUtils.equals("BigDecimal", column.getDataType())) {
                hasBigDecimal = true;
            }
            // 是否主键
            if ( tableConfig.getPk() == null && StringUtils.equalsIgnoreCase("PRI", column.getColumnKey()) ) {
                tableConfig.setPk(columnEntity);
            }

            columnInfoConfigs.add(columnEntity);
        }
        tableConfig.setColumnInfo(columnInfoConfigs);

        // 没主键，则第一个字段为主键
        if (tableConfig.getPk() == null) {
            tableConfig.setPk(tableConfig.getColumnInfo().get(0));
        }
    }

    /**
     * 生成代码
     * @param buildConfigDTO
     * @param tableConfig
     * @param zip
     */
    public static void gen(BuildConfigDTO buildConfigDTO,TableInfoConfig tableConfig, ZipOutputStream zip) {
        // 设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);
        LocalDateTime now = LocalDateTime.now();

        // 封装模板数据
        Map<String, Object> map = new HashMap<>();
        map.put("ids", getIdList());
        map.put("tableInfo", tableConfig);
        map.put("hasBigDecimal", hasBigDecimal);
        map.put("datetime", now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        map.put("columns", tableConfig.getColumnInfo());
        map.put("moduleName", toLowerCaseFirstOne(buildConfigDTO.getModuleName()));
        map.put("secondModuleName", toLowerCaseFirstOne(tableConfig.getClassName()));
        map.put("underlineModuleName", fieldCamel2Underline(tableConfig.getClassName()));
        VelocityContext context = new VelocityContext(map);

        // 获取模板列表
        List<String> templates = getTemplates();
        for (String template : templates) {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            tpl.merge(context, sw);
            String filePath = getFileName(template,buildConfigDTO, tableConfig);

            //直接写入文件文件
            fileEntry(sw,tableConfig,filePath);

            //压缩文件
            if(buildConfigDTO.getBuildCodeType() == 1){
                zipEntry(zip,sw,tableConfig,filePath);
            }

        }

    }




    /**
     * 首字母转小写
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 驼峰法转下划线
     */
    public static String fieldCamel2Underline(String str) {
        return fieldCamel2Charline(str,"_");
    }
    /**
     * 驼峰法转下划线
     */
    public static String fieldCamel2Midline(String str) {
        return fieldCamel2Charline(str,"-");
    }

    /**
     * 驼峰法转下划线
     */
    public static String fieldCamel2Charline(String str,String charStr) {
        if (StrUtil.isBlank(str)) {
            return "";
        }
        if(str.length()==1){
            return str.toLowerCase();
        }
        StringBuffer sb = new StringBuffer();
        for(int i=1;i<str.length();i++){
            if(Character.isUpperCase(str.charAt(i))){
                sb.append(charStr+Character.toLowerCase(str.charAt(i)));
            }else{
                sb.append(str.charAt(i));
            }
        }
        return (str.charAt(0)+sb.toString()).toLowerCase();
    }


    /**
     * 生成到文件
     * @param
     * @throws IOException
     */
    public static void fileEntry(StringWriter sw,TableInfoConfig tableConfig, String filePath){
        try {
            File file = new File(filePath);
            createDirectory(file.getParentFile());
            file.createNewFile();
            FileOutputStream out = new FileOutputStream(file);
            IOUtils.write(sw.toString(), out, "UTF-8");
            out.close();
        } catch (IOException e) {
            throw new RuntimeException("渲染模板失败，表名：" + tableConfig.getTableName(), e);
        }
    }


    /**
     * 生成压缩包
     */
    public static void zipEntry(ZipOutputStream zip,StringWriter sw,TableInfoConfig tableConfig, String filePath){
        try {
            // 添加到zip
            zip.putNextEntry(new ZipEntry(filePath));
            IOUtils.write(sw.toString(), zip, "UTF-8");
            IOUtils.closeQuietly(sw);
            zip.closeEntry();
        } catch (IOException e) {
            throw new RuntimeException("渲染模板失败，表名：" + tableConfig.getTableName(), e);
        }
    }

    /**
     * 创建文件路径
     */
    public static void createDirectory(File file) {
        if  (!file .exists() && !file .isDirectory()) {
            file.mkdirs();
        }
    }

    /**
     * 获取 Root Path
     */
    public static String getRootPath() {
        String root = System.getProperty("user.dir");
        return (new File(root)).getParentFile().getPath();
    }

    /**
     * 获取 id List
     */
    public static List<Long> getIdList() {
        List<Long> ids = new ArrayList<>();
        IdWorker idWorker = new IdWorker();
        for(int i = 0; i < 10; i++ ) {
            ids.add(idWorker.getId());
        }
        return ids;
    }

}
