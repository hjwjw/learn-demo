package io.github.hjwjw.infra.repository;

import io.github.hjwjw.domain.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This DataOpertion class.
 *
 * @author hjw
 * @date 2019/11/24 16:16
 */
@Component
public class DataOpertion {
    private static final Logger log = LoggerFactory.getLogger(DataOpertion.class);
    private static Map<Long, Person> personMap = new HashMap<>();
    private static Long personId = 1L;

    public Person createPerson(Person person){
        person.createBefore();
        person.setId(personId);
        personMap.put(personId,person);
        log.info("<==========createPerson:{}",person);
        //返回一个新对象。如直接返回原对象在切面被替换值后，Map中的值也会变，因为他们是同一个对象。
        Person personVO = new Person();
        BeanUtils.copyProperties(personMap.get(personId),personVO);
        personId++;
        return personVO;
    }

    public Person updatePerson(Person person){
        Assert.notNull(personMap.get(person.getId()),"数据不存在");
        person.updateBefore(person.getObjectVersionNumber());
        personMap.put(person.getId(),person);
        log.info("<==========updatePerson:{}",person);
        Person personVO = new Person();
        BeanUtils.copyProperties(personMap.get(person.getId()),personVO);
        return personVO;
    }

    public List<Person> queryPerson(){
        log.info("<==========queryPerson==============>");
        List<Person> personList = new ArrayList<>();
        for (Map.Entry<Long,Person> entry : personMap.entrySet()){
            Person personVO = new Person();
            BeanUtils.copyProperties(entry.getValue(),personVO);
            personList.add(personVO);
        }
        return personList;
    }

    public void delPerson(Long personId){
        log.info("<==========delPerson:{}==============>",personId);
        Assert.notNull(personMap.get(personId),"数据不存在");
        personMap.remove(personId);
    }
}
