package com.gzz100.Z100_HuiYi.data.signIn;

import android.content.Context;
import android.support.annotation.NonNull;

import com.gzz100.Z100_HuiYi.data.MeetingSummary;
import com.gzz100.Z100_HuiYi.data.UserBean;
import com.gzz100.Z100_HuiYi.network.HttpManager;
import com.gzz100.Z100_HuiYi.network.HttpRxCallbackListener;
import com.gzz100.Z100_HuiYi.network.ProgressSubscriber;
import com.gzz100.Z100_HuiYi.network.entity.MeetingSummaryPost;
import com.gzz100.Z100_HuiYi.network.entity.UserBeanPost;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by XieQXiong on 2016/9/23.
 */
public class SignInRemoteDataSource implements SignInDataSource {
    private static SignInRemoteDataSource mInstance;
    private Context mContext;
    private SignInRemoteDataSource(Context context) {
        this.mContext = context;
    }
    public static SignInRemoteDataSource getInstance(Context context){
        if (mInstance == null){
            synchronized (SignInRemoteDataSource.class){
                if (mInstance == null) {
                    mInstance = new SignInRemoteDataSource(context);
                }
            }
        }
        return mInstance;
    }
    @Override
    public void fetchUserBean(String IMEI, String meetingID, @NonNull final LoadUserBeanCallback callback) {
        checkNotNull(callback);

        //加载服务器数据
        UserBeanPost userBeanPost = new UserBeanPost(
                new ProgressSubscriber(new HttpRxCallbackListener<UserBean>(){
                    @Override
                    public void onNext(UserBean userBean) {
                        if (userBean != null){
                            callback.onUserBeanLoaded(userBean);
                        }else {
                            callback.onDataNotAvailable();
                        }
                    }
                }, mContext), IMEI,meetingID);
        HttpManager.getInstance(mContext).doHttpDeal(userBeanPost);
        
    }

    @Override
    public void signIn(String IMEI, String meetingID, @NonNull final LoadMeetingSummaryCallback callback) {

        //加载服务器数据
        MeetingSummaryPost meetingSummaryPost1 = new MeetingSummaryPost(
                new ProgressSubscriber(new HttpRxCallbackListener<MeetingSummary>(){
                    @Override
                    public void onNext(MeetingSummary meetingSummary) {
                        if (meetingSummary != null){
                            callback.onMeetingSummaryLoaded(meetingSummary);
                        }else {
                            callback.onDataNotAvailable();
                        }
                    }
                }, mContext), IMEI,meetingID);
        HttpManager.getInstance(mContext).doHttpDeal(meetingSummaryPost1);

    }
}
