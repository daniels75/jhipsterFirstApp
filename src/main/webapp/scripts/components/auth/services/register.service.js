'use strict';

angular.module('firstappApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


