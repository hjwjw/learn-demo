package io.github.hjwjw.api.v1;

import io.github.hjwjw.app.service.IPersonService;
import io.github.hjwjw.domain.entity.Person;
import io.github.hjwjw.infra.annotation.ProcessResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This PersonController class.
 *
 * @author hjw
 * @date 2019/11/24 16:05
 */
@RestController
@RequestMapping("/v1/hjwjw/person")
public class PersonController {

    private IPersonService personService;

    public PersonController(IPersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    @ProcessResult
    public ResponseEntity<List<Person>> query(){
        return ResponseEntity.ok(personService.queryPerson());
    }

    @PostMapping
    @ProcessResult
    public ResponseEntity<Person> createPerson(@RequestBody Person personVO){
        return ResponseEntity.ok(personService.createPerson(personVO));
    }

    @PutMapping
    @ProcessResult
    public ResponseEntity<Person> updatePerson(@RequestBody Person personVO){
        return ResponseEntity.ok(personService.updatePerson(personVO));
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity delPerson(@PathVariable("personId") Long personId){
        personService.delPerson(personId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
