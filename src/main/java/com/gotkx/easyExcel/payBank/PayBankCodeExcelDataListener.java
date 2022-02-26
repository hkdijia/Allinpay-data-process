package com.gotkx.easyExcel.payBank;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 支付行号
 * 模板的读取类
 *
 */
// 有个很重要的点 AllinpayAreaCodeExcelDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
public class PayBankCodeExcelDataListener extends AnalysisEventListener<PayBankCodeExcelData> {


    private static final Logger LOGGER = LoggerFactory.getLogger(PayBankCodeExcelDataListener.class);

    //计数器
    private AtomicInteger count = new AtomicInteger();

    //计数器
    private AtomicInteger batchCount = new AtomicInteger();

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 3000;
    List<PayBankCodeExcelData> list = new ArrayList<>();
    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    private PayBankCodeDao bankCodeDao;

    public PayBankCodeExcelDataListener() {
        // 这里是demo，所以随便new一个。实际使用如果到了spring,请使用下面的有参构造函数
        bankCodeDao = new PayBankCodeDao();
    }

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param allinpayPayBankCodeDao
     */
    public PayBankCodeExcelDataListener(PayBankCodeDao allinpayPayBankCodeDao) {
        this.bankCodeDao = allinpayPayBankCodeDao;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @SneakyThrows
    @Override
    public void invoke(PayBankCodeExcelData data, AnalysisContext context) {
        ObjectMapper objectMapper = new ObjectMapper();
        count.incrementAndGet();
        LOGGER.info("解析到一条数据:{}", objectMapper.writeValueAsString(data));
        list.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        System.out.println("excel操作总行数为： " +count);
        LOGGER.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        batchCount.incrementAndGet();
        LOGGER.info("第{}批次数据, {}条数据，开始存储数据库！", batchCount,list.size());
        bankCodeDao.save(list);
        LOGGER.info("存储数据库成功！");
    }
}
