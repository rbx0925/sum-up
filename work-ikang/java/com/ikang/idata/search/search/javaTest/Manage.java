package com.ikang.idata.search.search.javaTest;

import java.util.Iterator;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/12/14
 */
public class Manage {

    public static void main(String[] args) {
        More School = new More("zhejiang university");
        One Student0 = new One("xiaoming", 99);
        One Student1 = new One("xiaohong", 100);
        One Student2 = new One("xiaogang", 101);

        School.AddStu().add(Student0);//只是将其引用（地址）
        School.AddStu().add(Student1);
        School.AddStu().add(Student2);

        //Student0.SetName("阿黄");//该句的作用。看看在创建好一对多的关系后，是否能通过之前的Student0来改变School里面的Student的值

        System.out.println(School);//将School中冲定义的那个ToString（返回的是String）输出
        System.out.println(School.toString());//这句和上一句是一样的

        Iterator<One> ite = School.AddStu().iterator();//对象是类
        while (ite.hasNext()) {
            System.out.println(ite.next());
        }



        //***************************************************************************



//        List<One> amisModels = one.findByModelClass(one.getSimpleName());
//        if (CollectionUtils.isNotEmpty(amisModels) && amisModels.size() > 1) {
//            throw new Exception("存在重复记录");
//        }
//        List<AmisModelField> amisModelFields = new ArrayList<>();
//        AmisModel amisModel = new AmisModel().setModelClass(modelClass.getSimpleName());
//        if (CollectionUtils.isEmpty(amisModels)) {
//            //不存在则新增
//            for (int i = 0; i < modelClass.getDeclaredFields().length; i++) {
//                Field field = modelClass.getDeclaredFields()[i];
//                if (field.isAnnotationPresent(Comment.class)) {
//                    amisModelFields.add(new AmisModelField()
//                            .setAmisModel(amisModel)
//                            .setFieldName(field.getName())
//                            .setComment(field.getAnnotation(Comment.class).value())
//                            .setOrderNum(i));
//                }
//            }
//        } else {
//            //存在记录则更新
//            amisModel = amisModels.get(0);
//            List<AmisModelField> amisModelFieldList = amisModel.getAmisModelFieldList();
//            //新数据根据某唯一字段处理成map集合
//            Map<String, AmisModelField> fieldMap = amisModelFieldList.stream().collect(Collectors.toMap(AmisModelField::getFieldName, t -> t));
//            //旧数据删除不存在的字段记录
//            List<String> fields = ListUtil.of(modelClass.getDeclaredFields()).stream().map(Field::getName).collect(Collectors.toList());
//            List<AmisModelField> delFields = amisModelFieldList.stream().filter(t -> !fields.contains(t.getFieldName())).collect(Collectors.toList());
//            if(CollectionUtils.isNotEmpty(delFields)){
//                amisModelFieldDao.deleteAllInBatch(delFields);
//            }
//            for (int i = 0; i < modelClass.getDeclaredFields().length; i++) {
//                Field field = modelClass.getDeclaredFields()[i];
//                if (field.isAnnotationPresent(Comment.class)) {
//                    if (fieldMap.get(field.getName()) == null) {
//                        //一对多,多方新增
//                        amisModelFields.add(new AmisModelField()
//                                .setAmisModel(amisModel)
//                                .setFieldName(field.getName())
//                                .setComment(field.getAnnotation(Comment.class).value())
//                                .setOrderNum(i));
//                    } else {
//                        //一对多,多方更新
//                        amisModelFields.add(fieldMap.get(field.getName())
//                                .setComment(field.getAnnotation(Comment.class).value())
//                                .setOrderNum(i));
//                    }
//                }
//            }
//        }
//        amisModel.setAmisModelFieldList(amisModelFields);
//        amisModelDao.saveAndFlush(amisModel);
    }
}
