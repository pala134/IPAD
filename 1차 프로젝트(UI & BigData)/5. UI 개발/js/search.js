window.onload = function () {


    // const regionInfo = {
    //     region: 0,
    //     income: 0,
    //     rent: 0,
    //     floatingPopulation: 10000,
    //     countOfSurrounding: 0
    // }
    // console.log(regionInfo)
    var mapContainer = document.getElementById('map2'),
        mapOption = {
            center: new kakao.maps.LatLng(37.4765187, 127.1456706),
            level: 7
        };

    var map = new kakao.maps.Map(mapContainer, mapOption);
    setZoomable(false);
    var geocoder = new kakao.maps.services.Geocoder();
    var incomeDatas = [];
    var rentDatas = [];

    mainCity.onchange = function () {

        var mainOption = mainCity.options[mainCity.selectedIndex].innerText;

        $.getJSON("json/emdTest.geojson", function (geojson) {
            var data = geojson.features;
            var sidonm = ' ';
            var sggnm = ' ';
            var subCitySet = new Set();

            data.forEach(val => {
                sidonm = val.properties.sidonm;
                sggnm = val.properties.sggnm;

                if (mainOption == sidonm) {
                    subCitySet.add('시/군/구');
                    subCitySet.add(val.properties.sggnm);
                } else {
                    var extraCity = document.getElementById('extraCity');

                    subCitySet.add('시/군/구');

                    extraCity.options.length = 0;

                    var extraCitySet = new Set();

                    extraCitySet.add('동/면/읍');

                    Array.from(extraCitySet).forEach(value => {
                        var option = document.createElement('option');
                        option.value = value;
                        option.innerText = value;
                        extraCity.appendChild(option);
                    });
                }
            });

            var subCity = document.getElementById('subCity');

            Array.from(subCitySet).forEach(value => {
                var option = document.createElement('option');
                option.value = value;
                option.innerText = value;
                subCity.appendChild(option);
            });
        });

        subCity.options.length = 0;

        subCity.onchange = function () {

            var extraCity = document.getElementById('extraCity');
            var subOption = subCity.options[subCity.selectedIndex].innerText;

            $.getJSON("json/emdTest.geojson", function (geojson) {
                var data = geojson.features;
                var dongnm = ' ';
                var temp = ' ';
                var citynm = ' ';
                var extraCitySet = new Set();

                data.forEach(val => {
                    temp = val.properties.temp;
                    dongnm = temp.split(' ')[1];
                    citynm = temp.split(' ')[0];

                    if (subOption === citynm) {
                        extraCitySet.add('동/면/읍');
                        extraCitySet.add(dongnm);
                    } else {
                        extraCitySet.add('동/면/읍');
                    }
                });

                var extraCity = document.getElementById('extraCity');

                Array.from(extraCitySet).forEach(value => {
                    var option = document.createElement('option');
                    option.value = value;
                    option.innerText = value;
                    extraCity.appendChild(option);
                });
            });
            extraCity.options.length = 0;
        }
    }

    const button = document.querySelector('#submit');
    const polygons = [];
    var markers = [];
    const buttonClickHandler = () => {
        var areaValue = document.querySelector('#mainCity').value;
        var cityValue = document.querySelector('#subCity').value;
        var dongValue = document.querySelector('#extraCity').value;
        var address = cityValue + " " + dongValue;

        var markerAdd = areaValue + " " + cityValue + " " + dongValue;

        getIncomeJSON(address);



        var incomeData = 0;
        for (var i = 0; i < incomeDatas.length; i++) {
            incomeData += incomeDatas[i]
        }
        // regionInfo.income = incomeData / incomeDatas.length;
        getRentJSON(address);
        var rentData = 0;
        for (var i = 0; i < rentDatas.length; i++) {
            rentData += Number((rentDatas[i][0] / rentDatas[i][1]).toFixed(1));
        }
        // regionInfo.rent = rentData / rentDatas.length;
        // regionInfo.region = markerAdd;
        // console.log(regionInfo)
        removePolygon();
        map.setLevel(7);
        geocoder.addressSearch(address, function (result, status) {
            incomeDatas.length = 0;
            rentDatas.length = 0;
            hideMarkers();
            if (status === kakao.maps.services.Status.OK) {

                var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                var marker = new kakao.maps.Marker({
                    map: map,
                    position: coords
                });
                markers.push(marker);
                console.log(markers);
                map.setCenter(coords);

                iwPosition = marker.position;

                var infowindow = new kakao.maps.InfoWindow({
                    position: iwPosition,
                });
                var mainCity = document.querySelector("#mainCity");
                var subCity = document.querySelector("#subCity");
                var extraCity = document.querySelector("#extraCity");
                var mainOption = mainCity.options[mainCity.selectedIndex].value;
                var subOption = subCity.options[subCity.selectedIndex].value;
                var extraOption = extraCity.options[extraCity.selectedIndex].value;
                var address = mainOption + ' ' + subOption + ' ' + extraOption

                getIncomeJSON(address);
                getRentJSON(address);



                kakao.maps.event.addListener(marker, 'mouseover', function () {

                    var incomeData = 0;
                    for (var i = 0; i < incomeDatas.length; i++) {
                        incomeData += incomeDatas[i]
                        // console.log(incomeData);
                    }
                    var rentData = 0;
                    for (var i = 0; i < rentDatas.length; i++) {
                        rentData += Number((rentDatas[i][0] / rentDatas[i][1]).toFixed(1));
                        // console.log(rentData);
                    }
                    // regionInfo.income = Math.round(incomeData / incomeDatas.length);
                    // regionInfo.rent = (rentData / rentDatas.length).toFixed(1)

                    console.log(Math.round(incomeData / incomeDatas.length, 0))
                    infowindow.open(map, marker);
                    infowindow.setContent('<div class="container"' +
                        'style = "background-color: rgba(255,255,255,0.5);opacity: 0.5;width: 250px" >' +
                        '<div class="row">' +
                        '<div class="col-lg-12" style="font-size:20px;">' +
                        '<p id="region" style:"font-weight: bold;">' + address +
                        '</div>' +
                        '</div>' +
                        '<div class="row">' +
                        '<div class="col-xs-12 col-sm-12 col-lg-12">' +
                        '<span>유동인구&nbsp&nbsp&nbsp&nbsp</span><span>' + Math.round(Math.random() * 10000) + '명' + '</span>' +
                        '</div>' +
                        '</div>' +
                        '<div class="row">' +
                        '<div class="col-sm-12 col-lg-12">' +
                        '<span>소득수준&nbsp&nbsp&nbsp&nbsp</span><span>' + Math.round(incomeData / incomeDatas.length).toLocaleString('ko-KR') + '만원' + '</span>' +
                        '</div>' +
                        '</div>' +
                        '<div class="row">' +
                        '<div class="col-sm-12 col-lg-12">' +
                        '<span>임&nbsp&nbsp대&nbsp&nbsp료&nbsp&nbsp&nbsp&nbsp</span><span>' + (rentData / rentDatas.length).toFixed(1) + '만원 (평당)' + '</span>' +
                        '</div>' +
                        '</div>' +
                        '</div> '
                    );
                });

            }
            kakao.maps.event.addListener(marker, 'mouseout', function () {
                infowindow.close();
            })
        });

        $.getJSON("json/emdTest.geojson", function (geojson) {
            var data = geojson.features;
            var name = ' ';

            data.forEach(val => {
                var coordinates = val.geometry.coordinates;
                coordinates = coordinates[0];
                name = val.properties.temp;
                if (coordinates.length === 0) return;

                if (coordinates.length === 1 && address == name) {
                    displayArea(coordinates[0], val.properties.temp, val.properties);
                } else {
                    coordinates.forEach(polygonCoords => {
                        displayArea(polygonCoords[0], val.properties.temp, val.properties);
                    });

                }

            });
        });
    };

    button.addEventListener('click', buttonClickHandler);


    function displayArea(coordinates, name, properties) {
        var path = [];
        var areaValue = document.querySelector('#mainCity').value;
        var polygonAdd = areaValue + ' ' + name;

        coordinates.forEach(coordinate => {
            var point = {
                x: coordinate[1],
                y: coordinate[0]
            };
            path.push(new kakao.maps.LatLng(coordinate[1], coordinate[0]));
        });

        var polygon = new kakao.maps.Polygon({
            map: map,
            path: path,
            strokeWeight: 4,
            strokeColor: 'red',
            strokeOpacity: 0.5,
            fillColor: '#ccc',
            fillOpacity: 0.1
        });

        polygons.push({
            polygon: polygon,
            name: name,
            properties: properties
        });

        //폴리곤 영역 클릭 시 주변 업종 마커 표시
        kakao.maps.event.addListener(polygon, 'click', function (mouseEvent) {
            map.setLevel(4);

            var infowindow = new kakao.maps.InfoWindow();

            var ps = new kakao.maps.services.Places();

            ps.keywordSearch(name + ' 치과', placeSearch);

            function placeSearch(data, status, pagination) {
                if (status === kakao.maps.services.Status.OK) {
                    for (var i = 0; i < data.length; i++) {
                        displayMarker(data[i]);
                    }
                }
            }

            function displayMarker(place) {
                var imageSrc = 'img/dental_marker.png',
                    imageSize = new kakao.maps.Size(32, 40),
                    imageOption = { offset: new kakao.maps.Point(27, 69) };

                var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
                    markerPosition = new kakao.maps.LatLng(place.y, place.x);


                var marker = new kakao.maps.Marker({
                    map: map,
                    position: new kakao.maps.LatLng(place.y, place.x),
                    image: markerImage
                })


                markers.push(marker);
                var countOfSurrounding = markers.length;



                kakao.maps.event.addListener(marker, 'click', function () {
                    infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
                    infowindow.open(map, marker);
                });
            }
        });
        // console.log(regionInfo);
    }

    function removePolygon() {
        polygons.forEach((item) => {
            item.polygon.setMap(null);
        });

        polygons.length = 0;
    }
    function setMarkers(map) {
        for (var i = 0; i < markers.length; i++) {
            markers[i].setMap(map);
        }
    }
    function hideMarkers() {
        setMarkers(null);
        markers.length = 0;
    }
    function setZoomable(zoomable) {
        map.setZoomable(zoomable);
    }






    //소득수준 임대료 값 함수 
    function getIncomeJSON(address) {
        $.ajax({
            type: "get",
            url: "/json/newIncome.json",
            dataType: "json",
            success: function (data) {
                console.log("통신성공");
                // console.log(data);

                $.each(data, function (i) {
                    if (data[i].adstrd_nm === address) {
                        var incomeData = data[i].ave_income_amt;
                        incomeDatas.push(incomeData);
                        // console.log(incomeData)
                    }
                });

            },
            error: function () {
                console.log("통신에러");
            }
        })
    }
    function getRentJSON(address) {
        $.ajax({
            type: "get",
            url: "/json/rent.json",
            dataType: "json",
            success: function (data) {
                $.each(data, function (i) {
                    if (data[i].adstrd_nm === address) {
                        var rentData = [data[i].rent_fee, data[i].size];
                        rentDatas.push(rentData);
                        // console.log(rentData);
                    }
                })
                console.log(rentDatas.length)
            }
        })
    }

    var chartArea = document.getElementById('regionInfoChart').getContext('2d');
    var myChart = new Chart(chartArea, {
        type: 'line',
        data: {
            labels: ['2017', '2018', '2019', '2020', '2021', '2022'],
            datasets: [
                {
                    label: '총 환자수',
                    data: [Math.floor(Math.random() * 1000), Math.floor(Math.random() * 1000), Math.floor(Math.random() * 1000), Math.floor(Math.random() * 1000), Math.floor(Math.random() * 1000), Math.floor(Math.random() * 1000)], backgroundColor: ['red'],
                    borderWidth: 3,
                    borderColor: 'black',
                    backgroundColor: 'black',
                    // tension: 0.4,
                },
            ]
        },
        options: {
            reponsive: true,
            maintainAspectRatio: false,
            scales: {
                y: {
                    grid: {
                        display: true,
                        color: 'rgba(0, 0, 0, 0.3)',
                    },
                    beginAtZero: true,
                    min: 0,
                    max: 2000,
                    position: 'top',
                    ticks: {
                        callback: function (value, index, values) {
                            if (index === 0) {
                                return '(단위:1천)' + value;
                            } else {
                                return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
                            }
                        },
                    },
                },
            },
            plugins: {
                title: {
                    display: true,
                    text: "인구",
                    font: {
                        size: 30,
                    }
                },
                legend: {
                    display: false,
                },
            },
        }
    });

    var chartScalesOption = [
        {
            y: {
                grid: {
                    display: true,
                    color: 'rgba(0, 0, 0, 0.3)',
                },
                beginAtZero: true,
                min: 0,
                max: 2000,
                position: 'top',
                ticks: {
                    callback: function (value, index, values) {
                        if (index === 0) {
                            return '(단위:1천)' + value;
                        } else {
                            return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
                        }
                    },
                },
            },
        },
        {

            y: {
                grid: {
                    display: true,
                    color: 'rgba(0, 0, 0, 0.3)',
                },
                beginAtZero: true,
                min: 0,
                max: 20000,
                position: 'top',
                ticks: {
                    callback: function (value, index, values) {
                        if (index === 0) {
                            return '(단위:1천)' + value;
                        } else {
                            return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
                        }
                    },
                },
            },
        },
        {
            y: {
                grid: {
                    display: true,
                    color: 'rgba(0, 0, 0, 0.3)',
                },
                beginAtZero: true,
                min: 0,
                max: 20000,
                position: 'top',
                ticks: {
                    callback: function (value, index, values) {
                        if (index === 0) {
                            return '(단위:1명)' + value;
                        } else {
                            return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
                        }
                    },
                },
            },
        },
        {

            y: {
                grid: {
                    display: true,
                    color: 'rgba(0, 0, 0, 0.3)',
                },
                beginAtZero: true,
                min: 0,
                max: 16000,
                position: 'top',
                ticks: {
                    callback: function (value, index, values) {
                        if (index === 0) {
                            return '(단위:1천)' + value;
                        } else {
                            return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
                        }
                    },
                },
            },
        },
    ]

    category.onchange = function () {
        var category = document.querySelector('#category');
        categoryOpiton = category.options[category.selectedIndex].innerText;
        var categorys = category.options[category.selectedIndex].innerText;
        var title = categorys;

        myChart.options.plugins.title.text = title;

        for (var i = 0; i < myChart.data.datasets.length; i++) {
            var newData = [Math.floor(Math.random() * 1000), Math.floor(Math.random() * 1000), Math.floor(Math.random() * 1000), Math.floor(Math.random() * 1000), Math.floor(Math.random() * 1000), Math.floor(Math.random() * 1000)];
            myChart.data.datasets[i].data = newData;
        }

        if (title === '인구') {
            myChart.options.scales = chartScalesOption[0];
        } else if (title === '세대수 현황') {
            myChart.options.scales = chartScalesOption[1];
        } else if (title === '유동인구') {
            myChart.options.scales = chartScalesOption[2];
        } else {
            myChart.options.scales = chartScalesOption[3];
        }

        myChart.update();
    }

    function openModal() {
        const modal = new bootstrap.Modal(document.querySelector('.modal')); // 모달 객체 생성
        modal.show();
        var mainOption = mainCity.options[mainCity.selectedIndex].value;
        var subOption = subCity.options[subCity.selectedIndex].value;
        var extraOption = extraCity.options[extraCity.selectedIndex].value;
        var address = mainOption + ' ' + subOption + ' ' + extraOption
        console.log(address);
        
        document.getElementById('PopUpArea').innerText = address;

        updateChart();
    }


    function updateChart() {
        const selectedCategory = document.getElementById('category').value; // 선택된 카테고리 가져오기
        const chartCanvas = document.getElementById('regionInfoChart');

    }


    document.getElementById('chartImg').addEventListener('click', openModal);

    // window.onresize = function () {
    //     var width = window.innerWidth;
    //     var height = window.innerHeight;
    //     var sideNav = document.getElementById('sideNav');

    //     if (width < 1000) {
    //         sideNav.style.display = 'none';
    //     }

    //     if (width > 1000) {
    //         sideNav.style.display = 'block';
    //     }
    // }

    //     var currentUrl = window.location.href;
    //     var search = document.getElementById('search.html');
    //     var info = document.getElementById('info.html');
    //     var predict = document.getElementById('predict.html');

    //     if (currentUrl.includes(search)) {
    //         search.style.color = 'brown';
    //     } else if (currentUrl.includes(info)) {
    //         info.style.color = 'brown';
    //     } else {
    //         predict.style.color = 'brown';
    //     }

}


