package zw.co.soskode.SchoolManagementSystem.service;

import java.util.List;
import java.util.Optional;

/**
 * Created by clive on 9/14/15.
 */
public interface IService<T> {

    T save(T t);

    Optional<T> findOne(Long id);

    Optional<List<T>> findAll();

    void delete(Long id);


    public Boolean checkDuplicate(T t);
}
