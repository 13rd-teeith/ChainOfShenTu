var provinceList = [];

var provinceArray = new Array();
var cityArray = new Array();
var areaArray = new Array();
var provinceTag = document.getElementById("province");


var cityTag = document.getElementById("city");
var areaTag = document.getElementById("area");
var cityList;
var areaList;
var provinceArray = new Array();
var cityArray = new Array();
var areaArray = new Array();

document.addEventListener("DOMContentLoaded", function () {
    $.ajax({
        url: "http://localhost:8000/province.json",
        type: "GET",
        dataType: "json",
        success: function (data) {
            provinceList = data; // 将数据存储在全局数组中
            console.log("读取省市县区数据成功");
        }
    });
});

function getProvince() {
    var provinceTag = document.getElementById("province");
    for (var i = 0; i < provinceList.length; i++) {　　　　//provinceList.length为省数组的长度，下标从0开始，所以定义var i=0
        var province = provinceList[i];　　　　　　　　　　//通过下标获取省列表（上面的列出列表）中的数据
        var provinceName = province.name;　　　　　　　　//根据 province.name获取省的名字
        provinceArray[i] = provinceName;　　　　　　　　　　　//将获得的省的名字注入到数组中去
        provinceTag.add(new Option(provinceName, i));　　　　//通过Option方法将省的名字与下标i对应，取出来。然后通过add()方法，将每一个名字放到provinceTag中
    }
}

function chooseProvince(th) {　　　　　　　　//通过方法的调用来实现省 市之间的二级联动，th是我们设置的一个参数，方便下面进行使用，可以理解为province的一个元素（名字）
    var cityTag = document.getElementById("city");
    var index = th.selectedIndex - 1;　　　　　　　　　　//此处selectedIndex的索引减1是因为我们在写<select><option>按钮时  “请选择省”  占了一个索引，所以需要减1才能对应
    var provinceName = provinceArray[index];　　　　//通过数组下标获取数据（名字）
    for (var n = 0; n < provinceList.length; n++) {　　　　//通过循环遍历列表数组
        var provice = provinceList[n];　　　　　　　　　　　//通过列表下标获取数据
        if (provice.name == provinceName) {　　　　　　//开始建立连接，通过if判断，前提是要满足if中的条件
            cityList = provice.cityList;　　　　　　　　　　　　//通过province的cityList获取城市列表
            cityTag.innerHTML = "";　　　　　　　　　　　　　　//这个步骤很重要，分析语句，是吧里面的内容变为空，意思就是当你选择一个省时，后面会出现城市的数据，当你选择另外的省时会                                                                            出现相应的市的数据，如果不删除就会同时显示在一个选择框中，就不会是我们想要的结果
            for (var c = 0; c < cityList.length; c++) {　　　　　　//使用循环遍历城市数列
                var city = cityList[c];　　　　　　　　　　　　　　　//根据数列下标获取城市数据
                var cityName = city.name;　　　　　　　　　　　　//获取城市的名字
                cityArray[c] = cityName;　　　　　　　　　　　　　//将城市名称存入数组中
                cityTag.add(new Option(cityName, c));　　　　　　//使用Option()方法获取每一个索引对应的数据，然后使用add()方法一一存入到数组中去，这样就可以成功创建省市连接
            }
        }
    }
}

function chooseCity(ci) {
    var areaTag = document.getElementById("area");
    var index = ci.selectedIndex;
    var cityName = cityArray[index];
    for (var j = 0; j < cityList.length; j++) {
        var city = cityList[j];
        if (city.name == cityName) {
            var areaList = city.areaList;
            areaTag.innerHTML = "";
            for (var k = 0; k < areaList.length; k++) {
                var area = areaList[k];
                areaTag.add(new Option(area, k));
            }
        }
    }
}
