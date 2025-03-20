package movie.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import utility.Paging;

@Component("myMovieDao")
public class MovieDao {

	private String namespace = "movie.MovieBean";

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public int getTotalCount(Map<String, String> map) {
		int cnt = sqlSessionTemplate.selectOne(namespace +".getTotalCount", map);
		return cnt;
	}//getTotalCount

	public List<MovieBean> getMovieList(Paging pageInfo, Map<String, String> map) {

		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit());
		//pageInfo.getOffset() : �ǳʶ� ���ڵ� ����
		//pageInfo.getLimit() : ������ ���ڵ� ����

		List<MovieBean> lists = sqlSessionTemplate.selectList(namespace + ".getMovieList", map, rowBounds);
		System.out.println("MovieDao lists.size() : " + lists.size());

		return lists;
	}//getMovieList

	public int insertMovie(MovieBean movie) {
		System.out.println(movie.getGenre());
		int cnt = sqlSessionTemplate.insert(namespace + ".insertMovie", movie);
		System.out.println("MovieDao insert cnt : " + cnt);

		return cnt;
	}//insertMovie

	public int deleteMovie(int num) {
		int cnt=sqlSessionTemplate.delete(namespace + ".deleteMovie",num);
		return cnt;
	}//deleteMovie

	public MovieBean getOneMovie(int num) {
		MovieBean mb = sqlSessionTemplate.selectOne(namespace + ".getOneMovie", num);
		return mb;
	}//getOneMovie

	public int updateMovie(MovieBean movie) {
		int cnt = sqlSessionTemplate.update(namespace + ".updateMovie",movie);
		return cnt;
	}//updateMovie

	public int findTitle(String inputtitle) {
		int cnt = sqlSessionTemplate.selectOne(namespace +".findTitle", inputtitle);
		return cnt;
	}

}
