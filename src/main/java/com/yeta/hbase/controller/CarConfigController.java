package com.yeta.hbase.controller;

import com.yeta.hbase.domain.HBaseResult;
import com.yeta.hbase.domain.HbaseRequest;
import com.yeta.hbase.service.CarConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * Created by Administrator on 2017-12-11.
 */
@RestController
@RequestMapping(value = "/carConfig")
public class CarConfigController {

    @Autowired
    private CarConfigService carConfigService;

    @GetMapping(value = "/queryAllTable")
    public HBaseResult queryAllTable(HbaseRequest hbaseRequest) throws IOException {
        System.out.println(hbaseRequest);
        return carConfigService.queryAllTable(hbaseRequest);
    }

    @GetMapping(value = "/save")
    public HBaseResult save(HbaseRequest hbaseRequest) throws IOException {
        System.out.println(hbaseRequest);
        return carConfigService.save(hbaseRequest);
    }

    @GetMapping(value = "/update")
    public HBaseResult update(HbaseRequest hbaseRequest) throws IOException {
        System.out.println(hbaseRequest);
        return carConfigService.update(hbaseRequest);
    }

    @GetMapping(value = "/queryTableByRowKey")
    public HBaseResult queryTableByRowKey(HbaseRequest hbaseRequest) throws IOException {
        System.out.println(hbaseRequest);
        return carConfigService.queryTableByRowKey(hbaseRequest);
    }

    @GetMapping(value = "/delete")
    public HBaseResult delete(HbaseRequest hbaseRequest) throws IOException {
        System.out.println(hbaseRequest);
        return carConfigService.delete(hbaseRequest);
    }

    @GetMapping(value = "/findBrandAveragePrice")
    public HBaseResult findBrandAveragePrice(HbaseRequest hbaseRequest) throws IOException {
        System.out.println(hbaseRequest);
        return carConfigService.findBrandAveragePrice(hbaseRequest);
    }
}
