/* globals $ */
'use strict';

angular.module('firstappApp')
    .directive('firstappAppPagination', function() {
        return {
            templateUrl: 'scripts/components/form/pagination.html'
        };
    });
