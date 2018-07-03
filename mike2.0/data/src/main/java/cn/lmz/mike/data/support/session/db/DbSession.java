package cn.lmz.mike.data.support.session.db;

import cn.lmz.mike.common.M;
import cn.lmz.mike.data.exception.DataException;
import cn.lmz.mike.data.support.session.ISession;
import cn.lmz.mike.data.support.session.db.sql.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class DbSession implements ISession {

    private static Logger log = LoggerFactory.getLogger(DbSession.class);
    @Resource
    protected JdbcOperations jdbcTemplate;

    @Override
    public List query(Query query){
        try{
            return query(query.getSql(),query.getParams().toArray());
        }catch (Exception e){
            throw new DataException(query,e);
        }
    }

    @Override
    public <T> T queryObj(Class<?extends T> clz,Query query) {
        return queryObj(clz,query.getSql(),query.getParams().toArray());
    }

    @Override
    public int insert(Insert insert)  {
        try{
            return execute(insert.getSql(),insert.getParams().toArray());
        }catch (Exception e){
            throw new DataException(insert,e);
        }
    }

    @Override
    public int delete(Delete delete)  {
        try{
            return execute(delete.getSql(),delete.getParams().toArray());
        }catch (Exception e){
            throw new DataException(delete,e);
        }
    }

    @Override
    public int update(Update update)  {
        try{
            return execute(update.getSql(),update.getParams().toArray());
        }catch (Exception e){
            throw new DataException(update,e);
        }
    }


    @Override
    public int execute(String sql, Object... objs)  {
        try{
            log.debug("execute=>sql:{},objs:{}",sql, M.string.toString(objs));
            return jdbcTemplate.update(sql,objs);
        }catch (Exception e){
            throw new DataException(sql,objs,e);
        }
    }

    @Override
    public <T> T queryObj(Class<? extends T> clz,String sql, Object... objs)  {
        try{
            log.debug("queryObj=>clz:{},sql:{},objs:{}",clz,sql, M.string.toString(objs));
            return jdbcTemplate.queryForObject(sql,objs,clz);
        }catch (Exception e){
            throw new DataException(sql,objs,e);
        }
    }

    @Override
    public List query(String sql, Object... objs)  {
        try{
            log.debug("query=>sql:{},objs:{}",sql, M.string.toString(objs));
            return jdbcTemplate.queryForList(sql,objs);
        }catch (Exception e){
            throw new DataException(sql,objs,e);
        }
    }

}
