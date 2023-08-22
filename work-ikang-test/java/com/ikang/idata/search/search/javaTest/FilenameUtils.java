package com.ikang.idata.search.search.javaTest;

import cn.hutool.core.bean.*;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.SimpleCache;
import cn.hutool.core.lang.func.Func0;
import cn.hutool.core.map.CaseInsensitiveMap;
import cn.hutool.core.util.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;

import java.beans.*;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Consumer;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/10/10
 */

public class FilenameUtils {

    public static void main(String[] args) throws IOException {

//		FileUtils.write(new File("/Users/jjs/Desktop/abc.txt"), "commons-io工具类", "UTF-8", true);
        //以追加的方式写入
//		FileUtils.writeStringToFile(new File("/Users/jjs/Desktop/abcd.txt"), "作者：cxy", "UTF-8", true);
        //写入多行
        List<String> lines = new ArrayList<String>();
        lines.add("第一行");
        lines.add("第二行");
        FileUtils.writeLines(new File("/Users/jjs/Desktop/abcd.txt"), lines, true);
    }

    public static final long ONE_KB = 1024L;
    public static final long ONE_MB = 1048576L;
    private static final long FILE_COPY_BUFFER_SIZE = 31457280L;
    public static final long ONE_GB = 1073741824L;
    public static final long ONE_TB = 1099511627776L;
    public static final long ONE_PB = 1125899906842624L;
    public static final long ONE_EB = 1152921504606846976L;
    public static final BigInteger ONE_ZB = BigInteger.valueOf(1024L).multiply(BigInteger.valueOf(1152921504606846976L));
//    public static final BigInteger ONE_YB;
//    public static final File[] EMPTY_FILE_ARRAY;
//    private static final Charset UTF8;

    public FilenameUtils() {
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

//    private static void innerListFiles(Collection<File> files, File directory, IOFileFilter filter, boolean includeSubDirectories) {
//        File[] found = directory.listFiles(filter);
//        if (found != null) {
//            File[] arr$ = found;
//            int len$ = found.length;
//
//            for(int i$ = 0; i$ < len$; ++i$) {
//                File file = arr$[i$];
//                if (file.isDirectory()) {
//                    if (includeSubDirectories) {
//                        files.add(file);
//                    }
//
//                    innerListFiles(files, file, filter, includeSubDirectories);
//                } else {
//                    files.add(file);
//                }
//            }
//        }
//
//    }


//    public static Collection<File> listFiles(File directory, IOFileFilter fileFilter, IOFileFilter dirFilter) {
//        validateListFilesParameters(directory, fileFilter);
//        IOFileFilter effFileFilter = setUpEffectiveFileFilter(fileFilter);
//        IOFileFilter effDirFilter = setUpEffectiveDirFilter(dirFilter);
//        Collection<File> files = new LinkedList();
//        innerListFiles(files, directory, FileFilterUtils.or(new IOFileFilter[]{effFileFilter, effDirFilter}), false);
//        return files;
//    }


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

    @org.junit.jupiter.api.Test
    public void test38() {
//        String fileName="text.text";
//        String directory="/work/";
//        String fullName=directory+fileName;
//        String fir_1="/a/b/c";
//        String fir_2="/a/b/c";
//        String windowUrl="\\aa\\bb\\cc";
//        //获取文件名，去除文件路径和后缀名
//        //结果:text
//        System.out.println(FilenameUtils.getBaseName(fullName));
//        //获得前缀
//        //结果:/
//        System.out.println(FilenameUtils.getPrefix(fullName));
//        //获得文件名称
//        //结果:text.text
//        System.out.println(FilenameUtils.getName(fullName));
//        //window文件路径转换为Linux的文件路径
//        //结果:/aa/bb/cc
//        System.out.println(FilenameUtils.separatorsToUnix(windowUrl));
//        //合并路径和文件名，成为全路径名称,参数二不能带有"/"
//        //结果:/work/text.text
//        System.out.println(FilenameUtils.concat(directory,fileName));
//        //判断文件路径是否相同
//        System.out.println(FilenameUtils.equals(fir_1,fir_2));
//        System.out.println(FilenameUtils.equalsNormalized(fir_1,fir_2));
//        //判断指定的文件名称是否包含指定的文件扩展名
//        System.out.println(FilenameUtils.wildcardMatch(fileName,"*.text"));
//        //获得后缀名
//        System.out.println(FilenameUtils.getExtension(fullName));

    }


    private final SimpleCache<Class<?>, Map<String, PropertyDescriptor>> pdCache = new SimpleCache<>();
    private final SimpleCache<Class<?>, Map<String, PropertyDescriptor>> ignoreCasePdCache = new SimpleCache<>();

    /**
     * 获得属性名和{@link PropertyDescriptor}Map映射
     *
     * @param beanClass  Bean的类
     * @param ignoreCase 是否忽略大小写
     * @return 属性名和{@link PropertyDescriptor}Map映射
     */
    public Map<String, PropertyDescriptor> getPropertyDescriptorMap(Class<?> beanClass, boolean ignoreCase) {
        return getCache(ignoreCase).get(beanClass);
    }

    /**
     * 获得属性名和{@link PropertyDescriptor}Map映射
     *
     * @param beanClass  Bean的类
     * @param ignoreCase 是否忽略大小写
     * @param supplier   缓存对象产生函数
     * @return 属性名和{@link PropertyDescriptor}Map映射
     * @since 5.4.1
     */
    public Map<String, PropertyDescriptor> getPropertyDescriptorMap(
            Class<?> beanClass,
            boolean ignoreCase,
            Func0<Map<String, PropertyDescriptor>> supplier) {
        return getCache(ignoreCase).get(beanClass, supplier);
    }

    /**
     * 加入缓存
     *
     * @param beanClass                      Bean的类
     * @param fieldNamePropertyDescriptorMap 属性名和{@link PropertyDescriptor}Map映射
     * @param ignoreCase                     是否忽略大小写
     */
    public void putPropertyDescriptorMap(Class<?> beanClass, Map<String, PropertyDescriptor> fieldNamePropertyDescriptorMap, boolean ignoreCase) {
        getCache(ignoreCase).put(beanClass, fieldNamePropertyDescriptorMap);
    }

    /**
     * 根据是否忽略字段名的大小写，返回不用Cache对象
     *
     * @param ignoreCase 是否忽略大小写
     * @return SimpleCache
     * @since 5.4.1
     */
    private SimpleCache<Class<?>, Map<String, PropertyDescriptor>> getCache(boolean ignoreCase) {
        return ignoreCase ? ignoreCasePdCache : pdCache;
    }


    /**
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
     * 创建动态Bean
     *
     * @param bean 普通Bean或Map
     * @return {@link DynaBean}
     * @since 3.0.7
     */
    public static DynaBean createDynaBean(Object bean) {
        return new DynaBean(bean);
    }

    /**
     * 查找类型转换器 {@link PropertyEditor}
     *
     * @param type 需要转换的目标类型
     * @return {@link PropertyEditor}
     */
    public static PropertyEditor findEditor(Class<?> type) {
        return PropertyEditorManager.findEditor(type);
    }

    /**
     * 获取{@link BeanDesc} Bean描述信息
     *
     * @param clazz Bean类
     * @return {@link BeanDesc}
     * @since 3.1.2
     */
    public static BeanDesc getBeanDesc(Class<?> clazz) {
        return BeanDescCache.INSTANCE.getBeanDesc(clazz, () -> new BeanDesc(clazz));
    }

    /**
     * 遍历Bean的属性
     *
     * @param clazz  Bean类
     * @param action 每个元素的处理类
     * @since 5.4.2
     */
    public static void descForEach(Class<?> clazz, Consumer<? super PropDesc> action) {
        getBeanDesc(clazz).getProps().forEach(action);
    }

    // --------------------------------------------------------------------------------------------------------- PropertyDescriptor

    /**
     * 获得Bean字段描述数组
     *
     * @param clazz Bean类
     * @return 字段描述数组
     * @throws BeanException 获取属性异常
     */
    public static PropertyDescriptor[] getPropertyDescriptors(Class<?> clazz) throws BeanException {
        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(clazz);
        } catch (IntrospectionException e) {
            throw new BeanException(e);
        }
        return ArrayUtil.filter(beanInfo.getPropertyDescriptors(), t -> {
            // 过滤掉getClass方法
            return false == "class".equals(t.getName());
        });
    }

    /**
     * 获得字段名和字段描述Map，获得的结果会缓存在 {@link BeanInfoCache}中
     *
     * @param clazz      Bean类
     * @param ignoreCase 是否忽略大小写
     * @return 字段名和字段描述Map
     * @throws BeanException 获取属性异常
     */
    public static Map<String, PropertyDescriptor> getPropertyDescriptorMap1(Class<?> clazz, boolean ignoreCase) throws BeanException {
        return BeanInfoCache.INSTANCE.getPropertyDescriptorMap(clazz, ignoreCase, () -> internalGetPropertyDescriptorMap(clazz, ignoreCase));
    }

    /**
     * 获得字段名和字段描述Map。内部使用，直接获取Bean类的PropertyDescriptor
     *
     * @param clazz      Bean类
     * @param ignoreCase 是否忽略大小写
     * @return 字段名和字段描述Map
     * @throws BeanException 获取属性异常
     */
    private static Map<String, PropertyDescriptor> internalGetPropertyDescriptorMap(Class<?> clazz, boolean ignoreCase) throws BeanException {
        final PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(clazz);
        final Map<String, PropertyDescriptor> map = ignoreCase ? new CaseInsensitiveMap<>(propertyDescriptors.length, 1)
                : new HashMap<>((int) (propertyDescriptors.length), 1);

        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            map.put(propertyDescriptor.getName(), propertyDescriptor);
        }
        return map;
    }

    /**
     * 获得Bean类属性描述，大小写敏感
     *
     * @param clazz     Bean类
     * @param fieldName 字段名
     * @return PropertyDescriptor
     * @throws BeanException 获取属性异常
     */
    public static PropertyDescriptor getPropertyDescriptor(Class<?> clazz, final String fieldName) throws BeanException {
        return getPropertyDescriptor(clazz, fieldName, false);
    }

    /**
     * 获得Bean类属性描述
     *
     * @param clazz      Bean类
     * @param fieldName  字段名
     * @param ignoreCase 是否忽略大小写
     * @return PropertyDescriptor
     * @throws BeanException 获取属性异常
     */
    public static PropertyDescriptor getPropertyDescriptor(Class<?> clazz, final String fieldName, boolean ignoreCase) throws BeanException {
        final Map<String, PropertyDescriptor> map = getPropertyDescriptorMap1(clazz, ignoreCase);
        return (null == map) ? null : map.get(fieldName);
    }

    /**
     * 获得字段值，通过反射直接获得字段值，并不调用getXXX方法<br>
     * 对象同样支持Map类型，fieldNameOrIndex即为key
     *
     * <ul>
     *     <li>Map: fieldNameOrIndex需为key，获取对应value</li>
     *     <li>Collection: fieldNameOrIndex当为数字，返回index对应值，非数字遍历集合返回子bean对应name值</li>
     *     <li>Array: fieldNameOrIndex当为数字，返回index对应值，非数字遍历数组返回子bean对应name值</li>
     * </ul>
     *
     * @param bean             Bean对象
     * @param fieldNameOrIndex 字段名或序号，序号支持负数
     * @return 字段值
     */
    public static Object getFieldValue(Object bean, String fieldNameOrIndex) {
        if (null == bean || null == fieldNameOrIndex) {
            return null;
        }

        if (bean instanceof Map) {
            return ((Map<?, ?>) bean).get(fieldNameOrIndex);
        } else if (bean instanceof Collection) {
            try {
                return CollUtil.get((Collection<?>) bean, Integer.parseInt(fieldNameOrIndex));
            } catch (NumberFormatException e) {
                // 非数字，see pr#254@Gitee
                return CollUtil.map((Collection<?>) bean, (beanEle) -> getFieldValue(beanEle, fieldNameOrIndex), false);
            }
        } else if (ArrayUtil.isArray(bean)) {
            try {
                return ArrayUtil.get(bean, Integer.parseInt(fieldNameOrIndex));
            } catch (NumberFormatException e) {
                // 非数字，see pr#254@Gitee
                return ArrayUtil.map(bean, Object.class, (beanEle) -> getFieldValue(beanEle, fieldNameOrIndex));
            }
        } else {// 普通Bean对象
            return ReflectUtil.getFieldValue(bean, fieldNameOrIndex);
        }
    }

    /**
     * 设置字段值，通过反射设置字段值，并不调用setXXX方法<br>
     * 对象同样支持Map类型，fieldNameOrIndex即为key
     *
     * @param bean             Bean
     * @param fieldNameOrIndex 字段名或序号，序号支持负数
     * @param value            值
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void setFieldValue(Object bean, String fieldNameOrIndex, Object value) {
        if (bean instanceof Map) {
            ((Map) bean).put(fieldNameOrIndex, value);
        } else if (bean instanceof List) {
            CollUtil.setOrAppend((List) bean, Convert.toInt(fieldNameOrIndex), value);
        } else if (ArrayUtil.isArray(bean)) {
            ArrayUtil.setOrAppend(bean, Convert.toInt(fieldNameOrIndex), value);
        } else {
            // 普通Bean对象
            ReflectUtil.setFieldValue(bean, fieldNameOrIndex, value);
        }
    }

    /**
     * 解析Bean中的属性值
     *
     * @param <T>        属性值类型
     * @param bean       Bean对象，支持Map、List、Collection、Array
     * @param expression 表达式，例如：person.friend[5].name
     * @return Bean属性值，bean为{@code null}或者express为空，返回{@code null}
     * @see BeanPath#get(Object)
     * @since 3.0.7
     */
    @SuppressWarnings("unchecked")
    public static <T> T getProperty(Object bean, String expression) {
        if (null == bean || StrUtil.isBlank(expression)) {
            return null;
        }
        return (T) BeanPath.create(expression).get(bean);
    }

    /**
     * 解析Bean中的属性值
     *
     * @param bean       Bean对象，支持Map、List、Collection、Array
     * @param expression 表达式，例如：person.friend[5].name
     * @param value      属性值
     * @see BeanPath#get(Object)
     * @since 4.0.6
     */
    public static void setProperty(Object bean, String expression, Object value) {
        BeanPath.create(expression).set(bean, value);
    }

    // --------------------------------------------------------------------------------------------- mapToBean
}
