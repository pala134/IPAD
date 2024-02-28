const abc = [];
const polygons = [];
// ##############송도훈 추가################
function newpage() {
    window.open('analyze.html', 'result', 'width=800, height=1200, left=550');
    return true;
}
// #############################################


window.onload = function () {
    // var defaultCenter = { lat: 37.4775187, lng: 127.1456706 };
    // var defaultZoom = 5;
    // var mapContainer = document.getElementById('map3');
    // function initMap() {
    //     var mapOption = {
    //         center: defaultCenter,
    //         zoom: defaultZoom,
    //     };

    //     var map = new kakao.maps.Map(mapContainer, mapOption);

    //     window.addEventListener('resize', function () {
    //         map.setCenter(defaultCenter);
    //     });
    // }

    // initMap();

    var defaultCenter = new kakao.maps.LatLng(37.4775187, 127.1456706);

    var mapContainer = document.getElementById('map3'),
        mapOption = {
            center: new kakao.maps.LatLng(37.4775187, 127.1456706),
            level: 5
        };

    var map = new kakao.maps.Map(mapContainer, mapOption);

    window.addEventListener('resize', function () {
        map.setCenter(defaultCenter);
    });

    setZoomable(false);
    setDraggable(false);

    function setZoomable(zoomable) {
        map.setZoomable(zoomable);
    }

    function setDraggable(draggable) {
        map.setDraggable(draggable);
    }

    var positions = [
        {
            title: '성남시 위례동',
            latlng: new kakao.maps.LatLng(37.4713243, 127.1422983)
        },
        {
            title: '하남시 위례동',
            latlng: new kakao.maps.LatLng(37.4771689, 127.1523914)
        },
        {
            title: '송파구 위례동',
            latlng: new kakao.maps.LatLng(37.4811716, 127.1439458)
        },
    ];


    for (var i = 0; i < positions.length; i++) {

        //아이콘에 따른 지역정보 출력과 동 이름 출력할 커스텀 오버레이
        var dongName = positions[i].title;
        var customOverlay = new kakao.maps.CustomOverlay({
            map: map,
            clickable: true,
            content:
                '<div  id="overlay' + i + '" style="background-color: rgba(255, 255, 0, 0.61); border-radius: 50%; width: 120px; height: 120px; text-align: center; font-size: 1.2rem;"><br><div id="dong' + i + '" style="font-weight: bold; margin-bottom: 5px;">' + dongName + '</div><div id="infoData' + i + '"></div></div>',
            position: positions[i].latlng,
            title: positions[i].title,
            xAnchor: 0.5,
            yAnchor: 1,
            zIndex: 3
        });
        abc.push(customOverlay);
        customOverlay.setMap(map);

    }

    var geocoder = new kakao.maps.services.Geocoder();

    var customOverlays = {};
    var dongs = {};
    var infoDatas = {};
    var array = [];
    
    for (let i = 0; i < abc.length; i++) {
        customOverlays['co' + i] = document.getElementById('overlay' + i)
        infoDatas['infoData' + i] = document.getElementById('infoData' + i)
        dongs['dong' + i] = document.getElementById('dong' + i).innerText;
        array.push(eval('dongs.dong' + i))

    }

    for (let i = 0; i < abc.length; i++) {
        eval('customOverlays.co' + i).addEventListener('mouseover', function () {
            eval('customOverlays.co' + i).style.cursor = "pointer";
            eval('customOverlays.co' + i).style.backgroundColor = "	#4B89DC";
            eval('customOverlays.co' + i).style.animation = "ripple 1s linear infinite";
        })

        eval('customOverlays.co' + i).addEventListener('mouseout', function () {
            eval('customOverlays.co' + i).style.backgroundColor = "yellow";
            eval('customOverlays.co' + i).style.opacity = "0.7";
            eval('customOverlays.co' + i).style.animation = "none";
        })

        eval('customOverlays.co' + i).onclick =

            function () {
                // #########################송도훈 수정################
                var areaname = document.getElementById("area-name");
                // #################################################
                var simpleBtn = document.getElementById('searchButton');
                var detailBtn = document.getElementById('detailButton');

                simpleBtn.disabled = false;
                detailBtn.disabled = false;
                removePolygon();
                $.getJSON("json/emdTest.geojson", function (geojson) {
                    var data = geojson.features;
                    var name = ' ';
                    var a = array[i];

                    data.forEach(val => {
                        var coordinates = val.geometry.coordinates;
                        coordinates = coordinates[0];


                        if (val.properties.temp === a) {
                            name = val.properties.temp;
                            region = val.properties.adm_nm;
                            // ############송도훈 수정###########
                            areaname.value = region;
                            // ##################################3
                            var regionChoice = document.getElementById("regionChoice");
                            regionChoice.innerHTML = "<span>&#183;&nbsp;&nbsp;" + region + "</span>";

                            for (var i = 0; i < address.length; i++) {
                                if (coordinates.length === 0) return;

                                if (coordinates.length === 1 && address[i].addr == name) {
                                    displayArea(coordinates[0], val.properties.temp, val.properties);
                                } else {
                                    coordinates.forEach(polygonCoords => {
                                        displayArea(polygonCoords[0], val.properties.temp, val.properties);
                                    });
                                }
                            }
                        }
                    });
                });

                function displayArea(coordinates, name, properties) {

                    var path = [];

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
                        fillOpacity: 0.3
                    });

                    polygons.push(polygon)

                    kakao.maps.event.addListener(polygon, 'click', function (mouseEvent) {


                    })
                }
            }
    }

    var data = [[1, 2, 3, 4, 5, 6], [7, 8, 9, 0, 1, 2], [3, 4, 5, 6, 7, 8]]
    var box = document.querySelectorAll('.iconBox');

    for (let i = 0; i < box.length; i++) {
        box[i].onclick = function () {
            for (let j = 0; j < data.length; j++) {
                eval('infoDatas.infoData' + j).innerText = data[j][i]
            }

        }
    }

    //여기서 map.getCenter를 클릭한 위치로
    searchAddrFromCoords(map.getCenter(), displayCenterInfo);

    kakao.maps.event.addListener(map, 'idle', function () {
        searchAddrFromCoords(map.getCenter(), displayCenterInfo);
    });

    function searchAddrFromCoords(coords, callback) {
        geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);
    }

    function displayCenterInfo(result, status) {
        if (status === kakao.maps.services.Status.OK) {
            var infoDiv = document.getElementById('searchD');

            for (var i = 0; i < result.length; i++) {
                if (result[i].region_type === 'H') {
                    infoDiv.innerHTML = result[i].address_name;
                    break;
                }
            }
        }
    }

    var simpleSearch = document.getElementById('simpleSearch');

    var simpleclose = document.getElementById('simpleClose');
    var detailclose = document.getElementById('detailClose')


    simpleclose.onclick = function () {
        var myCollapse = new bootstrap.Collapse(document.getElementById('simpleInfo'));
        myCollapse.hide();
    }

    detailclose.onclick = function () {
        var myCollapse = new bootstrap.Collapse(document.getElementById('detailInfo'))
        myCollapse.hide();
    }

    var address = [
        { addr: "성남시 위례동" },
        { addr: "하남시 위례동" },
        { addr: "송파구 위례동" }
    ];

    function removePolygon() {
        // 배열에 있는 모든 폴리곤을 순회하면서 지도에서 제거
        polygons.forEach(function (polygon) {
            polygon.setMap(null); // 지도에서 제거
        });

        // 배열 비우기
        polygons.length = 0;
    }

    var searchInfoIcon = document.getElementById('searchInfoIcon');

    searchInfoIcon.onclick = function () {

        var searchInfo = document.getElementById('searchInfo');
        var simpleInfo = document.getElementById('simpleInfo');
        var detailInfo = document.getElementById('detailInfo');

        if (searchInfo.style.display == "block") {
            searchInfo.style.display = "none";
            $(simpleInfo).collapse("hide");
            $(detailInfo).collapse("hide");

        }
        else {
            searchInfo.style.display = "block";
            searchInfo.style.width = "500px";
            searchInfo.style.opacity = "1";

        }

    }

    var searchButton = document.getElementById('searchButton');
    var detailButton = document.getElementById('detailButton');

    searchButton.onclick = function () {
        var simpleInfo = document.getElementById('simpleInfo');
        var detailInfo = document.getElementById('detailInfo');

        if (window.innerWidth <= 1200) {
            searchInfo.style.opacity = "0";
            searchInfo.style.transition = "opacity 0.5s ease";
            simpleInfo.style.width = "50%"
        }
    }
    detailButton.onclick = function () {
        if (window.innerWidth <= 1200) {
            searchInfo.style.opacity = "0";
            searchInfo.style.transition = "opacity 0.5s ease";
        }
    }

    var subject = document.getElementById("subject");
    var subjectOption = subject.options[subject.selectedIndex].value;

    var subjectChoice = document.getElementById("subjectChoice");
    subjectChoice.innerHTML = "<span>&#183;&nbsp;&nbsp;" + subjectOption + "</span>";

    var monthSale;
    var population;
    var floatingPp;
    var countHospital;

    var salePredictText = document.getElementById("salePredictText");
    salePredictText.innerText = "월평균 추정매출은 " + monthSale + "만원 입니다.";

    var populationText = document.getElementById("populationText");
    populationText.innerText = "지역 총 인구수는 " + population + "명 입니다.";

    var floatingPpText = document.getElementById("floatingPpText");
    floatingPpText.innerHTML = "일일평균 유동인구는 " + floatingPp + "명 입니다.";

    var countHospitalText = document.getElementById("countHospitalText");
    countHospitalText.innerText = "선택 진료과 병원 수는 " + countHospital + "개 입니다.";

    var searchBtn = document.getElementById("searchBtn");
    var selectRegion = document.getElementById('selectRegion');

    // 지역 검색창

    searchBtn.onclick = function () {
        var inputAddress = selectRegion.value;

        var ps = new kakao.maps.services.Places();

        // 키워드로 장소를 검색합니다
        ps.keywordSearch(inputAddress, placesSearchCB);

        // 키워드 검색 완료 시 호출되는 콜백함수 입니다
        function placesSearchCB(data, status, pagination) {
            if (status === kakao.maps.services.Status.OK) {

                // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
                // LatLngBounds 객체에 좌표를 추가합니다
                var bounds = new kakao.maps.LatLngBounds();

                for (var i = 0; i < data.length; i++) {
                    bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
                }

                // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
                map.setLevel(5);
                map.setBounds(bounds);
            }
        }



    }

    // Chart
var region;

    var chartData = {
        ['서울시 송파구 위례동']: [[Math.round(Math.random() * 1000), Math.round(Math.random() * 1000), Math.round(Math.random() * 1000), Math.round(Math.random() * 1000), Math.round(Math.random() * 1000), Math.round(Math.random() * 1000)],
        [Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100)],
        [Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100)]],
        ['경기도 하남시 위례동']: [[Math.round(Math.random() * 1000), Math.round(Math.random() * 1000), Math.round(Math.random() * 1000), Math.round(Math.random() * 1000), Math.round(Math.random() * 1000), Math.round(Math.random() * 1000)],
        [Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100)],
        [Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100)]],
        ['경기도 성남시 위례동']: [[Math.round(Math.random() * 1000), Math.round(Math.random() * 1000), Math.round(Math.random() * 1000), Math.round(Math.random() * 1000), Math.round(Math.random() * 1000), Math.round(Math.random() * 1000)],
        [Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100)],
        [Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100)]]
    };
    var optionData = {
        ['1']: [['2019', '2020', '2021', '2022'], [['0']], ['500'], ['(단위:천 명)'], ['총 인구수']],
        ['2']: [['2019', '2020', '2021', '2022'], ['0'], '[3200]', ['(단위: 천 명'], ['유동인구']],
        ['3']: [['2019', '2020', '2021', '2022'], ['0'], ['30'], ['(개 수)'], ['동종 병원 수']]
    };
    // lebels y축 min max ticks, plugins.text 4개

    function makingChart(chartArea, chartNumber) {

        for (let b = 0; b < Object.keys(optionData).length; b++) {

            if (chartNumber == Object.keys(optionData)[b]) {
                for (let c = 0; c < Object.keys(chartData).length; c++) {
                    if (region == Object.keys(chartData)[b]) {
                        chartArea = new Chart(chartArea, {
        type: 'line',
        data: {
            labels: Object.values(optionData)[c][b],
            datasets: [
                {
                    // label: 'population',
                    data: Object.values(chartData)[b][0],
                    borderWidth: 3,
                    borderColor: 'black',
                    backgroundColor: 'black'
                    // tension: 0.4,
                }

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
                    min: Object.values(optionData)[b][1],
                    max: Object.values(optionData)[b][2],
                    position: 'top',
                    ticks: {
                        callback: function (value, index, values) {
                            if (index === 0) {
                                return Object.values(optionData)[b][3] + value;
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
                    text: Object.values(optionData)[b][4],
                    font: {
                        size: 25,
                    }
                },
                legend: {
                    display: false,
                    labels: {
                        usePointStyle: true
                    },
                },
            },
        }
    });
}
                }
            }
        };

    };

    function makingRoundChart(ChartArea) {
        ChartArea = new Chart(ChartArea, {
            type: 'doughnut',
            data: {
                labels: ['10대', '20대', '30대', '40대', '50대', '60대', '70대 이상'],
                datasets: [{
                    // label: '인구',
                    data: [Math.floor(Math.random() * 100), Math.floor(Math.random() * 100), Math.floor(Math.random() * 100), Math.floor(Math.random() * 100), Math.floor(Math.random() * 100), Math.floor(Math.random() * 100), Math.floor(Math.random() * 100)],
                    backgroundColor: ['rgba(132, 235, 220, 0.82)', 'rgba(190, 132, 235, 0.82)', 'rgba(136, 235, 132, 0.82)', 'rgba(235, 197, 132, 0.82)', 'rgba(202, 33, 162, 0.82)', 'rgba(39, 110, 18, 0.82)', 'rgba(221, 146, 158, 0.92)'],
                    // borderColor: 'none',
                    borderWidth: 1
                }]
            },
            options: {
                plugins: {
                    title: {
                        display: false,
                        text: '인구',
                        font: {
                            size: 30,
                        }
                    },
                    legend: {
                        display: true

                    }
                },
                responsive: false,

            },
        });
    }

    // var salePredictArea = document.getElementById("salePredictChart").getContext('2d');
    var populationChart = document.getElementById("populationChart").getContext('2d');
    var floatingPpChart = document.getElementById("floatingPpChart").getContext('2d');
    var countHospitalChart = document.getElementById("countHospitalChart").getContext('2d');
    var populationRoundChart = document.getElementById("populationRoundChart").getContext('2d');
    var floatingPpRoundChart = document.getElementById("floatingPpRoundChart").getContext('2d');

    // makingChart(salePredictArea);
    makingChart(populationChart, 1);
    makingChart(floatingPpChart, 2);
    makingChart(countHospitalChart, 3);
    makingRoundChart(populationRoundChart);
    makingRoundChart(floatingPpRoundChart);

}


