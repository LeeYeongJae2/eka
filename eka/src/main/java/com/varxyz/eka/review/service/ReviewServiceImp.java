
package com.varxyz.eka.review.service;

import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.varxyz.eka.review.domain.Review;
import com.varxyz.eka.review.repository.ReviewDao;

@Service("ReviewServiceImp")
public class ReviewServiceImp implements ReviewService {

	@Autowired
	private ReviewDao reviewDao;

	public ReviewServiceImp(DataSource dataSource) {
		reviewDao = new ReviewDao(dataSource);
	}

	@Override
	public void addReview(Review review) {
		reviewDao.addReview(review);
	}

	@Override
	public void updateReview(Review review) {
		reviewDao.updateReview(review);
	}

	@Override
	public void deleteReview(Long rid) {
		reviewDao.deleteReview(rid);
	}

	@Override
	public List<Review> findReviewByAcademyId(Long academyId) {
		return reviewDao.findReviewByAcademyId(academyId);
	}

	@Override
	public List<Review> findReviewByekaUserId(Long ekaUserId) {
		return reviewDao.findReviewByekaUserId(ekaUserId);
	}

	public double percent(int a, int b) {
		if (a != 0 || b != 0) {
			return a * 100 / b;	
		} else {
			return 0;
		}
	}
}
