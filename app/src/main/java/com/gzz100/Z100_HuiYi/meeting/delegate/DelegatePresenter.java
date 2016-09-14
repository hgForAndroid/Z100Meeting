package com.gzz100.Z100_HuiYi.meeting.delegate;

import android.support.annotation.NonNull;

import com.gzz100.Z100_HuiYi.data.DelegateBean;
import com.gzz100.Z100_HuiYi.data.delegate.DelegateDataSource;
import com.gzz100.Z100_HuiYi.data.delegate.DelegateRepository;


import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by DELL on 2016/8/31.
 */
public class DelegatePresenter implements DelegateContract.Presenter {
    private final DelegateRepository mDelegateRepository;
    private final DelegateContract.View mDelegateView;


    public DelegatePresenter (@NonNull DelegateRepository delegateRepository, DelegateContract.View delegateView) {
        this.mDelegateRepository = checkNotNull(delegateRepository,"delegateRepository cannot be null");
        this.mDelegateView = checkNotNull(delegateView,"delegateView cannot be null");
        mDelegateView.setPresenter(this);
    }
    @Override
    public void start() {
        fetchRoleList();
        fetchDelegateList(0);
        mDelegateView.setIsFirstLoad(false);

    }

    @Override
    public void fetchRoleList() {

            mDelegateRepository.getRoleList(new DelegateDataSource.LoadRoleListCallback() {
                @Override
                public void onRoleListLoaded(List<String> roleList) {
                    mDelegateView.showRoleList(roleList);

                }

                @Override
                public void onDataNotAvailable() {
                    mDelegateView.showNoRoleList();
                }
            });


    }


    @Override
    public void fetchDelegateList(int rolePos) {

            mDelegateRepository.getDelegateList(rolePos, new DelegateDataSource.LoadDelegateListCallback() {
                @Override
                public void onDelegateListLoaded(List<DelegateBean> delegateBeans) {
                    mDelegateView.showDelegateList(delegateBeans);
                }

                @Override
                public void onDataNotAvailable() {
                    mDelegateView.showNoDelegateList();
                }
            });
    }

    @Override
    public void searchByName() {

    }

    @Override
    public void showDelegateDetail( final int delegateNamePos) {

        mDelegateRepository.getDelegateBean(delegateNamePos,new DelegateDataSource.LoadDelegateDetailCallback() {

            @Override
            public void onDelegateDetailLoaded(DelegateBean delegateBeans) {
                mDelegateView.showDelegateDetail(delegateBeans,delegateNamePos);
            }

            @Override
            public void onDataNotAvailable() {
                mDelegateView.showNoDelegateDetail();
            }
        });



    }
}

