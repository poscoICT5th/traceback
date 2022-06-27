 package Pack.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import Pack.vo.TraceDTO;
import lombok.RequiredArgsConstructor;
import Pack.service.TracebackService;


@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*") 
public class MainController {
    @Autowired
	TracebackService tracebackService;
    
    @GetMapping("/test")
    public void test(){
    	TraceDTO traceDTO = new TraceDTO();
    	traceDTO.setLot_no("strip123123");
    	TraceDTO traceDTO2 = new TraceDTO();
    	traceDTO2.setLot_no("strip1231555");
    	List templist = new ArrayList();
    	templist.add(traceDTO2);
    	List<TraceDTO> templist2 = Collections.EMPTY_LIST;
    	templist2.add(traceDTO2);
    	traceDTO.setConsumed(templist2);
    	tracebackService.insert(traceDTO);
    }
    
    @GetMapping("/")
    public List<TraceDTO> selectAll(){
    	return tracebackService.selectAll();
    }
    
    @GetMapping("/lotno/{lotno}")
    public TraceDTO selectByLotNo(@PathVariable String lotno){
    	return tracebackService.selectByLotNo(lotno);
    }
}
