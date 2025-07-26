package org.kolbasa;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException {
        ApplicationContext context = new ApplicationContext(
//                SupportServiceImpl.class,
//                SupportRepository.class
                );
//        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
        //dispatcherServlet.service();
        System.out.println(context.getBean(SupportService.class).getSupportPhrase());
        System.out.println(context.getBean(SupportRepository.class));
    }
}
