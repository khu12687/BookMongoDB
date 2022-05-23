package kr.ac.kopo.dao;

import java.lang.reflect.Field;
import java.util.List;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class MongoRepository<T> {
	protected MongoTemplate mongo;
	protected String collectionName;
	protected Class<T> entityClass;
	
	public MongoRepository(MongoTemplate mongo, String collectionName, Class<T> entityClass) {		
		this.mongo = mongo;
		this.collectionName = collectionName;
		this.entityClass = entityClass;
	}

	public List<T> list() {		
		Query query = new Query();
		
		return mongo.find(query, entityClass, collectionName);
	}

	public void add(T item) {
		mongo.insert(item, collectionName);
	}

	public T item(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
				
		return mongo.findOne(query, entityClass, collectionName);
	}

	public void update(String id, T item) {
		Query query = new Query(Criteria.where("_id").is(id));
		
		Update update = new Update();
		
		// Reflection
		for(Field field : item.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			
			try {				
				update.set(field.getName(), field.get(item));
			} catch (Exception e) {
				e.printStackTrace();			
			}
		}
		
		mongo.findAndModify(query, update, entityClass, collectionName);
	}

	public void delete(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		
		mongo.remove(query, collectionName);
	}

}
