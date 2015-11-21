'use strict';

angular.module('firstappApp')
    .controller('SimpleController', function ($scope, Simple) {
        $scope.simples = [];
        $scope.loadAll = function() {
            Simple.query(function(result) {
               $scope.simples = result;
            });
        };
        $scope.loadAll();

        $scope.showUpdate = function (id) {
            Simple.get({id: id}, function(result) {
                $scope.simple = result;
                $('#saveSimpleModal').modal('show');
            });
        };

        $scope.save = function () {
            if ($scope.simple.id != null) {
                Simple.update($scope.simple,
                    function () {
                        $scope.refresh();
                    });
            } else {
                Simple.save($scope.simple,
                    function () {
                        $scope.refresh();
                    });
            }
        };

        $scope.delete = function (id) {
            Simple.get({id: id}, function(result) {
                $scope.simple = result;
                $('#deleteSimpleConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Simple.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteSimpleConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.loadAll();
            $('#saveSimpleModal').modal('hide');
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.simple = {name: null, model: null, price: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });
