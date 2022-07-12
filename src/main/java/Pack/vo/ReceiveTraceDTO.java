package Pack.vo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@Setter
public class ReceiveTraceDTO {
	String lot_no;
	String item_name;
	String item_code;
	int amount;
	String stock_quality_status;
	String status_cause;
	String inventory_date;
	List<ItemInfo> consumed_infos;
	List<String> statusChangeList;
}
