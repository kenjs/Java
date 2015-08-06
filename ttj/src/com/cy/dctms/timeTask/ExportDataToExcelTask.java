package com.cy.dctms.timeTask;

import com.cy.dctms.service.TransactionInfoService;

import javax.annotation.Resource;

/**
 * Created by haoy on 2014/10/28.
 */
public class ExportDataToExcelTask {

    @Resource
    private TransactionInfoService transactionInfoService;

    public void execute() throws Exception {
        try {
            transactionInfoService.exportExcel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
