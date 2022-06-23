package Pack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import Pack.vo.TestVo;
import Pack.vo.TraceDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TracebackService {

	@Autowired 
	private MongoTemplate mongoTemplate;
	
    public List<TraceDTO> getTracebackList() {
        System.out.println("test");
        return null;
    }
}
