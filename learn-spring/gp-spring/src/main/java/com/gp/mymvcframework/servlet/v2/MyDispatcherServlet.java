package com.gp.mymvcframework.servlet.v2;


import com.gp.mymvcframework.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class MyDispatcherServlet extends HttpServlet {

    private static final String parmName = "contextConfigLocation";

    private List<String> classNmeList = new ArrayList<String>();

    private Map<String, Object> iocMap = new HashMap<>();

    Properties properties = new Properties();

    private Map handleMapping = new HashMap();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    //执行业务处理
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //6、调用，运行阶段
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("500 Exection,Detail : " + Arrays.toString(e.getStackTrace()));
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String url = req.getRequestURI();
        String basePath = req.getContextPath();
        String path = url.replaceAll(basePath, "").replaceAll("/+", "/");
        if (!this.handleMapping.containsKey(path)) {
            resp.getWriter().write("500 Exection,Detail : " + path + " 不存在！");
            return;
        }
        Method method = (Method) handleMapping.get(path);
        String beanName = firstCharToLow(method.getDeclaringClass().getSimpleName());
        Map<String, String[]> parameterMap = req.getParameterMap();
        Class<?>[] parameterTypes = method.getParameterTypes();
        //保存赋值参数的位置
        Object[] paramValues = new Object[parameterTypes.length];

        Map<String, Integer> paramIndexMapping = new HashMap<>();

        Annotation[][] annotations = method.getParameterAnnotations();
        for (int i = 0; i < annotations.length; ++i) {
            for (Annotation a : annotations[i]) {
                if (a instanceof MyRequestParam) {
                    String value = ((MyRequestParam) a).value();
                    if (!"".equals(value)) {
                        paramIndexMapping.put(value, i);
                    }
                }
            }
        }
        //按根据参数位置动态赋值
        for (int i = 0; i < parameterTypes.length; i++) {
            Class parameterType = parameterTypes[i];
            if (parameterType == HttpServletRequest.class) {
                paramIndexMapping.put(parameterType.getName(), i);
            } else if (parameterType == HttpServletResponse.class) {
                paramIndexMapping.put(parameterType.getName(), i);
            }
        }

        Map<String, String[]> params = req.getParameterMap();
        for (Map.Entry<String, String[]> param : params.entrySet()) {
            String value = Arrays.toString(param.getValue()).replaceAll("\\[|\\]", "").replaceAll(",\\s", ",");
            //如果找到匹配的对象，则开始填充参数值
            if (!paramIndexMapping.containsKey(param.getKey())) {
                continue;
            }
            int index = paramIndexMapping.get(param.getKey());
            paramValues[index] = convert(parameterTypes[index], value);
        }


        //设置方法中的request和response对象
        int reqIndex = paramIndexMapping.get(HttpServletRequest.class.getName());
        paramValues[reqIndex] = req;
        int respIndex = paramIndexMapping.get(HttpServletResponse.class.getName());
        paramValues[respIndex] = resp;

        method.invoke(iocMap.get(beanName), paramValues);

    }

    private Object convert(Class<?> parameterType, String value) {
        if (Integer.class == parameterType) {
            return Integer.valueOf(value);
        }
        return value;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //1.初始化配置文件
        doLoadConfig(config);
        //2.根据配置文件扫描文件
        doScannerFile(properties.getProperty("scanPackage"));
        //3.将类文件初始化到IOC容器中
        doInstance();
        //4.依赖注入
        doAutowired();
        //5.构建handleMapping（url 和方法的关系列表）
        creeateHandleMapping();
        //6.等待请求，匹配URL，定位方法， 反射调用执行
        System.out.println("my MyDispatcherServlet init sucessfull");
    }

    //5.构建handleMapping（url 和方法的关系列表） 初始化url和Method的一对一对应关系
    private void creeateHandleMapping() {
        if (iocMap.isEmpty()) {
            return;
        }
        String path = "";
        for (Object object : iocMap.values()) {
            Class clazz = object.getClass();
            if (!clazz.isAnnotationPresent(MyController.class)) {
                continue;
            }
            MyRequestMapping myRequestMapping = (MyRequestMapping) clazz.getAnnotation(MyRequestMapping.class);
            String topPath = myRequestMapping.value();
            for (Method method : clazz.getMethods()) {
                if (!method.isAnnotationPresent(MyRequestMapping.class)) continue;
                MyRequestMapping myRequestMapping2 = (MyRequestMapping) method.getAnnotation(MyRequestMapping.class);
                path = ("/" + topPath + "/" + myRequestMapping2.value()).replaceAll("/+", "/");
                handleMapping.put(path, method);

            }


        }

    }

    //4.依赖注入
    private void doAutowired() {
        if (iocMap.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : iocMap.entrySet()) {
            //Declared 所有的，特定的 字段，包括private/protected/default
            //正常来说，普通的OOP编程只能拿到public的属性
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(MyAutowired.class)) continue;
                MyAutowired myAutowired = field.getAnnotation(MyAutowired.class);
                String beanName = myAutowired.value().trim();
                if ("".equals(beanName)) {
                    beanName = field.getType().getName();
                }
                //授权
                field.setAccessible(true);
                try {
                    field.set(entry.getValue(), iocMap.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }


        }

    }

    //3.将类文件初始化到IOC容器中
    private void doInstance() {
        if (classNmeList.isEmpty()) {
            return;
        }
        try {
            //不是所有的类都要被解析的，只有满足一定条件的类才被解析
            for (String className : classNmeList) {
                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(MyController.class)) {
                    String clazzName = firstCharToLow(clazz.getSimpleName());
                    Object instance = clazz.newInstance();
                    iocMap.put(clazzName, instance);
                } else if (clazz.isAnnotationPresent(MyService.class)) {
                    Object instance = clazz.newInstance();
                    //1.自定义bean
                    MyService myService = (MyService) clazz.getAnnotation(MyService.class);
                    String clazzName = myService.value();
                    if (!"".equals(clazzName)) {
                        iocMap.put(clazzName, instance);
                    } else {//2.默认 值
                        clazzName = firstCharToLow(clazz.getSimpleName());
                        iocMap.put(clazzName, clazz.newInstance());
                    }
                    //3、根据类型自动赋值,投机取巧的方式
                    for (Class anInterface : clazz.getInterfaces()) {
                        if (iocMap.containsKey(anInterface.getName())) {
                            throw new Exception(" the " + anInterface.getName() + " exist");
                        }
                        //把接口的类型直接当成key了
                        iocMap.put(anInterface.getName(), instance);
                    }
                } else {
                    continue;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private static String firstCharToLow(String className) {
        char[] clssCharArray = className.toCharArray();
        if (clssCharArray.length == 0) {
            return className;
        }
        if (clssCharArray[0] <= 90 && clssCharArray[0] >= 65) {
            clssCharArray[0] += 32;
            return String.valueOf(clssCharArray);
        }
        return className;
    }

    //2.根据配置文件扫描文件
    private void doScannerFile(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/") + "/");
        File file = new File(url.getFile());
        for (File file1 : file.listFiles()) {
            if (file1.isDirectory()) {//如果是目录，递归往下继续找
                doScannerFile(scanPackage + "." + file1.getName());
            } else if (file1.getName().endsWith(".class")) {
                classNmeList.add((scanPackage + "." + file1.getName().replaceAll(".class", "").trim()));
            } else {
                continue;
            }

        }
    }

    //1.初始化配置文件
    private void doLoadConfig(ServletConfig config) {
        InputStream is = null;
        try {
            String path = config.getInitParameter(parmName);
            is = this.getClass().getClassLoader().getResourceAsStream(path);
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
