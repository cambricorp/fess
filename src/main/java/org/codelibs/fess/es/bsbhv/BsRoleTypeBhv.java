package org.codelibs.fess.es.bsbhv;

import java.util.Map;

import org.codelibs.fess.es.bsentity.AbstractEntity;
import org.codelibs.fess.es.bsentity.AbstractEntity.RequestOptionCall;
import org.codelibs.fess.es.bsentity.dbmeta.RoleTypeDbm;
import org.codelibs.fess.es.cbean.RoleTypeCB;
import org.codelibs.fess.es.exentity.RoleType;
import org.dbflute.Entity;
import org.dbflute.bhv.readable.CBCall;
import org.dbflute.cbean.ConditionBean;
import org.dbflute.cbean.result.ListResultBean;
import org.dbflute.cbean.result.PagingResultBean;
import org.dbflute.exception.IllegalBehaviorStateException;
import org.dbflute.optional.OptionalEntity;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.index.IndexRequestBuilder;

/**
 * @author FreeGen
 */
public abstract class BsRoleTypeBhv extends AbstractBehavior<RoleType, RoleTypeCB> {

    @Override
    public String asTableDbName() {
        return "role_type";
    }

    @Override
    protected String asIndexEsName() {
        return ".fess_config";
    }

    @Override
    public RoleTypeDbm asDBMeta() {
        return RoleTypeDbm.getInstance();
    }

    @Override
    protected <RESULT extends RoleType> RESULT createEntity(Map<String, Object> source, Class<? extends RESULT> entityType) {
        try {
            final RESULT result = entityType.newInstance();
            result.setCreatedBy((String) source.get("createdBy"));
            result.setCreatedTime((Long) source.get("createdTime"));
            result.setId((String) source.get("id"));
            result.setName((String) source.get("name"));
            result.setSortOrder((Integer) source.get("sortOrder"));
            result.setUpdatedBy((String) source.get("updatedBy"));
            result.setUpdatedTime((Long) source.get("updatedTime"));
            result.setValue((String) source.get("value"));
            return result;
        } catch (InstantiationException | IllegalAccessException e) {
            final String msg = "Cannot create a new instance: " + entityType.getName();
            throw new IllegalBehaviorStateException(msg, e);
        }
    }

    public int selectCount(CBCall<RoleTypeCB> cbLambda) {
        return facadeSelectCount(createCB(cbLambda));
    }

    public OptionalEntity<RoleType> selectEntity(CBCall<RoleTypeCB> cbLambda) {
        return facadeSelectEntity(createCB(cbLambda));
    }

    protected OptionalEntity<RoleType> facadeSelectEntity(RoleTypeCB cb) {
        return doSelectOptionalEntity(cb, typeOfSelectedEntity());
    }

    protected <ENTITY extends RoleType> OptionalEntity<ENTITY> doSelectOptionalEntity(RoleTypeCB cb, Class<? extends ENTITY> tp) {
        return createOptionalEntity(doSelectEntity(cb, tp), cb);
    }

    @Override
    public RoleTypeCB newConditionBean() {
        return new RoleTypeCB();
    }

    @Override
    protected Entity doReadEntity(ConditionBean cb) {
        return facadeSelectEntity(downcast(cb)).orElse(null);
    }

    public RoleType selectEntityWithDeletedCheck(CBCall<RoleTypeCB> cbLambda) {
        return facadeSelectEntityWithDeletedCheck(createCB(cbLambda));
    }

    public OptionalEntity<RoleType> selectByPK(String id) {
        return facadeSelectByPK(id);
    }

    protected OptionalEntity<RoleType> facadeSelectByPK(String id) {
        return doSelectOptionalByPK(id, typeOfSelectedEntity());
    }

    protected <ENTITY extends RoleType> ENTITY doSelectByPK(String id, Class<? extends ENTITY> tp) {
        return doSelectEntity(xprepareCBAsPK(id), tp);
    }

    protected RoleTypeCB xprepareCBAsPK(String id) {
        assertObjectNotNull("id", id);
        return newConditionBean().acceptPK(id);
    }

    protected <ENTITY extends RoleType> OptionalEntity<ENTITY> doSelectOptionalByPK(String id, Class<? extends ENTITY> tp) {
        return createOptionalEntity(doSelectByPK(id, tp), id);
    }

    @Override
    protected Class<? extends RoleType> typeOfSelectedEntity() {
        return RoleType.class;
    }

    @Override
    protected Class<RoleType> typeOfHandlingEntity() {
        return RoleType.class;
    }

    @Override
    protected Class<RoleTypeCB> typeOfHandlingConditionBean() {
        return RoleTypeCB.class;
    }

    public ListResultBean<RoleType> selectList(CBCall<RoleTypeCB> cbLambda) {
        return facadeSelectList(createCB(cbLambda));
    }

    public PagingResultBean<RoleType> selectPage(CBCall<RoleTypeCB> cbLambda) {
        // TODO same?
        return (PagingResultBean<RoleType>) facadeSelectList(createCB(cbLambda));
    }

    public void insert(RoleType entity) {
        doInsert(entity, null);
    }

    public void insert(RoleType entity, RequestOptionCall<IndexRequestBuilder> opLambda) {
        if (entity instanceof AbstractEntity) {
            entity.asDocMeta().indexOption(opLambda);
        }
        doInsert(entity, null);
    }

    public void update(RoleType entity) {
        doUpdate(entity, null);
    }

    public void update(RoleType entity, RequestOptionCall<IndexRequestBuilder> opLambda) {
        if (entity instanceof AbstractEntity) {
            entity.asDocMeta().indexOption(opLambda);
        }
        doUpdate(entity, null);
    }

    public void insertOrUpdate(RoleType entity) {
        doInsertOrUpdate(entity, null, null);
    }

    public void insertOrUpdate(RoleType entity, RequestOptionCall<IndexRequestBuilder> opLambda) {
        if (entity instanceof AbstractEntity) {
            entity.asDocMeta().indexOption(opLambda);
        }
        doInsertOrUpdate(entity, null, null);
    }

    public void delete(RoleType entity) {
        doDelete(entity, null);
    }

    public void delete(RoleType entity, RequestOptionCall<DeleteRequestBuilder> opLambda) {
        if (entity instanceof AbstractEntity) {
            entity.asDocMeta().deleteOption(opLambda);
        }
        doDelete(entity, null);
    }

    // TODO create, modify, remove
}