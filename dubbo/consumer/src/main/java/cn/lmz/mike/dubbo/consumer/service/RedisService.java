package cn.lmz.mike.dubbo.consumer.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RedisService {

    public void set(String key, Object value) ;
    public Object get(String key) ;
    public void setList(String key, List<?> value);
    public Object getList(String key) ;
    public void setSet(String key, Set<?> value);
    public Object getSet(String key) ;
    public void setHash(String key, Map<String, ?> value);
    public Object getHash(String key);
    public void delete(String key);

}
