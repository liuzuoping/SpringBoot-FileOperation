package com.lemonyliu.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lemonyliu.BaseMockitoTest;
import com.lemonyliu.service.impl.ReportServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;

public class ReportServiceTest  extends BaseMockitoTest {
    @InjectMocks
    private ReportServiceImpl reportService;
    @Mock
    private BaseMapper baseMapper;
//    @Test
//    public void countWarning(){
//        int count = 1;
//        when(baseMapper.selectCount(any(Wrapper.class))).thenReturn(count);
//        int count1 = noticeAlarmRecordService.countWarning(new ArrayList<>(),"2022-03-01","2022-03-22");
//        Assertions.assertTrue(count1 == 0);
//        int count2 = noticeAlarmRecordService.countWarning(Arrays.asList(11L),"2022-03-01","2022-03-22");
//        Assertions.assertEquals(count2, 1);
//    }
//
//    @Test
//    public void countByItemCode(){
//        List<NoticeAlarmRecord> noticeAlarmRecordList = new ArrayList<>();
//        NoticeAlarmRecord noticeAlarmRecord = new NoticeAlarmRecord();
//        noticeAlarmRecord.setId(11L);
//        noticeAlarmRecordList.add(noticeAlarmRecord);
//        when(baseMapper.selectList(any(Wrapper.class))).thenReturn(noticeAlarmRecordList);
//        List<NoticeAlarmRecord> noticeAlarmRecordList1 = noticeAlarmRecordService.countByItemCode(new ArrayList<>(),"2022-03-01","2022-03-22",4);
//        Assertions.assertTrue(noticeAlarmRecordList1.size() == 0);
//        List<NoticeAlarmRecord> noticeAlarmRecordList2 = noticeAlarmRecordService.countByItemCode(Arrays.asList(11L),"2022-03-01","2022-03-22",4);
//        Assertions.assertEquals(noticeAlarmRecordList2.get(0).getId(), 11L);
//    }
//
//    @Test
//    public void countUndoByItemCode(){
//        List<NoticeAlarmRecord> noticeAlarmRecordList = new ArrayList<>();
//        NoticeAlarmRecord noticeAlarmRecord = new NoticeAlarmRecord();
//        noticeAlarmRecord.setId(11L);
//        noticeAlarmRecordList.add(noticeAlarmRecord);
//        when(baseMapper.selectList(any(Wrapper.class))).thenReturn(noticeAlarmRecordList);
//        List<NoticeAlarmRecord> noticeAlarmRecordList1 = noticeAlarmRecordService.countUndoByItemCode(new ArrayList<>(),"2022-03-01","2022-03-22", StatusTypeEnum.TODO,4);
//        Assertions.assertTrue(noticeAlarmRecordList1.size() == 0);
//        List<NoticeAlarmRecord> noticeAlarmRecordList2 = noticeAlarmRecordService.countUndoByItemCode(Arrays.asList(11L),"2022-03-01","2022-03-22",StatusTypeEnum.TODO,4);
//        Assertions.assertEquals(noticeAlarmRecordList2.get(0).getId(), 11L);
//    }
//
//    @Test
//    public void getAlarmRecordList(){
//        List<NoticeAlarmRecord> noticeAlarmRecordList = new ArrayList<>();
//        NoticeAlarmRecord noticeAlarmRecord = new NoticeAlarmRecord();
//        noticeAlarmRecord.setId(11L);
//        noticeAlarmRecordList.add(noticeAlarmRecord);
//        when(baseMapper.selectList(any(Wrapper.class))).thenReturn(noticeAlarmRecordList);
//        List<NoticeAlarmRecord> noticeAlarmRecordList1 = noticeAlarmRecordService.getAlarmRecordList(new ArrayList<>(),"2022-03-01","2022-03-22");
//        Assertions.assertTrue(noticeAlarmRecordList1.size() == 0);
//        List<NoticeAlarmRecord> noticeAlarmRecordList2 = noticeAlarmRecordService.getAlarmRecordList(Arrays.asList(11L),"2022-03-01","2022-03-22");
//        Assertions.assertEquals(noticeAlarmRecordList2.get(0).getId(), 11L);
//    }
//
//    @Test
//    public void countTodoEvent(){
//        int count = 1;
//        when(baseMapper.selectCount(any(Wrapper.class))).thenReturn(count);
//        int count1 = noticeAlarmRecordService.countTodoEvent(new ArrayList<>(),"2022-03-01","2022-03-22");
//        Assertions.assertTrue(count1 == 0);
//        int count2 = noticeAlarmRecordService.countTodoEvent(Arrays.asList(11L),"2022-03-01","2022-03-22");
//        Assertions.assertEquals(count2, 1);
//    }
//
//    @Test
//    public void countDoneEvent(){
//        int count = 1;
//        when(baseMapper.selectCount(any(Wrapper.class))).thenReturn(count);
//        int count1 = noticeAlarmRecordService.countDoneEvent(new ArrayList<>(),"2022-03-01","2022-03-22");
//        Assertions.assertTrue(count1 == 0);
//        int count2 = noticeAlarmRecordService.countDoneEvent(Arrays.asList(11L),"2022-03-01","2022-03-22");
//        Assertions.assertEquals(count2, 1);
//    }
//
//    @Test
//    public void getAlarmRecord(){
//        try {
//            List<NoticeAlarmRecord> noticeAlarmRecordList = new ArrayList<>();
//            NoticeAlarmRecord noticeAlarmRecord = new NoticeAlarmRecord();
//            noticeAlarmRecord.setId(11L);
//            noticeAlarmRecordList.add(noticeAlarmRecord);
//            when(baseMapper.selectList(any(Wrapper.class))).thenReturn(noticeAlarmRecordList);
//            List<NoticeAlarmRecord> noticeAlarmRecordList1 = noticeAlarmRecordService.getAlarmRecord(new ArrayList<>(),"2022-03-01","2022-03-22","YL");
//            Assertions.assertTrue(noticeAlarmRecordList1.size() == 0);
//            List<NoticeAlarmRecord> noticeAlarmRecordList2 = noticeAlarmRecordService.getAlarmRecord(Arrays.asList(11L),"2022-03-01","2022-03-22","YL");
//            Assertions.assertEquals(noticeAlarmRecordList2.get(0).getId(), 11L);
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//    }
//
//    @Test
//    public void countRedAlarmLevel(){
//        int count = 1;
//        when(baseMapper.selectCount(any(Wrapper.class))).thenReturn(count);
//        int count1 = noticeAlarmRecordService.countRedAlarmLevel(new ArrayList<>(),"2022-03-01","2022-03-22");
//        Assertions.assertTrue(count1 == 0);
//        int count2 = noticeAlarmRecordService.countRedAlarmLevel(Arrays.asList(11L),"2022-03-01","2022-03-22");
//        Assertions.assertEquals(count2, 1);
//    }
//
//    @Test
//    public void countOrangeAlarmLevel(){
//        int count = 1;
//        when(baseMapper.selectCount(any(Wrapper.class))).thenReturn(count);
//        int count1 = noticeAlarmRecordService.countOrangeAlarmLevel(new ArrayList<>(),"2022-03-01","2022-03-22");
//        Assertions.assertTrue(count1 == 0);
//        int count2 = noticeAlarmRecordService.countOrangeAlarmLevel(Arrays.asList(11L),"2022-03-01","2022-03-22");
//        Assertions.assertEquals(count2, 1);
//    }
//
//    @Test
//    public void countYellowAlarmLevel(){
//        int count = 1;
//        when(baseMapper.selectCount(any(Wrapper.class))).thenReturn(count);
//        int count1 = noticeAlarmRecordService.countYellowAlarmLevel(new ArrayList<>(),"2022-03-01","2022-03-22");
//        Assertions.assertTrue(count1 == 0);
//        int count2 = noticeAlarmRecordService.countYellowAlarmLevel(Arrays.asList(11L),"2022-03-01","2022-03-22");
//        Assertions.assertEquals(count2, 1);
//    }
//
//    @Test
//    public void countBlueAlarmLevel(){
//        int count = 1;
//        when(baseMapper.selectCount(any(Wrapper.class))).thenReturn(count);
//        int count1 = noticeAlarmRecordService.countBlueAlarmLevel(new ArrayList<>(),"2022-03-01","2022-03-22");
//        Assertions.assertTrue(count1 == 0);
//        int count2 = noticeAlarmRecordService.countBlueAlarmLevel(Arrays.asList(11L),"2022-03-01","2022-03-22");
//        Assertions.assertEquals(count2, 1);
//    }
//
//    @Test
//    public void countDoneRedAlarm(){
//        int count = 1;
//        when(baseMapper.selectCount(any(Wrapper.class))).thenReturn(count);
//        int count1 = noticeAlarmRecordService.countDoneRedAlarm(new ArrayList<>(),"2022-03-01","2022-03-22");
//        Assertions.assertTrue(count1 == 0);
//        int count2 = noticeAlarmRecordService.countDoneRedAlarm(Arrays.asList(11L),"2022-03-01","2022-03-22");
//        Assertions.assertEquals(count2, 1);
//    }
//
//    @Test
//    public void countDoneOrangeAlarm(){
//        int count = 1;
//        when(baseMapper.selectCount(any(Wrapper.class))).thenReturn(count);
//        int count1 = noticeAlarmRecordService.countDoneOrangeAlarm(new ArrayList<>(),"2022-03-01","2022-03-22");
//        Assertions.assertTrue(count1 == 0);
//        int count2 = noticeAlarmRecordService.countDoneOrangeAlarm(Arrays.asList(11L),"2022-03-01","2022-03-22");
//        Assertions.assertEquals(count2, 1);
//    }
//
//    @Test
//    public void countDoneYellowAlarm(){
//        int count = 1;
//        when(baseMapper.selectCount(any(Wrapper.class))).thenReturn(count);
//        int count1 = noticeAlarmRecordService.countDoneYellowAlarm(new ArrayList<>(),"2022-03-01","2022-03-22");
//        Assertions.assertTrue(count1 == 0);
//        int count2 = noticeAlarmRecordService.countDoneYellowAlarm(Arrays.asList(11L),"2022-03-01","2022-03-22");
//        Assertions.assertEquals(count2, 1);
//    }
//
//    @Test
//    public void countDoneBlueAlarm(){
//        int count = 1;
//        when(baseMapper.selectCount(any(Wrapper.class))).thenReturn(count);
//        int count1 = noticeAlarmRecordService.countDoneBlueAlarm(new ArrayList<>(),"2022-03-01","2022-03-22");
//        Assertions.assertTrue(count1 == 0);
//        int count2 = noticeAlarmRecordService.countDoneBlueAlarm(Arrays.asList(11L),"2022-03-01","2022-03-22");
//        Assertions.assertEquals(count2, 1);
//    }
}
