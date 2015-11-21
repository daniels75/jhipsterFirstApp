'use strict';

angular.module('firstappApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('simple', {
                parent: 'entity',
                url: '/simple',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'firstappApp.simple.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/simple/simples.html',
                        controller: 'SimpleController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('simple');
                        return $translate.refresh();
                    }]
                }
            })
            .state('simpleDetail', {
                parent: 'entity',
                url: '/simple/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'firstappApp.simple.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/simple/simple-detail.html',
                        controller: 'SimpleDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('simple');
                        return $translate.refresh();
                    }]
                }
            });
    });
