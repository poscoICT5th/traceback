package Pack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.mongodb.client.result.UpdateResult;

import Pack.vo.ReceiveTraceDTO;
import Pack.vo.TestVo;
import Pack.vo.TraceDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TracebackService {

	@Autowired 
	private MongoTemplate mongoTemplate;
	
    public List<TraceDTO> selectAll() {
    	return mongoTemplate.findAll(TraceDTO.class);
    }
    
    public TraceDTO selectByLotNo(String lotno){
    	System.out.println("selectBy" + lotno);
    	Query query = new Query();
    	query.addCriteria(Criteria.where("lot_no").is(lotno));
    	return mongoTemplate.findOne(query, TraceDTO.class);
    }
    
    public TraceDTO insert(TraceDTO traceDTO) {
    	return mongoTemplate.insert(traceDTO);
    }

	public UpdateResult updateByLotNo(ReceiveTraceDTO receiveTraceDTO) {
    	Query query = new Query();
    	query.addCriteria(Criteria.where("lot_no").is(receiveTraceDTO.getLot_no()));
    	Update update = new Update();
    	update.set("stock_quality_status", receiveTraceDTO.getStock_quality_status());
    	update.set("stock_cause", receiveTraceDTO.getStock_cause());
		return mongoTemplate.upsert(query, update, TraceDTO.class);		
	}
}
