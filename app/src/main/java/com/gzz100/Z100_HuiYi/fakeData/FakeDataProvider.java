package com.gzz100.Z100_HuiYi.fakeData;

import com.gzz100.Z100_HuiYi.data.Agenda;
import com.gzz100.Z100_HuiYi.data.FileBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XieQXiong on 2016/8/29.
 */
public class FakeDataProvider {
    public static List<Agenda> getAgendas() {
        List<Agenda> agendas = new ArrayList<>();
        Agenda agenda1 = new Agenda();
        agenda1.setAgendaIndex(1 + "");
        agenda1.setAgendaName("这是关于议程1的一切标题");
        agenda1.setAgendaTime("30");
        agenda1.setKeynoteSpeaker("老大");
        Agenda agenda2 = new Agenda();
        agenda2.setAgendaIndex(2 + "");
        agenda2.setAgendaName("关于这个议程2的事有什么");
        agenda2.setAgendaTime("32");
        agenda2.setKeynoteSpeaker("老二");
        Agenda agenda3 = new Agenda();
        agenda3.setAgendaIndex(3 + "");
        agenda3.setAgendaName("马上就到议程3，还有什么事要处理");
        agenda3.setAgendaTime("33");
        agenda3.setKeynoteSpeaker("老三");
        agendas.add(agenda1);
        agendas.add(agenda2);
        agendas.add(agenda3);

        return agendas;
    }

    public static List<FileBean> getFileListByindex(int index) {
        List<FileBean> files = new ArrayList<>();
        switch (index) {
            case 1:
                for (int i = 1; i < 4; i++) {
                    FileBean fileBean = new FileBean();
                    fileBean.setAgendaIndex(1 + "");
                    fileBean.setFileAddress("agenda_one_address" + i);
                    fileBean.setFileIndex(i + "");
                    fileBean.setFileName("agenda_one_fileName" + i);
                    fileBean.setFileSize((12 + i) + "kb");
                    fileBean.setKeyNoteSpeaker("张三");
                    files.add(fileBean);
                }

                break;
            case 2:
                for (int i = 1; i < 5; i++) {
                    FileBean fileBean = new FileBean();
                    fileBean.setAgendaIndex(1 + "");
                    fileBean.setFileAddress("agenda_two_address" + i);
                    fileBean.setFileIndex(i + "");
                    fileBean.setFileName("agenda_two_fileName" + i);
                    fileBean.setFileSize((16 + i) + "kb");
                    fileBean.setKeyNoteSpeaker("李四");
                    files.add(fileBean);
                }
                break;

            case 3:
                for (int i = 1; i < 5; i++) {
                    FileBean fileBean = new FileBean();
                    fileBean.setAgendaIndex(1 + "");
                    fileBean.setFileAddress("agenda_three_address" + i);
                    fileBean.setFileIndex(i + "");
                    fileBean.setFileName("agenda_three_fileName" + i);
                    fileBean.setFileSize((14 + i) + "kb");
                    fileBean.setKeyNoteSpeaker("王五");
                    files.add(fileBean);
                }
                break;
        }
        return files;
    }
}