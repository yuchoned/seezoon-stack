package com.seezoon.admin.framework.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seezoon.dao.framework.CrudDao;
import com.seezoon.dao.framework.entity.BaseEntity;
import com.seezoon.dao.framework.entity.QueryCondition;

/**
 * 增删改查service
 *
 * @author hdf
 */
public abstract class AbstractCrudService<D extends CrudDao<T, PK>, T extends BaseEntity<PK>, PK>
    extends AbstractTransactionService {

    @SuppressWarnings("all")
    @Autowired
    protected D d;

    /**
     * 根据主键查询
     *
     * @param pk
     * @return
     */
    @Transactional(readOnly = true)
    public T findById(PK pk) {
        return this.d.selectByPrimaryKey(pk);
    }

    /**
     * 自定义条件查询
     *
     * @param condition
     * @return
     */
    @Transactional(readOnly = true)
    public List<T> findByCondition(QueryCondition condition) {
        return this.d.selectByCondition(condition);
    }

    /**
     * 分页查询
     *
     * @param condition
     * @param pageNum
     * @param pageSize
     * @param count
     * @return
     */
    @Transactional(readOnly = true)
    public PageInfo<T> findByPage(QueryCondition condition, int pageNum, int pageSize, boolean count) {
        PageHelper.startPage(pageNum, pageSize, count);
        List<T> list = this.findByCondition(condition);
        PageInfo<T> pageInfo = new PageInfo<T>(list);
        return pageInfo;
    }

    /**
     * 分页查询
     *
     * @param condition
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Transactional(readOnly = true)
    public PageInfo<T> findByPage(QueryCondition condition, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize, Boolean.TRUE);
        List<T> list = this.findByCondition(condition);
        PageInfo<T> pageInfo = new PageInfo<T>(list);
        return pageInfo;
    }

    /**
     * 插入支持批量
     *
     * @param records
     * @return
     */
    public int save(T... records) {
        return this.d.insert(records);
    }

    /**
     * 按主键选择性更新，默认自动修改更新时间及更新人
     *
     * @param record
     * @return
     */
    public int updateSelective(T record) {
        record.setUpdateTime(new Date());
        return this.d.updateByPrimaryKey(record);
    }

    /**
     * 按主键全量更新,默认自动修改更新时间及更新人
     *
     * <pre>
     * 默认不更新创建时间及创建时间
     * </pre>
     *
     * @param record
     * @return
     */
    public int update(T record) {
        return this.d.updateByPrimaryKey(record);
    }

    /**
     * 按主键删除，一般业务在逻辑上保证不会删除，如果非要删除建议使用逻辑删除
     *
     * <pre>
     * 该方法不做权限控制
     * </pre>
     *
     * @param pks
     * @return
     */
    public int delete(PK... pks) {
        return this.d.deleteByPrimaryKey(pks);
    }
}