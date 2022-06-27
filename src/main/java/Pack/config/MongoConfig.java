package Pack.config;
// 안씀 no use
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
 
public class MongoConfig { 
 
  public MongoClient mongoClient() { 
 
    MongoClient mongoClient = MongoClients.create( 
      new ConnectionString("mongodb://admin:00000000@35.76.107.22:27017/pscict_final authSource=admin") 
    ); 
 
    return mongoClient; 
  } 
 
  public MongoTemplate mongoTemplate() { 
    return new MongoTemplate(mongoClient(), "pscict_final"); 
  } 
} 
