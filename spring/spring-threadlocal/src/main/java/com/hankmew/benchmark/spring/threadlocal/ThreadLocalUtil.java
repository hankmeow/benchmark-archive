package com.hankmew.benchmark.spring.threadlocal;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 本类封装了ThreadLocal，
 * 使用线程池的情况下（各种MVC框架都用了线程池）在使用最后一定要调用本工具的remove()方法，否则会内存泄露（一般在请求介绍后，过滤器拦截器中调用）
 *
 * 还是建议使用spring mvc框架的request.settAttbute方法，随着请求周期自动结束。框架已经做了很严密的清理方式。尤其是在请求发生问题而不走我们自定义的remove方法时。
 *
 * @param
 */
public class ThreadLocalUtil {

    //这里使用静态内部类实现懒加载模式。如果只是引入这个类而不是用这个类的话可以减少一点内存占用
    private static class HOLDER {
        // 不用map里面放threadlocal而是用entry里面放map的方式，是因为在清理的时候直接调用一次threadlocal.remove()，而不是循环map中的threadlocal挨个remove(), 速度快。
        private static final ThreadLocal<Map<String, Object>> INSTANCE = new ThreadLocal(){
            //这里使用内部初始化值的方式，不用在HOLDER类下面再写static方法给threadLocal赋值了
            @Override
            protected Map<String, Object> initialValue() {
                return new HashMap<>();
            }
        };
    }
    
    public static void remove() {
        HOLDER.INSTANCE.remove();
    }

    public static void put(String key, Object value) {
        Map<String, Object> map = HOLDER.INSTANCE.get();
        map.put(key, value);
    }

    public static<T> Optional<T> get(String key, Class<T> c) {
        Map<String, Object> map = HOLDER.INSTANCE.get();
        Object o = map.get(key);
        if (c.isInstance(o)) {
            return Optional.of((T) o);
        }
        return Optional.empty();
    }
}
