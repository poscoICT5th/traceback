package Pack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import Pack.vo.TraceDTO;
import lombok.RequiredArgsConstructor;
import Pack.service.TracebackService;


@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MainController {
    private final TracebackService tracebackService;
    
    @Autowired
    MongoTemplate mongoTemplate;
    
    @GetMapping("/")
    public List<TraceDTO> test(){
        System.out.println("test");
        return mongoTemplate.findAll(TraceDTO.class, "traceback");
        // return (ShelterNameSearchResponsseDto) shelterService.getShelterList(title);
    }
}
