package io.github.hjwjw.app.service;

import io.github.hjwjw.domain.entity.Person;

import java.util.List;

/**
 * This IPersonService class.
 *
 * @author hjw
 * @date 2019/11/24 16:04
 */
public interface IPersonService {

    List<Person> queryPerson();

    Person createPerson(Person personVO);

    Person updatePerson(Person personVO);

    void delPerson(Long personId);
}
