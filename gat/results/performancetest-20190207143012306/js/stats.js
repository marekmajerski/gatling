var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "2",
        "ok": "2",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "623",
        "ok": "623",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "633",
        "ok": "633",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "628",
        "ok": "628",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "5",
        "ok": "5",
        "ko": "-"
    },
    "percentiles1": {
        "total": "628",
        "ok": "628",
        "ko": "-"
    },
    "percentiles2": {
        "total": "631",
        "ok": "631",
        "ko": "-"
    },
    "percentiles3": {
        "total": "633",
        "ok": "633",
        "ko": "-"
    },
    "percentiles4": {
        "total": "633",
        "ok": "633",
        "ko": "-"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 2,
        "percentage": 100
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 0,
        "percentage": 0
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 0,
        "percentage": 0
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "0.125",
        "ok": "0.125",
        "ko": "-"
    }
},
contents: {
"req_login-as-a-clie-aad8b": {
        type: "REQUEST",
        name: "Login as a Client",
path: "Login as a Client",
pathFormatted: "req_login-as-a-clie-aad8b",
stats: {
    "name": "Login as a Client",
    "numberOfRequests": {
        "total": "2",
        "ok": "2",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "623",
        "ok": "623",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "633",
        "ok": "633",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "628",
        "ok": "628",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "5",
        "ok": "5",
        "ko": "-"
    },
    "percentiles1": {
        "total": "628",
        "ok": "628",
        "ko": "-"
    },
    "percentiles2": {
        "total": "631",
        "ok": "631",
        "ko": "-"
    },
    "percentiles3": {
        "total": "633",
        "ok": "633",
        "ko": "-"
    },
    "percentiles4": {
        "total": "633",
        "ok": "633",
        "ko": "-"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 2,
        "percentage": 100
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 0,
        "percentage": 0
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 0,
        "percentage": 0
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "0.125",
        "ok": "0.125",
        "ko": "-"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
