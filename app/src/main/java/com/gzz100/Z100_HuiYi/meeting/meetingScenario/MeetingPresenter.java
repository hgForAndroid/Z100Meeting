package com.gzz100.Z100_HuiYi.meeting.meetingScenario;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.gzz100.Z100_HuiYi.R;
import com.gzz100.Z100_HuiYi.data.DelegateBean;
import com.gzz100.Z100_HuiYi.data.DelegateModel;
import com.gzz100.Z100_HuiYi.data.MeetingInfo;
import com.gzz100.Z100_HuiYi.data.meeting.MeetingDataSource;
import com.gzz100.Z100_HuiYi.data.meeting.MeetingRepository;
import com.gzz100.Z100_HuiYi.utils.SharedPreferencesUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by XieQXiong on 2016/9/5.
 */
public class MeetingPresenter implements MeetingContract.Presenter {
    private MeetingContract.View mView;
    private MeetingRepository mMeetingRepository;
    private Dialog mDialog;

    public MeetingPresenter(@NonNull MeetingRepository meetingRepository, @NonNull MeetingContract.View view) {
        this.mMeetingRepository = checkNotNull(meetingRepository, "meetingRepository cannot be null");
        this.mView = checkNotNull(view, "view cannot be null");
        this.mView.setPresenter(this);
    }

    @Override
    public void getMeetingInfo(final Context context) {
        mMeetingRepository.getMetingInfo(new MeetingDataSource.LoadMeetingInfoCallback() {
            @Override
            public void onMeetingInfoLoaded(MeetingInfo meetingInfo) {
                View titleView = LayoutInflater.from(context).inflate(R.layout.dialog_custom_title, null);
                View contentView = LayoutInflater.from(context).inflate(R.layout.dialog_meeting_info, null);
                //                        .setTitle("会议概况")
                mDialog = new AlertDialog.Builder(context)
                        .setCustomTitle(titleView)
//                        .setTitle("会议概况")
                        .setView(contentView)
                        .setNegativeButton("确定", new DismissDialog())
                        .create();
                mDialog.setCanceledOnTouchOutside(false);
                mView.showMeetingInfo(mDialog, contentView, meetingInfo);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    //关闭弹窗
    class DismissDialog implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            mView.dismissDialog(mDialog);
        }
    }

    //这个是全部参会人员
    private List<DelegateModel> mDelegateBeen = null;

    @Override
    public void fetchUserInfo(int userId) {
        DelegateModel delegate = null;
        for (DelegateModel tempDelegate :mDelegateBeen) {
            if (tempDelegate.getDelegateID() == userId){
                delegate = tempDelegate;
                break;
            }
        }
        if (delegate != null){
            mView.showUserInfo(delegate);
        }else {
            mView.showNoUser();
        }
    }

    boolean firstLoad = true;
    @Override
    public void fetchMainUsers() {
        if (firstLoad) {
            firstLoad = false;
            mMeetingRepository.getDelegateList(new MeetingDataSource.LoadDelegateCallback() {
                @Override
                public void onDelegateLoaded(List<DelegateModel> users) {
                    if (users != null && users.size()>0){
                        mDelegateBeen = users;
                        mView.showMeetingRoom(users);
                        //参会人数大于10个人、
                        int othersNum = 0;
                        if (users.size() > 10){
                            othersNum = users.size() - 10;
                        }
                        mView.setOthersNum(true,othersNum);
                    }

                }

                @Override
                public void onDataNotAvailable() {

                }
            });
        }
    }

    @Override
    public void resetFirstLoad() {
        firstLoad = true;
    }

    @Override
    public void showDelegate() {
        mView.showDelegate(true);
    }

    @Override
    public void fetchMeetingEndData(String hour, String min, String meetingBeginTime,String currentTime) {
        mView.setEndBegin(meetingBeginTime);
        mView.setEndDuration(Integer.valueOf(hour) +"小时"+min+"分钟");
        mView.setEndEnding(currentTime);
        mView.setEndRecord("会议纪要");
    }

    @Override
    public void start() {
        fetchMainUsers();
    }
}
