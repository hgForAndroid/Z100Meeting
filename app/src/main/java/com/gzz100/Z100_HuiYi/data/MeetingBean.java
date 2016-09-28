package com.gzz100.Z100_HuiYi.data;

import android.widget.TextView;

/**
* 会议实体类
* @author XieQXiong
* create at 2016/9/22 14:48
*/
public class MeetingBean {
    private int meetingID;
    private String meetingName;
    private String MeetingTime;
    private int meetingState;

    public MeetingBean(int meetingID, String meetingName, String meetingTime, int meetingState) {
        this.meetingID = meetingID;
        this.meetingName = meetingName;
        MeetingTime = meetingTime;
        this.meetingState = meetingState;
    }

    public int getMeetingID() {
        return meetingID;
    }

    public void setMeetingID(int meetingID) {
        this.meetingID = meetingID;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getMeetingTime() {
        return MeetingTime;
    }

    public void setMeetingTime(String meetingTime) {
        MeetingTime = meetingTime;
    }

    public int getMeetingState() {
        return meetingState;
    }

    public void setMeetingState(int meetingState) {
        this.meetingState = meetingState;
    }

    public void setMeetingState(TextView view){
        if (meetingState == 0){
            view.setText("未开始");
        }else {
            view.setText("已结束");
        }
    }
}
