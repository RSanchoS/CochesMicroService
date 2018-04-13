package com.roberto.CochesMicroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coches")
public class Controller {


    @Autowired
    private MongoTemplate mongoTemplate;


    @GetMapping
    private ResponseEntity<?> getCoches(){
        try{
            System.out.println("[RS] getCoches httpStatus.OK");
        return new ResponseEntity<>(this.mongoTemplate.findAll(Coche.class), HttpStatus.OK);

        } catch (Exception e){
            System.out.println("[RS] getCoches httpStatus.OK");
            return new ResponseEntity<>("Internal Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{numeroMatricula}")
    private ResponseEntity<?> getCoche(@PathVariable("numeroMatricula") String numeroMatricula){

            Query query = new Query();
            query.addCriteria(Criteria.where("numeroMatricula").is(numeroMatricula));
            try{
            return new ResponseEntity<>(this.mongoTemplate.findOne(query,Coche.class), HttpStatus.OK);
            } catch (Exception e){
                return new ResponseEntity<>("Internal Error", HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @GetMapping("/marca/{marca}")
    private ResponseEntity<?> getCochesByMarca(@PathVariable("marca") String marca){
        Query query = new Query();
        query.addCriteria(Criteria.where("marca").is(marca));
        try{
        return new ResponseEntity<>(this.mongoTemplate.find(query, Coche.class), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Internal Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    private ResponseEntity<?> saveCoche(@RequestBody Coche coche){
        try {
            mongoTemplate.save(coche);
            return new  ResponseEntity<>(coche, HttpStatus.CREATED);
        } catch (Exception e){
        return new ResponseEntity<>("Internal Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/{numeroMatricula}")
    private ResponseEntity<?> deleteCocheByMatricula(@PathVariable("numeroMatricula") String numeroMatricula){
        Query query = new Query();
        query.addCriteria(Criteria.where("numeroMatricula").is(numeroMatricula));
        try{
        Coche deleteCoche = this.mongoTemplate.findOne(query, Coche.class);
        if (deleteCoche == null) return new ResponseEntity<>("Coche no existente", HttpStatus.NOT_FOUND);
        mongoTemplate.remove(deleteCoche);
        return new ResponseEntity<>("Coche eliminado",HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity<>("Internal Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    private ResponseEntity<?> putCoche(@RequestBody Coche coche){
        Query query = new Query();
        query.addCriteria(Criteria.where("numeroMatricula").is(coche.getNumeroMatricula()));

        try{
            Coche modifyCoche = this.mongoTemplate.findOne(query, Coche.class);
            if (modifyCoche == null) return new ResponseEntity<>("Coche no existente", HttpStatus.NOT_FOUND);
        modifyCoche.setMarca(coche.getMarca());
        modifyCoche.setModelo(coche.getModelo());

        mongoTemplate.save(modifyCoche);

        return new ResponseEntity<>(modifyCoche, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Internal Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
