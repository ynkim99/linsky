package app.labs.linksy.Service;

import app.labs.linksy.DAO.CommentRepository;
import app.labs.linksy.Model.Comment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CommentService {

	private final CommentRepository commentRepository;

	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	public Map<Integer, List<Comment>> getCommentsByFeedIds(List<Integer> feedIds) {
		List<Comment> comments = commentRepository.getCommentsByFeedIds(feedIds);

		// Group comments by feedId
		return comments.stream().collect(Collectors.groupingBy(Comment::getFeedId));
	}
}
