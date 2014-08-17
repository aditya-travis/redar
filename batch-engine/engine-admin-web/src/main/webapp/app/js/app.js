'use strict';


// Declare app level module which depends on filters, and services
angular.module('myApp', [
  'ngRoute',
  'myApp.filters',
  'myApp.services',
  'myApp.directives',
  'myApp.controllers'
]).
config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/job-history', {templateUrl: 'app/partials/job-history.html', controller: 'JobHistoryController'});
    $routeProvider.when('/job-admin', {templateUrl: 'app/partials/job-admin.html', controller: 'JobAdminController'});
    $routeProvider.otherwise({redirectTo: '/job-history'});
}]);
