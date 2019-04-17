package com.gp.mymvcframework.myspring.webmvc.servlet;


import com.gp.mymvcframework.annotation.MyController;
import com.gp.mymvcframework.annotation.MyRequestMapping;
import com.gp.mymvcframework.myspring.context.MyApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Administrator
 */
public class MyDispatcherServlet extends HttpServlet {

    private final String CONTEXT_CONFIG_LOCATION = "contextConfigLocation";

    private MyApplicationContext context;

    private List<MyHandlerMapping> handlerMappings = new ArrayList<MyHandlerMapping>();

    private Map<MyHandlerMapping,MyHandlerAdapter> handlerAdapters = new HashMap<MyHandlerMapping,MyHandlerAdapter>();

    private List<MyViewResolver> viewResolvers = new ArrayList<MyViewResolver>();

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
        //1、通过从request中拿到URL，去匹配一个HandlerMapping
        MyHandlerMapping handler = getHandler(req);

        if(handler == null){
            processDispatchResult(req,resp,new MyModelAndView("404"));
            return;
        }

        //2、准备调用前的参数
        MyHandlerAdapter ha = getHandlerAdapter(handler);

        //3、真正的调用方法,返回ModelAndView存储了要穿页面上值，和页面模板的名称
        MyModelAndView mv = ha.handle(req,resp,handler);

        //这一步才是真正的输出
        processDispatchResult(req, resp, mv);

    }

    private MyHandlerAdapter getHandlerAdapter(MyHandlerMapping handler) {
        if(this.handlerAdapters.isEmpty()){return null;}
        MyHandlerAdapter ha = this.handlerAdapters.get(handler);
        if(ha.supports(handler)){
            return ha;
        }
        return null;
    }

    private void processDispatchResult(HttpServletRequest req, HttpServletResponse resp, MyModelAndView myModelAndView) throws Exception {
        //把给我的ModleAndView变成一个HTML、OuputStream、json、freemark、veolcity
        //ContextType
        if(null == myModelAndView){return;}

        //如果ModelAndView不为null，怎么办？
        if(this.viewResolvers.isEmpty()){return;}

        for (MyViewResolver viewResolver : this.viewResolvers) {
            MyView view = viewResolver.resolveViewName(myModelAndView.getViewName(),null);
            view.render(myModelAndView.getModel(),req,resp);
            return;
        }
    }

    private MyHandlerMapping getHandler(HttpServletRequest req) {
        if(this.handlerMappings.isEmpty()){ return null; }

        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");

        for (MyHandlerMapping handler : this.handlerMappings) {
            try{
                Matcher matcher = handler.getPattern().matcher(url);
                //如果没有匹配上继续下一个匹配
                if(!matcher.matches()){ continue; }

                return handler;
            }catch(Exception e){
                throw e;
            }
        }
        return null;
    }


    @Override
    public void init(ServletConfig config) throws ServletException {
        //1、初始化ApplicationContext
        context = new MyApplicationContext(config.getInitParameter(CONTEXT_CONFIG_LOCATION));
        //2、初始化Spring MVC 九大组件
        initStrategies(context);
    }

    //初始化策略
    protected void initStrategies(MyApplicationContext context) {
        //1.多文件上传的组件
        //initMultipartResolver(context);
        //2.初始化本地语言环境
       // initLocaleResolver(context);
        //3.初始化模板处理器
       // initThemeResolver(context);
        //4.handlerMapping，必须实现
        initHandlerMappings(context);
        //5.初始化参数适配器，必须实现
        initHandlerAdapters(context);
        //6.初始化异常拦截器
       // initHandlerExceptionResolvers(context);
        //7.初始化视图预处理器
       // initRequestToViewNameTranslator(context);
        //8.初始化视图转换器，必须实现
        initViewResolvers(context);
        //9.参数缓存器
       // initFlashMapManager(context);
    }

    //4handlerMapping，必须实现
    private void initHandlerMappings(MyApplicationContext context) {
        String [] beanNames = context.getBeanDefinitionNames();
        try {

            for (String beanName : beanNames) {
                Object controller = context.getBean(beanName);
                Class<?> clazz = controller.getClass();
                if(!clazz.isAnnotationPresent(MyController.class)){
                    continue;
                }

                String baseUrl = "";
                //获取Controller的url配置
                if(clazz.isAnnotationPresent(MyRequestMapping.class)){
                    MyRequestMapping requestMapping = clazz.getAnnotation(MyRequestMapping.class);
                    baseUrl = requestMapping.value();
                }

                //获取Method的url配置
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {

                    //没有加RequestMapping注解的直接忽略
                    if(!method.isAnnotationPresent(MyRequestMapping.class)){ continue; }

                    //映射URL
                    MyRequestMapping requestMapping = method.getAnnotation(MyRequestMapping.class);
                    String regex = ("/" + baseUrl + "/" + requestMapping.value().replaceAll("\\*",".*")).replaceAll("/+", "/");
                    Pattern pattern = Pattern.compile(regex);

                    this.handlerMappings.add(new MyHandlerMapping(pattern,controller,method));
                    //log.info("Mapped " + regex + "," + method);
                }


            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //5.初始化参数适配器，必须实现
    private void initHandlerAdapters(MyApplicationContext context) {

        //把一个requet请求变成一个handler，参数都是字符串的，自动配到handler中的形参

        //可想而知，他要拿到HandlerMapping才能干活
        //就意味着，有几个HandlerMapping就有几个HandlerAdapter
        for (MyHandlerMapping handlerMapping : this.handlerMappings) {
            this.handlerAdapters.put(handlerMapping,new MyHandlerAdapter());
        }

    }

    //8.初始化视图转换器，必须实现
    private void initViewResolvers(MyApplicationContext context) {
        //拿到模板的存放目录
        String templateRoot = context.getConfig().getProperty("templateRoot");
        String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();

        File templateRootDir = new File(templateRootPath);
        String[] templates = templateRootDir.list();
        for (int i = 0; i < templates.length; i ++) {
            //这里主要是为了兼容多模板，所有模仿Spring用List保存
            //在我写的代码中简化了，其实只有需要一个模板就可以搞定
            //只是为了仿真，所有还是搞了个List
            MyViewResolver myViewResolver =  new MyViewResolver(templateRoot);
            this.viewResolvers.add(myViewResolver);
        }
    }






}
