package kr.ac.kopo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.ac.kopo.dao.MongoRepository;
import kr.ac.kopo.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	@Qualifier("CustomerDao")
	MongoRepository<Customer> dao;

	@Override
	public List<Customer> list() {		
		return dao.list();
	}

	@Override
	public void add(Customer item) {
		dao.add(item);
	}

	@Override
	public Customer item(String custid) {
		return dao.item(custid);
	}

	@Override
	public void update(Customer item) {
		dao.update(item.getCustid(), item);
	}

	@Override
	public void delete(String custid) {
		dao.delete(custid);
	}

}
