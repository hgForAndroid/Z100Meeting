package com.gzz100.Z100_HuiYi.data.vote;

import android.support.annotation.NonNull;

import com.gzz100.Z100_HuiYi.data.Vote;
import com.gzz100.Z100_HuiYi.data.VoteResult;
import com.gzz100.Z100_HuiYi.meeting.vote.UpLoadVote;

import java.util.List;

/**
 * Created by Lee on 2016/9/3.
 */
public interface VoteDataSource {
    interface LoadVoteDetailCallback{
        void onVoteDetailLoaded(Vote vote);
        void onDataNotAvailable();
    }

    interface LoadAllVoteInfCallBack{
        void onAllVoteLoaded(List<Vote> voteList);
        void onDataNotAvailable();
    }
    interface LoadVoteResultCallback{
        void onVoteResultLoaded(List<VoteResult> voteResults);
        void onDataNotAvailable(String errorString);
    }
    interface SubmitCallback {
        void onSuccess();
        void onFail();
    }

    /**
     * 获取所有会议列表
     * @param meetingID    会议id
     * @param callback
     */
    void getAllVoteInf(String meetingID, @NonNull LoadAllVoteInfCallBack callback);

    void getVoteDetail(String IMEI, String userID, int voteId, @NonNull LoadVoteDetailCallback callback);

    void getVoteResult(int voteId,@NonNull LoadVoteResultCallback callback);

    void submitVote(UpLoadVote data, @NonNull SubmitCallback callback);

    void startOrEndVote(String IMEI,int meetingID, int voteID,int startOrEnd,@NonNull SubmitCallback callback);
}
