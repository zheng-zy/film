package com.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

/**
 * <p></p>
 * Created by zhezhiyong@163.com on 2018/1/5.
 */
public interface BaseJPA<T> extends JpaRepository<T, Integer>, JpaSpecificationExecutor<T>, Serializable {

}
