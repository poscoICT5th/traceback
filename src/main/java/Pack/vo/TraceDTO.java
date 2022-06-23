package Pack.vo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Document(collection = "traceback")
@Getter
@NoArgsConstructor
public class TraceDTO {
	@Id
	String id;
	String lot_no;
	List<TraceDTO> consumed;
}
