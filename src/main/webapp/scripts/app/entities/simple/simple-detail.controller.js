'use strict';

angular.module('firstappApp')
    .controller('SimpleDetailController', function ($scope, $stateParams, Simple) {
        $scope.simple = {};
        $scope.load = function (id) {
            Simple.get({id: id}, function(result) {
              $scope.simple = result;
            });
        };
        $scope.load($stateParams.id);
    });
