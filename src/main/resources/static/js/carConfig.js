$(function() {

	//初始化请求地址
	var queryAllTableUrl = "carConfig/queryAllTable";
	var saveUrl = "carConfig/save";
	var updateUrl = "carConfig/update";
	var queryTableByRowKeyUrl = "carConfig/queryTableByRowKey";
	var deleteUrl = "carConfig/delete";
	var findBrandAveragePriceUrl = "carConfig/findBrandAveragePrice";

	//row key数组
	var rowKeyArray = [null];

	//查询表名
	var tableName = "car_config";

	//每页显示数据条数
	var pageSize = 5;

	//品牌数据
	var brandStr = "AC Schnitzer,DS,GMC,Jeep,KTM,MG,MINI,smart,一汽,三菱,上汽大通,世爵,东南,东风,东风小康,东风风度,东风风神,东风风行,中兴,中华,丰田,九龙,五十铃,五菱汽车,众泰,依维柯,保时捷,光冈,克莱斯勒,兰博基尼,凯翼,凯迪拉克,别克,前途,力帆,劳斯莱斯,北京,北汽制造,北汽威旺,北汽幻速,北汽新能源,北汽绅宝,华普,华泰,华颂,南京金龙,卡威,卡尔森,双环,双龙,吉利汽车,启辰,哈弗,哈飞,大众,大发,奇瑞,奔腾,奔驰,奥迪,如虎,威兹曼,威麟,安凯客车,宝马,宝骏,宾利,巴博斯,布加迪,帕加尼,广汽传祺,广汽吉奥,开瑞,思铭,恒天,悍马,成功汽车,捷豹,摩根,斯巴鲁,斯柯达,日产,昌河,本田,林肯,标致,欧宝,欧朗,比亚迪,永源,江淮,江铃,江铃集团轻汽,沃尔沃,法拉利,海格,海马,潍柴英致,特斯拉,猎豹汽车,玛莎拉蒂,现代,理念,瑞麒,知豆,福汽启腾,福特,福田,福迪,科尼赛克,红旗,纳智捷,腾势,英菲尼迪,荣威,莲花汽车,菲亚特,萨博,西雅特,观致,讴歌,赛麟,起亚,路特斯,路虎,迈凯伦,迈巴赫,道奇,野马汽车,金旅,金杯,金龙,铃木,长城,长安,长安商用,阿尔法罗密欧,阿斯顿·马丁,陆风,雪佛兰,雪铁龙,雷克萨斯,雷诺,马自达,黄海";

	//座位数数据
	var seatNumberStr = "10,11,12,13,14,15,16,17,18,19,2,20,21,23,3,4,5,6,7,8,9";

	//全选/反选
	$(".allCheck").click(function() {
		var $check = $(".check");
		if($(this)[0].checked == true) {
			//如果全选
			for(var i = 0; i < $check.length; i++) {
				$check[i].checked = true;
			}
		} else {
			//如果反选
			for(var i = 0; i < $check.length; i++) {
				$check[i].checked = false;
			}
		}
	});

	//重新加载原始数据表、行式数据表
	function reloadTable(data) {
		console.log(data);
		if(data.result) {
			//清空原表
			$("#originalData tbody").html("");
			$("#columnData tbody").html("");
			//加载总页数
			$("#totalPageNumber").html(Math.ceil(data.totalNumber / 5));
			//加载原始数据
			var originalResultList = data.data.originalResultList;
			for(var i = 0; i < originalResultList.length; i++) {
				var originalResults = originalResultList[i];
				for(var j = 0; j < originalResults.length; j++) {
					$("#originalData tbody").append($('<tr>' +
						'<td> ' + originalResults[j].row + ' </td>' +
						'<td> ' + originalResults[j].family + ' </td>' +
						'<td> ' + originalResults[j].qualifier + ' </td>' +
						'<td> ' + originalResults[j].value + ' </td>' +
						'<td> ' + originalResults[j].timestamp + ' </td>' +
						'</tr>'));
				}
				$("#originalData tbody").append("<hr></hr>");
			}
			//加载列式数据
			var columnResultList = data.data.columnResultList;
			for(var i = 0; i < columnResultList.length; i++) {
				var columnResults = columnResultList[i];
				var row = columnResults.row;
				var timestamps = columnResults.timestamps;
				var baseInfos = columnResults.baseInfos;
				var baseAttributes = columnResults.baseAttributes;
				var performanceAttributes = columnResults.performanceAttributes;
				//设置row key
				if(i == columnResultList.length - 1) {
					rowKeyArray.push(row);
				}
				for(var j = 0; j < timestamps.length; j++) {
					$tr = $('<tr>' +
						'<td><input type="checkbox" class="check" /></td>' +
						'</tr>');
					$td = $('<td style="cursor:pointer;"> ' + row + ' </td>').click(function() {
						//数据加载到modal中
						$("#modalTitle").attr("updateRowKey", $($(this).parent().children()[1]).html().trim());

						var brandArr = brandStr.split(",");
						for(var i = 0; i < brandArr.length; i++) {
							if(brandArr[i] == $($(this).parent().children()[3]).html().trim()) {
								$("#inputBrand option")[i].selected = true;
							}
						}
						$("#inputCarSeries").val($($(this).parent().children()[4]).html().trim());
						$("#inputCarType").val($($(this).parent().children()[5]).html().trim());
						$("#inputManufacturer").val($($(this).parent().children()[6]).html().trim());
						$("#inputPrice").val($($(this).parent().children()[7]).html().trim());
						$("#inputYear").val($($(this).parent().children()[8]).html().trim());

						$("#inputCarDoors").val($($(this).parent().children()[9]).html().trim());
						var seatNumberArr = seatNumberStr.split(",");
						for(var i = 0; i < seatNumberArr.length; i++) {
							if(seatNumberArr[i] == $($(this).parent().children()[10]).html().trim()) {
								$("#inputCarSeats option")[i].selected = true;
							}
						}
						$("#inputCarStructure").val($($(this).parent().children()[11]).html().trim());
						$("#inputGearbox").val($($(this).parent().children()[12]).html().trim());
						$("#inputGrade").val($($(this).parent().children()[13]).html().trim());
						$("#inputSize").val($($(this).parent().children()[14]).html().trim());

						$("#inputAccelerationCapability").val($($(this).parent().children()[15]).html().trim());
						$("#inputCarCylinders").val($($(this).parent().children()[16]).html().trim());
						$("#inputDisplacement").val($($(this).parent().children()[17]).html().trim());
						$("#inputEngine").val($($(this).parent().children()[18]).html().trim());
						$("#inputMaxSpeed").val($($(this).parent().children()[19]).html().trim());
						$("#inputOilDrive").val($($(this).parent().children()[20]).html().trim());
						$("#inputOilTankCapacity").val($($(this).parent().children()[21]).html().trim());

						//修改modal状态
						$("#modalTitle").html("UPDATE CAR INFO");
						$("#save").css("display", "none");
						$("#update").css("display", "block");

						//显示modal
						$("#carConfigodal").modal();
					});
					$tr.append($td);
					$tr.append($('<td> ' + timestamps[j] + ' </td>' +
						'<td> ' + baseInfos[j].brand + ' </td>' +
						'<td> ' + baseInfos[j].carSeries + ' </td>' +
						'<td> ' + baseInfos[j].carType + ' </td>' +
						'<td> ' + baseInfos[j].manufacturer + ' </td>' +
						'<td> ' + baseInfos[j].price + ' </td>' +
						'<td> ' + baseInfos[j].year + ' </td>' +

						'<td> ' + baseAttributes[j].carDoors + ' </td>' +
						'<td> ' + baseAttributes[j].carSeats + ' </td>' +
						'<td> ' + baseAttributes[j].carStructure + ' </td>' +
						'<td> ' + baseAttributes[j].gearbox + ' </td>' +
						'<td> ' + baseAttributes[j].grade + ' </td>' +
						'<td> ' + baseAttributes[j].size + ' </td>' +

						'<td> ' + performanceAttributes[j].accelerationCapability + ' </td>' +
						'<td> ' + performanceAttributes[j].carCylinders + ' </td>' +
						'<td> ' + performanceAttributes[j].displacemen + ' </td>' +
						'<td> ' + performanceAttributes[j].engine + ' </td>' +
						'<td> ' + performanceAttributes[j].maxSpeed + ' </td>' +
						'<td> ' + performanceAttributes[j].oilDrive + ' </td>' +
						'<td> ' + performanceAttributes[j].oilTankCapacity + ' </td>'));

					$("#columnData tbody").append($tr);
				}
			}
		} else {
			alert(data.message);
		}
	};

	//页面加载时获取数据
	ajax_("get", queryAllTableUrl, {
		"tableName": tableName,
		"pageSize": pageSize,
		"lastRow": null
	}, function(data) {
		reloadTable(data);
	});

	//判断查询参数
	function getParameter(lastRow) {
		//获取用户选择的查询参数
		var brand = $("#brand span")[0].innerHTML;
		var minPrice = $("#minPrice").val().trim();
		var maxPrice = $("#maxPrice").val().trim();
		var seatNumber = $("#seatNumber span")[0].innerHTML;
		//初始化查询参数
		var queryBrand;
		var queryMinPrice;
		var queryMaxPrice;
		var querySeatNumber;
		//如果什么都没选
		if(brand == "BRAND" && minPrice == "" && maxPrice == "" && seatNumber == "SEAT NUMBER") {
			//
		} else {
			//选了一个或多个查询参数
			if(brand != "BRAND") {
				//brand已选
				queryBrand = brand;
			}
			if(minPrice != "") {
				queryMinPrice = minPrice;
			}
			if(maxPrice != "") {
				queryMaxPrice = maxPrice;
			}
			if(seatNumber != "SEAT NUMBER") {
				//seatNumber已选
				querySeatNumber = seatNumber;
			}
		}
		//封装查询参数
		return {
			"tableName": tableName,
			"pageSize": pageSize,
			"lastRow": lastRow,
			"brand": queryBrand,
			"minPrice": queryMinPrice,
			"maxPrice": queryMaxPrice,
			"carSeats": querySeatNumber
		};
	}

	//点击下一页
	$("#nextPage").click(function() {
		//如果当前是最后一页
		if(parseInt($("#currentPageNumber").html()) == parseInt($("#totalPageNumber").html())) {
			return;
		}
		//修改当前页数
		$("#currentPageNumber").html(parseInt($("#currentPageNumber").html()) + 1);
		//获取数据
		ajax_("get", queryAllTableUrl, getParameter(rowKeyArray[parseInt($("#currentPageNumber").html()) - 1]), function(data) {
			reloadTable(data);
		});
	});

	//点击上一页
	$("#lastPage").click(function() {
		//如果当前是第1页
		if(parseInt($("#currentPageNumber").html()) == 1) {
			return;
		}
		//修改当前页数
		$("#currentPageNumber").html(parseInt($("#currentPageNumber").html()) - 1);
		//获取数据
		ajax_("get", queryAllTableUrl, getParameter(rowKeyArray[parseInt($("#currentPageNumber").html()) - 1]), function(data) {
			reloadTable(data);
		});
	})

	//点击查询按钮
	$("#search").click(function() {
		//重置rowKeyArray
		rowKeyArray = [null];
		//如果用户输入了row key
		var rowKey = $("#rowKey").val().trim();
		if(rowKey != "") {
			//通过row key查询
			ajax_("get", queryTableByRowKeyUrl, {
				"tableName": tableName,
				"queryRow": rowKey
			}, function(data) {
				reloadTable(data);
			});
		} else {
			//通过条件查询
			ajax_("get", queryAllTableUrl, getParameter(null), function(data) {
				reloadTable(data);
			});
		}
		//修改当前页数
		$("#currentPageNumber").html(1);

	});

	//select改变时，把数据同步到显示的button中
	$(".menuUl select").change(function() {
		$(this).parent().prev().children()[0].innerHTML = $(this).val();
	});

	//页面加载初始化商标select
	var brandArr = brandStr.split(",");
	for(var i = 0; i < brandArr.length; i++) {
		$($(".selectBox ul select")[0]).append($('<option>' + brandArr[i] + '</option>'));
		$("#inputBrand").append($('<option>' + brandArr[i] + '</option>'));
	}

	//页面加载初始化座位数select
	var seatNumberArr = seatNumberStr.split(",");
	for(var i = 0; i < seatNumberArr.length; i++) {
		$($(".selectBox ul select")[1]).append($('<option>' + seatNumberArr[i] + '</option>'));
		$("#inputCarSeats").append($('<option>' + seatNumberArr[i] + '</option>'));
	}

	//点击新增
	$("#add").click(function() {
		$("#modalTitle").html("ADD CAR INFO");
		$("#save").css("display", "block");
		$("#update").css("display", "none");
		//清空modal
		$("#inputCarSeries").val("");
		$("#inputCarType").val("");
		$("#inputManufacturer").val("");
		$("#inputPrice").val("");
		$("#inputYear").val("");

		$("#inputCarDoors").val("");
		$("#inputCarStructure").val("");
		$("#inputGearbox").val("");
		$("#inputGrade").val("");
		$("#inputSize").val("");

		$("#inputAccelerationCapability").val("");
		$("#inputCarCylinders").val("");
		$("#inputDisplacement").val("");
		$("#inputEngine").val("");
		$("#inputMaxSpeed").val("");
		$("#inputOilDrive").val("");
		$("#inputOilTankCapacity").val("");
	});

	//新增、修改保存
	$("#save, #update").click(function() {
		//请求路径
		var queryUrl;
		//修改的row key
		var updateRowKey;
		//操作类型
		var saveOrUpdate;
		if($(this).attr("id") == "save") {
			queryUrl = saveUrl;
			saveOrUpdate = "save";
		} else if($(this).attr("id") == "update") {
			queryUrl = updateUrl;
			updateRowKey = $("#modalTitle").attr("updateRowKey");
			saveOrUpdate = "update";
		}
		//获取填写数据
		var inputBrand = $("#inputBrand").val().trim();
		var inputCarSeries = $("#inputCarSeries").val().trim();
		var inputCarType = $("#inputCarType").val().trim();
		var inputManufacturer = $("#inputManufacturer").val().trim();
		var inputPrice = $("#inputPrice").val().trim();
		var inputYear = $("#inputYear").val().trim();

		var inputCarDoors = $("#inputCarDoors").val().trim();
		var inputCarSeats = $("#inputCarSeats").val().trim();
		var inputCarStructure = $("#inputCarStructure").val().trim();
		var inputGearbox = $("#inputGearbox").val().trim();
		var inputGrade = $("#inputGrade").val().trim();
		var inputSize = $("#inputSize").val().trim();

		var inputAccelerationCapability = $("#inputAccelerationCapability").val().trim();
		var inputCarCylinders = $("#inputCarCylinders").val().trim();
		var inputDisplacement = $("#inputDisplacement").val().trim();
		var inputEngine = $("#inputEngine").val().trim();
		var inputMaxSpeed = $("#inputMaxSpeed").val().trim();
		var inputOilDrive = $("#inputOilDrive").val().trim();
		var inputOilTankCapacity = $("#inputOilTankCapacity").val().trim()
		//判断填写情况
		if(inputPrice == "") {
			alert("PLEASE FILL IN THE RED FIELD");
			return;
		}
		if(inputCarSeries == "") {
			inputCarSeries = "未知";
		}
		if(inputCarType == "") {
			inputCarType = "未知";
		}
		if(inputManufacturer == "") {
			inputManufacturer = "未知";
		}
		if(inputYear == "") {
			inputYear = "未知";
		}
		if(inputCarDoors == "") {
			inputCarDoors = "未知";
		}
		if(inputCarStructure == "") {
			inputCarStructure = "未知";
		}
		if(inputGearbox == "") {
			inputGearbox = "未知";
		}
		if(inputGrade == "") {
			inputGrade = "未知";
		}
		if(inputSize == "") {
			inputSize = "未知";
		}
		if(inputAccelerationCapability == "") {
			inputAccelerationCapability = "未知";
		}
		if(inputCarCylinders == "") {
			inputCarCylinders = "未知";
		}
		if(inputDisplacement == "") {
			inputDisplacement = "未知";
		}
		if(inputEngine == "") {
			inputEngine = "未知";
		}
		if(inputMaxSpeed == "") {
			inputMaxSpeed = "未知";
		}
		if(inputOilDrive == "") {
			inputOilDrive = "未知";
		}
		if(inputOilTankCapacity == "") {
			inputOilTankCapacity = "未知";
		}
		ajax_("get", queryUrl, {
			"tableName": tableName,
			"updateRow": updateRowKey,

			"brand": inputBrand,
			"carSeries": inputCarSeries,
			"carType": inputCarType,
			"manufacturer": inputManufacturer,
			"price": inputPrice,
			"year": inputYear,

			"carDoors": inputCarDoors,
			"carSeats": inputCarSeats,
			"carStructure": inputCarStructure,
			"gearbox": inputGearbox,
			"grade": inputGrade,
			"size": inputSize,

			"accelerationCapability": inputAccelerationCapability,
			"carCylinders": inputCarCylinders,
			"displacemen": inputDisplacement,
			"engine": inputEngine,
			"maxSpeed": inputMaxSpeed,
			"oilDrive": inputOilDrive,
			"oilTankCapacity": inputOilTankCapacity
		}, function(data) {
			if(data.result) {
				console.log(data);
				//关闭modal
				$("#carConfigodal").modal("hide");
				if(saveOrUpdate == "save") {
					alert("SAVE SUCCESS!");
				} else if(saveOrUpdate == "update") {
					alert("UPDATE SUCCESS!");
				}
				//查询之前修改或新增的数据
				ajax_("get", queryTableByRowKeyUrl, {
					"tableName": tableName,
					"pageSize": pageSize,
					"queryRow": data.data
				}, function(data) {
					reloadTable(data);
				});
			}
		});
	});

	//删除
	$("#delete").click(function() {
		//获取要删除的row key
		var $check = $(".check");
		var checkedRowKeyStr = "";
		for(var i = 0; i < $check.length; i++) {
			if($check[i].checked == true) {
				//console.log($($check[i]).parent().next().html().trim());
				checkedRowKeyStr += $($check[i]).parent().next().html().trim() + ",";
			}
		}
		//去掉最后一个逗号
		checkedRowKeyStr = checkedRowKeyStr.substring(0, checkedRowKeyStr.length - 1);
		if(checkedRowKeyStr == "") {
			alert("PLEASE SELECT THE ROW TO DELETE!");
			return;
		}
		//删除请求
		ajax_("get", deleteUrl, {
			"tableName": tableName,
			"pageSize": pageSize,
			"deleteRow": checkedRowKeyStr
		}, function(data) {
			console.log(data);
			if(data.result) {
				alert('DELETE SUCCESS!');
				$("#search").trigger("click");
			} else {
				alert(data.message);
			}
		});
	});

	//点击图表数据
	$("#myTabs a").click(function() {
		if($(this).attr("href") == "#chartsData") {
			//显示loading
			$(".loading").css("display", "block");
			//获取数据
			ajax_("get", findBrandAveragePriceUrl, {
				"tableName": tableName
			}, function(data) {
				console.log(data);
				if(data.result) {
					var option = {
				        tooltip: {
				            trigger: 'axis'
				        },
				        xAxis: {
				            data: data.data.brands
				        },
				        yAxis: {
				            splitLine: {
				                show: false
				            }
				        },
				        toolbox: {
				            left: 'center',
				            feature: {
				                dataZoom: {
				                    yAxisIndex: 'none'
				                },
				                restore: {},
				                saveAsImage: {}
				            }
				        },
				        dataZoom: [{
				            startValue: '2014-06-01'
				        }, {
				            type: 'inside'
				        }],
				        visualMap: {
				            top: 10,
				            right: 10,
				            pieces: [{
				                gt: 0,
				                lte: 10,
				                color: '#096'
				            }, {
				                gt: 10,
				                lte: 100,
				                color: '#ffde33'
				            }, {
				                gt: 100,
				                lte: 500,
				                color: '#ff9933'
				            }, {
				                gt: 500,
				                lte: 1000,
				                color: '#cc0033'
				            }, {
				                gt: 1000,
				                color: '#7e0023'
				            }],
				            outOfRange: {
				                color: '#999'
				            }
				        },
				        series: {
				            name: 'AVERAGE PRICE',
				            type: 'line',
				            data: data.data.prices,
				            markLine: {
				                silent: true,
				                data: [{
				                    yAxis: 10
				                }, {
				                    yAxis: 50
				                }, {
				                    yAxis: 100
				                }, {
				                    yAxis: 500
				                }, {
				                    yAxis: 1000
				                }]
				            }
				        }
					}
					//初始化echarts实例
        			var myChart = echarts.init(document.getElementById('chartsData'));
        			//使用刚指定的配置项和数据显示图表。
        			myChart.setOption(option);
        			//隐藏loading
        			$(".loading").css("display", "none");
				} else {
					alert(data.message);
				}
			});
		}
	});
})