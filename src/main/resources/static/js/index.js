var stompClient = null;

$(document).ready(function () {
    connect();
    $("#sendJob").click(function () {
        var task = {};
        task.typeTask = $("#typeTask").val();
        task.numberOfStepsInTheJob = $("#numberOfStepsInTheJob").val();
        task.timeForOneStep = $("#timeForOneStep").val();
        task.taskPerformer = $("#taskPerformer").val();
        if (task.typeTask === "" || task.numberOfStepsInTheJob === "" || task.timeForOneStep === "") {

        } else {
            sendJob(task);
        }
    });
    $("#clearLog").click(function () {
        $('#robotLogsArea').empty();
    });
    $("#killRobot").click(function () {
        var task = {};
        task.typeTask = "kill";
        task.numberOfStepsInTheJob = 0;
        task.timeForOneStep = 0;
        task.taskPerformer = $("#killRobotInput").val();
        if (task.taskPerformer==="" ) {

        } else {
            sendJob(task);
        }
    });
});

function connect() {
    var socket = new SockJS('/logJobsRobots');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/robotWorkLog', function (robotLog) {
            console.log(JSON.parse(robotLog.body).content);
            showRobotLog(JSON.parse(robotLog.body).content);
        });
    });
}

function sendJob(task) {
    $.ajax({
        url: "api/createTaskRobots",
        type: "POST",
        dataType: "json",
        async: false,
        contentType: "application/json",
        data: JSON.stringify(task),
        success: function (data) {
        }
    });
}

function showRobotLog(message) {
    $('#robotLogsArea').append(message + "\n");
}