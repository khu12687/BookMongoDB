package kr.ac.kopo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.ac.kopo.dao.MongoRepository;
import kr.ac.kopo.model.Book;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	@Qualifier("BookDao")
	MongoRepository<Book> dao;

	@Override
	public List<Book> list() {
		return dao.list();
	}

	@Override
	public void add(Book item) {
		dao.add(item);
	}

	@Override
	public Book item(String bookid) {
		return dao.item(bookid);
	}

	@Override
	public void update(Book item) {
		dao.update(item.getBookid(), item);		
	}

	@Override
	public void delete(String bookid) {
		dao.delete(bookid);
	}

}
