window.onload = function () {
    // 나중에 삭제할 거 시작
    const queryString = window.location.search;

    // URLSearchParams 객체를 사용하여 쿼리 문자열 파싱
    const urlParams = new URLSearchParams(queryString);

    // 쿼리 문자열에 포함된 모든 파라미터 출력
    for (const [key, value] of urlParams) {
        console.log(`${key}: ${value}`);
    }
    // 나중에 삭제할 거 끝
    var monthSale;

    var salePredictText = document.getElementById("s_salePredictText");
    salePredictText.innerText = "월평균 추정매출은 " + monthSale + "만원 입니다.";

    var salePredictArea = document.getElementById("s_salePredictChart").getContext('2d');
    var salePredictChart = new Chart(salePredictArea, {
        type: 'line',
        data: {
            labels: ['2017', '2018', '2019', '2020', '2021', '2022'],
            datasets: [
                {
                    label: '총 환자수',
                    data: [150, 157, 160, 154, 170, 180],
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
                    min: 0,
                    max: 13000,
                    position: 'top',
                    ticks: {
                        callback: function (value, index, values) {
                            if (index === 0) {
                                return '(단위:만원)' + value;
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
                    text: '월평균 추정매출',
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