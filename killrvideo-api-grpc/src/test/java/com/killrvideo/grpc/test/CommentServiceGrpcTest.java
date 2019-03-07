package com.killrvideo.grpc.test;

import java.util.UUID;

import org.junit.Test;

import com.killrvideo.grpc.KillrVideoGrpcClient;
import com.killrvideo.grpc.utils.GrpcMapper;

import killrvideo.comments.CommentsServiceOuterClass.GetUserCommentsRequest;
import killrvideo.comments.CommentsServiceOuterClass.GetUserCommentsResponse;
import killrvideo.comments.CommentsServiceOuterClass.UserComment;

/**
 * Sample Operation on GRPC.
 * 
 * @author DataStax Evangelist Team
 */
public class CommentServiceGrpcTest {
    
	String MY_USER_ID = "f2bd4c1e-fa3f-4e71-97ea-20e0f2305240";
	
    @Test
    public void testListCommentForUser() throws Exception {
        KillrVideoGrpcClient killrVideoClient = new KillrVideoGrpcClient("127.0.0.1", 8899);
        
        GetUserCommentsRequest gcReq = GetUserCommentsRequest.newBuilder()
                .setUserId(GrpcMapper.uuidToUuid(UUID.fromString(MY_USER_ID)))
                .setPageSize(10)
                .build();
        
        GetUserCommentsResponse gcRes = killrVideoClient.getCommentService().getUserComments(gcReq);
        Thread.sleep(1000);
        
        for (UserComment gRpcComment : gcRes.getCommentsList()) {
           System.out.println(gRpcComment.getComment());
        }
    }

}
