package io.github.hjwjw.app.service.impl;

import io.github.hjwjw.app.service.IPersonService;
import io.github.hjwjw.domain.entity.Person;
import io.github.hjwjw.infra.repository.DataOpertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This PersonServiceImpl class.
 *
 * @author hjw
 * @date 2019/11/24 16:04
 */
@Service
public class PersonServiceImpl implements IPersonService {
    @Autowired
    private DataOpertion dataOpertion;

    @Override
    public List<Person> queryPerson() {
        return dataOpertion.queryPerson();
    }

    @Override
    public Person createPerson(Person personVO) {
        return dataOpertion.createPerson(personVO);
    }

    @Override
    public Person updatePerson(Person personVO) {
        return dataOpertion.updatePerson(personVO);
    }

    @Override
    public void delPerson(Long personId) {
        dataOpertion.delPerson(personId);
    }
}
