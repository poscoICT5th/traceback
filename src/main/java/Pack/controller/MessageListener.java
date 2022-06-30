package Pack.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Delivery;

import Pack.service.TracebackService;
import Pack.vo.ItemInfo;
import Pack.vo.ReceiveTraceDTO;
import Pack.vo.TraceDTO;

import org.springframework.amqp.rabbit.annotation.*;

import java.util.HashMap;
import java.util.List;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;

@Component
public class MessageListener {
	@Autowired
	TracebackService tracebackService;
	
	@RabbitListener(
		bindings = @QueueBinding(
			exchange = @Exchange(name="posco", type=ExchangeTypes.TOPIC),
			value=@Queue(name="toTraceback"),
			key="*.Traceback.*"))
	public void receiver(Message message, ReceiveTraceDTO receiveTraceDTO ) {
		System.out.println("trace object 받음");
		System.out.println(receiveTraceDTO);
		String receivedRoutingKey = message.getMessageProperties().getReceivedRoutingKey();
		String receiveCMD = receivedRoutingKey.split("\\.")[2];
		TraceDTO traceDTO = new TraceDTO(receiveTraceDTO);
		if (receiveCMD.equals("import")) {
			System.out.println("from import");
			tracebackService.insert(traceDTO);
		} else if (receiveCMD.equals("manufacture")) {
			System.out.println("from manufacture");
			List<ItemInfo> consumed_item_infos = receiveTraceDTO.getConsumed_infos();
			for (ItemInfo itemInfo : consumed_item_infos) {
				TraceDTO consumed_info = tracebackService.selectByLotNo(itemInfo.getLot_no());
				consumed_info.setAmount(itemInfo.getAmount());
				traceDTO.addConsumed(consumed_info);
			}
			tracebackService.insert(traceDTO);
		} else if (receiveCMD.equals("update")) {
			System.out.println("from update");
			tracebackService.updateByLotNo(receiveTraceDTO);
		}else {
			System.out.println("else dd");
		}			
		System.out.println("다시 listen중");
	}
}