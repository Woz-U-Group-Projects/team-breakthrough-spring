package com.springbreakthrough;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class ReviewController {
	
	@Autowired
	ReviewRepository reviewRepository;
	
	@GetMapping()
	public ResponseEntity<List<Review>> getReviews() {
		return ResponseEntity.ok(reviewRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Review> getReview(@PathVariable int id) {
		Review foundReview = reviewRepository.findById(id).orElse(null);
		return ResponseEntity.ok(foundReview);
	}
	
	@PostMapping()
	public ResponseEntity<Review> addReview(@RequestBody Review review){
		reviewRepository.save(review);
		return ResponseEntity.ok(review);
	}
	
	@DeleteMapping("/{id}")
	public void deleteReview(@PathVariable int id) {
		reviewRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Review> updateReview(@RequestBody Review review, @PathVariable int id) {
		Review foundReview = reviewRepository.findById(id).orElse(null);
		foundReview.setFirstName(review.getFirstName());
		foundReview.setLastName(review.getLastName());
		foundReview.setEmail(review.getEmail());
		foundReview.setComment(review.getComment());
		reviewRepository.save(foundReview);
		return ResponseEntity.ok(foundReview);
	}

}
