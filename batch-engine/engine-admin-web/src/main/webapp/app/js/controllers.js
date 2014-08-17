'use strict';

/* Controllers */

angular.module('myApp.controllers', [])
    .controller('JobHistoryController', ['$scope','$http', function($scope, $http) {


        $scope.fetchData = function(){
            $http({method:'GET', url:'history'}).success(function(data, status, headers, config){
                $scope.pagedResult=data;
                $scope.historyEntries=$scope.pagedResult.content;
            }).error(function(data, status, headers, config){

            });
        }

        $scope.refresh = function(){
            $scope.fetchData();
        }

        $scope.fetchData();
    }])
    .controller('JobAdminController', ['$scope','$http', function($scope, $http) {


        $scope.fetchData = function(){
            $http({method:'GET', url:'triggers'}).success(function(data, status, headers, config){
                $scope.allTriggers=data;
            }).error(function(data, status, headers, config){

            });
        }

        $scope.refresh = function(){
            $scope.fetchData();
        }

        $scope.forceExecute=function(scheduler, jobGroup, jobName){

            $http(
                {
                    method:'POST',
                    url:'force-exec/'+ scheduler + '/' + jobGroup+'/'+jobName
                }
            ).success(function(data, status, headers, config){

                    $scope.refresh();
            }).error(function(data, status, headers, config){

            });

        }

        $scope.fetchData();
    }]);
