package shopping.dao;

import java.util.List;

import org.bson.Document;
import org.springframework.stereotype.Repository;

@Repository
public abstract class BaseDAO<T>
{
	public BaseDAO() {

	}

	abstract Document convertToDocument(T type);

	abstract T convertToDTO(Document doc);

	public abstract void add(T product);

//	public abstract List<T> getAll(long offset);

}
