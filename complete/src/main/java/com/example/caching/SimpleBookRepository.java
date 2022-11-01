package com.example.caching;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class SimpleBookRepository implements BookRepository {
	private static final Logger logger = LoggerFactory.getLogger(SimpleBookRepository.class);
	@Override
	@Cacheable("books")
	public Book getByIsbn(String isbn) {
		simulateSlowService();
		Book rVal = new Book(isbn, "Some book");
		logger.info("pulled {}", rVal.toString());
		return rVal;
	}

	// Don't do this at home
	private void simulateSlowService() {
		try {
			long time = 3000L;
			Thread.sleep(time);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}

}
