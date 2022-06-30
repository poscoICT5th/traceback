package Pack.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "traceback")
@Getter
@NoArgsConstructor
@ToString
@Setter
public class TraceDTO {
	@Id
	String id;
	String lot_no;
	String item_name;
	String item_code;
	int amount;
	String stock_quality_status;
	String stock_cause;
	List<TraceDTO> consumed;
	
	public TraceDTO(ReceiveTraceDTO receiveTraceDTO) {
		this.lot_no = receiveTraceDTO.getLot_no();
		this.item_name = receiveTraceDTO.getItem_name();
		this.item_code = receiveTraceDTO.getItem_code();
		this.amount = receiveTraceDTO.getAmount();
		this.stock_quality_status = receiveTraceDTO.getStock_quality_status();
		this.stock_cause = receiveTraceDTO.getStock_cause();
		this.consumed = new ArrayList<>();
	}
	
	public void addConsumed(TraceDTO consumedInfo) {
		this.consumed.add(consumedInfo);
	}
}
