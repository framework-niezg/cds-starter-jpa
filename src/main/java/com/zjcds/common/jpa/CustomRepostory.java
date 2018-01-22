package com.zjcds.common.jpa;

import com.zjcds.common.base.domain.page.Paging;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * created date：2017-08-31
 * @author niezhegang
 */
@NoRepositoryBean
public interface CustomRepostory<T,ID extends Serializable> extends JpaRepository<T,ID>,JpaSpecificationExecutor<T>{

    public PageResult<T> findAll(Paging paging, List<String> queryString, List<String> orderBy);

    public EntityManager getEntityManager();

}
