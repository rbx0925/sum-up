package com.ikang.idata.search.search.util;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ModifierUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.write.metadata.WriteSheet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.logging.log4j.util.PropertySource;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.junit.Test;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.beans.Introspector;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author <a href="mailto:minxin.fan-ext@ikang.com">minxin.fan</a>
 * @version 1.0
 * @description: TODO
 * @date 2023/2/15 9:26
 */
public class FileUtilsTest {

    private static void fileUtilsTest() {

        try {

            //读取文件内容

            String readFileToString = FileUtils.readFileToString(new File("D:\\guor\\data\\20211022000000.txt"));

            System.out.println(readFileToString);

            //删除文件夹

            FileUtils.deleteDirectory(new File("D:\\guor1\\data1"));

            FileUtils.deleteQuietly(new File("D:\\guor\\data"));

            //清空文件夹

            FileUtils.cleanDirectory(new File("D:\\guor\\data"));

            //判断文件内容是否一致

            boolean contentEquals = FileUtils.contentEquals(new File("D:\\guor\\data\\20211022000000.txt"), new File("D:\\guor\\data\\New20211021000000.txt"));

            boolean contentEquals2 = FileUtils.contentEquals(new File("D:\\guor\\data\\中文20211021100000.txt"), new File("D:\\guor\\data\\中文New20211022100000.txt"));

            System.out.println(contentEquals + "," + contentEquals2);

            //拷贝特定类型的文件

            FileUtils.copyDirectory(new File("D:\\guor\\data"), new File("D:\\guor\\data2"), new FileFilter() {

                @Override

                public boolean accept(File pathname) {

                    if (pathname.getName().startsWith("test")) {

                        return true;

                    }

                    return false;

                }

            });

            FileUtils.moveDirectory(new File("D:\\guor\\data"), new File("D:\\guor\\data3"));

            FileUtils.moveFileToDirectory(new File("D:\\guor\\data"), new File("D:\\guor\\data3"), true);

            FileUtils.moveToDirectory(new File("D:\\guor\\data"), new File("D:\\guor\\data4"), true);

            boolean directoryContains = FileUtils.directoryContains(new File("D:\\guor\\data"), new File("D:\\guor\\data\\est20211022000000.txt"));

            System.out.println(directoryContains);

            directoryContains = FileUtils.directoryContains(new File("D:\\guor\\data"), new File("*.txt"));

            System.out.println(directoryContains);

            //获取某文件夹下特定格式文件

            File[] listFiles = new File("D:\\guor\\data").listFiles(new FilenameFilter() {

                @Override

                public boolean accept(File dir, String name) {

                    return name.startsWith("test");

                }

            });


            System.out.println(Arrays.toString(listFiles));

            //获取系统temp文件夹路径

            File tempDirectory = FileUtils.getTempDirectory();

            System.out.println(tempDirectory);

            //获取系统用户文件夹路径

            File userDirectory = FileUtils.getUserDirectory();

            System.out.println(userDirectory);


            //查看是否是新建的文件夹

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

            Date parse = sdf.parse("20210206134900");

            boolean fileNewer = FileUtils.isFileNewer(new File("D:\\guor\\data"), parse);

            System.out.println(fileNewer);

            //更新文件修改时间，如果不存在，则新建；

            FileUtils.touch(new File("D:\\guor\\data\\20211022000000.txt"));

            //延迟查看文件是否存在

            boolean waitFor = FileUtils.waitFor(new File("D:\\guor\\data\\New20211021000000.txt"), 5);

            System.out.println(waitFor);

        } catch (Exception e) {

            System.out.println(e);

        }
    }


    //根据文件修改时间排序

    public static void test02() {
        String dir = "D:\\data";

        File[] listFiles = new File(dir).listFiles();

        List asList = Arrays.asList(listFiles);

        for (File file : listFiles) {
            System.out.println(file);
        }

        Collections.sort(asList, new PropertySource.Comparator() {
            public int compare(File o1, File o2) {
                if (o1.lastModified() == 1) {
                    return -1;
                } else if (o1.lastModified() > o2.lastModified()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
    }

    public static File getFile(File directory, String... names) {
        if (directory == null) {
            throw new NullPointerException("directorydirectory must not be null");
        } else if (names == null) {
            throw new NullPointerException("names must not be null");
        } else {
            File file = directory;
            String[] arr$ = names;
            int len$ = names.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                String name = arr$[i$];
                file = new File(file, name);
            }

            return file;
        }
    }

    public static File getFile(String... names) {
        if (names == null) {
            throw new NullPointerException("names must not be null");
        } else {
            File file = null;
            String[] arr$ = names;
            int len$ = names.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                String name = arr$[i$];
                if (file == null) {
                    file = new File(name);
                } else {
                    file = new File(file, name);
                }
            }

            return file;
        }
    }

    public static String getTempDirectoryPath() {
        return System.getProperty("java.io.tmpdir");
    }

    public static File getTempDirectory() {
        return new File(getTempDirectoryPath());
    }

    public static String getUserDirectoryPath() {
        return System.getProperty("user.home");
    }

    public static File getUserDirectory() {
        return new File(getUserDirectoryPath());
    }

    public static FileInputStream openInputStream(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            } else if (!file.canRead()) {
                throw new IOException("File '" + file + "' cannot be read");
            } else {
                return new FileInputStream(file);
            }
        } else {
            throw new FileNotFoundException("File '" + file + "' does not exist");
        }
    }

    public static FileOutputStream openOutputStream(File file) throws IOException {
        return openOutputStream(file, false);
    }

    public static FileOutputStream openOutputStream(File file, boolean append) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }

            if (!file.canWrite()) {
                throw new IOException("File '" + file + "' cannot be written to");
            }
        } else {
            File parent = file.getParentFile();
            if (parent != null && !parent.mkdirs() && !parent.isDirectory()) {
                throw new IOException("Directory '" + parent + "' could not be created");
            }
        }

        return new FileOutputStream(file, append);
    }

    public static String byteCountToDisplaySize(long size) {
        String displaySize;
        if (size / 1152921504606846976L > 0L) {
            displaySize = size / 1152921504606846976L + " EB";
        } else if (size / 1125899906842624L > 0L) {
            displaySize = size / 1125899906842624L + " PB";
        } else if (size / 1099511627776L > 0L) {
            displaySize = size / 1099511627776L + " TB";
        } else if (size / 1073741824L > 0L) {
            displaySize = size / 1073741824L + " GB";
        } else if (size / 1048576L > 0L) {
            displaySize = size / 1048576L + " MB";
        } else if (size / 1024L > 0L) {
            displaySize = size / 1024L + " KB";
        } else {
            displaySize = size + " bytes";
        }

        return displaySize;
    }

    public static void touch(File file) throws IOException {
        if (!file.exists()) {
            OutputStream out = openOutputStream(file);
            IOUtils.closeQuietly(out);
        }

        boolean success = file.setLastModified(System.currentTimeMillis());
        if (!success) {
            throw new IOException("Unable to set the last modification time for " + file);
        }
    }

    public static File[] convertFileCollectionToFileArray(Collection<File> files) {
        return (File[])files.toArray(new File[files.size()]);
    }

    private static void innerListFiles(Collection<File> files, File directory, IOFileFilter filter, boolean includeSubDirectories) {
       // File[] found = directory.listFiles(filter);
       // if (found != null) {
       //     File[] arr$ = found;
       //     int len$ = found.length;
       //
       //     for(int i$ = 0; i$ < len$; ++i$) {
       //         File file = arr$[i$];
       //         if (file.isDirectory()) {
       //             if (includeSubDirectories) {
       //                 files.add(file);
       //             }
       //
       //             innerListFiles(files, file, filter, includeSubDirectories);
       //         } else {
       //             files.add(file);
       //         }
       //     }
       // }

    }

    public static Collection<File> listFiles(File directory, IOFileFilter fileFilter, IOFileFilter dirFilter) {
        validateListFilesParameters(directory, fileFilter);
        IOFileFilter effFileFilter = setUpEffectiveFileFilter(fileFilter);
        IOFileFilter effDirFilter = setUpEffectiveDirFilter(dirFilter);
        Collection<File> files = new LinkedList();
        innerListFiles(files, directory, FileFilterUtils.or(new IOFileFilter[]{effFileFilter, effDirFilter}), false);
        return files;
    }

    private static void validateListFilesParameters(File directory, IOFileFilter fileFilter) {
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Parameter 'directory' is not a directory");
        } else if (fileFilter == null) {
            throw new NullPointerException("Parameter 'fileFilter' is null");
        }
    }

    private static IOFileFilter setUpEffectiveFileFilter(IOFileFilter fileFilter) {
        return FileFilterUtils.and(new IOFileFilter[]{fileFilter, FileFilterUtils.notFileFilter(DirectoryFileFilter.INSTANCE)});
    }

    private static IOFileFilter setUpEffectiveDirFilter(IOFileFilter dirFilter) {
        return dirFilter == null ? FalseFileFilter.INSTANCE : FileFilterUtils.and(new IOFileFilter[]{dirFilter, DirectoryFileFilter.INSTANCE});
    }


    /**
     * 递归统计ES聚合的数量
     * @param aggregations
     * @return
     */
    public static long count(Aggregations aggregations) {
        long totalCount = 0L;
        for (Aggregation aggregation : aggregations) {
            Terms terms = (Terms) aggregation;
            List<? extends Terms.Bucket> buckets = terms.getBuckets();
            if (buckets.size() > 0) {
                if (buckets.get(0).getAggregations().iterator().hasNext()) {
                    // 如果内部还有aggregation，就继续往下走，不能统计
                    for (Terms.Bucket bucket : buckets) {
                        Aggregations aggregationsInners = bucket.getAggregations();
                        if (aggregationsInners == null || aggregationsInners.asList().size() == 0) {
                            System.out.println("进入了aggregationsInners == null || aggregationsInners.asList().size() == 0");
                        } else {
                            totalCount += count(aggregationsInners);
                        }
                    }
                } else {
                    // 到底了，后面没有新的aggregation，可以统计数据了
                    totalCount += buckets.size();
                }
            }
        }
        return totalCount;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @HeadRowHeight(20) //表头行高
    @ContentRowHeight(15) //行高
    @ColumnWidth(15)  //列宽
    public class DemoData {
        @ExcelProperty(index = 0,value = "字符串标题") //对应导出表中的表头标题
        private String id;
        @ColumnWidth(25) //单独设置列宽
        @ExcelProperty(index = 1,value = "日期标题")
        private Date date;
        @ExcelProperty(index = 2,value = "数字标题")
        private Double number;
        @ExcelIgnore //忽略该字段
        private String ignore;
    }

    @Test
    void contextLoads() {
    }

    final static String path = "C:\\Users\\EDY\\Desktop\\";  //绝对路径
    private List<DemoData> data() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setId("字符串" + i);  //字符串
            data.setDate(new Date());   //获取当前日期
            data.setNumber(Math.random());  //生成随机小数【0，1）
            list.add(data);
        }
        return list;
    }


    @Test
    public void simpleWrite() {

        // 注意 simpleWrite在数据量不大的情况下可以使用（5000以内，具体也要看实际情况），数据量大参照 重复多次写入
        // 写法1 JDK8+
        // since: 3.0.0-beta1
        String fileName = path + "simpleWrite" + System.currentTimeMillis() + ".xlsx";  //文件中命名中加入时间戳，避免每次导出数据到同一个表格，引起数据覆盖
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, DemoData.class)
                .sheet("模板")
                .doWrite(data());

        // 写法2
        fileName = path + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());

        // 写法3:使用 try-with-resources @since 3.1.0
        fileName = path + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = EasyExcel.write(fileName, DemoData.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
        excelWriter.write(data(), writeSheet);
        excelWriter.finish(); //关闭
        System.out.println("导出成功！");

        // 写法4: 不使用 try-with-resources
        fileName = path+ "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写
        excelWriter = null;
        try {
            excelWriter = EasyExcel.write(fileName, DemoData.class).build();
            writeSheet = EasyExcel.writerSheet("模板").build();
            excelWriter.write(data(), writeSheet);
        } finally {
            // 千万别忘记close 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }


    private final ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();
    private final ArrayList<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();


    public void ZhouyuApplicationContext(Class<?> configClass) throws ClassNotFoundException {

        // 扫描--->BeanDefinition-->beanDefinitionMap
        if (configClass.isAnnotationPresent(ComponentScan.class)) {

            ComponentScan componentScanAnnotation = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
            String path = null; // 扫描路径 com.zhouyu.service

            path = path.replace(".", "/"); //  com/zhouyu/service

            ClassLoader classLoader = com.ikang.idata.search.search.common.ZhouyuApplicationContext.class.getClassLoader();
            URL resource = classLoader.getResource(path);

            File file = new File(resource.getFile());

            if (file.isDirectory()) {
                File[] files = file.listFiles();

                for (File f : files) {
                    String fileName = f.getAbsolutePath();

                    if (fileName.endsWith(".class")) {

                        String className = fileName.substring(fileName.indexOf("com"), fileName.indexOf(".class"));

                        className = className.replace("\\", ".");

                        try {
                            Class<?> clazz = classLoader.loadClass(className);

                            if (clazz.isAnnotationPresent(Component.class)) {

                                if (BeanPostProcessor.class.isAssignableFrom(clazz)) {
                                    BeanPostProcessor instance = (BeanPostProcessor) clazz.newInstance();
                                    beanPostProcessorList.add(instance);
                                }

                                Component component = clazz.getAnnotation(Component.class);
                                String beanName = component.value();

                                if (beanName.equals("")) {
                                    beanName = Introspector.decapitalize(clazz.getSimpleName());
                                }


                                BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
//                                beanDefinition.setType(clazz);
//
                                if (clazz.isAnnotationPresent(Scope.class)) {
                                    Scope scopeAnnotation = clazz.getAnnotation(Scope.class);
                                    beanDefinition.setScope(scopeAnnotation.value());
                                } else {
                                    beanDefinition.setScope("singleton");
                                }

                                beanDefinitionMap.put(beanName, beanDefinition);

                            }
                        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                            e.printStackTrace();
                        }


                    }
                }
            }
        }


        // 实例化单例Bean
        for (String beanName : beanDefinitionMap.keySet()) {

            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);

            if (Objects.equals(beanDefinition.getScope(), "singleton")) {

                Object bean = createBean(beanName, beanDefinition);
                assert bean != null;
                singletonObjects.put(beanName, bean);
            }
        }


    }

    private Object createBean(String beanName, BeanDefinition beanDefinition) throws ClassNotFoundException {

        Class<?> clazz = Class.forName(beanName);

        try {
            Object instance = clazz.getConstructor().newInstance();

            // 依赖注入
            for (Field f : clazz.getDeclaredFields()) {
                if (f.isAnnotationPresent(Autowired.class)) {
                    f.setAccessible(true);
                    f.set(instance, getBean(f.getName()));
                }
            }

            // Aware
            if (instance instanceof BeanNameAware) {
                ((BeanNameAware)instance).setBeanName(beanName);
            }

//                for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
//                instance = beanPostProcessor.postProcessBeforeInitialization(beanName, instance);
//                }

            // 初始化
//            if (instance instanceof InitializingBean) {
//                ((InitializingBean)instance).afterPropertiesSet();
//            }

//                for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
//                instance = beanPostProcessor.postProcessAfterInitialization(beanName, instance);
//                }

            return instance;

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }


    public Object getBean(String beanName) throws ClassNotFoundException {

        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);

        if (beanDefinition == null) {
            throw new NullPointerException();
        } else {
            String scope = beanDefinition.getScope();

            if (Objects.equals(scope, "singleton")) {
                Object bean = singletonObjects.get(beanName);
                if (bean == null) {
                    Object o = createBean(beanName, beanDefinition);
                    assert o != null;
                    singletonObjects.put(beanName, o);
                }
                return bean;
            } else {
                return createBean(beanName, beanDefinition);
            }
        }

    }

    /**
     *
     * BeanUtil
     *
     *
     * 判断是否为可读的Bean对象，判定方法是：
     *
     * <pre>
     *     1、是否存在只有无参数的getXXX方法或者isXXX方法
     *     2、是否存在public类型的字段
     * </pre>
     *
     * @param clazz 待测试类
     * @return 是否为可读的Bean对象
     * @see #hasGetter(Class)
     * @see #hasPublicField(Class)
     */
    public static boolean isReadableBean(Class<?> clazz) {
        return hasGetter(clazz) || hasPublicField(clazz);
    }

    /**
     * 判断是否为Bean对象，判定方法是：
     *
     * <pre>
     *     1、是否存在只有一个参数的setXXX方法
     *     2、是否存在public类型的字段
     * </pre>
     *
     * @param clazz 待测试类
     * @return 是否为Bean对象
     * @see #hasSetter(Class)
     * @see #hasPublicField(Class)
     */
    public static boolean isBean(Class<?> clazz) {
        return hasSetter(clazz) || hasPublicField(clazz);
    }

    /**
     * 指定类中是否有public类型字段(static字段除外)
     *
     * @param clazz 待测试类
     * @return 是否有public类型字段
     * @since 5.1.0
     */
    public static boolean hasPublicField(Class<?> clazz) {
        if (ClassUtil.isNormalClass(clazz)) {
            for (Field field : clazz.getFields()) {
                if (ModifierUtil.isPublic(field) && false == ModifierUtil.isStatic(field)) {
                    //非static的public字段
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否有Setter方法<br>
     * 判定方法是否存在只有一个参数的setXXX方法
     *
     * @param clazz 待测试类
     * @return 是否为Bean对象
     * @since 4.2.2
     */
    public static boolean hasSetter(Class<?> clazz) {
        if (ClassUtil.isNormalClass(clazz)) {
            final Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (method.getParameterTypes().length == 1 && method.getName().startsWith("set")) {
                    // 检测包含标准的setXXX方法即视为标准的JavaBean
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否为Bean对象<br>
     * 判定方法是否存在只有无参数的getXXX方法或者isXXX方法
     *
     * @param clazz 待测试类
     * @return 是否为Bean对象
     * @since 4.2.2
     */
    public static boolean hasGetter(Class<?> clazz) {
        if (ClassUtil.isNormalClass(clazz)) {
            for (Method method : clazz.getMethods()) {
                if (method.getParameterTypes().length == 0) {
                    if (method.getName().startsWith("get") || method.getName().startsWith("is")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

