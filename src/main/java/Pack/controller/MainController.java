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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Pack.vo.ItemInfo;
import Pack.vo.ReceiveTraceDTO;
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
    public void test(@RequestBody ReceiveTraceDTO receiveTraceDTO){
//    	TraceDTO traceDTO = new TraceDTO();
//    	traceDTO.setLot_no("strip123123");
//    	TraceDTO traceDTO2 = new TraceDTO();
//    	traceDTO2.setLot_no("strip1231555");
//    	List templist = new ArrayList();
//    	templist.add(traceDTO2);
//    	List<TraceDTO> templist2 = Collections.EMPTY_LIST;
//    	templist2.add(traceDTO2);
//    	traceDTO.setConsumed(templist2);
//    	tracebackService.insert(traceDTO);
    	System.out.println(receiveTraceDTO);
    	TraceDTO traceDTO = new TraceDTO(receiveTraceDTO);
    	System.out.println(traceDTO);
    	List<ItemInfo> consumed_item_infos = receiveTraceDTO.getConsumed_infos();
		for (ItemInfo itemInfo : consumed_item_infos) {
			TraceDTO consumed_info = tracebackService.selectByLotNo(itemInfo.getLot_no());
			System.out.println("소모품");
			System.out.println(consumed_info);
			consumed_info.setAmount(itemInfo.getAmount());
			traceDTO.addConsumed(consumed_info);
		}
		TraceDTO temp = tracebackService.insert(traceDTO);
		System.out.println("여기가 인서트 후");
		System.out.println(temp);
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
