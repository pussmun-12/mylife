function CalendarController($scope, $navigate, dateService){
    $scope.$watch("assignments", function (value) {//I change here
        $scope.datesHavingImages = [];
        $scope.datesHavingText = {};
        $scope.currentDate = dateService.getCurrentDate();
        $scope.calendarUtil.initCalendar($scope, $scope.currentDate);
        dateService.setCurrentDate($scope.currentDate);
    });
    $scope.back = function(){
        console.log('back');
        $navigate.back();
    }
}
