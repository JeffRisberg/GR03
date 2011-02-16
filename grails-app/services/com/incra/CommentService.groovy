package com.incra

import java.util.List;

/**
 * The <i>CommentService</i> provides business logic to maintain comments.  We already have code to 
 * fetch comments written in DashboardController, the AccountController, and the ProjectController.
 * Move that code into this class.
 * 
 * @author Jeffrey Risberg
 * @since 11/21/10
 */
class CommentService {
	
	/**
	 * Fetch all comments for a given entityType and entityId
	 * @param entityType
	 * @param entityId
	 * @return
	 */
	List<Comment> getComments(EntityType entityType, Long entityId) {
		List<Comment> result =  Comment.createCriteria().list {
			and {
				eq('entityType', entityType)
				eq('entityId', entityId)
			}
			order("dateCreated", "desc")
		}		
		
		result
	}
	
	void saveComment(Object obj, User user, String body){
		
	}
}
