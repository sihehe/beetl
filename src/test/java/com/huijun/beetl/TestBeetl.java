package com.huijun.beetl;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.core.resource.StringTemplateResourceLoader;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: beetl
 * @description: beetl牛刀小试
 * @author: Mr.si
 * @create: 2019-06-14 13:45
 **/


public class TestBeetl {

    @Test
    public  void test1() throws Exception {
        StringTemplateResourceLoader stringTemplateResourceLoader = new StringTemplateResourceLoader();
        Configuration configuration = Configuration.defaultConfiguration();
        GroupTemplate groupTemplate = new GroupTemplate(stringTemplateResourceLoader, configuration);
        Template template = groupTemplate.getTemplate("hello,${name},${age},${sex}");
        HashMap<Object, Object> map = new HashMap<>();
        map.put("name","beetl");
        map.put("age","16");
        map.put("sex","男");
        template.binding(map);
        String render = template.render();
        System.out.println(render);
    }

    @Test
    public void testLoaderFile() throws IOException {
        ClasspathResourceLoader loader = new ClasspathResourceLoader("template/");
        Configuration configuration = Configuration.defaultConfiguration();
        GroupTemplate groupTemplate = new GroupTemplate(loader, configuration);
        Template template = groupTemplate.getTemplate("/template.txt");
        List<Map<String,String>> list = new ArrayList();
        for (int i = 0; i < 100; i++) {
            HashMap<String, String> map = new HashMap<>();
            map.put("text","个人简历"+i);
            map.put("name","张三"+i);
            map.put("sex","男"+i);
            map.put("age","18"+i);
            list.add(map);
        }
        File file = new File("D:\\hello.txt");
        long start = System.currentTimeMillis();
       /* for (int i = 0; i < 10; i++) {
            aaa(template, list, file);
        }*/
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    synchronized (TestBeetl.class) {
                        try {
                            aaa(template, list, file);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

        long end = System.currentTimeMillis();
        System.out.println(end-start);

//        System.out.println(render);
    }

    private void aaa(Template template, List<Map<String, String>> list, File file) throws IOException {
            for (Map<String, String> obj : list) {
                template.binding(obj);
                FileWriter writer = new FileWriter(file,true);
                template.renderTo(writer);
            }
    }

}
