package com.yeta.hbase.service;

import com.yeta.hbase.domain.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-12-9.
 */
@Service
public class CarConfigService {

    /**
     * hbase配置对象
     */
    private Configuration configuration = HBaseConfiguration.create();

    /**
     * hbase连接对象
     */
    private Connection connection = null;

    /**
     * hbase操作对象
     */
    private Admin admin = null;

    /**
     * 品牌初始化，用于设置row key
     */
    private String[] brandArray = {"AC Schnitzer","DS","GMC","Jeep","KTM","MG","MINI","smart","一汽","三菱","上汽大通","世爵","东南","东风","东风小康","东风风度","东风风神","东风风行","中兴","中华","丰田","九龙","五十铃","五菱汽车","众泰","依维柯","保时捷","光冈","克莱斯勒","兰博基尼","凯翼","凯迪拉克","别克","前途","力帆","劳斯莱斯","北京","北汽制造","北汽威旺","北汽幻速","北汽新能源","北汽绅宝","华普","华泰","华颂","南京金龙","卡威","卡尔森","双环","双龙","吉利汽车","启辰","哈弗","哈飞","大众","大发","奇瑞","奔腾","奔驰","奥迪","如虎","威兹曼","威麟","安凯客车","宝马","宝骏","宾利","巴博斯","布加迪","帕加尼","广汽传祺","广汽吉奥","开瑞","思铭","恒天","悍马","成功汽车","捷豹","摩根","斯巴鲁","斯柯达","日产","昌河","本田","林肯","标致","欧宝","欧朗","比亚迪","永源","江淮","江铃","江铃集团轻汽","沃尔沃","法拉利","海格","海马","潍柴英致","特斯拉","猎豹汽车","玛莎拉蒂","现代","理念","瑞麒","知豆","福汽启腾","福特","福田","福迪","科尼赛克","红旗","纳智捷","腾势","英菲尼迪","荣威","莲花汽车","菲亚特","萨博","西雅特","观致","讴歌","赛麟","起亚","路特斯","路虎","迈凯伦","迈巴赫","道奇","野马汽车","金旅","金杯","金龙","铃木","长城","长安","长安商用","阿尔法罗密欧","阿斯顿·马丁","陆风","雪佛兰","雪铁龙","雷克萨斯","雷诺","马自达","黄海"};

    /**
     * 座位数初始化，用于设置row key
     */
    private String[] seatNumberArray = {"10","11","12","13","14","15","16","17","18","19","2","20","21","23","3","4","5","6","7","8","9"};

    /**
     * 构造方法
     * @throws IOException
     */
    public CarConfigService() throws IOException {
        this.configuration.set("hbase.zookeeper.quorum", "yeta");
        this.connection = ConnectionFactory.createConnection(this.configuration);
        this.admin = connection.getAdmin();
    }

    /**
     * 创建表
     * @param tableName
     * @param columnFamilies
     * @return
     * @throws IOException
     */
    public HBaseResult createTable(String tableName, String[] columnFamilies) throws IOException {
        //初始化返回对象
        HBaseResult hBaseResult = new HBaseResult();
        //判断表是否存在
        if (admin.tableExists(TableName.valueOf(tableName))) {
            hBaseResult.setMessage("表[" + tableName + "]已存在！");
        }else {
            //数据表描述对象
            HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
            //遍历列族
            for (String columnFamily : columnFamilies) {
                HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(columnFamily);
                hTableDescriptor.addFamily(hColumnDescriptor);
            }
            //创建表
            admin.createTable(hTableDescriptor);
            //判断表是否存在
            if (admin.tableExists(TableName.valueOf(tableName))) {
                hBaseResult.setResult(true);
            }
        }
        return hBaseResult;
    }

    /**
     * 保存一条数据
     * @param hbaseRequest
     * @return
     * @throws IOException
     */
    public HBaseResult save(HbaseRequest hbaseRequest) throws IOException {
        List<CarConfig> carConfigs = new ArrayList<>();
        CarConfig carConfig = new CarConfig();

        //品牌
        String brand = "";
        //价格，随机四位数
        String price = Math.round(Math.random() * 8999 + 1000) + "";
        //座位数
        String carSeat = "";
        //遍历品牌
        for (int i = 0; i < brandArray.length; i++) {
            if (brandArray[i].equals(hbaseRequest.getBrand())) {
                if (i < 10) {
                    //如果i是1位
                    brand = "00" + i;
                }else if (i >= 10 && i < 100) {
                    //如果i是2位
                    brand = "0" + i;
                }else if (i >= 100 && i < 1000) {
                    //如果i是3位
                    brand = "" + i;
                }
            }
        }
        //遍历座位数
        for (int i = 0; i < seatNumberArray.length; i++) {
            if (seatNumberArray[i].equals(hbaseRequest.getCarSeats())) {
                if (i < 10) {
                    //如果i是1位
                    carSeat = "0" + i;
                }else if (i >= 10 && i < 100) {
                    //如果i是2位
                    carSeat = "" + i;
                }
            }
        }
        carConfig.setId(brand + price + carSeat + System.currentTimeMillis());

        carConfig.setBrand(hbaseRequest.getBrand());
        carConfig.setCarSeries(hbaseRequest.getCarSeries());
        carConfig.setCarType(hbaseRequest.getCarType());
        carConfig.setManufacturer(hbaseRequest.getManufacturer());
        carConfig.setPrice(hbaseRequest.getPrice());
        carConfig.setYear(hbaseRequest.getYear());

        carConfig.setCarDoors(hbaseRequest.getCarDoors());
        carConfig.setCarSeats(hbaseRequest.getCarSeats());
        carConfig.setCarStructure(hbaseRequest.getCarStructure());
        carConfig.setGearbox(hbaseRequest.getGearbox());
        carConfig.setGrade(hbaseRequest.getGrade());
        carConfig.setSize(hbaseRequest.getSize());

        carConfig.setAccelerationCapability(hbaseRequest.getAccelerationCapability());
        carConfig.setCarCylinders(hbaseRequest.getCarCylinders());
        carConfig.setDisplacemen(hbaseRequest.getDisplacemen());
        carConfig.setEngine(hbaseRequest.getEngine());
        carConfig.setMaxSpeed(hbaseRequest.getMaxSpeed());
        carConfig.setOilDrive(hbaseRequest.getOilDrive());
        carConfig.setOilTankCapacity(hbaseRequest.getOilTankCapacity());

        carConfigs.add(carConfig);
        return insertCarConfig(hbaseRequest.getTableName(), carConfigs);
    }

    /**
     * 插入数据
     * @param tableName
     */
    public HBaseResult insertCarConfig(String tableName, List<CarConfig> carConfigList) throws IOException {
        //初始化返回对象
        HBaseResult hBaseResult = new HBaseResult();
        //获取表对象
        Table table = connection.getTable(TableName.valueOf(tableName));
        //插入数据
        for (CarConfig carConfig : carConfigList) {
            //Put对象
            Put put = new Put(Bytes.toBytes(carConfig.getId() + ""));
            //列族：baseInfo
            put.add(Bytes.toBytes("baseInfo"), Bytes.toBytes("brand_"), Bytes.toBytes(carConfig.getBrand() + ""));
            put.add(Bytes.toBytes("baseInfo"), Bytes.toBytes("car_series"), Bytes.toBytes(carConfig.getCarSeries() + ""));
            put.add(Bytes.toBytes("baseInfo"), Bytes.toBytes("car_type"), Bytes.toBytes(carConfig.getCarType() + ""));
            put.add(Bytes.toBytes("baseInfo"), Bytes.toBytes("year_"), Bytes.toBytes(carConfig.getYear() + ""));
            put.add(Bytes.toBytes("baseInfo"), Bytes.toBytes("price_"), Bytes.toBytes(carConfig.getPrice() + ""));
            put.add(Bytes.toBytes("baseInfo"), Bytes.toBytes("manufacturer_"), Bytes.toBytes(carConfig.getManufacturer() + ""));
            //列族：baseAttribute
            put.add(Bytes.toBytes("baseAttribute"), Bytes.toBytes("grade_"), Bytes.toBytes(carConfig.getGrade() + ""));
            put.add(Bytes.toBytes("baseAttribute"), Bytes.toBytes("size_"), Bytes.toBytes(carConfig.getSize() + ""));
            put.add(Bytes.toBytes("baseAttribute"), Bytes.toBytes("car_structure"), Bytes.toBytes(carConfig.getCarStructure() + ""));
            put.add(Bytes.toBytes("baseAttribute"), Bytes.toBytes("car_doors"), Bytes.toBytes(carConfig.getCarDoors() + ""));
            put.add(Bytes.toBytes("baseAttribute"), Bytes.toBytes("car_seats"), Bytes.toBytes(carConfig.getCarSeats() + ""));
            put.add(Bytes.toBytes("baseAttribute"), Bytes.toBytes("gearbox_"), Bytes.toBytes(carConfig.getGearbox() + ""));
            //列族：performanceAttribute
            put.add(Bytes.toBytes("performanceAttribute"), Bytes.toBytes("displacemen_"), Bytes.toBytes(carConfig.getDisplacemen() + ""));
            put.add(Bytes.toBytes("performanceAttribute"), Bytes.toBytes("max_speed"), Bytes.toBytes(carConfig.getMaxSpeed() + ""));
            put.add(Bytes.toBytes("performanceAttribute"), Bytes.toBytes("0_100_acceleration_capability"), Bytes.toBytes(carConfig.getAccelerationCapability() + ""));
            put.add(Bytes.toBytes("performanceAttribute"), Bytes.toBytes("engine_"), Bytes.toBytes(carConfig.getEngine() + ""));
            put.add(Bytes.toBytes("performanceAttribute"), Bytes.toBytes("oil_tank_capacity"), Bytes.toBytes(carConfig.getOilTankCapacity() + ""));
            put.add(Bytes.toBytes("performanceAttribute"), Bytes.toBytes("car_cylinders"), Bytes.toBytes(carConfig.getCarCylinders() + ""));
            put.add(Bytes.toBytes("performanceAttribute"), Bytes.toBytes("oil_drive"), Bytes.toBytes(carConfig.getOilDrive() + ""));
            //插入到HBase表中
            table.put(put);
        }
        //关闭表对象
        table.close();
        hBaseResult.setResult(true);
        hBaseResult.setData(carConfigList.get(0).getId());
        return hBaseResult;
    }

    /**
     * 修改数据
     * @param hbaseRequest
     * @return
     */
    public HBaseResult update(HbaseRequest hbaseRequest) throws IOException {
        //初始化返回结果
        HBaseResult hBaseResult = new HBaseResult();
        List<CarConfig> carConfigs = new ArrayList<>();
        CarConfig carConfig = new CarConfig();

        carConfig.setId(hbaseRequest.getUpdateRow());

        carConfig.setBrand(hbaseRequest.getBrand());
        carConfig.setCarSeries(hbaseRequest.getCarSeries());
        carConfig.setCarType(hbaseRequest.getCarType());
        carConfig.setManufacturer(hbaseRequest.getManufacturer());
        carConfig.setPrice(hbaseRequest.getPrice());
        carConfig.setYear(hbaseRequest.getYear());

        carConfig.setCarDoors(hbaseRequest.getCarDoors());
        carConfig.setCarSeats(hbaseRequest.getCarSeats());
        carConfig.setCarStructure(hbaseRequest.getCarStructure());
        carConfig.setGearbox(hbaseRequest.getGearbox());
        carConfig.setGrade(hbaseRequest.getGrade());
        carConfig.setSize(hbaseRequest.getSize());

        carConfig.setAccelerationCapability(hbaseRequest.getAccelerationCapability());
        carConfig.setCarCylinders(hbaseRequest.getCarCylinders());
        carConfig.setDisplacemen(hbaseRequest.getDisplacemen());
        carConfig.setEngine(hbaseRequest.getEngine());
        carConfig.setMaxSpeed(hbaseRequest.getMaxSpeed());
        carConfig.setOilDrive(hbaseRequest.getOilDrive());
        carConfig.setOilTankCapacity(hbaseRequest.getOilTankCapacity());

        carConfigs.add(carConfig);
        //使用调用PUT修改数据，与新增的区别在于row key不一样
        return insertCarConfig(hbaseRequest.getTableName(), carConfigs);
    }

    /**
     * 封装获取到的数据
     * @param result
     * @return
     */
    public Map encapsulationData(Result result) {
        //初始化返回数据
        Map<String, Object> encapsulationMap = new HashMap<>();
        //初始化原始返回数据
        List<OriginalResult> originalResults = new ArrayList<>();
        //初始化列式返回数据
        ColumnResult columnResults = new ColumnResult();
        //列式数据初步组装
        List<Long> timestampList = new ArrayList<>();
        List<String> brandList = new ArrayList<>();
        List<String> carSeriesList = new ArrayList<>();
        List<String> carTypeList = new ArrayList<>();
        List<String> manufacturerList = new ArrayList<>();
        List<String> priceList = new ArrayList<>();
        List<String> yearList = new ArrayList<>();
        List<String> carDoorsList = new ArrayList<>();
        List<String> carSeatsList = new ArrayList<>();
        List<String> carStructureList = new ArrayList<>();
        List<String> gearboxList = new ArrayList<>();
        List<String> gradeList = new ArrayList<>();
        List<String> sizeList = new ArrayList<>();
        List<String> accelerationCapabilityList = new ArrayList<>();
        List<String> carCylindersList = new ArrayList<>();
        List<String> displacemenList = new ArrayList<>();
        List<String> engineList = new ArrayList<>();
        List<String> maxSpeedList = new ArrayList<>();
        List<String> oilDriveList = new ArrayList<>();
        List<String> oilTankCapacityList = new ArrayList<>();
        //遍历
        for (KeyValue keyValue : result.list()) {
            //原始数据
            OriginalResult originalResult = new OriginalResult();
            originalResult.setRow(Bytes.toString(keyValue.getRow()));
            originalResult.setFamily(Bytes.toString(keyValue.getFamily()));
            originalResult.setQualifier(Bytes.toString(keyValue.getQualifier()));
            originalResult.setValue(Bytes.toString(keyValue.getValue()));
            originalResult.setTimestamp(keyValue.getTimestamp());
            originalResults.add(originalResult);
            //列式数据
            //基本信息
            if (Bytes.toString(keyValue.getFamily()).equals("baseInfo") && Bytes.toString(keyValue.getQualifier()).equals("brand_")) {
                brandList.add(Bytes.toString(keyValue.getValue()));
                timestampList.add(keyValue.getTimestamp());
            }
            if (Bytes.toString(keyValue.getFamily()).equals("baseInfo") && Bytes.toString(keyValue.getQualifier()).equals("car_series")) {
                carSeriesList.add(Bytes.toString(keyValue.getValue()));
            }
            if (Bytes.toString(keyValue.getFamily()).equals("baseInfo") && Bytes.toString(keyValue.getQualifier()).equals("car_type")) {
                carTypeList.add(Bytes.toString(keyValue.getValue()));
            }
            if (Bytes.toString(keyValue.getFamily()).equals("baseInfo") && Bytes.toString(keyValue.getQualifier()).equals("manufacturer_")) {
                manufacturerList.add(Bytes.toString(keyValue.getValue()));
            }
            if (Bytes.toString(keyValue.getFamily()).equals("baseInfo") && Bytes.toString(keyValue.getQualifier()).equals("price_")) {
                priceList.add(Bytes.toString(keyValue.getValue()));
            }
            if (Bytes.toString(keyValue.getFamily()).equals("baseInfo") && Bytes.toString(keyValue.getQualifier()).equals("year_")) {
                yearList.add(Bytes.toString(keyValue.getValue()));
            }
            //基本属性
            if (Bytes.toString(keyValue.getFamily()).equals("baseAttribute") && Bytes.toString(keyValue.getQualifier()).equals("car_doors")) {
                carDoorsList.add(Bytes.toString(keyValue.getValue()));
            }
            if (Bytes.toString(keyValue.getFamily()).equals("baseAttribute") && Bytes.toString(keyValue.getQualifier()).equals("car_seats")) {
                carSeatsList.add(Bytes.toString(keyValue.getValue()));
            }
            if (Bytes.toString(keyValue.getFamily()).equals("baseAttribute") && Bytes.toString(keyValue.getQualifier()).equals("car_structure")) {
                carStructureList.add(Bytes.toString(keyValue.getValue()));
            }
            if (Bytes.toString(keyValue.getFamily()).equals("baseAttribute") && Bytes.toString(keyValue.getQualifier()).equals("gearbox_")) {
                gearboxList.add(Bytes.toString(keyValue.getValue()));
            }
            if (Bytes.toString(keyValue.getFamily()).equals("baseAttribute") && Bytes.toString(keyValue.getQualifier()).equals("grade_")) {
                gradeList.add(Bytes.toString(keyValue.getValue()));
            }
            if (Bytes.toString(keyValue.getFamily()).equals("baseAttribute") && Bytes.toString(keyValue.getQualifier()).equals("size_")) {
                sizeList.add(Bytes.toString(keyValue.getValue()));
            }
            //性能属性
            if (Bytes.toString(keyValue.getFamily()).equals("performanceAttribute") && Bytes.toString(keyValue.getQualifier()).equals("0_100_acceleration_capability")) {
                accelerationCapabilityList.add(Bytes.toString(keyValue.getValue()));
            }
            if (Bytes.toString(keyValue.getFamily()).equals("performanceAttribute") && Bytes.toString(keyValue.getQualifier()).equals("car_cylinders")) {
                carCylindersList.add(Bytes.toString(keyValue.getValue()));
            }
            if (Bytes.toString(keyValue.getFamily()).equals("performanceAttribute") && Bytes.toString(keyValue.getQualifier()).equals("displacemen_")) {
                displacemenList.add(Bytes.toString(keyValue.getValue()));
            }
            if (Bytes.toString(keyValue.getFamily()).equals("performanceAttribute") && Bytes.toString(keyValue.getQualifier()).equals("engine_")) {
                engineList.add(Bytes.toString(keyValue.getValue()));
            }
            if (Bytes.toString(keyValue.getFamily()).equals("performanceAttribute") && Bytes.toString(keyValue.getQualifier()).equals("max_speed")) {
                maxSpeedList.add(Bytes.toString(keyValue.getValue()));
            }
            if (Bytes.toString(keyValue.getFamily()).equals("performanceAttribute") && Bytes.toString(keyValue.getQualifier()).equals("oil_drive")) {
                oilDriveList.add(Bytes.toString(keyValue.getValue()));
            }
            if (Bytes.toString(keyValue.getFamily()).equals("performanceAttribute") && Bytes.toString(keyValue.getQualifier()).equals("oil_tank_capacity")) {
                oilTankCapacityList.add(Bytes.toString(keyValue.getValue()));
            }
        }
        //列式数据再次组装
        List<BaseInfo> baseInfos = new ArrayList<>();
        List<BaseAttribute> baseAttributes = new ArrayList<>();
        List<PerformanceAttribute> performanceAttributes = new ArrayList<>();
        for (int i = 0; i < timestampList.size(); i++) {
            //基本信息
            BaseInfo baseInfo = new BaseInfo();
            baseInfo.setBrand(brandList.get(i));
            baseInfo.setCarSeries(carSeriesList.get(i));
            baseInfo.setCarType(carTypeList.get(i));
            baseInfo.setManufacturer(manufacturerList.get(i));
            baseInfo.setPrice(priceList.get(i));
            baseInfo.setYear(yearList.get(i));
            baseInfos.add(baseInfo);
            //基本属性
            BaseAttribute baseAttribute = new BaseAttribute();
            baseAttribute.setCarDoors(carDoorsList.get(i));
            baseAttribute.setCarSeats(carSeatsList.get(i));
            baseAttribute.setCarStructure(carStructureList.get(i));
            baseAttribute.setGearbox(gearboxList.get(i));
            baseAttribute.setGrade(gradeList.get(i));
            baseAttribute.setSize(sizeList.get(i));
            baseAttributes.add(baseAttribute);
            //性能属性
            PerformanceAttribute performanceAttribute = new PerformanceAttribute();
            performanceAttribute.setAccelerationCapability(accelerationCapabilityList.get(i));
            performanceAttribute.setCarCylinders(carCylindersList.get(i));
            performanceAttribute.setDisplacemen(displacemenList.get(i));
            performanceAttribute.setEngine(engineList.get(i));
            performanceAttribute.setMaxSpeed(maxSpeedList.get(i));
            performanceAttribute.setOilDrive(oilDriveList.get(i));
            performanceAttribute.setOilTankCapacity(oilTankCapacityList.get(i));
            performanceAttributes.add(performanceAttribute);
        }
        columnResults.setRow(Bytes.toString(result.list().get(0).getRow()));
        columnResults.setTimestamps(timestampList);
        columnResults.setBaseInfos(baseInfos);
        columnResults.setBaseAttributes(baseAttributes);
        columnResults.setPerformanceAttributes(performanceAttributes);
        //封装返回对象
        encapsulationMap.put("originalResults", originalResults);
        encapsulationMap.put("columnResults", columnResults);
        return encapsulationMap;
    }

    /**
     * 根据行健查询数据
     * @param hbaseRequest
     * @return
     */
    public HBaseResult queryTableByRowKey(HbaseRequest hbaseRequest) throws IOException {
        //初始化返回结果
        HBaseResult hBaseResult = new HBaseResult();
        //获取表对象
        Table table = connection.getTable(TableName.valueOf(hbaseRequest.getTableName()));
        //新建查询对象
        Get get = new Get(Bytes.toBytes(hbaseRequest.getQueryRow()));
        //设置最大版本
        get.setMaxVersions(3);
        //按行健查询
        Result result = table.get(get);
        //获取封装结果
        Map encapsulationMap = encapsulationData(result);
        //封装返回数据
        List<ColumnResult> columnResultList = new ArrayList<>();
        columnResultList.add((ColumnResult) encapsulationMap.get("columnResults"));
        List<List> originalResultList = new ArrayList<>();
        originalResultList.add((List) encapsulationMap.get("originalResults"));
        //判断数据长度
        if (originalResultList.get(0).size() > 0) {
            hBaseResult.setResult(true);
            Map<String, List> data = new HashMap<>();
            data.put("columnResultList", columnResultList);
            data.put("originalResultList", originalResultList);
            hBaseResult.setData(data);
            hBaseResult.setTotalNumber(columnResultList.get(0).getTimestamps().size());
        }else {
            hBaseResult.setMessage("获取数据失败！");
        }
        table.close();
        return hBaseResult;
    }

    /**
     * 根据条件统计数据总量
     * @param hbaseRequest
     * @return
     * @throws IOException
     */
    public Integer count(HbaseRequest hbaseRequest) throws IOException {
        Integer totalNumber = 0;
        //获取表对象
        Table table = connection.getTable(TableName.valueOf(hbaseRequest.getTableName()));
        Scan scan = new Scan();
        //设置最大版本
        scan.setMaxVersions(1);
        //过滤器列表
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        if (hbaseRequest.getBrand() != null) {
            //创建品牌过滤器
            Filter brandFilter = new SingleColumnValueFilter(Bytes.toBytes("baseInfo"), Bytes.toBytes("brand_"),
                    CompareFilter.CompareOp.EQUAL, Bytes.toBytes(hbaseRequest.getBrand()));
            filterList.addFilter(brandFilter);
        }
        if (hbaseRequest.getMinPrice() != 0.0) {
            //创建最低价格过滤器
            Filter minPriceFilter = new SingleColumnValueFilter(Bytes.toBytes("baseInfo"), Bytes.toBytes("price_"),
                    CompareFilter.CompareOp.GREATER_OR_EQUAL, Bytes.toBytes(hbaseRequest.getMinPrice() + "万"));
            filterList.addFilter(minPriceFilter);
        }
        if (hbaseRequest.getMaxPrice() != 0.0) {
            //创建最高价格过滤器
            Filter maxPriceFilter = new SingleColumnValueFilter(Bytes.toBytes("baseInfo"), Bytes.toBytes("price_"),
                    CompareFilter.CompareOp.LESS_OR_EQUAL, Bytes.toBytes(hbaseRequest.getMaxPrice() + "万"));
            filterList.addFilter(maxPriceFilter);
        }
        if (hbaseRequest.getCarSeats() != null) {
            //创建座位数过滤器
            Filter seatNumberFilter = new SingleColumnValueFilter(Bytes.toBytes("baseAttribute"), Bytes.toBytes("car_seats"),
                    CompareFilter.CompareOp.EQUAL, Bytes.toBytes(hbaseRequest.getCarSeats() + ""));
            filterList.addFilter(seatNumberFilter);
        }
        if (hbaseRequest.getBrand() == null && hbaseRequest.getMinPrice() == 0.0 && hbaseRequest.getMaxPrice() == 0.0 && hbaseRequest.getCarSeats() == null) {
            //没有过滤器

        }else {
            scan.setFilter(filterList);
        }
        ResultScanner resultScanner = table.getScanner(scan);
        for (Result result : resultScanner) {
            totalNumber++;
        }
        return totalNumber;
    }

    /**
     * 获取各品牌的平均价格
     * @param hbaseRequest
     * @return
     * @throws IOException
     */
    public HBaseResult findBrandAveragePrice(HbaseRequest hbaseRequest) throws IOException {
        //初始化返回对象
        HBaseResult hBaseResult = new HBaseResult();
        //获取表对象
        Table table = connection.getTable(TableName.valueOf(hbaseRequest.getTableName()));
        Scan scan = new Scan();
        //设置最大版本
        scan.setMaxVersions(1);
        ResultScanner resultScanner = table.getScanner(scan);
        //获取所有的row key
        List<byte[]> rowKeys = new ArrayList<>();
        for (Result result : resultScanner) {
            rowKeys.add(result.getRow());
        }
        //初始化价格列表
        List<List> brandPriceList = new ArrayList<>();
        for (int i = 0; i < brandArray.length; i++) {
            brandPriceList.add(new ArrayList());
        }
        //根据row key查询品牌和价格
        for (byte[] rowKey : rowKeys) {
            Get getBrand = new Get(rowKey);
            getBrand.addColumn(Bytes.toBytes("baseInfo"), Bytes.toBytes("brand_"));
            Get getPrice = new Get(rowKey);
            getPrice.addColumn(Bytes.toBytes("baseInfo"), Bytes.toBytes("price_"));
            Result brandResult = table.get(getBrand);
            Result priceResult = table.get(getPrice);
            //遍历品牌
            for (int i = 0; i < brandArray.length; i++) {
                if (Bytes.toString(brandResult.list().get(0).getValue()).equals(brandArray[i])) {
                    if (!Bytes.toString(priceResult.list().get(0).getValue()).equals("暂无报价")) {
                        brandPriceList.get(i).add(Double.valueOf(Bytes.toString(priceResult.list().get(0).getValue()).substring(0, Bytes.toString(priceResult.list().get(0).getValue()).length() - 1)));
                    }
                }
            }
        }
        //计算平均价格
        List<Double> brandAveragePrice = new ArrayList<>();
        for (List<Double> brandPrices : brandPriceList) {
            double totalPrice = 0;
            for (Double price : brandPrices) {
                totalPrice += price;
            }
            brandAveragePrice.add(totalPrice / brandPrices.size());
        }
        //判断数据长度
        if (brandAveragePrice.size() > 0) {
            hBaseResult.setResult(true);
            Map data = new HashMap();
            data.put("brands", brandArray);
            data.put("prices", brandAveragePrice);
            hBaseResult.setData(data);
        }else {
            hBaseResult.setMessage("获取数据错误！");
        }
        return hBaseResult;
    }

    /**
     * 查询全表
     * @param hbaseRequest
     * @return
     * @throws IOException
     */
    public HBaseResult queryAllTable(HbaseRequest hbaseRequest) throws IOException {
        //初始化返回对象
        HBaseResult hBaseResult = new HBaseResult();
        //获取表对象
        Table table = connection.getTable(TableName.valueOf(hbaseRequest.getTableName()));
        //
        final byte[] POSTFIX = new byte[] { 0x00 };
        //
        Scan scan = new Scan();
        //设置最大版本
        scan.setMaxVersions(1);
        //过滤器列表
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        if (hbaseRequest.getBrand() != null) {
            //创建品牌过滤器
            Filter brandFilter = new SingleColumnValueFilter(Bytes.toBytes("baseInfo"), Bytes.toBytes("brand_"),
                    CompareFilter.CompareOp.EQUAL, Bytes.toBytes(hbaseRequest.getBrand()));
            filterList.addFilter(brandFilter);
        }
        if (hbaseRequest.getMinPrice() != 0.0) {
            //创建最低价格过滤器
            Filter minPriceFilter = new SingleColumnValueFilter(Bytes.toBytes("baseInfo"), Bytes.toBytes("price_"),
                    CompareFilter.CompareOp.GREATER_OR_EQUAL, Bytes.toBytes(hbaseRequest.getMinPrice() + "万"));
            filterList.addFilter(minPriceFilter);
        }
        if (hbaseRequest.getMaxPrice() != 0.0) {
            //创建最高价格过滤器
            Filter maxPriceFilter = new SingleColumnValueFilter(Bytes.toBytes("baseInfo"), Bytes.toBytes("price_"),
                    CompareFilter.CompareOp.LESS_OR_EQUAL, Bytes.toBytes(hbaseRequest.getMaxPrice() + "万"));
            filterList.addFilter(maxPriceFilter);
        }
        if (hbaseRequest.getCarSeats() != null) {
            //创建座位数过滤器
            Filter seatNumberFilter = new SingleColumnValueFilter(Bytes.toBytes("baseAttribute"), Bytes.toBytes("car_seats"),
                    CompareFilter.CompareOp.EQUAL, Bytes.toBytes(hbaseRequest.getCarSeats() + ""));
            filterList.addFilter(seatNumberFilter);
        }
        if (hbaseRequest.getLastRow().length != 0) {
            //注意这里添加了POSTFIX操作，不然死循环了
            byte[] startRow = Bytes.add(hbaseRequest.getLastRow(), POSTFIX);
            scan.setStartRow(startRow);
        }
        //设置分页
        Filter pageFilter = new PageFilter(hbaseRequest.getPageSize());
        filterList.addFilter(pageFilter);
        scan.setFilter(filterList);
        ResultScanner resultScanner = table.getScanner(scan);
        //初始化返回结果
        List<ColumnResult> columnResultList = new ArrayList<>();
        List<List> originalResultList = new ArrayList<>();
        for (Result result : resultScanner) {
            //获取封装结果
            Map encapsulationMap = encapsulationData(result);
            columnResultList.add((ColumnResult) encapsulationMap.get("columnResults"));
            originalResultList.add((List) encapsulationMap.get("originalResults"));
        }
        //判断数据长度
        if (originalResultList.size() > 0 && originalResultList.get(0).size() > 0) {
            hBaseResult.setResult(true);
            Map<String, List> data = new HashMap<>();
            data.put("columnResultList", columnResultList);
            data.put("originalResultList", originalResultList);
            hBaseResult.setData(data);
            hBaseResult.setTotalNumber(count(hbaseRequest));
        }else {
            hBaseResult.setMessage("获取数据失败！");
        }
        table.close();
        return hBaseResult;
    }

    /**
     * 删除数据
     * @param hbaseRequest
     * @return
     */
    public HBaseResult delete(HbaseRequest hbaseRequest) throws IOException {
        //初始化返回对象
        HBaseResult hBaseResult = new HBaseResult();
        //获取表对象
        Table table = connection.getTable(TableName.valueOf(hbaseRequest.getTableName()));
        //遍历row key删除
        for (String deleteRow : hbaseRequest.getDeleteRow()) {
            //创建删除对象
            Delete delete = new Delete(Bytes.toBytes(deleteRow));
            table.delete(delete);
        }
        hBaseResult.setResult(true);
        return hBaseResult;
    }

    /**
     * 添加列族
     * @param tableName
     * @param columnFamilies
     * @throws IOException
     */
    public HBaseResult addColumnFamily(String tableName, String[] columnFamilies) throws IOException {
        //初始化返回对象
        HBaseResult hBaseResult = new HBaseResult();
        //遍历要添加的列族
        for (String columnFamily : columnFamilies) {
            //创建列族描述对象
            HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(columnFamily);
            //将列族添加到指定表
            admin.addColumn(TableName.valueOf(tableName), hColumnDescriptor);
        }
        hBaseResult.setResult(true);
        return hBaseResult;
    }

    /**
     * 删除列族
     * @param tableName
     * @param columnFamilies
     * @throws IOException
     */
    public HBaseResult deleteColumnFamily(String tableName, String[] columnFamilies) throws IOException {
        //初始化返回对象
        HBaseResult hBaseResult = new HBaseResult();
        //遍历要删除的列族
        for (String columnFamily : columnFamilies) {
            admin.deleteColumn(TableName.valueOf(tableName), columnFamily.getBytes());
        }
        hBaseResult.setResult(true);
        return hBaseResult;
    }

    /**
     * 获取所有列族
     * @param tableName
     * @return
     * @throws IOException
     */
    public HBaseResult getAllColumnFamilies(String tableName) throws IOException {
        //初始化返回结果
        HBaseResult hBaseResult = new HBaseResult();
        //获取列族描述对象
        HColumnDescriptor[] hColumnDescriptors = admin.getTableDescriptor(TableName.valueOf(tableName)).getColumnFamilies();
        //初始化返回列族列表
        ArrayList<String> columnFamilies = new ArrayList<>();
        //遍历列族描述对象，把列族存入列族列表
        for (HColumnDescriptor hColumnDescriptor : hColumnDescriptors) {
            String columnFamily = Bytes.toString(hColumnDescriptor.getName());
            columnFamilies.add(columnFamily);
        }
        hBaseResult.setResult(true);
        hBaseResult.setData(columnFamilies);
        return hBaseResult;
    }
}
