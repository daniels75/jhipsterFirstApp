'use strict';

angular.module('firstappApp')
    .factory('Simple', function ($resource, DateUtils) {
        return $resource('api/simples/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
